var userDetailsData={};
var rolesArray=[];
var rolesSelectHtml="";
var itemDetailsObject={};
var rolesNameObj="";
var roleListObject={};
var rowEle="";

$(document).ready(function() {	
	$('#addUserDetailsForm').hide();
	$('#addUserDetailsId').on("click" ,function (event){
		userDetailsModalData(null);
	});
	getUserDetails();
	getRolesDetails();
});

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
		var gKey=userDetailsModelAttribute['gKey'];
		var name=userDetailsModelAttribute['userFullName'];
				
		var userId=userDetailsModelAttribute['userId'];
		
		var emailId=userDetailsModelAttribute['emailId'];

		var active=userDetailsModelAttribute['active'];	
		if(active){
			active="Yes";
		}
		else{
			active="No";
		}
		var createdOn=getDateValue(userDetailsModelAttribute['createdOn'],'dd/MM/yyyy',"-")	
		userDetailsData[gKey]=userDetailsModelAttribute;
		html += '<tr id="'+gKey+'">' ;
		html += '<td>'+sNo+'</td>' ;
		html += '<td>'+name+'</td>' ;
		html +=	'<td>'+userId+'</td>' ;
		html += '<td>'+emailId+'</td>' ;
		html += '<td>'+roleDesc+'</td>' ;
		html +=	'<td>'+active+'</td>' ;
		html += '<td>'+createdOn+'</td>' ;
		html += '<td id="userDetailsEditRowId" onclick="userDetailsEdit('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span><a id="userDetailsSaveRowId" style="display:none;" href="#"> <span class="glyphicon glyphicon-check"></span></a></td>' ;
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

function userDetailsModalData(gKey){
	
	var currentSelectedObject=null;
	
	currentSelectedObject = null != gKey ? userDetailsData[gKey] : null;
	
	fillSelectDropDown('roleDescId',rolesArray, null != currentSelectedObject ? currentSelectedObject['role_name'] : "");
	
	$("#addUserDetailsTrigger").trigger( "click" );
	$('#addUserDetailsDiv').html($('#addUserDetailsForm'));
	
	$('#addUserDetailsDiv').find('#addUserDetailsForm').show();
}

function addUserRows(){
	var tBody=$('#show-user-details-id').find('tbody');
	var totalRows=$(tBody).find('tr').length;
	var nextRow=(totalRows+1);
	var html = "";
	var createdOnDate = $.datepicker.formatDate('dd-mm-yy', new Date());
	html += '<tr id="row_'+nextRow+'" class="'+nextRow+'">';
	html += '<td id="sNo">'+nextRow+'</td>';
	html += '<td id="userNameId"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="userId"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="emailId"><input type="text" class="input-sm form-control" value=""  /></td>';	
	html += '<td id="roleId">'+rolesSelectHtml+'</td>';
	html += '<td id="activeId"><select id="activeUserId"> <option value=true>Yes</option> <option value=false>No</option> </select></td>';
	html += '<td id="createdOnId"><input type="text" class="input-sm form-control" value="'+createdOnDate+'" readonly="readonly"/></td>';
	html += '<td id="action" style="text-align: -webkit-center;">';
	html += '<a  id="userDetailsEditRowId" style="display:none;" href="#" onclick="userDetailsEdit('+nextRow+')"> <span class="glyphicon glyphicon-edit"></span></a>';
	html += '<span>&nbsp;</span>';
	html += '<a id="userDetailsSaveRowId" href="#" onclick="userDeatilsSave('+nextRow+')"> <span class="glyphicon glyphicon-check"></span></a>';
	html += '</td>';
	html += '</tr>';
	$(tBody).append(html);
}



function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(selectedOption);
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

function userDetailsEdit(rowId){
	var rowEle=$('#show-user-details-id').find('table').find('.'+rowId);
	$(rowEle).find('input').prop('disabled',false);
	$(rowEle).find('#action').find('#userDetailsEditRowId').hide();
	$(rowEle).find('#action').find('#userDetailsSaveRowId').show();
}

function userDeatilsSave(rowId){
	rowEle=$('#show-user-details-id').find('tbody').find('#row_'+rowId);
	var uName=$(rowEle).find('#userNameId').find('input').val();
	var uId=$(rowEle).find('#userId').find('input').val();
	var eId=$(rowEle).find('#emailId').find('input').val();
	var isAdd=false;
	var userObject={};
	if (null ==uName || ""== uName){
		var notifyObj={msg: '<b>Warning : </b> Please Enter Name !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (null ==uId || ""== uId){
		var notifyObj={msg: '<b>Warning : </b> Please Enter User ID !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}else if (uId.length<=2){
		var notifyObj={msg: '<b>Warning : </b> User ID should be minimum 3 letters !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
	}
	else if (null ==eId || ""== eId){
		var notifyObj={msg: '<b>Warning : </b> Please Enter Email ID !!!',type: "warning",position: "center" ,autohide: false};
		notif(notifyObj);
		
	}else if (null !=eId && ""!= eId){
		if(!isEmail(eId)){
			var notifyObj={msg: '<b>Warning : </b> You have entered an invalid email address !!!',type: "warning",position: "center" ,autohide: false};
			notif(notifyObj);
		}
		else{
			isAdd=true;
		}
	}
	userObject['userFullName']=$(rowEle).find('#userNameId').find('input').val();
	userObject['userId']=$(rowEle).find('#userId').find('input').val();
	userObject['emailId']=$(rowEle).find('#emailId').find('input').val();
	userObject['roleId']=roleListObject[$(rowEle).find('#roleId').find('#rolesListId').val()];		
	userObject['active']=$(rowEle).find('#activeUserId').val();	
	userObject['createdOn']=$.datepicker.formatDate('yy-mm-dd', new Date());
	userObject['roleId']['createdOn']=getDateValue(['roleId']['createdOn'],'yyyy-MM-dd',"-");
	userObject['roleId']['modifiedOn']=getDateValue(['roleId']['modifiedOn'],'yyyy-MM-dd',"-");
	
	/*	
	if(null == userObject['userId'] || "" == userObject['userId']){
		alert("Please enter user ID");
		isAdd=false;
	}
	if(!isAdd){
		console.log("--- update  user---");
		ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"rbac/updateUserDetails", 'json', null, userDeatilsEditSuccess,true);
	
	}else*/ 
	if(isAdd){
		ajaxHandler("POST", JSON.stringify(userObject), "application/json", getApplicationRootPath()+"rbac/addUserDetails", 'json', null,userDeatilsSaveSuccess,true);
	}
}
function isEmail(email) {
	var checkMail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return checkMail.test(email);
	}

function userDeatilsSaveSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	
		$(rowEle).find('#action').find('#userDetailsEditRowId').show();
		$(rowEle).find('#action').find('#userDetailsSaveRowId').hide();
		$(rowEle).find('input').prop('disabled',true);
		$(rowEle).find('#roleId').find('#rolesListId').prop("disabled", true);
		$(rowEle).find('#activeUserId').prop("disabled", true);
		var notifyObj={msg: '<b>Success : </b> User added Successfully !!!',type: "success",position: "center" ,autohide: false};
		notif(notifyObj);
	}
}
