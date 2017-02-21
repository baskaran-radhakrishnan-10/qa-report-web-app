function fetchItemDetailsByBtpNo(btpNo){
	var data={};
	data['btpno']=btpNo;
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"item_details/getItemDetails", 'json', null, fetchItemDetailsByBtpNoSuccess,true);
}

function addItemDetailsRows(){
	var tBody=$('#itemDeatilsParentDivId').find('table').find('tbody');
	var rowEle=$('#itemDeatilsParentDivId').find('table tbody').find('tr');
	if(!$(rowEle).hasClass('selected')){
		console.log(statusArraySelectHtml);
		var totalRows=$(tBody).find('tr').length;
		var nextRow=(totalRows+1);
		var html = "";
		html += '<tr id="row_'+nextRow+'" class="'+nextRow+'">';
		html += '<td id="sNo">'+nextRow+'</td>';
		html += '<td id="itemDesc">'+itemDescArraySelectHtml+'</td>';
		html += '<td id="itemCount"><input type="text" class="input-sm form-control" value=""  /></td>';
		html += '<td id="effortCost"><input type="text" class="input-sm form-control" value=""  /></td>';
		html += '<td id="effortActual"><input type="text" class="input-sm form-control" value=""  disabled /></td>';
		html += '<td id="status">'+statusArraySelectHtml+'</td>';
		html += '<td id="remarks"><input type="text" class="input-sm form-control" value=""  /></td>';
		html += '<td id="action" style="text-align: -webkit-center;">';
		html += '<a  id="itemRowEditId" style="display:none;" href="#" onclick="itemDeatilsEdit('+nextRow+')"> <span class="glyphicon glyphicon-edit"></span></a>'; 
		html += '<span>&nbsp;</span>';
		html += '<a id="itemRowSaveId" href="#" onclick="itemDeatilsSave('+nextRow+')"> <span class="glyphicon glyphicon-check"></span></a>';
		html += '<span>&nbsp;</span>';
		html += '<a id="itemRowRemoveId" href="#" onclick="itemDeatilsRemove('+nextRow+')"> <span class="glyphicon glyphicon-remove"></span></a>';
		html += '</td>';
		html += '</tr>';
		$(tBody).append(html);
	}
}

function itemDeatilsRemove(rowId){
	$('#itemDeatilsParentDivId').find('table tbody').find('.'+rowId).remove();
}

function fetchItemDetailsByBtpNoSuccess(serverData){
	console.log(serverData);
	if('ERROR' != serverData['STATUS']){
		var itemDetailsList=serverData['SERVER_DATA'];
		var itemDetailsHtmlArray=new Array();
		for(var index in itemDetailsList){
			var sNo=parseInt(index)+parseInt(1);
			var html = "";
			var itemDetailObj=itemDetailsList[index];
			html += '<tr id="row_'+itemDetailObj['itemNo']+'" class="'+sNo+'">';
			html += '<td id="sNo">'+sNo+'</td>';
			html += '<td id="itemDesc"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemDescription']+'" disabled /></td>';
			html += '<td id="itemCount"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemCount']+'" disabled /></td>';
			html += '<td id="effortCost"><input type="text" class="input-sm form-control" value="'+itemDetailObj['estimatedEffort']+'" disabled /></td>';
			html += '<td id="effortActual"><input type="text" class="input-sm form-control" value="'+itemDetailObj['actualEffort']+'" disabled /></td>';
			html += '<td id="status"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemStatus']+'" disabled /></td>';
			html += '<td id="remarks"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemRemarks']+'" disabled /></td>';
			html += '<td id="action" style="text-align: -webkit-center;">';
			html += '<a  id="itemRowEditId" href="#" onclick="itemDeatilsEdit('+sNo+')"> <span	class="glyphicon glyphicon-edit"></span></a>'; 
			html += '<span>&nbsp;</span>';
			html += '<a id="itemRowSaveId" style="display:none;" href="#" onclick="itemDeatilsSave('+sNo+')"> <span class="glyphicon glyphicon-check"></span></a>';
			if("ROLE_SUPER_ADMIN" == $('#loggedInRoleId').val()){
				html += '<span>&nbsp;</span>';
				html += '<a id="itemRowRemoveId" href="#" onclick="deleteItemDetails('+itemDetailObj['itemNo']+','+itemDetailObj['gKey']+')"> <span class="glyphicon glyphicon-trash"></span></a>';
			}
			html += '<span>&nbsp;</span>';
			html += '<a id="itemRowDeleteId" href="#" onclick="showResourceDetails('+sNo+')"> <span class="glyphicon glyphicon-link"></span></a>';
			html += '</td>';
			html += '</tr>';
			itemDetailsHtmlArray.push(html);
			itemDetailsObject[sNo]=itemDetailObj;
		}
		$('#itemDeatilsParentDivId').find('table').find('tbody').html(itemDetailsHtmlArray);
		$('#itemDeatilsParentDivId').show();
	}
}

