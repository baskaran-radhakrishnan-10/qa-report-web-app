function fetchResourceNames(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"resource_details/getUniqueResources", 'json', null, fetchResourceNamesSuccess,true);
}

function fetchResourceNamesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var resourceNameObjList=serverData['SERVER_DATA'];
		resourceArraySelectHtml += '<select id="resourceNameListId" name="resourceNameList" class="input-sm form-control">';
		for(var index in resourceNameObjList){
			var resourceNameObj=resourceNameObjList[index];
			resourcesArray.push(resourceNameObj['resourcename']);
			resourceArraySelectHtml += '<option value="'+resourceNameObj['resourcename']+'">'+resourceNameObj['resourcename']+'</option>';
		}
		resourceArraySelectHtml += '</select>';
	}
}

function showResourceDetails(rowId){
	var rowEle=$('#itemDeatilsParentDivId').find('table').find('.'+rowId);
	if(!$(rowEle).hasClass('selected')){
		$('#itemDeatilsParentDivId').find('table').find('.'+rowId).addClass('selected');
		var btpNo = $('#selectedRowKeyInput').val();
		var currentItemDetailsObj=itemDetailsObject[rowId];
		var itemNo=currentItemDetailsObj['itemNo'];
		$('#selectedItemNo').val(rowId);
		fetchResourceByBtpItemNo(btpNo,itemNo);
	}
}

function fetchResourceByBtpItemNo(btpNo,itemNo){
	var data={};
	data['btpNo']=btpNo;
	data['itemNo']=itemNo;
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"resource_details/getResourceDetails", 'json', null, fetchResourceByBtpItemNoSuccess,true);
}

function fetchResourceByBtpItemNoSuccess(serverData,inputData){
	if('ERROR' != serverData['STATUS']){
		resourceDeatilsObject={};
		var resourceObjList=serverData['SERVER_DATA'];
		console.log(inputData);
		var rowEleArray=$('#itemDeatilsParentDivId').find('table').find('tbody').find('tr');
		console.log($(rowEleArray));
		rowEleArray.filter('.selected').show();
		rowEleArray.not('.selected').hide();
		$('#resourceDeatilsParentDivId').show();
		var htmlArray=new Array();
		for(var index in resourceObjList){
			var html = "";
			var resourceObj=resourceObjList[index];
			console.log(resourceObj);
			var sNo=parseInt(index)+parseInt(1);
			html += '<tr id="row_'+resourceObj['itemNo']+'" class="'+sNo+'">';
			html += '<td id="sNo">'+sNo+'</td>';
			html += '<td id="resourceName"><input type="text" class="input-sm form-control" value="'+resourceObj['resourceName']+'" disabled /></td>';
			html += '<td id="itemCount"><input type="text" class="input-sm form-control" value="'+resourceObj['itemCount']+'" disabled /></td>';
			html += '<td id="actualTime"><input type="text" class="input-sm form-control" value="'+resourceObj['actTime']+'" disabled /></td>';
			html += '<td id="bugsLogged"><input type="text" class="input-sm form-control" value="'+resourceObj['bugsLogged']+'" disabled /></td>';
			html += '<td id="pass"><input type="text" class="input-sm form-control" value="'+resourceObj['pass']+'" disabled /></td>';
			html += '<td id="fail"><input type="text" class="input-sm form-control" value="'+resourceObj['fail']+'" disabled /></td>';
			html += '<td id="clarification"><input type="text" class="input-sm form-control" value="'+resourceObj['clarification']+'" disabled /></td>';
			html += '<td id="unableToSet"><input type="text" class="input-sm form-control" value="'+resourceObj['unableToSet']+'" disabled /></td>';
			html += '<td id="pending"><input type="text" class="input-sm form-control" value="'+resourceObj['pending']+'" disabled /></td>';
			html += '<td id="blocked"><input type="text" class="input-sm form-control" value="'+resourceObj['blocked']+'" disabled /></td>';
			html += '<td id="action" style="text-align: -webkit-center;">';
			html += '<a  id="resourceRowEditId" href="#" onclick="resourceDeatilsEdit('+sNo+')"> <span	class="glyphicon glyphicon-edit"></span></a>'; 
			html += '<span>&nbsp;</span>';
			html += '<a id="resourceRowSaveId" style="display:none;" href="#" onclick="resourceDeatilsSave('+sNo+')"> <span class="glyphicon glyphicon-check"></span></a>';
			html += '</td>';
			html += '</tr>';
			htmlArray.push(html);
			resourceDeatilsObject[sNo]=resourceObj;
		}
		$('#resourceDeatilsParentDivId').find('tbody').html(htmlArray);
	}
}

function resourceDeatilsEdit(rowId){
	var rowEle=$('#resourceDeatilsParentDivId').find('table tbody').find('.'+rowId);
	$(rowEle).find('input').prop('disabled',false);
	$(rowEle).find('#resourceName').find('input').prop('disabled',true);
	/*var innerChildTagNmae=$(rowEle).find('#resourceName')[0]['firstChild']['tagName'];
	var currentValue=$(rowEle).find('#resourceName')[0]['firstChild']['value'];
	if('INPUT' == innerChildTagNmae){
		$(rowEle).find('#resourceName').html(resourceArraySelectHtml);
		$(rowEle).find('#resourceName').find('select').val(currentValue);
	}*/
	$(rowEle).find('#action').find('#resourceRowEditId').hide();
	$(rowEle).find('#action').find('#resourceRowSaveId').show();
	//$(rowEle).find('#action').find('#resourceRowDeleteId').hide();
}

