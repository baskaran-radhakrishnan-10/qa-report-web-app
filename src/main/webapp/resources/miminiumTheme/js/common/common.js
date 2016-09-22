function ajaxHandler(requestType,data,contentType,url,dataType,errorCallback,successCallback,async){
	console.log("url :"+url);
	$.ajax({
		type : requestType,
		contentType : contentType,
		url : url,
		data : data,
		dataType : dataType,
		async : async,
		success : function(data) {
			console.log("SUCCESS: ", data);
			if(typeof data != 'undefined' && null != data){
				if(null != data['REDIRECT_TO_LOGIN_PAGE'] && data['REDIRECT_TO_LOGIN_PAGE']){
					logoutMethod();
				}else {
					if(null != successCallback){
						successCallback(data);
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
	console.log(rootPathArray.length);
	if(rootPathArray.length > 5){
		for(var index in rootPathArray){
			if(index < 4){
				rootPath += rootPathArray[index] + "/";
			}
		}
	}else{
		rootPath=href.substring(0,href.lastIndexOf("/")+1);
	}
	console.log(rootPath);
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
