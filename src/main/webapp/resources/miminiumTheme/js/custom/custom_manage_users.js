var userDetailsData={};
var rolesArray=[];
var rolesSelectHtml="";
var rolesNameObj="";
var roleListObject={};
var rowEle="";
var roleStatus=[true,false];
var egKey="";

var createdOnDt = $.datepicker.formatDate('yy-mm-dd', new Date());
$(document).ready(function() {	
	showLoader();
	getUserDetails();
	getRolesDetails();
	
	$('#addUserDetailsForm').hide();
	$('#addUserDetailsId').on("click" ,function (event){
		userDetailsModalData(null);
	});

	$('#show-user-details-id tbody').on('click', 'tr', function () {
		console.log('user edit');
		var gKey=$(this).attr('id');
		$('#show-user-details-id tbody tr').removeClass('selected');
		$('#show-user-details-id tr').css('background-color','');
		if (!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).css('background-color','#B0BED9 !important');
			userDetailsEdit(gKey);
		}
	});
});

function showLoader(){
	$('#userMainDiv').hide();
	$('#applyFilter').hide();
	$('#loader_div').show();
}

function hideLoader(){
	$('#userMainDiv').show();
	$('#applyFilter').show();
	$('#loader_div').hide();
}

function getUserDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getUserDetails", 'json', null, fetchUserDetailsSuccess,true);
}

function fetchUserDetailsSuccess(serverData){
	$('#show-user-details-id').find('#tbody_id').html(populateUserDetails(serverData['SERVER_DATA']));
	$('#show-user-details-id').DataTable({
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
		var userObject = entriesList[i];
		var rolesObject= userObject['roleId'];
		var roleDesc=rolesObject['roleDesc'];		
		var html ="";
		var userDetailsModelAttribute=entriesList[i];
		var gKey=userDetailsModelAttribute['gkey'];
		var name=userDetailsModelAttribute['userFullName'];
				
		var userId=userDetailsModelAttribute['userId'];
		
		var emailId=userDetailsModelAttribute['emailId'];

		var active=userDetailsModelAttribute['active'];	
	/*	if(active){
			active="Yes";
		}
		else{
			active="No";
		}*/
		var createdOn=getDateValue(userDetailsModelAttribute['createdOn'],'yyyy-MM-dd',"-")	
		userDetailsData[gKey]=userDetailsModelAttribute;
		html += '<tr id="'+gKey+'">' ;
		html += '<td>'+sNo+'</td>' ;
		html += '<td>'+name+'</td>' ;
		html +=	'<td>'+userId+'</td>' ;
		html += '<td>'+emailId+'</td>' ;
		html += '<td>'+roleDesc+'</td>' ;
		html +=	'<td>'+active+'</td>' ;
		html += '<td>'+createdOn+'</td>' ;
//		html += '<td id="userDetailsEditRowId" onclick="userDetailsEdit('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span><a id="userDetailsSaveRowId" style="display:none;" href="#"> <span class="glyphicon glyphicon-check"></span></a></td>' ;
		html += '</tr>' ;
		htmlArray.push(html);
		sNo++;
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
	userDetailsModalData(gKey);
	
}

function getRolesDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getRolesList", 'json', null, fetchRolesNamesSuccess,true);
}

function fetchRolesNamesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var rolesNameObjList=serverData['SERVER_DATA'];
		rolesSelectHtml += '<select id="rolesListId" class="input-sm form-control">'
		for(var index in rolesNameObjList){
			var roleName=rolesNameObjList[index]['roleDesc'];
			roleListObject[roleName]=rolesNameObjList[index];
			rolesNameObj=rolesNameObjList[index];
			rolesArray.push(rolesNameObj['roleDesc']);
			rolesSelectHtml += '<option value="'+rolesNameObj['roleDesc']+'">'+rolesNameObj['roleDesc']+'</option>';
		}
		rolesSelectHtml += '</select>'
	}
}

