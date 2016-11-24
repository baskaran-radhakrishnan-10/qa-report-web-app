var userDetailsData={};
var rolesArray=[];
var rolesSelectHtml="";
var rolesNameObj="";
var roleListObject={};
var rowEle="";
var roleStatus=[true,false];
var egKey="";

var currentDate = $.datepicker.formatDate('yy-mm-dd', new Date());
var currentUser=$('#loggedInUserId').val();
var date = new Date();
var currentTime=(date.getHours()<10 ? "0"+date.getHours() : date.getHours())+":"+(date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes());
currentTime=parseInt(currentTime.replace(":",""));
$(document).ready(function() {	
	showLoader();
	getReminderDetails();
	getKTDetails();
	fetchTestPlanEntries();
	getUserDetails();
	
});

function showLoader(){
	$('#homeMainDiv').hide();
	$('#homeLoader_div').show();
}

function hideLoader(){
	$('#homeMainDiv').show();
	$('#homeLoader_div').hide();
}
function getUserDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getUserDetails", 'json', null, fetchUserDetailsSuccess,true);
}

function fetchUserDetailsSuccess(serverData){
	$('#hUsersId').html(populateUserDetails(serverData['SERVER_DATA']));
	}
function getKTDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"kt_plan/getKTPlanDetails", 'json', null, fetchKTDetailsSuccess,true);
}
function fetchKTDetailsSuccess(serverData){
$('#hKtPlanId').html(populateKTDetails(serverData['SERVER_DATA']));
$('#hOrdersId').html(populateKTDetails(serverData['SERVER_DATA']));
setTimeout(function(){
	hideLoader();
}, 1000);
}

function fetchTestPlanEntries(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"build_test_plan/getData", 'json', null, fetchTestPlanEntriesSuccess,true);
}

function fetchTestPlanEntriesSuccess(serverData){
	$('#hBtpId').html(populateBTPDetails(serverData['BTP_ENTRIES']));
}
function getReminderDetails(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"operation/getRemainderDetails", 'json', null, fetchReminderDetailsSuccess,true);
}

function fetchReminderDetailsSuccess(serverData){
	$('#remaindersTodayId').html(populateReminderDetails(serverData['SERVER_DATA']));
}

function populateReminderDetails(entriesList){
	var htmlArray=new Array();
	var html ="";
	for(var i=0;i<entriesList.length;i++){		
		var reminderDetailsModelAttribute=entriesList[i];
//		var gKey=reminderDetailsModelAttribute['gkey'];
		var remainderDate=getDateValue(reminderDetailsModelAttribute['remainderDate'],'yyyy-MM-dd',"-");
		var remainderTime=reminderDetailsModelAttribute['remainderTime'];
//		var remainderTimeVal=parseInt(remainderTime.replace(":",""));
		var remainderMessage=reminderDetailsModelAttribute['remainderMessage'];
		var remainderUser=reminderDetailsModelAttribute['remainderUser'];
			if(currentDate==remainderDate && currentUser==remainderUser && currentTime<=parseInt(remainderTime.replace(":",""))){
				html += '<h4> <span class="glyphicon glyphicon-hand-right" style="color:blue"></span>'+" "+ remainderMessage+' Today @ '+remainderTime+'</h4>';
			}
	}
	if(null == html | ""==html){
		html += '<h4> <span class="glyphicon glyphicon-hand-right" style="color:blue"></span> No Reminders Now !</h4>';
	}
	htmlArray.push(html);
	return htmlArray;
}

function populateKTDetails(entriesList){
	var htmlArray=new Array();
	var html ="";
	var kt=0;
	var rKt=0;
	var gKt=0;
 for (var i=0; i<entriesList.length;i++){
	 var ktPlan=entriesList[i];
	 var ktSession=ktPlan['session']
	 if(ktSession=="KT"){
		 kt++;
	 }else if(ktSession=="Reverse KT"){
		 rKt++;
	 }else if(ktSession=="General"){
		 gKt++;
	 }
 }
//	html += '<h5> <span class="glyphicon glyphicon-hand-right" style="color:blue"></span>'+" "+" Count is : "+entriesList.length+'</h5>';

	
 html+='<table align="center">';
 html+='<tr>';
 html+='<td>General</td>';
 html+='<td>'+gKt+'</td>';
 html+='</tr>';
 html+='<tr>';
 html+='<td>KT</td>';
 html+=' <td>'+kt+'</td>';
 html+='</tr>';
 html+='<tr>';
 html+='<td>Reverse KT</td>';
 html+='<td>'+rKt+'</td>';
 html+='</tr>';
 html+='<tr>';
 html+='<td><b><font color="black">Total KT</font></b></td>';
 html+='<td><b><font color="black">'+entriesList.length+'</font></b></td>';
 html+='</tr>';
 html+='</table>';
	htmlArray.push(html);
	return htmlArray;
}

function populateBTPDetails(entriesList){
	var htmlArray=new Array();
	
	var html ="";
	

	var ipCount=0;
	var dCount=0;
	var pCount=0;
	var aCount=0;
	var cCount=0;
  
	
	for(var i=0;i<entriesList.length;i++){
		var btpDetailsModelAttribute=entriesList[i];
		var btpStatus=btpDetailsModelAttribute['btpStatus'];
	if(btpStatus=="In Progress"){
		ipCount++;
	}else if(btpStatus=="Deferred"){
		dCount++;
	}else if(btpStatus=="Planned"){
		pCount++;
	}else if(btpStatus=="Abandoned"){
		aCount++;
	}else if(btpStatus=="Completed"){
		cCount++;
	}
	}
	
	  html+='<table align="center">';
	  html+='<tr>';
	  html+='<td>In Progress</td>';
	  html+='<td>'+ipCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td>Deferred</td>';
	  html+=' <td>'+dCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td>Planned</td>';
	  html+='<td>'+pCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td>Abandoned</td>';
	  html+='<td>'+aCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td>Completed</td>';
	  html+=' <td>'+cCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td><b><font color="black">Total Plan</font></b></td>';
	  html+='<td><b><font color="black">'+entriesList.length+'</font></b></td>';
	  html+='</tr>';
	  html+='</table>';
	  
	//html += '<h4> <span class="glyphicon glyphicon-hand-right" style="color:blue"></span>'+" "+" Count is : "+entriesList.length+'</h4>';
	htmlArray.push(html);

	return htmlArray;
}

function populateUserDetails(entriesList){
	var htmlArray=new Array();
	var html ="";
	var adminCount=0;
	var superAdminCount=0;
	var normalUserCount=0;
	for (var i=0; i<entriesList.length; i++){
		var userObject=entriesList[i];
		var rolesObject= userObject['roleId'];
		var roleName=rolesObject['roleName'];
		if(roleName=="ROLE_ADMIN"){
			adminCount++;
		}else if(roleName=="ROLE_SUPER_ADMIN"){
			superAdminCount++;
		}else if(roleName=="ROLE_NORMAL_USER"){
			normalUserCount++;
		}
	}

	  html+='<table align="center">';
	  html+='<tr>';
	  html+='<td>Super Admin</td>';
	  html+='<td>'+superAdminCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td>Admin</td>';
	  html+=' <td>'+adminCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td>User</td>';
	  html+='<td>'+normalUserCount+'</td>';
	  html+='</tr>';
	  html+='<tr>';
	  html+='<td><b><font color="black">Total User</font></b></td>';
	  html+='<td><b><font color="black">'+entriesList.length+'</font></b></td>';
	  html+='</tr>';
	  html+='</table>';
	htmlArray.push(html);
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