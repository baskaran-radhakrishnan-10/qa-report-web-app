var defaultSearchEntryCount=10;

var buildTestPlanData={};

var phaseArray=['FAT','SAT','LIVE','RLS','DEMO','INTERNAL','EXTERNAL'];

var planArray=['Automation Plan','Build Test Plan','Test Design Plan'];

var statusArray=['Completed','Deferred','In Progress','Planned','Abandoned'];

var itemDescriptionArray=[];

var resourcesArray=[];

var projectArray=[];

var selectedResourcesArray=[];

var resourceArraySelectHtml="";

var itemDescArraySelectHtml="";

var itemDetailsObject={};

$(document).ready(function() {
	
	console.log("documet.ready build test plan");
	
	$('#buildTestPlanForm').hide();
	
	$('#addTestPlanDataId').on("click" ,function (event){
		console.log("btp add button clicked");
		buildTestPlanModalData(null);
	});
	
	/*$('#addItemDetailRowButton').on("click" ,function(event){
		console.log("addItemDetailRowButton clicked!!!");
		addItemDetailsRows();
	})*/
	
	$('#exportTestPlanDataId').on("click" ,function (event){
		
	});
	
	$('#save_button').on("click" ,function(event){
		event.preventDefault();
		console.log("Save Button Clicked");
		addOrUpdateBtp();
	});
	
	fetchTestPlanEntries();
	
	fetchResourceNames();
	
	fetchProjectNames();
	
	fetchItemDescriptionList();

});

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

function fetchProjectNames(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"project/getUniqueProjectList", 'json', null, fetchProjectNamesSuccess,true);
}

function fetchProjectNamesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var projectNameObjList=serverData['SERVER_DATA'];
		for(var index in projectNameObjList){
			var projectNameObj=projectNameObjList[index];
			projectArray.push(projectNameObj['projectname']);
		}
	}
}

function fetchItemDetailsByBtpNo(btpNo){
	var data={};
	data['btpno']=btpNo;
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"item_details/getItemDetails", 'json', null, fetchItemDetailsByBtpNoSuccess,true);
}

function addItemDetailsRows(){
	var tBody=$('#itemDeatilsParentDivId').find('table').find('tbody');
	var totalRows=$(tBody).find('tr').length;
	var nextRow=(totalRows+1);
	var html = "";
	html += '<tr id="row_'+nextRow+'" class="'+nextRow+'">';
	html += '<td id="sNo">'+nextRow+'</td>';
	html += '<td id="itemDesc">'+itemDescArraySelectHtml+'</td>';
	html += '<td id="itemCount"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="effortCost"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="effortActual"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="status"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="remarks"><input type="text" class="input-sm form-control" value=""  /></td>';
	html += '<td id="action" style="text-align: -webkit-center;">';
	html += '<a  id="itemRowEditId" style="display:none;" href="#" onclick="itemDeatilsEdit('+nextRow+')"> <span class="glyphicon glyphicon-edit"></span></a>'; 
	html += '<span>&nbsp;</span>';
	html += '<a id="itemRowSaveId" href="#" onclick="itemDeatilsSave('+nextRow+')"> <span class="glyphicon glyphicon-check"></span></a>';
	html += '</td>';
	html += '</tr>';
	$(tBody).append(html);
}

function fetchItemDetailsByBtpNoSuccess(serverData){
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
			//html += '<span>&nbsp;</span>';
			//html += '<a id="itemRowDeleteId" href="#" onclick="itemDeatilsDelete('+sNo+')"> <span class="glyphicon glyphicon-trash"></span></a>';
			html += '</td>';
			html += '</tr>';
			itemDetailsHtmlArray.push(html);
			itemDetailsObject[sNo]=itemDetailObj;
		}
		$('#itemDeatilsParentDivId').find('table').find('tbody').html(itemDetailsHtmlArray);
		$('#itemDeatilsParentDivId').show();
	}
}

function itemDeatilsEdit(rowId){
	var rowEle=$('#itemDeatilsParentDivId').find('table').find('.'+rowId);
	$(rowEle).find('input').prop('disabled',false);
	var innerChildTagNmae=$(rowEle).find('#itemDesc')[0]['firstChild']['tagName'];
	var currentValue=$(rowEle).find('#itemDesc')[0]['firstChild']['value'];
	if('INPUT' == innerChildTagNmae){
		$(rowEle).find('#itemDesc').html(itemDescArraySelectHtml);
		$(rowEle).find('#itemDesc').find('select').val(currentValue);
	}
	$(rowEle).find('#action').find('#itemRowEditId').hide();
	$(rowEle).find('#action').find('#itemRowDeleteId').hide();
	$(rowEle).find('#action').find('#itemRowSaveId').show();
}

