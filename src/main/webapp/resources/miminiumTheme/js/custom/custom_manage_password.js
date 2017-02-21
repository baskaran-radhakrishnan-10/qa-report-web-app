var resetPasswordFormErrorMessages={};

resetPasswordFormErrorMessages['userId']="Please Enter User ID !!!";
resetPasswordFormErrorMessages['newPasswordId']="Please Enter New Password !!!";
resetPasswordFormErrorMessages['confirmPasswordId']="Please Enter Confirm Password !!!";
resetPasswordFormErrorMessages['modifiedOnId']="Please Enter Modified on date !!!";


var userDetailsData={};
var rolesArray=[];
var rolesSelectHtml="";
var itemDetailsObject={};
var rolesNameObj="";
var roleListObject={};
var userIdListObject={};
var resetPassword=false;



$(document).ready(function() {
	getUserDetails();
	
	$("#modifiedOnId").val($.datepicker.formatDate('dd-mm-yy', new Date()));
	
	$('#newPasswordId').on("blur" ,function (event){
		
		$(this).removeClass("error");
		
		var value = $(this).val();
		
		if("" != value && value.length <8){
			$(this).addClass("error");
			var notifyObj={msg: '<b>Warning : </b> New Password should be minimum 8 letters !!!',type: "error",position: "center" };
			notif(notifyObj);
			return false;
		}
	});
	
	$('#confirmPasswordId').on("blur" ,function (event){
		
		$(this).removeClass("error");
		
		var value = $(this).val();
		
		if("" != value && value.length <8){
			$(this).addClass("error");
			var notifyObj={msg: '<b>Warning : </b> Confirm Password should be minimum 8 letters !!!',type: "error",position: "center" };
			notif(notifyObj);
			return false;
		}
	});
	
	$('#cancel_button').on("click",function(event){
		console.log("clear button");
		$('#userId').val("Please Select");
	});
	
	$('#reset_password_button').on("click" ,function (event){
		
		$('#resetUserDetailsForm :input').removeClass('error');
		
		if(!validateBeforeSave()){
			return false;
		}
		
		var nPwd=$("#newPasswordId").val().trim();
		var cPwd=$("#confirmPasswordId").val().trim();
		
		if((nPwd != cPwd)){
			var notifyObj={msg: '<b>Warning : </b> New and Confirm Password not matching !!!',type: "error",position: "center" };
			notif(notifyObj);
			return false;
		}else if((nPwd.length<8) && (cPwd.length<8)){
			var notifyObj={msg: '<b>Warning : </b> Password should be minimum 8 letters !!!',type: "error",position: "center" };
			notif(notifyObj);
			return false;
		}
		
		var emailId=$('#emailIdHidden').val();
		var userObject={};
		userObject['userId']=$("#userId").val();
		userObject['password']=nPwd;
		userObject['emailId']= (emailId.length > 0) ? emailId : userIdListObject[userObject['userId']]['emailId']
		ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"rbac/resetPassword", 'json', null, resetPasswordSuccess,true);

		/*if(resetPassword){
			var emailId=$('#emailIdHidden').val();
			var userObject={};
			userObject['userId']=$("#userId").val();
			userObject['password']=nPwd;
			userObject['emailId']= (emailId.length > 0) ? emailId : userIdListObject[userObject['userId']]['emailId']
			ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"rbac/resetPassword", 'json', null, resetPasswordSuccess,true);
		}*/
	});
	
	$("#newPasswordId").on("keydown", function (e) {
	    return e.which !== 32;
	});
	$("#confirmPasswordId").on("keydown", function (e) {
	    return e.which !== 32;
	});
	
	$('#cancel_button').on("click" ,function (event){
		$('#resetUserDetailsForm :input').removeClass('error');
		$('#userId').val("");
		$('#newPasswordId').val("");
		$('#confirmPasswordId').val(""); 
	});
	
});

function validateBeforeSave(){
	$('#resetUserDetailsForm :input').removeClass('error');
	var errorMsgArray=[];
	$("#resetUserDetailsForm :input").each(function(){
		if($(this).hasClass('imp')){
			var input = $(this);
			var id=$(input).attr('id');
			var value=$(input).val();
			if(null == value || value.length == 0 || typeof(value) == 'undefined'){
				errorMsgArray.push(resetPasswordFormErrorMessages[id]);
				$(input).addClass('error');
			}else if("Please Select" == value){
				$(input).addClass('error');
			}
		}
	});
	if(errorMsgArray.length > 0){
		var notifyObj={msg: '<b>Please Fix Reset Password Form Validation Errors !!! </b>',type: "error",position: "center",autohide: true};
		notif(notifyObj);
		return false;
	}
	return true;
}

function getUserDetails(){
	if("ROLE_SUPER_ADMIN" == $('#loggedInRoleId').val()){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getUserDetails", 'json', null, fetchUserDetailsSuccess,true);
	}else{
		var splitedArray = $('#loggedInUser').val().split(" -- ");
		var userId = splitedArray[1].split(" : ")[1];
		var emailId = splitedArray[2].split(" : ")[1];
		var tempUserIdArray=new Array();
		tempUserIdArray.push(userId);
		fillSelectDropDown('userId',tempUserIdArray, userId);
		$('#emailIdHidden').val(emailId);
	}
	
}

function fetchUserDetailsSuccess(serverData){
	populateUserDetails(serverData['SERVER_DATA']);
}

function populateUserDetails(entriesList){
	rolesArray.push("Please Select");
	for(var i=0;i<entriesList.length;i++){		
		var userDetailsModelAttribute=entriesList[i];
		var userId=entriesList[i]['userId'];
		var emailId=entriesList[i]['emailId'];
		userIdListObject[userId]=entriesList[i];
		rolesArray.push(userDetailsModelAttribute['userId']);
		rolesArray.sort();
		fillSelectDropDown('userId',rolesArray, null != userDetailsModelAttribute ? userDetailsModelAttribute['userId'] : "");
		$('#userId').val('Please Select');
	}
}

/*function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(" ");
}*/

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

function resetPasswordSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var userIdTemp = $('#userId').val();
		$('#userId').val("Please Select");
		$('#newPasswordId').val("");
		$('#confirmPasswordId').val("");       
		var notifyObj={msg: '<b>Success : </b> Password Updated for userid : '+userIdTemp,type: "success",position: "center" };
		notif(notifyObj);
	}
}

/*function currentDate(){
	var dat = new Date();
	var month = dat.getMonth()+1;
	var day = dat.getDate();
	var dateTime = dat.getFullYear() + '-' +(month<10 ? '0' : '') + month + '-' +(day<10 ? '0' : '') + day +" "+ dat.getHours( )+ ":" + dat.getMinutes() + ":" + dat.getSeconds();
return dateTime;
}*/
