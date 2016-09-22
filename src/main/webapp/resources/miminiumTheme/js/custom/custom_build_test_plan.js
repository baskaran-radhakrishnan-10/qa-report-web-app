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

$(document).ready(function() {
	
	console.log("documet.ready build test plan");
	
	$('#buildTestPlanForm').hide();
	
	$('#addTestPlanDataId').on("click" ,function (event){
		
	});
	
	$('#exportTestPlanDataId').on("click" ,function (event){
		
	});
	
	$('#itemRowEditId').on("click" ,function(event){
		console.log("Item Row Edit Button Clicked");
	});
	
	$('#resourceNameTD').on("click" ,function(event){
		console.log("on click resource name td");
	})
	
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

function fetchItemDetailsByBtpNoSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var itemDetailsList=serverData['SERVER_DATA'];
		var itemDetailsHtmlArray=new Array();
		for(var index in itemDetailsList){
			var sNo=parseInt(index)+parseInt(1);
			var html = "";
			var itemDetailObj=itemDetailsList[index];
			html += '<tr id="row_'+itemDetailObj['itemNo']+'">';
			html += '<td id="sNo">'+sNo+'</td>';
			html += '<td id="itemDesc"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemDescription']+'" disabled /></td>';
			html += '<td id="itemCount"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemCount']+'" disabled /></td>';
			html += '<td id="effortCost"><input type="text" class="input-sm form-control" value="'+itemDetailObj['estimatedEffort']+'" disabled /></td>';
			html += '<td id="effortActual"><input type="text" class="input-sm form-control" value="'+itemDetailObj['actualEffort']+'" disabled /></td>';
			html += '<td id="status"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemStatus']+'" disabled /></td>';
			html += '<td id="remarks"><input type="text" class="input-sm form-control" value="'+itemDetailObj['itemRemarks']+'" disabled /></td>';
			html += '<td style="text-align: -webkit-center;">';
			html += '<a id="itemRowEditId" href="#" onclick="itemDeatilsEdit(this)"> <span	class="glyphicon glyphicon-edit"></span></a>'; 
			html += '<span>&nbsp;</span>';
			//html += '<a id="itemRowSaveId" style="display:none;" href="#"> <span class="glyphicon glyphicon-check"></span></a>';
			html += '<span>&nbsp;</span>';
			html += '<a id="itemRowDeleteId" href="#" onclick="itemDeatilsDelete(this)"> <span class="glyphicon glyphicon-trash"></span></a>';
			html += '</td>';
			html += '</tr>';
			itemDetailsHtmlArray.push(html);
		}
		$('#itemDeatilsParentDivId').find('table').find('tbody').html(itemDetailsHtmlArray);
		$('#itemDeatilsParentDivId').show();
	}
}

function itemDeatilsEdit(thisElement){
	
}

function itemDeatilsDelete(thisElement){
	
}

function fetchItemDescriptionList(){
	var data={};
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"item_details/getUniqueItemDesc", 'json', null, fetchItemDescriptionListSuccess,true);
}

function fetchItemDescriptionListSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var itemDescNameList=serverData['SERVER_DATA'];
		for(var index in itemDescNameList){
			var itemDescNameObj=itemDescNameList[index];
			itemDescriptionArray.push(itemDescNameObj['itemdescription']);
		}
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
		html += '<td id="testPlanEditRowId" onclick="editTestPlanRowInfo('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span></td>' ;
		html += '</tr>' ;
		htmlArray.push(html);
		sNo++;
	}
	return htmlArray;
}

function getDateValue(dateObj,format,delimeter){
	var formatedDateStr="";
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

function editTestPlanRowInfo(gKey){
	
	console.log("gKey :"+gKey);
	
	console.log(buildTestPlanData[gKey]);
	
	var currentSelectedObject=buildTestPlanData[gKey];
	
	selectedResourcesArray = constructResourceArray(currentSelectedObject);
	
	fillSelectDropDown('projectId',projectArray,currentSelectedObject['projectName']);
	
	fillSelectDropDown('phaseId',phaseArray,currentSelectedObject['phase']);
	
	fillSelectDropDown('planId',planArray,currentSelectedObject['btpPlan']);
	
	fillSelectDropDown('statusId',statusArray,currentSelectedObject['btpStatus']);
	
	$('#cycleId').val(currentSelectedObject['cycle']);
	
	$('#iteration_id').val(currentSelectedObject['sPrint']);
	
	$('#buildNoId').val(currentSelectedObject['buildNo']);
	
	$('#remarksId').val(currentSelectedObject['btpRemarks']);
	
	$('#startDateId').val(getDateValue(currentSelectedObject['startDate'],'yyyy-MM-dd',"-"));
	
	$('#endDateId').val(getDateValue(currentSelectedObject['endDate'],'yyyy-MM-dd',"-"));
	
	$('#revisedEndDateId').val(getDateValue(currentSelectedObject['revisedEndDate'],'yyyy-MM-dd',"-"));
	
	constructResourceTable(selectedResourcesArray);
	
	$("#editBuildTestPlanTrigger").trigger( "click" );
	
	$('#buildTestPlanDiv').html($('#buildTestPlanForm'));
	
	$('#buildTestPlanDiv').find('#buildTestPlanForm').show();
	
	fetchItemDetailsByBtpNo(gKey);
	
	constructItemDetailsTable();
}

function constructResourceArray(btpObject){
	var array=[];
	var objectKeys=Object.keys(btpObject);
	for(var index in objectKeys){
		var keyStr=objectKeys[index];
		if(keyStr.indexOf("resource") != -1){
			var value=btpObject[keyStr];
			if(value.length > 0){
				array.push(btpObject[keyStr]);
			}
		}
	}
	return array;
}

function constructResourceTable(resourceArray){
	var html="";
	for(var index=0 ; index<resourceArray.length;index++ ){
		html += '<tr id="resourcetr_'+(index+1)+'">';
		if(index == 0){
			html += '<td><label class="control-label">Resource'+(index+1)+':*</label></td>';
			html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)"><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value="'+resourceArray[index]+'"></td>';
			html += '<td style="text-align: -webkit-center;">';
			html += '<a onclick="addTableRow(this)" href="#"><span data-href="#" class="fa fa-plus" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Add Fields"></span></a>';
			html += '</td>';
		}else{
			html += '<td><label class="control-label">Resource'+(index+1)+':</label></td>';
			html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)" ><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value="'+resourceArray[index]+'"></td>';
			html += '<td style="text-align: -webkit-center;">';
			html += '<a onclick="deleteTableRow(this)" href="#"><span class="fa fa-remove" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Remove Resource"></span></a></td>';
		}
		html += '</tr>';
	}
	$('#resourceMgmtTableId').find('tbody').html(html);
}

function clickedResourceName(thisElement){
	
	var innerChildTagNmae=$(thisElement)[0]['firstChild']['tagName'];
	
	var resourceNameIndex=$(thisElement)[0]['parentElement']['id'].replace('resourcetr_','');
	
	if('INPUT' == innerChildTagNmae){
		$(thisElement).html(resourceArraySelectHtml);
		$(thisElement).find('select').val(selectedResourcesArray[resourceNameIndex-1]);
	}
	
}

function addTableRow(thisObject){
	console.log(thisObject);
}

function deleteTableRow(thisObject){
	console.log(thisObject);
}

function fillSelectDropDown(dropDownId,arrayData,selectedOption){
	$('#'+dropDownId).html("");
	$.each(arrayData, function(key, value) {   
	     $('#'+dropDownId).append($("<option></option>").attr("value",value).text(value)); 
	});
	$('#'+dropDownId).val(selectedOption);
}