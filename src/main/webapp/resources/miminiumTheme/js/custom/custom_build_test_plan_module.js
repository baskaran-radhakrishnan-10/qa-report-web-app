var defaultSearchEntryCount=10;

var buildTestPlanData={};

var phaseArray=['FAT','SAT','LIVE','RLS','DEMO','INTERNAL','EXTERNAL'];

var planArray=['Automation Plan','Build Test Plan','Test Design Plan'];

var statusArray=['Completed','Deferred','In Progress','Planned','Abandoned'];

var itemDescriptionArray=[];

var resourcesArray=[];

var projectArray=[];

var selectedResourcesArray=[];

var resourceArraySelectHtml="";

var itemDescArraySelectHtml="";

var itemDetailsObject={};

var resourceDeatilsObject={};

$(document).ready(function() {
	
	console.log("documet.ready build test plan module ready!!!");
	
	$('#buildTestPlanForm').hide();
	
	$('#resourceDeatilsParentDivId').hide();
	
	$('#addTestPlanDataId').on("click" ,function (event){
		buildTestPlanModalData(null);
	});
	
	$('#resourceMgmtTableId').on('blur', 'td#resourceNameTDId :input', function (event) {
		var currentValue=$(this).val();
		var rowId=parseInt($(this).parent().parent().attr('id').replace('resourcetr_',''));
		if(null != currentValue && currentValue.length > 0){
			var isResourceNameExsist=false;
			var trArr=$('#resourceMgmtTableId').find('tbody').find('tr');
			for(var index=0;index<trArr.length;index++){
				var rowIndex = (index+1);
				if(rowId != rowIndex){
					var otherRowValue=$('#resourcetr_'+rowIndex).find('#resourceNameTDId').find(':input').val();
					if(otherRowValue == currentValue){
						isResourceNameExsist=true;
						break;
					}
				}
			}
			if(isResourceNameExsist){
				$(this).addClass('error');
				var notifyObj={msg: '<b> Resource : '+currentValue+' </b> is already selected ',type: "error",position: "center"};
				notif(notifyObj);
			}else{
				$(this).addClass('error');
			}
		}
	});
	
	$('#buildTestPlanForm :input').on('blur', function() {
		console.log($(this));
		var value=$(this).val();
		console.log(value);
		var id=$(this).attr('id');
		
		if($(this).hasClass('error')){
			if(null != value && value.length > 0){
				$(this).removeClass('error');
			}
		}else{
			if(null == value || value.length == 0){
				$(this).addClass('error');
			}
		}
		
		if(!$(this).hasClass('error')){
			if("startDateId" == id){
				
				var startDate = new Date(Date.parse(value));
				var currDate = new Date();
				var isCurrentDate=false;
				
				if(startDate.getDay() == currDate.getDay() && startDate.getMonth() == currDate.getMonth() && startDate.getFullYear() == startDate.getFullYear()){
					isCurrentDate=true;
				}
				if(startDate > currDate || isCurrentDate){
					
				}else{
					//var notifyObj={msg: '<b>Start Date should be greater or equal to the Current Date</b>',type: "warning",position: "center"};
					//notif(notifyObj);
					//$(this).addClass('error');
				}
			}else if("endDateId" == id){
				var startDateStr=$('#startDateId').val();
				if(null == startDateStr || startDateStr.length == 0){
					var notifyObj={msg: '<b> StartDate should not be empty </b>',type: "error",position: "center"};
					notif(notifyObj);
				}else{
					var revisedEndDate = null;
					var startDate=new Date(Date.parse(startDateStr));
					var endDate = new Date(Date.parse(value));
					if(null != $('#revisedEndDateId').val() && $('#revisedEndDateId').val().length > 0){
						revisedEndDate=new Date(Date.parse($('#revisedEndDateId').val()));
					}
					if(null != revisedEndDate){
						if(endDate > revisedEndDate){
							var notifyObj={msg: '<b> EndDate should be lesser than the RevisedEndDate </b>',type: "error",position: "center"};
							notif(notifyObj);
							$(this).addClass('error');
						}
					}
					if(startDate > endDate ){
						var notifyObj={msg: '<b> EndDate should be greater than the StartDate </b>',type: "error",position: "center"};
						notif(notifyObj);
						$(this).addClass('error');
					}
				}
			}else if("revisedEndDateId" == id){
				var endDateStr=$('#endDateId').val();
				if(null == endDateStr || endDateStr.length == 0){
					var notifyObj={msg: '<b> EndDate should not be empty </b>',type: "error",position: "center"};
					notif(notifyObj);
				}else{
					var endate=new Date(Date.parse(endDateStr));
					var revEndDate = new Date(Date.parse(value));
					if(endate < revEndDate){
						
					}else{
						var notifyObj={msg: '<b> RevisedEndDate should be greater than the EndDate </b>',type: "error",position: "center"};
						notif(notifyObj);
						$(this).addClass('error');
					}
				}
			}
		}
		
	});
	
	showLoader();
	
	fetchTestPlanEntries();
	
	fetchResourceNames();
	
	fetchProjectNames();
	
	fetchItemDescriptionList();
	
});

function fetchProjectNames(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"project/getUniqueProjectList", 'json', null, fetchProjectNamesSuccess,true);
}

function fetchProjectNamesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var projectNameObjList=serverData['SERVER_DATA'];
		for(var index in projectNameObjList){
			var projectNameObj=projectNameObjList[index];
			projectArray.push(projectNameObj['projectname']);
		}
	}
}

function getDateValue(dateObj,format,delimeter){
	var formatedDateStr="";
	if($.type(dateObj) === "string"){
		return dateObj;
	}
	if(null != dateObj){
		var day=(dateObj['dayOfMonth'] > 9) ? (""+dateObj['dayOfMonth']) : ("0"+dateObj['dayOfMonth']);
		var month=(dateObj['monthOfYear'] > 9) ? (""+dateObj['monthOfYear']) : ("0"+dateObj['monthOfYear']);
		if("dd/MM/yyyy" == format){
			if("/" == delimeter){
				formatedDateStr=day+"/"+ month+"/"+ dateObj['year'];
			}else if("-" == delimeter){
				formatedDateStr=day+"-"+ month+"-"+ dateObj['year'];
			}
		}else if("yyyy-MM-dd" == format){
			if("/" == delimeter){
				formatedDateStr=dateObj['year']+"/"+month+"/"+day;
			}else if("-" == delimeter){
				formatedDateStr=dateObj['year']+"-"+month+"-"+day;
			}
		}
	}
	return formatedDateStr;
}

function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(selectedOption);
}

function showLoader(){
	$('#btpMainDiv').hide();
	$('#loader_div').show();
}

function hideLoader(){
	$('#btpMainDiv').show();
	$('#loader_div').hide();
}