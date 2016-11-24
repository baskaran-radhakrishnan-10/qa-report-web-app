var btpFormErrorMessages={};
btpFormErrorMessages['projectId']="Please select any prject from the list";
btpFormErrorMessages['phaseId']="Please select any phase from the list";
btpFormErrorMessages['planId']="Please select any plan from the list";
btpFormErrorMessages['statusId']="Please select any status from the list";
btpFormErrorMessages['cycleId']="Cycle field should not be empty";
btpFormErrorMessages['iteration_id']="Iteration||sPrint field should not be empty";
btpFormErrorMessages['buildNoId']="Build No field should not be empty";
btpFormErrorMessages['startDateId']="Start Date field should not be empty";
btpFormErrorMessages['endDateId']="End Date field should not be empty";
btpFormErrorMessages['resource1Id']="Please select any resource from the list";

function fetchTestPlanEntries(){
	var entryCount=parseInt($('#build_test_plan_table_id_wrapper').find('select').val());
	var data={};
	data['maxEntries']=entryCount;
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"build_test_plan/getData", 'json', null, fetchTestPlanEntriesSuccess,true);
}

function fetchTestPlanEntriesSuccess(serverData){
	if(null == btpServerData){
		btpServerData=serverData;
	}
	$('#build_test_plan_table_id').find('#tbody_id').empty();
	$('#build_test_plan_table_id').find('#tbody_id').html(populateTestPlanEntries(serverData['BTP_ENTRIES']));
	btpDataTableRef = $('#build_test_plan_table_id').DataTable({
		"responsive" : false,
		"processing": true
	});
	if(!isFilterConstructed){
		fillSelectDropDown('filter_projectId',projectArray, "");
		fillSelectDropDown('filter_statusId',statusArray, "");
		fillSelectDropDown('filter_planId',planArray, "");
		isFilterConstructed=true;
	}
	if(null != sessionStorageObj){
		var notifyObj=sessionStorageObj.getItem("NOTIFICATION");
		if(null != notifyObj){
			notif(notifyObj);
			sessionStorageObj.removeItem("NOTIFICATION");
		}
	}
	setTimeout(function(){
		hideLoader();
	}, 500);
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

function buildTestPlanModalData(gKey){
	
	$('#resourceDeatilsParentDivId').hide();
	
	$('#buildTestPlanForm :input').removeClass('error');
	
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

function validateBeforeSave(){
	var errorMsgArray=[];
	$("#buildTestPlanForm :input").each(function(){
		if($(this).hasClass('imp')){
			var input = $(this);
			//console.log($(input));
			//console.log($(input).attr('id'));
			var id=$(input).attr('id');
			var value=$(input).val();
			
			if(null == value || value.length == 0){
				errorMsgArray.push(btpFormErrorMessages[id]);
				$(input).addClass('error');
			}
		}
	});
	console.log(errorMsgArray);
	if(errorMsgArray.length > 0){
		var notifyObj={msg: '<b>Please Fix BTP Form Validation Errors</b>',type: "error",position: "center",autohide: true};
		notif(notifyObj);
		return false;
	}
	return true;
}

function addOrUpdateBtp(){
	
	if(!validateBeforeSave()){
		return false;
	}
	
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
		ajaxHandler("POST", JSON.stringify(btpObject), "application/json", getApplicationRootPath()+"build_test_plan/addData", 'json', addBtpChangesError, addBtpChangesSuccess,true);
	}else{
		ajaxHandler("POST", JSON.stringify(btpObject), "application/json", getApplicationRootPath()+"build_test_plan/updateData", 'json', null, updateBtpModifiedChangesSuccess,true);
	}
}

function constructResourceTable(resourceArray){
	var html="";
	
	if(null != resourceArray && resourceArray.length > 0){
		for(var index=0 ; index<resourceArray.length;index++ ){
			html += '<tr id="resourcetr_'+(index+1)+'">';
			if(index == 0){
				html += '<td id="resourceNameCaptionId"><label class="control-label">Resource'+(index+1)+':*</label></td>';
				html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)" ><input id="resource1Id" name="do_nbr" type="text" class="form-control form-control3 imp" value="'+resourceArray[index]+'"></td>';
				html += '<td style="text-align: -webkit-center;">';
				html += '<a onclick="addTableRow(this)" href="#"><span data-href="#" class="fa fa-plus" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add Resource"></span></a>';
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
		html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)"><input id="resource1Id" name="do_nbr" type="text" class="form-control form-control3 imp" value=""></td>';
		html += '<td style="text-align: -webkit-center;">';
		html += '<a onclick="addTableRow(this)" href="#"><span data-href="#" class="fa fa-plus" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add Fields"></span></a>';
		html += '</td>';
		
	}
	
	$('#resourceMgmtTableId').find('tbody').html(html);
}

function updateBtpModifiedChangesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var notifyObj={msg: "<b>Success:</b> BTP Details Updated Successfully !!!",type: "success",position: "center",autohide: true};
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"build_test_plan/show";
	}else {
		alert('Update Operation Failed!!!');
	}
}

function addBtpChangesSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var notifyObj={msg: "<b>Success:</b> BTP Details Added Successfully !!!",type: "success",position: "center",autohide: true};
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"build_test_plan/show";
	}else {
		alert('Add Operation Failed!!!');
	}
}

function addBtpChangesError(errorData){
	console.log(errorData);
	var notifyObj={msg: '<b>'+errorData['status']+'</b> '+errorData['statusText'],type: "error",position: "center",autohide: true};
	notif(notifyObj);
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

function addTableRow(thisObject){
	var html="";
	var resourceTrArray=$('#resourceMgmtTableId').find('tbody').find('tr');
	if(resourceTrArray.length <10){
		html += '<tr id="resourcetr_'+(resourceTrArray.length+1)+'">';
		html += '<td id="resourceNameCaptionId"><label class="control-label">Resource'+(resourceTrArray.length+1)+':*</label></td>';
		html += '<td id="resourceNameTDId" onclick="clickedResourceName(this)"><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value=""></td>';
		html += '<td style="text-align: -webkit-center;">';
		html += '<a onclick="deleteTableRow(this)" href="#"><span class="fa fa-remove" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Remove Resource"></span></a>';
		html += '</td>';
		html += '</tr>';
		$('#resourceMgmtTableId').find('tbody').append(html);
	}else{
		alert("More than 10 resources not allowed per btp");
	}
}

function deleteTableRow(thisObject){
	var trElementId=$($(thisObject)[0]['parentNode'])[0]['parentNode']['id'];
	var resourceTrArray=$('#resourceMgmtTableId').find('tbody').find('tr');
	if(resourceTrArray.length > 1){
		var numId=parseInt(trElementId.replace('resourcetr_',''));
		var deletedRowId=numId;
		$('#resourceMgmtTableId').find('#'+trElementId).remove();
		while(numId <= 10){
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

function filterBTPData(filterObj){
	var filteredBtpDataList = [];
	var filteredBtpDataObject={};
	var btpDataList = btpServerData['BTP_ENTRIES'];
	var filterKeys=Object.keys(filterObj);
	var isStartDate=_.contains(filterKeys, 'startDate');
	var filterStartDate = isStartDate ? getFormatedDateByTime(new Date(filterObj['startDate']),0,0,0,0) : null ;
	var isEndDate=_.contains(filterKeys, 'endDate');
	var filterEndDate   = isEndDate ?  getFormatedDateByTime(new Date(filterObj['endDate']),23,59,59,999) : null;
	for(var i=0;i<btpDataList.length;i++){
		var canFilter = true;
		var btpObject=btpDataList[i];
		var startDate=new Date(getDateValue(btpObject['startDate'],'yyyy-MM-dd',"-"));
		var endDate=new Date(getDateValue(btpObject['endDate'],'yyyy-MM-dd',"-"));
		if(isStartDate && isEndDate){
			if(!(startDate >= filterStartDate) || !(endDate <= filterEndDate)){
				canFilter = false ;
			}
		}else if(isStartDate){
			if(!(startDate >= filterStartDate)){
				canFilter = false ;
			}
		}else if(isEndDate){
			if(!(endDate <= filterEndDate)){
				canFilter = false ;
			}
		}
		if(canFilter){
			for(var index in filterKeys){
				var filterKey = filterKeys[index];
				var filterValue = filterObj[filterKey];
				if(filterKey.indexOf('startDate') == -1 && filterKey.indexOf('endDate') == -1){
					var btpValue = btpObject[filterKey];
					if(btpValue != filterValue){
						canFilter = false;
						break;
					}
				}
			}
		}
		if(canFilter){
			filteredBtpDataList.push(btpObject);
		}
	}
	filteredBtpDataObject['BTP_ENTRIES']=filteredBtpDataList;
	btpDataTableRef.destroy();
	fetchTestPlanEntriesSuccess(filteredBtpDataObject);
}

function deleteBtpRows(btpNoList){
	var data = {};
	data['btpNoList'] = btpNoList;
	ajaxHandler("POST", JSON.stringify(data), "application/json", getApplicationRootPath()+"build_test_plan/deleteData", 'json', deleteBtpRowsError, deleteBtpRowsSuccess,true);
}

function deleteBtpRowsError(errorRes){
	console.log(errorRes);
	var notifyObj={msg: '<b>Error : </b> Operation Failed Due to Server Issue !!!',type: "error",position: "center" };
	notif(notifyObj);
}

function deleteBtpRowsSuccess(serverData,inputData){
	if('ERROR' != serverData['STATUS']){
		var notifyObj={msg: "<b>Success:</b> Selected Records Deleted Successfully !!!",type: "success",position: "center",autohide: true};
		if(null != sessionStorageObj){
			sessionStorageObj.setItem("NOTIFICATION",notifyObj);
		}
		window.location.href=getApplicationRootPath()+"build_test_plan/show";
	}
}

