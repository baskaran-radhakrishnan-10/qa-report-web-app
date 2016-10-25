var ktDataTableRef = null;
var ktServerData=null;
var isFilterConstructed=false;


var ktDetailsData={};
var currentSelectedObject=null;
var resourceArraySelectHtml="";
var resourcesArray=[];

var projectSelectHtml="";
var projectArray=[];
var tranerAndAttendeeArray=[];

var rowEle="";
var egKey="";
var ktLocation=['George Town','Marina','Trafalgar','Training Room 2','Workstation','Other'];
var ktType=['Internal','External'];
var ktSession=['General','KT','Reverse KT'];

$(document).ready(function() {
	$('#addKtDetailsId').on("click" ,function (event){
		ktDetailsModalData(null);
	});
	
	$('#apply_filter').on('click',function(){
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			var name=$(this).prop('name');
			var value=$(this).val();
			if(name.length > 0 && null != value && value.length > 0){
				filterObject[name]=$(this).val();
			}
		});
		if(jQuery.isEmptyObject(filterObject)){
			var notifyObj={msg: '<b>Warning : </b> Please choose filter you want to apply !!!',type: "warning",position: "center" ,autohide: false};
			notif(notifyObj);
			notif(notifyObj);
		}
		else{
			filterKTData(filterObject);
		}
		
	});
	
	$('#clear_filter').on('click',function(){
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			if("button" != $(this).prop('type')){
				$(this).val(null);
			}
		})
		filterKTData(filterObject);
	});
	getKTDetails();
	fetchProjectNames();
	fetchResourceNames();
	
});

function getKTDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"kt_plan/getKTPlanDetails", 'json', null, fetchKTDetailsSuccess,true);
}

function fetchKTDetailsSuccess(serverData){
	if(null == ktServerData){
		ktServerData=serverData;
	}
	$('#show-kt-details-id').find('#tbody_id').empty();
	$('#show-kt-details-id').find('#tbody_id').html(populateKTDetails(serverData['SERVER_DATA']));
	ktDataTableRef = $('#show-kt-details-id').DataTable({
		"responsive" : false,
		"processing": true,
		"dom": 'Bfrtpl',
		"buttons": ['excel','csv']
	});
	if(!isFilterConstructed){
		//fillSelectDropDown('filter_projectId',projectArray, "");
		fillSelectDropDown('filter_trainingTypeId',ktType, "");
		fillSelectDropDown('filter_sessionId',ktSession, "");
		fillSelectDropDown('filter_locationId',ktLocation, "");
		isFilterConstructed=true;
	}
	
	if(null != sessionStorageObj){
		var notifyObj=sessionStorageObj.getItem("NOTIFICATION");
		if(null != notifyObj){
			notif(notifyObj);
			sessionStorageObj.removeItem("NOTIFICATION");
		}
	}
}

function populateKTDetails(entriesList){
//	var sNo = 1;
	var htmlArray=new Array();
	for(var i=0;i<entriesList.length;i++){		
		var html ="";
		var KTDetailsModelAttribute=entriesList[i];
		var gKey=KTDetailsModelAttribute['gkey'];
		var project=KTDetailsModelAttribute['project'];
		var trainingType=KTDetailsModelAttribute['trainingType'];
		var session=KTDetailsModelAttribute['session'];
		var title=KTDetailsModelAttribute['title'];	
		var trainers=KTDetailsModelAttribute['trainers'];	
		var attendees=KTDetailsModelAttribute['attendees'];	
		var location=KTDetailsModelAttribute['location'];
		var startDate=getDateValue(KTDetailsModelAttribute['startDate'],'dd/MM/yyyy',"-")
		var endDate=getDateValue(KTDetailsModelAttribute['endDate'],'dd/MM/yyyy',"-")
		var duration=KTDetailsModelAttribute['duration'];
		var remarks=KTDetailsModelAttribute['remarks'];
		var feedback=KTDetailsModelAttribute['feedback'];
		
		ktDetailsData[gKey]=KTDetailsModelAttribute;
		html += '<tr id="'+gKey+'">' ;
//		html += '<td  nowrap="nowrap">'+sNo+'</td>' ;
		html += '<td  nowrap="nowrap">'+gKey+'</td>' ;
		html +=	'<td  nowrap="nowrap">'+project+'</td>' ;
		html += '<td  nowrap="nowrap">'+trainingType+'</td>' ;
		html += '<td  nowrap="nowrap">'+session+'</td>' ;
		html +=	'<td  nowrap="nowrap">'+title+'</td>' ;
		html += '<td  nowrap="nowrap">'+trainers+'</td>' ;
		html += '<td  nowrap="nowrap">'+attendees+'</td>' ;
		html += '<td  nowrap="nowrap">'+location+'</td>' ;
		html += '<td  nowrap="nowrap">'+startDate+'</td>' ;
		html += '<td  nowrap="nowrap">'+endDate+'</td>' ;
		html += '<td  nowrap="nowrap">'+duration+'</td>' ;
		html += '<td  nowrap="nowrap">'+remarks+'</td>' ;
		html += '<td  nowrap="nowrap">'+feedback+'</td>' ;
		html += '<td id="ktDetailsEditRowId" onclick="editKTPlanDetails('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span><a id="ktDetailsSaveRowId" style="display:none;" href="#"> <span class="glyphicon glyphicon-check"></span></a></td>' ;
		html += '</tr>' ;
		htmlArray.push(html);
//		sNo++;
	}
	return htmlArray;
}