function deleteItemDetails(itemNo,gKey){
	if("ROLE_SUPER_ADMIN" == $('#loggedInRoleId').val()){
		
		var btpNo = $('#selectedRowKeyInput').val();
		var data = {};
		data['btpNo'] = btpNo;
		data['itemNo'] = itemNo;
		data['gKey'] = gKey;
		ajaxHandler("POST", JSON.stringify(data), "application/json", getApplicationRootPath()+"item_details/deleteData", 'json', deleteItemDetailsError, deleteItemDetailsSuccess,true);
	}
}

function deleteItemDetailsSuccess(serverData,inputData){
	if('ERROR' != serverData['STATUS']){
		var notifyObj={msg: "<b>Success:</b> Selected Record Deleted Successfully !!!",type: "success",position: "center",autohide: true};
		notif(notifyObj);
		fetchItemDetailsByBtpNo(inputData['btpNo']);
	}
}

function deleteItemDetailsError(errorData){
	console.log(errorData);
	var notifyObj={msg: '<b>Error : </b> Operation Failed Due to Server Issue !!!',type: "error",position: "center" };
	notif(notifyObj);
}

function itemDeatilsEdit(rowId){
	var rowEle=$('#itemDeatilsParentDivId').find('table').find('.'+rowId);
	//check if resource details for particular item is selected
	if(!$(rowEle).hasClass('selected')){
		
		$(rowEle).find('input').prop('disabled',false);
		$(rowEle).find('#effortActual').find('input').prop('disabled',true);
		
		var innerChildTagNmae=$(rowEle).find('#itemDesc')[0]['firstChild']['tagName'];
		var currentValue=$(rowEle).find('#itemDesc')[0]['firstChild']['value'];
		if('INPUT' == innerChildTagNmae){
			$(rowEle).find('#itemDesc').html(itemDescArraySelectHtml);
			$(rowEle).find('#itemDesc').find('select').val(currentValue);
		}
		
		innerChildTagNmae=$(rowEle).find('#status')[0]['firstChild']['tagName'];
		currentValue=$(rowEle).find('#status')[0]['firstChild']['value'];
		if('INPUT' == innerChildTagNmae){
			$(rowEle).find('#status').html(statusArraySelectHtml);
			$(rowEle).find('#status').find('select').val(currentValue);
		}
		
		$(rowEle).find('#action').find('#itemRowEditId').hide();
		$(rowEle).find('#action').find('#itemRowDeleteId').hide();
		$(rowEle).find('#action').find('#itemRowSaveId').show();
	}
}