function userDetailsModalData(gKey){
	
	var currentSelectedObject=null;
	currentSelectedObject = null != gKey ? userDetailsData[gKey] : null;
	
	fillSelectDropDown('activeId',roleStatus, null != currentSelectedObject ? currentSelectedObject['active'].toString() : "");
	fillSelectDropDown('roleId',rolesArray, null != currentSelectedObject ? currentSelectedObject['roleId']['roleDesc'] : "");
	
	$('#userNameId').val(null != currentSelectedObject ? currentSelectedObject['userFullName'] : "");
	$('#userId').val(null != currentSelectedObject ? currentSelectedObject['userId'] : "");
	$('#emailId').val(null != currentSelectedObject ? currentSelectedObject['emailId'] : "");
	$('#createdOnId').val(null != currentSelectedObject ? getDateValue(currentSelectedObject['createdOn'],'yyyy-MM-dd',"-") : createdOnDt);

	$("#addUserDetailsTrigger").trigger( "click" );
	$('#addUserDetailsDiv').html($('#addUserDetailsForm'));
	$('#addUserDetailsDiv').find('#addUserDetailsForm').show();
}

function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(selectedOption);
}


function addOrUpdateUser(){
	var isAdd=false;
	var isValid=false;
	var userObject={};
	
	var rowEle=$('#addUserDetailsDiv').find('form');
	var uName=rowEle.find('#userNameId').val();
	var uId=rowEle.find('#userId').val();
	var eId=rowEle.find('#emailId').val();
	var roleId=rowEle.find('#roleId').val();
	var activeId=rowEle.find('#activeId').val();
	var createdOn=rowEle.find('#createdOnId').val();
//	console.log(userDetailsData[egKey]['password']);
	if (null ==uName || ""== uName){
		var notifyObj={msg: '<b>Warning : </b> Please Enter Name !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (null ==uId || ""== uId){
		var notifyObj={msg: '<b>Warning : </b> Please Enter User ID !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (uId.length<=2){
		var notifyObj={msg: '<b>Warning : </b> User ID should be minimum 3 letters !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (null ==eId || ""== eId){
		var notifyObj={msg: '<b>Warning : </b> Please Enter Email ID !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if ((null !=eId || ""!= eId) && (!isEmail(eId))){
			var notifyObj={msg: '<b>Warning : </b> You have entered an invalid email address !!!',type: "warning",position: "center" ,autohide: false};
			notif(notifyObj);
	}else if (null ==roleListObject[roleId] || ""== roleListObject[roleId]){
		var notifyObj={msg: '<b>Warning : </b> Please select role !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (null ==activeId || ""== activeId){
			var notifyObj={msg: '<b>Warning : </b> Please select active option !!!',type: "warning",position: "center" ,autohide: false};
			notif(notifyObj);
	}
	else{
		isValid=true;
	}
	if(egKey ==null || "" == egKey){
		isAdd=true;
		
	}
	else{
		userObject['gkey']=egKey;
		userObject['password']=userDetailsData[egKey]['password'];
		userObject['modifiedOn']=getDateValue(createdOnDt);
	}
	
	userObject['userFullName']=uName;
	userObject['userId']=uId;
	userObject['emailId']=eId;
	userObject['roleId']=roleListObject[roleId];		
	userObject['active']=activeId;
	userObject['createdOn']=getDateValue(createdOn,'yyyy-MM-dd',"-");
	userObject['roleId']['createdOn']=getDateValue(userObject['roleId']['createdOn'],'yyyy-MM-dd',"-");
	userObject['roleId']['modifiedOn']=getDateValue(userObject['roleId']['modifiedOn'],'yyyy-MM-dd',"-");
	
	if(isAdd && isValid){
		ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"rbac/addUserDetails", 'json', null, addUserDetailsSuccess,true);
	
	}else if(isValid){
		ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"rbac/updateUserDetails", 'json', null,updateUserDetailsSuccess,true);
	}
}


function isEmail(email) {
	var checkMail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return checkMail.test(email);
}

function addUserDetailsSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	

		var notifyObj={msg: '<b>Success : </b> User added Successfully !!!',type: "success",position: "center" ,autohide: false};
		notif(notifyObj);
		
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"rbac/showUser";
	}
}

function updateUserDetailsSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	
		var notifyObj={msg: '<b>Success : </b> User Updated Successfully !!!',type: "success",position: "center" ,autohide: false};
		notif(notifyObj);
		
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"rbac/showUser";
	}
}