function addResourceDetRows(){
	var tBody=$('#resourceDeatilsParentDivId').find('table tbody');
	var rowEle=$(tBody).find('tr');
	var totalRows=$(rowEle).length;
	var nextRow=(totalRows+1);
	var html = "";
	html += '<tr id="row_'+nextRow+'" class="'+nextRow+'">';
	html += '<td id="sNo">'+nextRow+'</td>';
	html += '<td id="resourceName">'+resourceArraySelectHtml+'</td>';
	html += '<td id="itemCount"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="actualTime"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="bugsLogged"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="pass"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="fail"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="clarification"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="unableToSet"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="pending"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="blocked"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="action" style="text-align: -webkit-center;">';
	html += '<a  id="resourceRowEditId" style="display:none;" href="#" onclick="resourceDeatilsEdit('+nextRow+')"> <span	class="glyphicon glyphicon-edit"></span></a>'; 
	html += '<span>&nbsp;</span>';
	html += '<a id="resourceRowSaveId" href="#" onclick="resourceDeatilsSave('+nextRow+')"> <span class="glyphicon glyphicon-check"></span></a>';
	html += '</td>';
	html += '</tr>';
	$(tBody).append(html);
}

function resourceDeatilsSave(rowId){
	
	var btpObject=null;
	
	var isUpdate=true;
	
	var rowEle=$('#resourceDeatilsParentDivId').find('table tbody').find('.'+rowId);
	
	var gKey = $('#selectedRowKeyInput').val();
	
	var itemSNo = $('#selectedItemNo').val();
	
	var currentItemDetailsObj=itemDetailsObject[itemSNo];
	
	console.log(currentItemDetailsObj);
	
	var itemNo=currentItemDetailsObj['itemNo'];
	
	var itemName=currentItemDetailsObj['itemDescription'];
	
	btpObject = null != gKey ? buildTestPlanData[gKey] : null;
	
	var currentResourceDetObj=resourceDeatilsObject[rowId];
	
	if(null == currentResourceDetObj){
		isUpdate=false;
		currentResourceDetObj={};
	}
	
	currentResourceDetObj['btpNo']=btpObject;
	
	currentResourceDetObj['btpNo']['createdDate']=getDateValue(currentResourceDetObj['btpNo']['createdDate'],'yyyy-MM-dd',"-");
	currentResourceDetObj['btpNo']['endDate']=getDateValue(currentResourceDetObj['btpNo']['endDate'],'yyyy-MM-dd',"-");
	currentResourceDetObj['btpNo']['revisedEndDate']=getDateValue(currentResourceDetObj['btpNo']['revisedEndDate'],'yyyy-MM-dd',"-");
	currentResourceDetObj['btpNo']['startDate']=getDateValue(currentResourceDetObj['btpNo']['startDate'],'yyyy-MM-dd',"-");
	currentResourceDetObj['btpNo']['updatesDate']=getDateValue(currentResourceDetObj['btpNo']['updatesDate'],'yyyy-MM-dd',"-");
	
	currentResourceDetObj['actTime']=$(rowEle).find('#actualTime').find('input').val();
	currentResourceDetObj['blocked']=$(rowEle).find('#blocked').find('input').val();
	currentResourceDetObj['bugsLogged']=$(rowEle).find('#bugsLogged').find('input').val();
	currentResourceDetObj['resourceName']=$(rowEle).find('#resourceName :input').val();
	currentResourceDetObj['clarification']=$(rowEle).find('#clarification').find('input').val();
	currentResourceDetObj['fail']=$(rowEle).find('#fail').find('input').val();
	
	currentResourceDetObj['itemCount']=$(rowEle).find('#itemCount').find('input').val();
	
	if(!isUpdate){
		currentResourceDetObj['itemName']=itemName;
		currentResourceDetObj['itemNo']=itemNo;
	}
	currentResourceDetObj['pass']=$(rowEle).find('#pass').find('input').val();
	currentResourceDetObj['pending']=$(rowEle).find('#pending').find('input').val();
	currentResourceDetObj['unableToSet']=$(rowEle).find('#unableToSet').find('input').val();
	
	$(rowEle).find('#action').find('#resourceRowEditId').show();
	//$(rowEle).find('#action').find('#itemRowDeleteId').show();
	$(rowEle).find('#action').find('#resourceRowSaveId').hide();
	$(rowEle).find('#resourceName').html("");
	$(rowEle).find('#resourceName').append('<input type="text" class="input-sm form-control" value="'+currentResourceDetObj['resourceName']+'" disabled />');
	$(rowEle).find('input').prop('disabled',true);
	
	console.log(currentResourceDetObj);
	
	if(!isUpdate){
		ajaxHandler("POST", JSON.stringify(currentResourceDetObj), "application/json", getApplicationRootPath()+"resource_details/addResourceDetails", 'json', null, resourceDeatilsSaveSuccess,true);
	}else{
		ajaxHandler("POST", JSON.stringify(currentResourceDetObj), "application/json", getApplicationRootPath()+"resource_details/updateResourceDetails", 'json', null, resourceDeatilsEditSuccess,true);
	}
}

function resourceDeatilsSaveSuccess(serverData){
	var notifyObj={msg: '<b>Success</b> Row Saved Successfully !!!',type: "success",position: "center"};
	notif(notifyObj);
}

function resourceDeatilsEditSuccess(serverData,inputData){
	var notifyObj={msg: '<b>Success</b> Row Updated Successfully !!!',type: "success",position: "center"};
	notif(notifyObj);
}


function backToItemDeatils(){
	var rowEleArray=$('#itemDeatilsParentDivId').find('table').find('tbody').find('tr');
	$(rowEleArray).removeClass('selected');
	$(rowEleArray).show();
	$('#resourceDeatilsParentDivId').hide();
}