function itemDeatilsDelete(rowId){
	
}

function itemDeatilsSave(rowId){
	
	var btpObject=null;
	
	var isUpdate=true;
	
	var rowEle=$('#itemDeatilsParentDivId').find('table').find('.'+rowId);
	
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
	currentItemDetailsObj['estimatedEffort']=$(rowEle).find('#effortCost').find('input').val();
	currentItemDetailsObj['itemCount']=$(rowEle).find('#itemCount').find('input').val();
	currentItemDetailsObj['itemDescription']=$(rowEle).find('#itemDesc').find('#itemDescriptionListId').val();
	currentItemDetailsObj['itemStatus']=$(rowEle).find('#status').find('input').val();
	currentItemDetailsObj['itemRemarks']=$(rowEle).find('#remarks').find('input').val();
	
	$(rowEle).find('#action').find('#itemRowEditId').show();
	$(rowEle).find('#action').find('#itemRowDeleteId').show();
	$(rowEle).find('#action').find('#itemRowSaveId').hide();
	$(rowEle).find('#itemDesc').html("");
	$(rowEle).find('#itemDesc').append('<input type="text" class="input-sm form-control" value="'+currentItemDetailsObj['itemDescription']+'" disabled />');
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
		alert("Item Detail Row Added Successfully!!!")
	}
}

function itemDeatilsEditSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		alert("Item Detail Row Updated Successfully!!!")
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

function fetchTestPlanEntriesSuccess(serverData){
	console.log(serverData);
	$('#build_test_plan_table_id').find('#tbody_id').html(populateTestPlanEntries(serverData['BTP_ENTRIES']));
	$('#build_test_plan_table_id').DataTable({
		info : false,
		"responsive" : true
	});
}

