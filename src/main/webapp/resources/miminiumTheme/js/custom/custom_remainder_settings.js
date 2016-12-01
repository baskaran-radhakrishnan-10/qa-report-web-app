var reminderFormErrorMessages={};
reminderFormErrorMessages['remainderDateId']="Please Enter Reminder Date !!!";
reminderFormErrorMessages['remainderTimeId']="Please Enter Reminder Time !!!";
reminderFormErrorMessages['remainderAboutId']="Please Enter Reminder About !!!";


var userDetailsData={};
var rowEle="";
var egKey="";

var createdOnDt = $.datepicker.formatDate('yy-mm-dd', new Date());
var currentUser=$('#loggedInUserId').val();
$(document).ready(function() {	
	
	showLoader();
	getUserDetails();
	
	$('#addReminderDetailsForm').hide();
	$('#addReminderDetailsId').on("click" ,function (event){
		remainderDetailsModalData(null);
	});

	$('#show-reminder-details-id tbody').on('click', 'tr', function () {
		var gKey=$(this).attr('id');
		$('#show-reminder-details-id tbody tr').removeClass('selected');
		$('#show-reminder-details-id tr').css('background-color','');
		if (!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).css('background-color','#B0BED9 !important');
			userDetailsEdit(gKey);
		}
	});
});

function showLoader(){
	$('#reminderMainDiv').hide();
	$('#applyFilter').hide();
	$('#loader_div').show();
}

function hideLoader(){
	$('#reminderMainDiv').show();
	$('#applyFilter').show();
	$('#loader_div').hide();
}

function getUserDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"operation/getRemainderDetails", 'json', null, fetchUserDetailsSuccess,true);
}

function fetchUserDetailsSuccess(serverData){
	$('#show-reminder-details-id').find('#tbody_id').html(populateUserDetails(serverData['SERVER_DATA']));
	$('#show-reminder-details-id').DataTable({
		info : false,
		"responsive" : true
	});
	
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

function populateUserDetails(entriesList){
	var sNo = 1;
	var htmlArray=new Array();
	for(var i=0;i<entriesList.length;i++){		
		var html ="";
		var userDetailsModelAttribute=entriesList[i];
		var gKey=userDetailsModelAttribute['gkey'];
		var remainderDate=getDateValue(userDetailsModelAttribute['remainderDate'],'yyyy-MM-dd',"-");
		var remainderTime=userDetailsModelAttribute['remainderTime'];
		var remainderMessage=userDetailsModelAttribute['remainderMessage'];
		var remainderUser=userDetailsModelAttribute['remainderUser'];
		userDetailsData[gKey]=userDetailsModelAttribute;
		if(currentUser==remainderUser){
			html += '<tr id="'+gKey+'">' ;
			html += '<td>'+sNo+'</td>' ;
			html += '<td>'+remainderDate+'</td>' ;
			html +=	'<td>'+remainderTime+'</td>' ;
			html += '<td>'+remainderMessage+'</td>' ;
			html += '</tr>' ;
			sNo++;
		}

		htmlArray.push(html);
		
	}
	return htmlArray;
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
function userDetailsEdit(gKey){
	egKey=gKey;
	remainderDetailsModalData(gKey);
	
}

function remainderDetailsModalData(gKey){
	
	var currentSelectedObject=null;
	currentSelectedObject = null != gKey ? userDetailsData[gKey] : null;
	
	$('#remainderDateId').val(null != currentSelectedObject ? getDateValue(currentSelectedObject['remainderDate'],'yyyy-MM-dd',"-"):createdOnDt);
	$('#remainderTimeId').val(null != currentSelectedObject ? currentSelectedObject['remainderTime'] : "");
	$('#remainderAboutId').val(null != currentSelectedObject ? currentSelectedObject['remainderMessage'] : "");

	$("#addReminderDetailsTrigger").trigger( "click" );
	$('#addReminderDetailsDiv').html($('#addReminderDetailsForm'));
	$('#addReminderDetailsDiv').find('#addReminderDetailsForm').show();
}

function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(selectedOption);
}

function validateBeforeSave(){
	$('#addReminderDetailsForm :input').removeClass('error');
	var errorMsgArray=[];
	$("#addReminderDetailsForm :input").each(function(){
		if($(this).hasClass('imp')){
			var input = $(this);
			var id=$(input).attr('id');
			var value=$(input).val();
			if(null == value || value.length == 0 || typeof(value) == 'undefined'){
				errorMsgArray.push(reminderFormErrorMessages[id]);
				$(input).addClass('error');
			}
		}
		
	});
	if(errorMsgArray.length > 0){
		var notifyObj={msg: '<b>Please Fix Reminder Form Validation Errors !!! </b>',type: "error",position: "center",autohide: true};
		notif(notifyObj);
		return false;
	}
	return true;
}

function addOrUpdateReminder(){
	
	$('#addReminderDetailsForm :input').removeClass('error');
	
	if(!validateBeforeSave()){
		return false;
	}
	
	var isAdd=false;
	var isValid=false;
	var userObject={};
	
	var rowEle=$('#addReminderDetailsDiv').find('form');
	
	var remainderDate=rowEle.find('#remainderDateId').val();
	var remainderTime=rowEle.find('#remainderTimeId').val();
	var remainderAbout=rowEle.find('#remainderAboutId').val().trim();
//	console.log(remainderTime);
	var remainderDateVal=new Date(Date.parse(remainderDate));
	var currentDateVal=new Date(Date.parse(createdOnDt));
	
	if (remainderDateVal< currentDateVal){
		var notifyObj={msg: '<b>Warning : </b>Reminder Date must be >= current date !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}else if(isReminderMsgValid(remainderAbout)){
		var notifyObj={msg: '<b>Warning : </b>Reminder message criteria not matching !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}else if(isRemainderTimeValid(remainderTime)){
		var notifyObj={msg: '<b>Warning : </b>Reminder time criteria not matching !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}else{
		isValid=true;
	}
	if(egKey ==null || "" == egKey){
		isAdd=true;
		
	}
	else{
		userObject['gkey']=egKey;
	}

	userObject['remainderDate']=getDateValue(remainderDate,'yyyy-MM-dd',"-");
	userObject['remainderTime']=remainderTime;
	userObject['remainderMessage']=remainderAbout;
	userObject['remainderUser']=currentUser;
	
	if(isAdd && isValid){
		ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"operation/addRemainderDetails", 'json', null, addUserDetailsSuccess,true);
	
	}else if(isValid){
		ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"operation/updateRemainderDetails", 'json', null,updateUserDetailsSuccess,true);
	}
}



function isReminderMsgValid(title){
	var regexp= /^[A-Za-z0-9']+( [A-Za-z0-9']+)*$/;
	return title.search(regexp);
}

function isRemainderTimeValid(duration){
	var regexp = /^[0-9\:]+$/;
	return duration.search(regexp);
}

function addUserDetailsSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	

		var notifyObj={msg: '<b>Success : </b> Reminder Details added Successfully !!!',type: "success",position: "center" };
		notif(notifyObj);
		
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"operation/remainder_settings";
	}
}

function updateUserDetailsSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	
		var notifyObj={msg: '<b>Success : </b> Reminder Details Updated Successfully !!!',type: "success",position: "center" };
		notif(notifyObj);
		
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"operation/remainder_settings";
	}
}
