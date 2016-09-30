function SessionStorage(){}

var sessionStorageObj=null;

SessionStorage.prototype.setItem=function(key,value){
	sessionStorage.setItem(key,JSON.stringify(value));
}

SessionStorage.prototype.getItem=function(key){
	if(null != sessionStorage.getItem(key)){
		return JSON.parse(sessionStorage.getItem(key));
	}
	return null;
}

SessionStorage.prototype.removeItem=function(key,value){
	sessionStorage.removeItem(key);
}

var sessionStorageObj=null;

$(document).ready(function() {
	console.log("common js document ready");
	if (storageAvailable('sessionStorage')) {
		sessionStorageObj=new SessionStorage();
	}
});

function storageAvailable(type) {
	try {
		var storage = window[type],
			x = '__storage_test__';
		storage.setItem(x, x);
		storage.removeItem(x);
		return true;
	}
	catch(e) {
		return false;
	}
}

function ajaxHandler(requestType,data,contentType,url,dataType,errorCallback,successCallback,async){
	console.log("url :"+url);
	$.ajax({
		type : requestType,
		contentType : contentType,
		url : url,
		data : data,
		dataType : dataType,
		async : async,
		success : function(serverData) {
			console.log("SUCCESS : ", serverData);
			if(typeof serverData != 'undefined' && null != serverData){
				if(null != serverData['REDIRECT_TO_LOGIN_PAGE'] && serverData['REDIRECT_TO_LOGIN_PAGE']){
					logoutMethod();
				}else {
					if(null != successCallback){
						successCallback(serverData,JSON.parse(data));
					}
				}
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			if(typeof e != 'undefined' && null != e){
				if(null != errorCallback){
					errorCallback(e);
				}
			}
		}
	});
}

	
function getApplicationRootPath(){
	var href=window.location.href;
	var rootPathArray=href.split("/");
	var rootPath = "";
	if(rootPathArray.length > 5){
		for(var index in rootPathArray){
			if(index < 4){
				rootPath += rootPathArray[index] + "/";
			}
		}
	}else{
		rootPath=href.substring(0,href.lastIndexOf("/")+1);
	}
	return rootPath;
}

function getCurrentServerTimeSuccess(data){
	currentServerTime=data['SERVER_TIME'];
}

function getDifferenceBetweenDate(postedDate,currentDate){
	var diffDays = Math.round(Math.abs((postedDate.getTime() - currentDate.getTime())/(24*60*60*1000)));
	return diffDays;
}

function logoutMethod(){
	window.location.href=getApplicationRootPath()+"logout";
}

function constructModalDialogBox(isCloseButtonNeed,modalTitle,modalBodyContent,buttonArrayObj){
	var modalHtml='';
	modalHtml += '<div class="modal-header">';
	if(isCloseButtonNeed){
		modalHtml += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">(x)</button>';
	}
	modalHtml += '<h2 class="modal-title">'+modalTitle+'</h2>';
	modalHtml += '</div>';
	modalHtml += '<div class="modal-body">';
	modalHtml += '<p style="font-size: 0.95em;">'+modalBodyContent+'</p>';
	modalHtml += '</div>';
	modalHtml += '<div class="modal-footer">';
	for(var index in buttonArrayObj){
		var buttonObject=buttonArrayObj[index];
		var buttonClass=buttonObject['BUTTON_IS_PRIMARY'] ? 'btn btn-primary' : 'btn btn-default';
		var buttonClickFunction=buttonObject['FUNCTION'];
		modalHtml += '<button type="button" id="'+buttonObject['BUTTON_ID']+'" class="'+buttonClass+'" data-dismiss="modal" onclick="modalMethodExecution('+buttonClickFunction+')">'+buttonObject['BUTTON_NAME']+'</button>';
	}
	modalHtml += '</div>';
	return modalHtml;
}

function modalMethodExecution(method){
	method();
}

function isValidEmail(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9])+$/;
	return regex.test(email);
}