function editKTPlanDetails(gKey){
	ktDetailsModalData(gKey);
	egKey=gKey;
}

function fetchProjectNames(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"project/getUniqueProjectList", 'json', null, fetchProjectNamesSuccess,true);
}

function fetchProjectNamesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var projectNameObjList=serverData['SERVER_DATA'];
		projectSelectHtml += '<select id="projectId" class="input-sm form-control">'
		for(var index in projectNameObjList){
			projectNameObj=projectNameObjList[index];
			projectArray.push(projectNameObj['projectname']);
			fillSelectDropDown('filter_projectId',projectArray, "");
			projectSelectHtml += '<option value="'+projectNameObj['projectname']+'">'+projectNameObj['projectname']+'</option>';
		}
		projectSelectHtml += '</select>'
	}
	
}

function fetchResourceNames(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"resource_details/getUniqueResources", 'json', null, fetchResourceNamesSuccess,true);
}

function fetchResourceNamesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var resourceNameObjList=serverData['SERVER_DATA'];
		resourceArraySelectHtml += '<select id="resourceNameListId" name="resourceNameList" class="input-sm form-control">';
		for(var index in resourceNameObjList){
			var resourceNameObj=resourceNameObjList[index];
			resourcesArray.push(resourceNameObj['resourcename']);
			
			resourceArraySelectHtml += '<option value="'+resourceNameObj['resourcename']+'">'+resourceNameObj['resourcename']+'</option>';
		}
		resourceArraySelectHtml += '</select>';
	}
}
function ktDetailsModalData(gKey){

	currentSelectedObject = null != gKey ? ktDetailsData[gKey] : null;

	fillSelectDropDown('projectId',projectArray,null != currentSelectedObject ? currentSelectedObject['project'] : "");
	fillSelectDropDown('ktTypeId',ktType,null != currentSelectedObject ? currentSelectedObject['trainingType'] : "");
	fillSelectDropDown('locationId',ktLocation,null != currentSelectedObject ? currentSelectedObject['location'] : "");
	fillSelectDropDown('sessionId',ktSession,null != currentSelectedObject ? currentSelectedObject['session'] : "");
	fillSelectDropDown('trainerId',resourcesArray,null != currentSelectedObject ? currentSelectedObject['trainers'] : "");
	fillSelectDropDown('attendeeId',resourcesArray,null != currentSelectedObject ? currentSelectedObject['attendees'] : "");
	$('#ktTitleId').val( null != currentSelectedObject ? currentSelectedObject['title'] : "");
	$('#startDateId').val( null != currentSelectedObject ? getDateValue(currentSelectedObject['startDate'],'yyyy-MM-dd',"-") : "");
	$('#endDateId').val( null != currentSelectedObject ? getDateValue(currentSelectedObject['endDate'],'yyyy-MM-dd',"-") : "");
	$('#durationId').val( null != currentSelectedObject ? currentSelectedObject['duration'] : "");
	$('#remarksId').val( null != currentSelectedObject ? currentSelectedObject['remarks'] : "");
	$('#feedbackId').val( null != currentSelectedObject ? currentSelectedObject['feedback'] : "");
	
	$("#addKtDetailsTrigger").trigger( "click" );
	$('#addKtDetailsDiv').html($('#ktDetailsForm'));
	$('#addKtDetailsDiv').find('#ktDetailsForm').show();
}

function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) { 
		
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	
	if(null != selectedOption && selectedOption.indexOf(",") != -1){
		selectedOption=selectedOption.split(",");
	}
	$('#'+dropDownId).val(selectedOption);
	
}

