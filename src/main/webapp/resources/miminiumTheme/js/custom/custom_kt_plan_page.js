var ktpFormErrorMessages={};
ktpFormErrorMessages['projectId']="Please Select Project Name !!!";
ktpFormErrorMessages['ktTypeId']="Please Select KT Type !!!";
ktpFormErrorMessages['ktTitleId']="Please Enter Title !!!";
ktpFormErrorMessages['sessionId']="Please Select Session !!!";
ktpFormErrorMessages['trainerId']="Please Select Trainer Name !!!";
ktpFormErrorMessages['attendeeId']="Please Select Attendees Name !!!";
ktpFormErrorMessages['locationId']="Please Select Location !!!";
ktpFormErrorMessages['startDateId']="Please Select Start Date !!!";
ktpFormErrorMessages['endDateId']="Please Select End Date !!!";
ktpFormErrorMessages['durationId']="Please Enter Duration !!!";

var ktDataTableRef = null;
var ktServerData=null;
var isFilterConstructed=false;


var ktDetailsData={};
var currentSelectedObject=null;
var resourceArraySelectHtml="";
var resourcesArray=[];

var projectSelectHtml="";
var projectArray=[];
var tranerArray=[];
var attendeeArray=[];
var trainierSelectedVal=[];
var attendeeSelectedVal=[];

var rowEle="";
var egKey="";
var ktLocation=['George Town','Marina','Trafalgar','Training Room 2','Workstation','Other'];
var ktType=['Internal','External'];
var ktSession=['General','KT','Reverse KT'];

$(document).ready(function() {
	
	showLoader();
	getKTDetails();
	fetchProjectNames();
	fetchResourceNames();

	$('#addKtDetailsId').on("click" ,function (event){
		egKey="";
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
			var notifyObj={msg: '<b>Warning : </b> Please choose filter you want to apply !!!',type: "warning",position: "center" };
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
	
	$('#show-kt-details-id tbody').on('click', 'tr', function () {
		var gKey=$(this).attr('id');
		$('#show-kt-details-id tbody tr').removeClass('selected');
		$('#show-kt-details-id tr').css('background-color','');
		if (!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).css('background-color','#B0BED9 !important');
			editKTPlanDetails(gKey);
		}
	});

	$(document).on("change", "#trainerId", function(event) {
		trainierSelectedVal=$("#trainerId").val();
		attendeeSelectedVal=$("#attendeeId").val();
		if(tranerArray !=null || ""!=tranerArray){
		tranerArray=resourcesArray;
		}
		if(null != trainierSelectedVal && trainierSelectedVal.indexOf(",") != -1){
			trainierSelectedVal=trainierSelectedVal.split(",");
		}
		tranerArray = $.grep(tranerArray, function(value) {
			return $.inArray(value, trainierSelectedVal) < 0;
			});
		fillSelectDropDown('attendeeId',tranerArray,attendeeSelectedVal);
	});
	
	$(document).on("change","#attendeeId",function (event){
		trainierSelectedVal=$("#trainerId").val();
		attendeeSelectedVal=$("#attendeeId").val();
		if(attendeeArray !=null || ""!=attendeeArray){
		attendeeArray=resourcesArray;
		}

		if(null != attendeeSelectedVal && attendeeSelectedVal.indexOf(",") != -1){
			attendeeSelectedVal=attendeeSelectedVal.split(",");
		}
		attendeeArray = $.grep(attendeeArray, function(value) {
			return $.inArray(value, attendeeSelectedVal) < 0;
			});
		fillSelectDropDown('trainerId',attendeeArray,trainierSelectedVal);
	});

});

function showLoader(){
	$('#ktMainDiv').hide();
	$('#applyFilter').hide();
	$('#loader_div').show();
}

function hideLoader(){
	$('#ktMainDiv').show();
	$('#applyFilter').show();
	$('#loader_div').hide();
}
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
		"buttons": [{
					extend: 'excel',
                    title: 'KTPlanDataExport',
                },{
                    extend: 'csv',
                    title: 'KTPlanDataExport',
                }]
	});
	$('#show-kt-details-id_wrapper').find('.dt-buttons').children().removeClass('btn-default')
	$('#show-kt-details-id_wrapper').find('.dt-buttons').children().addClass('bg-light-grey')
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
	setTimeout(function(){
		hideLoader();
	}, 1000);
}

