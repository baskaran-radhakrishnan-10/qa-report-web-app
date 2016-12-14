var userDetailsData={};
var rolesArray=[];
var rolesSelectHtml="";

$(document).ready(function() {	
	showLoader();
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
		var roleDesc=userDetailsModelAttribute['roleDesc'];
		userDetailsData[gKey]=userDetailsModelAttribute;
		html += '<tr id="'+gKey+'">' ;
		html += '<td>'+sNo+'</td>' ;
		html += '<td>'+roleName+'</td>' ;
		html += '<td>'+roleDesc+'</td>' ;
//		html += '<td id="userDetailsEditRowId" onclick="roleDetailsModalData('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span></td>' ;
		html += '</tr>' ;
		htmlArray.push(html);
		sNo++;
	}
	return htmlArray;
}

function getRolesDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getRolesList", 'json', null, fetchRolesNamesListSuccess,true);
}

function fetchRolesNamesListSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		$('#show-role-details-id').find('#tbody_id').html(populateUserDetails(serverData['SERVER_DATA']));
		$('#show-role-details-id').DataTable({
			info : true,
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
	setTimeout(function(){
		hideLoader();
	}, 1000);
}

function showLoader(){
	$('#rolesMainDiv').hide();
	$('#loader_div').show();
}

function hideLoader(){
	$('#rolesMainDiv').show();
	$('#loader_div').hide();
}