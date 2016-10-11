var userDetailsData={};
var rolesArray=[];
var rolesSelectHtml="";

$(document).ready(function() {	
	console.log("documet.ready Manage Role Details");
	$('#addRoleDetailsForm').hide();
	$('#addRoleDetailsId').on("click" ,function (event){
		console.log("Add User button clicked");
		roleDetailsModalData(null);
	});
	//getUserDetails();
	getRolesDetails();
});

function getUserDetails(){
	var data={};
	data=JSON.stringify(data);
//	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"manage_users/getUserDetails");
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getUserDetails", 'json', null, fetchUserDetailsSuccess,true);
	//alert('getUserDetails');
}

function fetchUserDetailsSuccess(serverData){
	//alert('fetchUserDetailsSuccess');
	console.log(serverData);
	$('#show-user-details-id').find('#tbody_id').html(populateUserDetails(serverData['USER_DATA']));
	$('#show-user-details-id').DataTable({
		info : false,
		"responsive" : true
	});
}


function populateUserDetails(entriesList){
	//alert('populateUserDetails');
	var sNo = 1;
	var htmlArray=new Array();
	for(var i=0;i<entriesList.length;i++){		
		var html ="";
		var userDetailsModelAttribute=entriesList[i];
		var gKey=userDetailsModelAttribute['gKey'];
		var roleName=userDetailsModelAttribute['role_name'];
	
		
		userDetailsData[gKey]=userDetailsModelAttribute;
		html += '<tr id="'+gKey+'">' ;
		html += '<td>'+sNo+'</td>' ;
		html += '<td>'+roleName+'</td>' ;
		
		html += '<td id="userDetailsEditRowId" onclick="roleDetailsModalData('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span></td>' ;
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

function roleDetailsModalData(gKey){
	
	var currentSelectedObject=null;
	
	currentSelectedObject = null != gKey ? userDetailsData[gKey] : null;
	
	console.log(currentSelectedObject);
	
	console.log("---currentSelectedObject---"+currentSelectedObject);
	
	console.log("Begin--inside userDetailsModalData---");
	
	fillSelectDropDown('roleDescId',rolesArray, null != currentSelectedObject ? currentSelectedObject['role_name'] : "");
	
	$("#addUserDetailsTrigger").trigger( "click" );
	$('#addUserDetailsDiv').html($('#addUserDetailsForm'));
	
	$('#addUserDetailsDiv').find('#addUserDetailsForm').show();
	
	console.log("End--inside userDetailsModalData---");
}

function addUserRows(){
	console.log("----addUserRows----");
	var tBody=$('#show-role-details-id').find('tbody');
	var totalRows=$(tBody).find('tr').length;
	var nextRow=(totalRows+1);
	var html = "";
	html += '<tr id="row_'+nextRow+'" class="'+nextRow+'">';
	html += '<td id="sNo">'+nextRow+'</td>';
	html += '<td id="roleNameId"><input type="text" class="input-sm form-control" value=""  /></td>';
	/*html += '<td id="emailId"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="userId"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="roleId">'+rolesSelectHtml+'</td>';
	html += '<td id="activeId"><input type="checkbox" checked data-toggle="toggle" /></td>';
	html += '<td id="createdOnId"><input type="text" class="input-sm form-control" value=""  /></td>';*/
	html += '<td id="action" style="text-align: -webkit-center;">';
	html += '<a  id="userDetailsRowEditId" style="display:none;" href="#" onclick="userDetailsEdit('+nextRow+')"> <span class="glyphicon glyphicon-edit"></span></a>'; 
	html += '<span>&nbsp;</span>';
	html += '<a id="userDetailsRowSaveId" href="#" onclick="userDeatilsSave('+nextRow+')"> <span class="glyphicon glyphicon-check"></span></a>';
	html += '</td>';
	html += '</tr>';
	$(tBody).append(html);
}

function userDeatilsSave(rowId){
	var rowEle=$('#show-user-details-id').find('tbody').find('#row_'+rowId);
	
	var userObject={};
	userObject['role_name']=$(rowEle).find('#roleNameId').find('input').val();
/*	userObject['emailId']=$(rowEle).find('#emailId').find('input').val();
	userObject['userId']=$(rowEle).find('#userId').find('input').val();
	userObject['roleId']=$(rowEle).find('#roleId').find('#rolesListId').val();
	userObject['active']=$(rowEle).find('#activeId').find('input').val();*/
	
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
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getRolesList", 'json', null, fetchRolesNamesListSuccess,true);
}

function fetchRolesNamesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var rolesNameObjList=serverData['SERVER_DATA'];
		rolesSelectHtml += '<select id="rolesListId" class="input-sm form-control">'
		for(var index in rolesNameObjList){
			var rolesNameObj=rolesNameObjList[index];
			rolesArray.push(rolesNameObj['role_name']);
			rolesSelectHtml += '<option value="'+rolesNameObj['role_name']+'">'+rolesNameObj['role_name']+'</option>';
		}
		rolesSelectHtml += '</select>'
	}
}

function fetchRolesNamesListSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		
		console.log(serverData);
		$('#show-role-details-id').find('#tbody_id').html(populateUserDetails(serverData['SERVER_DATA']));
		$('#show-role-details-id').DataTable({
			info : false,
			"responsive" : true
		});
		
		
		var rolesNameObjList=serverData['SERVER_DATA'];
		rolesSelectHtml += '<select id="rolesListId" class="input-sm form-control">'
		for(var index in rolesNameObjList){
			var rolesNameObj=rolesNameObjList[index];
			rolesArray.push(rolesNameObj['role_name']);
			rolesSelectHtml += '<option value="'+rolesNameObj['role_name']+'">'+rolesNameObj['role_name']+'</option>';
		}
		rolesSelectHtml += '</select>'
	}
}