function itemDeatilsSave(rowId){
	
	var btpObject=null;
	
	var isUpdate=true;
	
	var rowEle=$('#itemDeatilsParentDivId').find('table').find('.'+rowId);
	
	var effortCost = $(rowEle).find('#effortCost').find('input').val();
	
	var itemCount = $(rowEle).find('#itemCount').find('input').val();
	
	$(rowEle).find('td input').removeClass('error');
	
	if(!$.isNumeric(effortCost)){
		$(rowEle).find('#effortCost').find('input').addClass('error');
	}
	
	if(!$.isNumeric(itemCount)){
		$(rowEle).find('#itemCount').find('input').addClass('error');
	}
	
	if($(rowEle).find('td input').hasClass('error')){
		return false;
	}
	
	var gKey = $('#selectedRowKeyInput').val();
	
	btpObject = null != gKey ? buildTestPlanData[gKey] : null;
	
	var currentItemDetailsObj=itemDetailsObject[rowId];
	
	if(null == currentItemDetailsObj){
		isUpdate=false;
		currentItemDetailsObj={};
	}
	
	currentItemDetailsObj['btpNo']=btpObject;
	
	currentItemDetailsObj['btpNo']['createdDate']=getDateValue(currentItemDetailsObj['btpNo']['createdDate'],'yyyy-MM-dd',"-");
	currentItemDetailsObj['btpNo']['endDate']=getDateValue(currentItemDetailsObj['btpNo']['endDate'],'yyyy-MM-dd',"-");
	currentItemDetailsObj['btpNo']['revisedEndDate']=getDateValue(currentItemDetailsObj['btpNo']['revisedEndDate'],'yyyy-MM-dd',"-");
	currentItemDetailsObj['btpNo']['startDate']=getDateValue(currentItemDetailsObj['btpNo']['startDate'],'yyyy-MM-dd',"-");
	currentItemDetailsObj['btpNo']['updatesDate']=getDateValue(currentItemDetailsObj['btpNo']['updatesDate'],'yyyy-MM-dd',"-");
	
	currentItemDetailsObj['actualEffort']=$(rowEle).find('#effortActual').find('input').val();
	currentItemDetailsObj['estimatedEffort']=effortCost;
	currentItemDetailsObj['itemCount']=itemCount;
	currentItemDetailsObj['itemDescription']=$(rowEle).find('#itemDesc :input').val();
	currentItemDetailsObj['itemStatus']=$(rowEle).find('#status :input').val();
	currentItemDetailsObj['itemRemarks']=$(rowEle).find('#remarks').find('input').val();
	
	$(rowEle).find('#action').find('#itemRowEditId').show();
	$(rowEle).find('#action').find('#itemRowDeleteId').show();
	$(rowEle).find('#action').find('#itemRowSaveId').hide();
	$(rowEle).find('#itemDesc').html("");
	$(rowEle).find('#itemDesc').append('<input type="text" class="input-sm form-control" value="'+currentItemDetailsObj['itemDescription']+'" disabled />');
	$(rowEle).find('#status').html("");
	$(rowEle).find('#status').append('<input type="text" class="input-sm form-control" value="'+currentItemDetailsObj['itemStatus']+'" disabled />');
	$(rowEle).find('input').prop('disabled',true);
	
	if(!isUpdate){
		ajaxHandler("POST", JSON.stringify(currentItemDetailsObj), "application/json", getApplicationRootPath()+"item_details/addItemDetails", 'json', null, itemDeatilsSaveSuccess,true);
	}else{
		ajaxHandler("POST", JSON.stringify(currentItemDetailsObj), "application/json", getApplicationRootPath()+"item_details/updateItemDetails", 'json', null, itemDeatilsEditSuccess,true);
	}
	
}

function itemDeatilsSaveSuccess(serverData,inputData){
	if('ERROR' != serverData['STATUS']){
		var gKey=serverData['SERVER_DATA'];
		inputData['gKey']=gKey;
		var size=(Object.keys(itemDetailsObject).length)+1;
		itemDetailsObject[size]=inputData;
		var notifyObj={msg: '<b>Success</b> Row No '+inputData['gKey']+' Saved Successfully !!!',type: "success",position: "center"};
		notif(notifyObj);
	}
}

function itemDeatilsEditSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var notifyObj={msg: '<b>Success</b> Row Updated Successfully !!!',type: "success",position: "center"};
		notif(notifyObj);
	}
}

function fetchItemDescriptionList(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"item_details/getUniqueItemDesc", 'json', null, fetchItemDescriptionListSuccess,true);
}

function fetchItemDescriptionListSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var itemDescNameList=serverData['SERVER_DATA'];
		itemDescArraySelectHtml += '<select id="itemDescriptionListId" name="itemDescriptionList" class="input-sm form-control">';
		for(var index in itemDescNameList){
			var itemDescNameObj=itemDescNameList[index];
			itemDescriptionArray.push(itemDescNameObj['itemdescription']);
			itemDescArraySelectHtml += '<option value="'+itemDescNameObj['itemdescription']+'">'+itemDescNameObj['itemdescription']+'</option>';
		}
		itemDescArraySelectHtml += '</select>';
	}
}