function populateTestPlanEntries(entriesList){
	var sNo = 1;
	var htmlArray=new Array();
	for(var i=0;i<entriesList.length;i++){
		var html ="";
		var testPlanModelAttribute=entriesList[i];
		var gKey=testPlanModelAttribute['gKey'];
		var projectName=testPlanModelAttribute['projectName'];
		var btpPlan=testPlanModelAttribute['btpPlan'];
		var buildNo=testPlanModelAttribute['buildNo'];
		var btpStatus=testPlanModelAttribute['btpStatus'];
		var startDate=getDateValue(testPlanModelAttribute['startDate'],'dd/MM/yyyy',"/");
		var endDate=getDateValue(testPlanModelAttribute['endDate'],'dd/MM/yyyy',"/");
		var revisedEndDate=getDateValue(testPlanModelAttribute['revisedEndDate'],'dd/MM/yyyy',"/");
		buildTestPlanData[gKey]=testPlanModelAttribute;
		html += '<tr id="'+gKey+'">' ;
		html += '<td>'+sNo+'</td>' ;
		html += '<td>'+projectName+'</td>' ;
		html +=	'<td>'+btpPlan+'</td>' ;
		html += '<td>'+buildNo+'</td>' ;
		html += '<td>'+btpStatus+'</td>' ;
		html +=	'<td>'+startDate+'</td>' ;
		html += '<td>'+endDate+'</td>' ;
		html +=	'<td>'+revisedEndDate+'</td>' ;
		html += '<td id="testPlanEditRowId" onclick="buildTestPlanModalData('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span></td>' ;
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

function fetchTestPlanEntries(){
	var entryCount=parseInt($('#build_test_plan_table_id_wrapper').find('select').val());
	var data={};
	data['maxEntries']=entryCount;
	data=JSON.stringify(data);
	console.log(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"build_test_plan/getData", 'json', null, fetchTestPlanEntriesSuccess,true);
}

function buildTestPlanModalData(gKey){
	
	var currentSelectedObject=null;
	
	$('#selectedRowKeyInput').val(null != gKey ? gKey : "");
	
	currentSelectedObject = null != gKey ? buildTestPlanData[gKey] : null;
	
	selectedResourcesArray = null != currentSelectedObject ? constructResourceArray(currentSelectedObject) : [];
	
	fillSelectDropDown('projectId',projectArray, null != currentSelectedObject ? currentSelectedObject['projectName'] : "");
	
	fillSelectDropDown('phaseId',phaseArray, null != currentSelectedObject ? currentSelectedObject['phase'] : "");
	
	fillSelectDropDown('planId',planArray, null != currentSelectedObject ? currentSelectedObject['btpPlan'] : "");
	
	fillSelectDropDown('statusId',statusArray, null != currentSelectedObject ? currentSelectedObject['btpStatus'] : "");
	
	$('#cycleId').val( null != currentSelectedObject ? currentSelectedObject['cycle'] : "");
	
	$('#iteration_id').val( null != currentSelectedObject ? currentSelectedObject['sPrint'] : "");
	
	$('#buildNoId').val( null != currentSelectedObject ? currentSelectedObject['buildNo'] : "");
	
	$('#remarksId').val( null != currentSelectedObject ? currentSelectedObject['btpRemarks'] : "");
	
	$('#startDateId').val( null != currentSelectedObject ? getDateValue(currentSelectedObject['startDate'],'yyyy-MM-dd',"-") : "");
	
	$('#endDateId').val( null != currentSelectedObject ? getDateValue(currentSelectedObject['endDate'],'yyyy-MM-dd',"-") : "");
	
	$('#revisedEndDateId').val( null != currentSelectedObject ? getDateValue(currentSelectedObject['revisedEndDate'],'yyyy-MM-dd',"-") : "");
	
	constructResourceTable(selectedResourcesArray);
	
	$("#editBuildTestPlanTrigger").trigger( "click" );
	
	$('#buildTestPlanDiv').html($('#buildTestPlanForm'));
	
	$('#buildTestPlanDiv').find('#buildTestPlanForm').show();
	
	if(null == gKey){
		$('#itemDeatilsParentDivId').hide();
	}else{
		$('#itemDeatilsParentDivId').show();
	}
	
	if(null != gKey){
		fetchItemDetailsByBtpNo(gKey);
	}
	
}

function addOrUpdateBtp(){
	
	var btpObject={};
	
	var btpForm=$('#buildTestPlanDiv').find('form');
	
	var gKey=$('#selectedRowKeyInput').val();
	
	btpObject=gKey == "" ? {} : buildTestPlanData[gKey] ;
	btpObject['projectName']=btpForm.find('#projectId').val();
	btpObject['phase']=btpForm.find('#phaseId').val();
	btpObject['btpPlan']=btpForm.find('#planId').val();
	btpObject['btpStatus']=btpForm.find('#statusId').val();
	btpObject['cycle']=btpForm.find('#cycleId').val();
	btpObject['sPrint']=btpForm.find('#iteration_id').val();
	btpObject['buildNo']=btpForm.find('#buildNoId').val();
	btpObject['btpRemarks']=btpForm.find('#remarksId').val();
	btpObject['startDate']=btpForm.find('#startDateId').val();
	btpObject['endDate']=btpForm.find('#endDateId').val();
	btpObject['revisedEndDate']=btpForm.find('#revisedEndDateId').val();
	
	var objectKeys=Object.keys(btpObject);
	for(var index in objectKeys){
		var keyStr=objectKeys[index];
		if(keyStr.indexOf("resource") != -1){
			btpObject[keyStr]=null;
		}
	}
	
	btpForm.find('#resourceMgmtTableId').find('tr').each(function( index, element ) {
		if(null != element){
			var value=$(element).find('#resourceNameTDId')[0]['lastChild']['value'];
			btpObject['resource'+(index+1)]=value;
		}
	});
	
	btpObject['createdDate']=getDateValue(btpObject['createdDate'],'yyyy-MM-dd',"-");
	
	btpObject['updatesDate']=getDateValue(btpObject['updatesDate'],'yyyy-MM-dd',"-");
	
	
	if("" == gKey){
		ajaxHandler("POST", JSON.stringify(btpObject), "application/json", getApplicationRootPath()+"build_test_plan/addData", 'json', null, addBtpChangesSuccess,true);
	}else{
		ajaxHandler("POST", JSON.stringify(btpObject), "application/json", getApplicationRootPath()+"build_test_plan/updateData", 'json', null, updateBtpModifiedChangesSuccess,true);
	}
	
}

function updateBtpModifiedChangesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		alert('Update Successfully!!!');
		window.location.href=getApplicationRootPath()+"build_test_plan/show";
	}else {
		alert('Update Operation Failed!!!');
	}
}

function addBtpChangesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		alert('Added Successfully!!!');
		window.location.href=getApplicationRootPath()+"build_test_plan/show";
	}else {
		alert('Add Operation Failed!!!');
	}
}

function constructResourceArray(btpObject){
	var array=[];
	var objectKeys=Object.keys(btpObject);
	for(var index in objectKeys){
		var keyStr=objectKeys[index];
		if(keyStr.indexOf("resource") != -1){
			var value=btpObject[keyStr];
			if(null != value && value.length > 0){
				array.push(btpObject[keyStr]);
			}
		}
	}
	return array;
}

function constructResourceTable(resourceArray){
	var html="";
	
	if(null != resourceArray && resourceArray.length > 0){
		for(var index=0 ; index<resourceArray.length;index++ ){
			html += '<tr id="resourcetr_'+(index+1)+'">';
			if(index == 0){
				html += '<td id="resourceNameCaptionId"><label class="control-label">Resource'+(index+1)+':*</label></td>';
				html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)"><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value="'+resourceArray[index]+'"></td>';
				html += '<td style="text-align: -webkit-center;">';
				html += '<a onclick="addTableRow(this)" href="#"><span data-href="#" class="fa fa-plus" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Add Fields"></span></a>';
				html += '</td>';
			}else{
				html += '<td id="resourceNameCaptionId"><label class="control-label">Resource'+(index+1)+':</label></td>';
				html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)" ><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value="'+resourceArray[index]+'"></td>';
				html += '<td style="text-align: -webkit-center;">';
				html += '<a onclick="deleteTableRow(this)" href="#"><span class="fa fa-remove" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Remove Resource"></span></a></td>';
			}
			html += '</tr>';
		}
	}
	else{
		
		html += '<tr id="resourcetr_1">';
		html += '<td id="resourceNameCaptionId"><label class="control-label">Resource1:*</label></td>';
		html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)"><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value=""></td>';
		html += '<td style="text-align: -webkit-center;">';
		html += '<a onclick="addTableRow(this)" href="#"><span data-href="#" class="fa fa-plus" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Add Fields"></span></a>';
		html += '</td>';
		
	}
	
	$('#resourceMgmtTableId').find('tbody').html(html);
}

function addTableRow(thisObject){
	var html="";
	var resourceTrArray=$('#resourceMgmtTableId').find('tbody').find('tr');
	if(resourceTrArray.length <6){
		html += '<tr id="resourcetr_'+(resourceTrArray.length+1)+'">';
		html += '<td id="resourceNameCaptionId"><label class="control-label">Resource'+(resourceTrArray.length+1)+':*</label></td>';
		html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)"><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value=""></td>';
		html += '<td style="text-align: -webkit-center;">';
		html += '<a onclick="deleteTableRow(this)" href="#"><span class="fa fa-remove" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Remove Resource"></span></a>';
		html += '</td>';
		html += '</tr>';
		$('#resourceMgmtTableId').find('tbody').append(html);
	}else{
		alert("More than 6 resources not allowed per btp");
	}
}

function deleteTableRow(thisObject){
	var trElementId=$($(thisObject)[0]['parentNode'])[0]['parentNode']['id'];
	var resourceTrArray=$('#resourceMgmtTableId').find('tbody').find('tr');
	if(resourceTrArray.length > 1){
		var numId=parseInt(trElementId.replace('resourcetr_',''));
		var deletedRowId=numId;
		$('#resourceMgmtTableId').find('#'+trElementId).remove();
		while(numId <= 6){
			numId++;
			$('#resourceMgmtTableId').find('#resourcetr_'+numId).find('#resourceNameCaptionId').find('label').text('Resource'+(numId-1)+':');
			$('#resourceMgmtTableId').find('#resourcetr_'+numId).attr('id','resourcetr_'+(numId-1));
		}
	}else{
		alert("you can't delete primary resource for btp");
	}
}

function clickedResourceName(thisElement){
	
	var innerChildTagNmae=$(thisElement)[0]['firstChild']['tagName'];
	
	var resourceNameIndex=$(thisElement)[0]['parentElement']['id'].replace('resourcetr_','');
	
	if('INPUT' == innerChildTagNmae){
		$(thisElement).html(resourceArraySelectHtml);
		$(thisElement).find('select').val(selectedResourcesArray[resourceNameIndex-1]);
	}
	
}



function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(selectedOption);
}