var userDetailsData={};
var rolesArray=[];
var rolesSelectHtml="";

$(document).ready(function() {	
	$('#addRoleDetailsForm').hide();
	$('#addRoleDetailsId').on("click" ,function (event){
		console.log("Add User button clicked");
		roleDetailsModalData(null);
	});
	getRolesDetails();
});
function populateUserDetails(entriesList){
	var sNo = 1;
	var htmlArray=new Array();
	for(var i=0;i<entriesList.length;i++){		
		var html ="";
		var userDetailsModelAttribute=entriesList[i];
		var gKey=userDetailsModelAttribute['gKey'];
		var roleName=userDetailsModelAttribute['roleName'];
		userDetailsData[gKey]=userDetailsModelAttribute;
		html += '<tr id="'+gKey+'">' ;
		html += '<td>'+sNo+'</td>' ;
		html += '<td>'+roleName+'</td>' ;
//		html += '<td id="userDetailsEditRowId" onclick="roleDetailsModalData('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span></td>' ;
		html += '</tr>' ;
		htmlArray.push(html);
		sNo++;
	}
	return htmlArray;
}
function roleDetailsModalData(gKey){
	
	var currentSelectedObject=null;
	
	currentSelectedObject = null != gKey ? userDetailsData[gKey] : null;
	
	console.log(currentSelectedObject);
	
	console.log("---currentSelectedObject---"+currentSelectedObject);
	
	console.log("Begin--inside userDetailsModalData---");
	
	fillSelectDropDown('roleDescId',rolesArray, null != currentSelectedObject ? currentSelectedObject['roleName'] : "");
	
	$("#addUserDetailsTrigger").trigger( "click" );
	$('#addUserDetailsDiv').html($('#addUserDetailsForm'));
	
	$('#addUserDetailsDiv').find('#addUserDetailsForm').show();
	
	console.log("End--inside userDetailsModalData---");
}

function addUserRows(){
	var tBody=$('#show-role-details-id').find('tbody');
	var totalRows=$(tBody).find('tr').length;
	var nextRow=(totalRows+1);
	var html = "";
	html += '<tr id="row_'+nextRow+'" class="'+nextRow+'">';
	html += '<td id="sNo">'+nextRow+'</td>';
	html += '<td id="roleNameId"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="action" style="text-align: -webkit-center;">';
	html += '<a  id="userDetailsRowEditId" style="display:none;" href="#" onclick="userDetailsEdit('+nextRow+')"> <span class="glyphicon glyphicon-edit"></span></a>'; 
	html += '<span>&nbsp;</span>';
	html += '<a id="userDetailsRowSaveId" href="#" onclick="userDeatilsSave('+nextRow+')"> <span class="glyphicon glyphicon-check"></span></a>';
	html += '</td>';
	html += '</tr>';
	$(tBody).append(html);
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
			rolesArray.push(rolesNameObj['roleName']);
			rolesSelectHtml += '<option value="'+rolesNameObj['roleName']+'">'+rolesNameObj['roleName']+'</option>';
		}
		rolesSelectHtml += '</select>'
	}
}

function fetchRolesNamesListSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		$('#show-role-details-id').find('#tbody_id').html(populateUserDetails(serverData['SERVER_DATA']));
		$('#show-role-details-id').DataTable({
			info : false,
			"responsive" : true
		});
		var rolesNameObjList=serverData['SERVER_DATA'];
		rolesSelectHtml += '<select id="rolesListId" class="input-sm form-control">'
		for(var index in rolesNameObjList){
			var rolesNameObj=rolesNameObjList[index];
			rolesArray.push(rolesNameObj['roleName']);
			rolesSelectHtml += '<option value="'+rolesNameObj['roleName']+'">'+rolesNameObj['roleName']+'</option>';
		}
		rolesSelectHtml += '</select>'
	}
}