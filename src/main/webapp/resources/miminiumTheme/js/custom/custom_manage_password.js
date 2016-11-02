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
	$('#reset_password_button').on("click" ,function (event){
		
		var nPwd=$("#newPasswordId").val();
		var cPwd=$("#confirmPasswordId").val();
		
		if(""==$("#userId").val()||null==$("#userId").val()){
			var notifyObj={msg: '<b>Warning : </b> Please select the User ID !!!',type: "warning",position: "center" };
			notif(notifyObj);
		}
		else if(""==nPwd||null==nPwd){
			var notifyObj={msg: '<b>Warning : </b> Please enter New password !!!',type: "warning",position: "center" };
			notif(notifyObj);
		}
		else if(""==cPwd||null==cPwd){
			var notifyObj={msg: '<b>Warning : </b> Please enter Confirm password !!!',type: "warning",position: "center" };
			notif(notifyObj);
		}
		else if((nPwd.length<5) && (cPwd.length<5)){
			var notifyObj={msg: '<b>Warning : </b> Password should be minimum 5 letters !!!',type: "warning",position: "center" };
			notif(notifyObj);
		}else if((nPwd != cPwd)){
			var notifyObj={msg: '<b>Warning : </b> New and Confirm Password not matching !!!',type: "warning",position: "center" };
			notif(notifyObj);
		}
		else if((nPwd==cPwd) && (null!=nPwd && null!=cPwd) && (""!=nPwd && ""!=cPwd ) && ((nPwd.length >=5) && (cPwd.length >=5))){
			resetPassword=true;
		}

		if(resetPassword){
			var userObject={};
			userObject['userId']=$("#userId").val();
			userObject['gkey']=userIdListObject[userObject['userId']]['gkey']
			userObject['userFullName']=userIdListObject[userObject['userId']]['userFullName']
			userObject['password']=$("#newPasswordId").val();
			userObject['emailId']= userIdListObject[userObject['userId']]['emailId']
			userObject['active']= userIdListObject[userObject['userId']]['active']
			userObject['roleId']= userIdListObject[userObject['userId']]['roleId']
			userObject['roleId']['createdOn']=getDateValue(['roleId']['createdOn'],'yyyy-MM-dd',"-");
			userObject['roleId']['modifiedOn']=getDateValue(['roleId']['modifiedOn'],'yyyy-MM-dd',"-");
			userObject['modifiedOn']=$.datepicker.formatDate('yy-mm-dd', new Date());
			ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"rbac/resetPassword", 'json', null, resetPasswordSuccess,true);
		}
	});
	
});

function getUserDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getUserDetails", 'json', null, fetchUserDetailsSuccess,true);
}

function fetchUserDetailsSuccess(serverData){
	console.log(serverData);
	populateUserDetails(serverData['SERVER_DATA']);
}

function populateUserDetails(entriesList){
	for(var i=0;i<entriesList.length;i++){		
		var userDetailsModelAttribute=entriesList[i];
		var userId=entriesList[i]['userId'];
		var emailId=entriesList[i]['emailId'];
		userIdListObject[userId]=entriesList[i];
		rolesArray.push(userDetailsModelAttribute['userId']);
		fillSelectDropDown('userId',rolesArray, null != userDetailsModelAttribute ? userDetailsModelAttribute['userId'] : "");
	}
}

function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(" ");
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

function resetPasswordSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		$('#userId').val("");
		$('#newPasswordId').val("");
		$('#confirmPasswordId').val("");       
		var notifyObj={msg: '<b>Success : </b> Password Updated Successfully !!!',type: "success",position: "center" };
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