function addOrUpdateKTPlan(){
	
	var isAdd=false;
	var isValid=false;
	var ktObject={};
	
	if (null ==egKey || "" ==egKey){
		isAdd=true;
	}
	
	ktObject=egKey == "" ? {} : ktDetailsData[egKey] ;
	
	var rowEle=$('#addKtDetailsDiv').find('form');
	
	var projectId=rowEle.find('#projectId').val();
	var typeId=rowEle.find('#ktTypeId').val();
	var sessionId=rowEle.find('#sessionId').val();
	var title=rowEle.find('#ktTitleId').val();
	if (null !=rowEle.find('#trainerId').val()){
		var trainersNameListId=rowEle.find('#trainerId').val().toString();
	}
	if (null !=rowEle.find('#attendeeId').val()){
		var attendeesNameListId=rowEle.find('#attendeeId').val().toString();
	}
	var locationId=rowEle.find('#locationId').val();
	var startDate=rowEle.find('#startDateId').val();
	var endDate=rowEle.find('#endDateId').val();
	var duration=rowEle.find('#durationId').val();
	var remarks=rowEle.find('#remarksId').val();
	var feedback=rowEle.find('#feedbackId').val();
	
	if (null ==projectId || ""== projectId){
		var notifyObj={msg: '<b>Warning : </b> Please Select Project Name !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (null ==typeId || ""== typeId){
		var notifyObj={msg: '<b>Warning : </b> Please Select KT Type !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (null ==title || ""== title){
		var notifyObj={msg: '<b>Warning : </b> Please Enter Title !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null ==sessionId || ""== sessionId){
		var notifyObj={msg: '<b>Warning : </b> Please Select Session !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null ==trainersNameListId || ""== trainersNameListId){
		var notifyObj={msg: '<b>Warning : </b> Please Select Trainer Name !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null ==attendeesNameListId || ""== attendeesNameListId){
		var notifyObj={msg: '<b>Warning : </b> Please Select Attendees Name !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null ==locationId || ""== locationId){
		var notifyObj={msg: '<b>Warning : </b> Please Select Location !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null ==startDate || ""== startDate){
		var notifyObj={msg: '<b>Warning : </b> Please Select Start Date !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null ==endDate || ""== endDate){
		var notifyObj={msg: '<b>Warning : </b> Please Select End Date !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null ==duration || ""== duration){
		var notifyObj={msg: '<b>Warning : </b> Please Enter Duration !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else{
		isValid=true;
	}
	ktObject['project']=projectId;
	ktObject['trainingType']=typeId;
	ktObject['session']=sessionId;
	ktObject['title']=title;		
	ktObject['trainers']=trainersNameListId;	
	ktObject['attendees']=attendeesNameListId;
//	ktObject['others']="";
	ktObject['location']=locationId;
	ktObject['startDate']=startDate;	/*getDateValue(startDate,'yyyy-MM-dd',"-");*/
	ktObject['endDate']=endDate;		/*getDateValue(endDate,'yyyy-MM-dd',"-");*/
	ktObject['duration']=duration;
	ktObject['remarks']=remarks;
	ktObject['feedback']=feedback;
	
	if(isAdd && isValid){
		ajaxHandler("POST", JSON.stringify(ktObject), "application/json", getApplicationRootPath()+"kt_plan/addKTDetails", 'json', null,ktDeatilsSaveSuccess,true);
	}
	else if(isValid){
		ajaxHandler("POST", JSON.stringify(ktObject), "application/json", getApplicationRootPath()+"kt_plan/updateKTDetails", 'json', null,ktDeatilsUpdateSuccess,true);
	}
}

function ktDeatilsSaveSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	
		var notifyObj={msg: '<b>Success : </b> KT Details added Successfully !!!',type: "success",position: "center" ,autohide: false};
		notif(notifyObj);
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"kt_plan/ktPlan";
	}
}

function ktDeatilsUpdateSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	
		var notifyObj={msg: '<b>Success : </b> KT Details Updated Successfully !!!',type: "success",position: "center" ,autohide: false};
		notif(notifyObj);
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"kt_plan/ktPlan";
	}
}

function getDateValue(dateObj,format,delimeter){
	var formatedDateStr="";
	if($.type(dateObj) == "string"){
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


/*----------------------------------------------------------------------------	KT Plan Filter functionality  ----------------------------------------------------------------------------*/


function filterKTData(filterObj){
	var filteredKTDataList = [];
	var filteredKTDataObject={};
	var ktDataList = ktServerData['SERVER_DATA'];
	var filterKeys=Object.keys(filterObj);
	var isStartDate=_.contains(filterKeys, 'startDate');
	var filterStartDate = isStartDate ? getFormatedDateByTime(new Date(filterObj['startDate']),0,0,0,0) : null ;
	var isEndDate=_.contains(filterKeys, 'endDate');
	var filterEndDate   = isEndDate ?  getFormatedDateByTime(new Date(filterObj['endDate']),23,59,59,999) : null;
	for(var i=0;i<ktDataList.length;i++){
		var canFilter = true;
		var ktObject=ktDataList[i];
		var startDate=new Date(getDateValue(ktObject['startDate'],'yyyy-MM-dd',"-"));
		var endDate=new Date(getDateValue(ktObject['endDate'],'yyyy-MM-dd',"-"));
		if(isStartDate && isEndDate){
			if(!(startDate >= filterStartDate) || !(endDate <= filterEndDate)){
				canFilter = false ;
			}
		}else if(isStartDate){
			if(!(startDate >= filterStartDate)){
				canFilter = false ;
			}
		}else if(isEndDate){
			if(!(endDate <= filterEndDate)){
				canFilter = false ;
			}
		}
		if(canFilter){
			for(var index in filterKeys){
				var filterKey = filterKeys[index];
				var filterValue = filterObj[filterKey];
				if(filterKey.indexOf('startDate') == -1 && filterKey.indexOf('endDate') == -1){
					var btpValue = ktObject[filterKey];
					if(btpValue != filterValue){
						canFilter = false;
						break;
					}
				}
			}
		}
		if(canFilter){
			filteredKTDataList.push(ktObject);
		}
	}
	filteredKTDataObject['SERVER_DATA']=filteredKTDataList;
	ktDataTableRef.destroy();
	fetchKTDetailsSuccess(filteredKTDataObject);
}

