var projectDetailsData={};
var rowEle="";
var egKey="";

var createdOnDt = $.datepicker.formatDate('yy-mm-dd', new Date());
var currentUser=$('#loggedInUserId').val();
$(document).ready(function() {	
	
	showLoader();
	getProjectDetails();
	
	$('#addProjectDetailsForm').hide();
	$('#addProjectDetailsId').on("click" ,function (event){
		egKey="";
		projectDetailsModalData(null);
	});

	$('#show-project-details-id tbody').on('click', 'tr', function () {
		var gKey=$(this).attr('id');
		$('#show-project-details-id tbody tr').removeClass('selected');
		$('#show-project-details-id tr').css('background-color','');
		if (!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).css('background-color','#B0BED9 !important');
			projectDetailsEdit(gKey);
		}
	});
});

function showLoader(){
	$('#projectMainDiv').hide();
	$('#applyFilter').hide();
	$('#loader_div').show();
}

function hideLoader(){
	$('#projectMainDiv').show();
	$('#applyFilter').show();
	$('#loader_div').hide();
}

function getProjectDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"operation/getProjectList", 'json', null, fetchProjectDetailsSuccess,true);
}

function fetchProjectDetailsSuccess(serverData){
	$('#show-project-details-id').find('#tbody_id').html(populateProjectDetails(serverData['SERVER_DATA']));
	$('#show-project-details-id').DataTable({
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

function populateProjectDetails(entriesList){
	var sNo = 1;
	var htmlArray=new Array();
	for(var i=0;i<entriesList.length;i++){		
		var html ="";
		var projectDetailsModelAttribute=entriesList[i];
		var gKey=projectDetailsModelAttribute['projectId'];
		var projectName=projectDetailsModelAttribute['projectName'];
		var createdOn=getDateValue(projectDetailsModelAttribute['createdOn'],'yyyy-MM-dd',"-");
		var createdBy=(null!=projectDetailsModelAttribute['createdBy']?projectDetailsModelAttribute['createdBy']:"");
		var modifiedOn=getDateValue(projectDetailsModelAttribute['modifiedOn'],'yyyy-MM-dd',"-");
		var modifiedBy=(null!=projectDetailsModelAttribute['modifiedBy'] ? projectDetailsModelAttribute['modifiedBy']:"");
		projectDetailsData[gKey]=projectDetailsModelAttribute;
			html += '<tr id="'+gKey+'">' ;
			html += '<td>'+sNo+'</td>' ;
			html += '<td>'+projectName+'</td>' ;
			html +=	'<td>'+createdOn+'</td>' ;
			html += '<td>'+createdBy+'</td>' ;
			html += '<td>'+modifiedOn+'</td>' ;
			html += '<td>'+modifiedBy+'</td>' ;
			html += '</tr>' ;
			sNo++;

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
function projectDetailsEdit(gKey){
	egKey=gKey;
	projectDetailsModalData(gKey);
	
}

function projectDetailsModalData(gKey){
	var currentSelectedObject=null;
	currentSelectedObject = null != gKey ? projectDetailsData[gKey] : null;
	
	$('#projectNameId').val(null != currentSelectedObject ? currentSelectedObject['projectName'] : "");
	$('#createdDateId').val(null != currentSelectedObject ? getDateValue(currentSelectedObject['createdOn'],'yyyy-MM-dd',"-"):"");
	$('#createdById').val(null != currentSelectedObject ? currentSelectedObject['createdBy'] : "");
	$('#modifiedDateId').val(null != currentSelectedObject ? getDateValue(currentSelectedObject['modifiedOn'],'yyyy-MM-dd',"-"):"");
	$('#modifiedById').val(null != currentSelectedObject ? currentSelectedObject['modifiedBy'] : "");
	if(null==gKey || ""==gKey){
		$('#createdDateId').val(createdOnDt);
		$('#createdById').val(currentUser);
	}else if(null!=gKey || ""!=gKey){
		$('#modifiedDateId').val(createdOnDt);
		$('#modifiedById').val(currentUser);
	}
	$("#addProjectDetailsTrigger").trigger( "click" );
	$('#addProjectDetailsDiv').html($('#addProjectDetailsForm'));
	$('#addProjectDetailsDiv').find('#addProjectDetailsForm').show();
}

function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(selectedOption);
}


function addOrUpdateProject(){
	var isAdd=false;
	var isValid=false;
	var projectObject={};
	
	var rowEle=$('#addProjectDetailsDiv').find('form');
	
	var projectName=rowEle.find('#projectNameId').val();
	var createdDateId=rowEle.find('#createdDateId').val();
	var createdById=rowEle.find('#createdById').val();
	var modifiedDateId=rowEle.find('#modifiedDateId').val(); 
	var modifiedById=rowEle.find('#modifiedById').val(); 

	
	if (null ==projectName || ""== projectName  || projectName.length == 0 || typeof(projectName) == 'undefined'){
		var notifyObj={msg: '<b>Warning : </b> Please enter project name !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}else if(isProjectNameValid(projectName.trim())){
		var notifyObj={msg: '<b>Warning : </b> Please Enter valid project Name !!!',type: "warning",position: "center" };
		notif(notifyObj);
	}
	else{
		isValid=true;
	}
	if(egKey ==null || "" == egKey){
		isAdd=true;
		
	}
	else{
		projectObject['projectId']=egKey;
	}

	projectObject['projectName']=projectName.trim();
	projectObject['createdOn']=getDateValue(createdDateId,'yyyy-MM-dd',"-");
	projectObject['createdBy']=createdById;
	projectObject['modifiedOn']=getDateValue(modifiedDateId,'yyyy-MM-dd',"-");
	projectObject['modifiedBy']=modifiedById;
	
	if(isAdd && isValid){
		ajaxHandler("POST", JSON.stringify(projectObject), "application/json", getApplicationRootPath()+"operation/addProject", 'json', null, addProjectDetailsSuccess,true);
	
	}else if(isValid){
		ajaxHandler("POST", JSON.stringify(projectObject), "application/json", getApplicationRootPath()+"operation/updateProject", 'json', null,updateProjectDetailsSuccess,true);
	}
}

function addProjectDetailsSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	

		var notifyObj={msg: '<b>Success : </b> Project Details added Successfully !!!',type: "success",position: "center" };
		notif(notifyObj);
		
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"operation/manage_projects";
	}
}

function updateProjectDetailsSuccess(serverData){
	if('ERROR' != serverData['STATUS']){	
		var notifyObj={msg: '<b>Success : </b> Project Details Updated Successfully !!!',type: "success",position: "center" };
		notif(notifyObj);
		
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"operation/manage_projects";
	}
}
function isProjectNameValid(name){
	var regexp= /^[A-Za-z\-']+( [A-Za-z\-']+)*$/;
	return name.search(regexp);
}

/*function isProjectNameValid(name){
	var regexp = /^[a-z0-9\ \-]+$/;
	return name.search(regexp);
}*/