function populateKTDetails(entriesList){
	var sNo = 1;
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
		html += '<td  nowrap>'+sNo+'</td>' ;
		html +=	'<td  nowrap>'+project+'</td>' ;
		html += '<td  nowrap>'+trainingType+'</td>' ;
		html += '<td  hidden>'+session+'</td>' ;
		html +=	'<td  nowrap>'+title+'</td>' ;
		html += '<td  hidden>'+trainers+'</td>' ;
		html += '<td  hidden>'+attendees+'</td>' ;
		
		html += '<td  nowrap>'+location+'</td>' ;
		html += '<td  nowrap>'+startDate+'</td>' ;
		html += '<td  nowrap>'+endDate+'</td>' ;

		html += '<td  hidden>'+duration+'</td>' ;
		html += '<td  hidden>'+remarks+'</td>' ;
		html += '<td  hidden>'+feedback+'</td>' ;
//		html += '<td id="ktDetailsEditRowId" onclick="editKTPlanDetails('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span><a id="ktDetailsSaveRowId" style="display:none;" href="#"> <span class="glyphicon glyphicon-check"></span></a></td>' ;
		html += '</tr>' ;
		htmlArray.push(html);
		sNo++;
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
	
	$('#ktDetailsForm :input').removeClass('error');

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
	$('#durationId').val( null != currentSelectedObject ? currentSelectedObject['duration'].toFixed(2) : "");
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
function validateBeforeSave(){
	$('#ktDetailsForm :input').removeClass('error');
	var errorMsgArray=[];
	$("#ktDetailsForm :input").each(function(){
		if($(this).hasClass('imp')){
			var input = $(this);
			var id=$(input).attr('id');
			var value=$(input).val();
			if(null == value || value.length == 0 || typeof(value) == 'undefined'){
				errorMsgArray.push(ktpFormErrorMessages[id]);
				$(input).addClass('error');
			}
		}
		
	});
	if(errorMsgArray.length > 0){
		var notifyObj={msg: '<b>Please Fix KT Plan Form Validation Errors !!! </b>',type: "error",position: "center",autohide: true};
		notif(notifyObj);
		return false;
	}
	return true;
}

function addOrUpdateKTPlan(){
	
	$('#ktDetailsForm :input').removeClass('error');
	
	if(!validateBeforeSave()){
		return false;
	}
	
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
	var startDateVal=new Date(Date.parse(startDate));
	var endDateVal=new Date(Date.parse(endDate));
	var duration=rowEle.find('#durationId').val();
	var remarks=rowEle.find('#remarksId').val();
	var feedback=rowEle.find('#feedbackId').val();
	if(isTitleValid(title.trim())){
		var notifyObj={msg: '<b>Warning : </b> Please Enter valid Title !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}else if(isDurationValid(duration.trim())){
		var notifyObj={msg: '<b>Warning : </b> Please Enter valid Duration !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}else if(startDateVal > endDateVal ){
		var notifyObj={msg: 'EndDate should be Equal or greater than the StartDate !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}else{
		isValid=true;
	}
	ktObject['project']=projectId;
	ktObject['trainingType']=typeId;
	ktObject['session']=sessionId;
	ktObject['title']=title.trim();		
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
		var notifyObj={msg: '<b>Success : </b> KT Details added Successfully !!!',type: "success",position: "center" };
		notif(notifyObj);
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"kt_plan/ktPlan";
	}
}

function ktDeatilsUpdateSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	
		var notifyObj={msg: '<b>Success : </b> KT Details Updated Successfully !!!',type: "success",position: "center" };
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
function isTitleValid(title){
	var regexp= /^[0-9A-Za-z\-\()']+( [0-9A-Za-z\-\()']+)*$/;
	return title.search(regexp);
}
function isDurationValid(duration){
	var regexp = /^[0-9\.]+$/;
	return duration.search(regexp);
/*	var regexp= /^[A-Za-z']+( [A-Za-z']+)*$/;
	return duration.search(regexp);*/
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

