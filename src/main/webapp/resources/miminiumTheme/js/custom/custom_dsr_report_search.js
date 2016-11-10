var dsrReportSearchRef = null;

$(document).ready(function(){
	console.log("Custom DSR REPORT READY !!!");

	$('#apply_filter').on('click',function(){
		var isEmpty = true;
		$('#applyFilter :input').each(function(){
			var value = $(this).val();
			var type = $(this).prop('type');
			if(null != value && value.length > 0 && "button" != type){
				isEmpty=false;
			}
		});
		if(isEmpty){
			var notifyObj={msg: '<b> Please choose filter you want to apply !!! </b>',type: "warning",position: "center"};
			notif(notifyObj);
			return false;
		}
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			var name=$(this).prop('name');
			var value=$(this).val();
			if(name.length > 0 && null != value && value.length > 0){
				filterObject[name]=$(this).val();
				if('startDate' == name){
					filterObject[name] = filterObject[name] + " 00:00:00";
				}else if('endDate' == name){
					filterObject[name] = filterObject[name] + " 23:59:59";
				}
			}
		});
		dsrReportSearchRef.filterDSRReport(filterObject);
	});

	$('#clear_filter').on('click',function(){
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			if("button" != $(this).prop('type')){
				$(this).val(null);
			}
		});
		$('#dsrMainDiv').hide();
		$('#exportDSRReportButton').hide();
	});

	$('#filter_dateFromId').on("blur" , function(){
		var value=$(this).val();
		if(null == value || value.length == 0 || typeof(value) == 'undefined'){
			$(this).addClass('error');
		}else{
			$(this).removeClass('error');
		}
	});
	
	$('#filter_dateToId').on("blur" , function(){
		var value=$(this).val();
		if(null == value || value.length == 0 || typeof(value) == 'undefined'){
			$(this).addClass('error');
		}else{
			$(this).removeClass('error');
		}
	});
	
	$('#export_file_button').on("click",function(){
		
		if("DSR Day Report" == $('#selectExportType').val()){
			var hasErrorClass=false;
			var accomplishedDate=$('#filter_dateFromId').val();
			var plannedDate=$('#filter_dateToId').val();
			if(null == accomplishedDate || accomplishedDate.length == 0 || typeof(accomplishedDate) == 'undefined'){
				$('#filter_dateFromId').addClass('error');
				hasErrorClass=true;
			}
			if(null == plannedDate || plannedDate.length == 0 || typeof(plannedDate) == 'undefined'){
				$('#filter_dateToId').addClass('error');
				hasErrorClass=true;
			}
			if(hasErrorClass){
				return false;
			}
			dsrReportSearchRef.exportSelectedBTP(accomplishedDate,plannedDate);
		}else{
			dsrReportSearchRef.downloadReport('DSR_SUMMARY');
		}
	});
	
	$('#exportDSRReportButton').on("click",function(){
		$("#exportDSRReportButtonTrigger").trigger( "click" );
		$('#filter_dateFromId').removeClass('error');
		$('#filter_dateToId').removeClass('error');
		$('#timePeriodDiv').hide();
		$('#selectExportType option:eq(0)').prop('selected', true);
	});
	
	$('#selectExportType').on('input',function(event){
		console.log($(this));
		if("DSR Day Report" == $(this).val()){
			$('#timePeriodDiv').show();
		}else{
			$('#timePeriodDiv').hide();
		}
	});
	
	if(null != indexDBObj){
		indexDBObj.openDatabase("QA_REPORT_DB","dsr","sNo");
	}

	dsrReportSearchRef =new DSRReportSearch();
	dsrReportSearchRef.onDocumentReady();

});


function DSRReportSearch(){
	this.dsrTableRef=null,
	this.projectArray=[],
	this.resourceArray=[],
	this.statusArray=['Completed','Deferred','In Progress','Planned','Abandoned'];
	this.setResourceArray = function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"project/getUniqueProjectList", 'json', null, DSRReportSearch.prototype.fetchProjectNamesSuccess,true);
	},
	this.setProjectArray = function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"resource_details/getUniqueResources", 'json', null, DSRReportSearch.prototype.fetchResourceNamesSuccess,true);
	}
}

DSRReportSearch.prototype = {

		constructor : DSRReportSearch,

		onDocumentReady : function(){
			dsrReportSearchRef.setResourceArray();
			dsrReportSearchRef.setProjectArray();
		},
		fetchProjectNamesSuccess : function(serverData){
			if('ERROR' != serverData['STATUS']){
				var projectNameObjList=serverData['SERVER_DATA'];
				for(var index in projectNameObjList){
					var projectNameObj=projectNameObjList[index];
					dsrReportSearchRef.projectArray.push(projectNameObj['projectname']);
				}
			}
			dsrReportSearchRef.constructFilterForm();
		},
		fetchResourceNamesSuccess : function(serverData){
			if('ERROR' != serverData['STATUS']){
				var resourceNameObjList=serverData['SERVER_DATA'];
				for(var index in resourceNameObjList){
					var resourceNameObj=resourceNameObjList[index];
					dsrReportSearchRef.resourceArray.push(resourceNameObj['resourcename']);
				}
			}
			dsrReportSearchRef.constructFilterForm();
		},
		constructFilterForm : function(){
			fillSelectDropDown('filter_projectId',dsrReportSearchRef.projectArray, "");
			fillSelectDropDown('filter_resourceId',dsrReportSearchRef.resourceArray,"");
			fillSelectDropDown('filter_statusId',dsrReportSearchRef.statusArray,"");
		},
		exportSelectedBTP : function(accomplishedDate,plannedDate){
			var filterObject={};
			filterObject['plannedDate']=plannedDate;
			filterObject['accomplishedDate']=accomplishedDate;
			filterObject['SINGLE_EXPORT']=true;
			ajaxHandler("POST", JSON.stringify(filterObject), "application/json", getApplicationRootPath()+"dsr/filterData", 'json', null, DSRReportSearch.prototype.filterDSRReportSuccess,true);
		},
		filterDSRReport : function(filterObject){
			dsrReportSearchRef.showLoader();
			var data={};
			filterObject['MULTI_EXPORT']=true;
			data=JSON.stringify(filterObject);
			ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"dsr/filterData", 'json', null, DSRReportSearch.prototype.filterDSRReportSuccess,true);
		},
		filterDSRReportSuccess : function(serverData,inputData){
			console.log(serverData);
			if('ERROR' != serverData['STATUS']){
				var entityList = serverData['SERVER_DATA'];
				if(null != inputData["SINGLE_EXPORT"]){
					if(null != entityList && entityList.length > 0){
						dsrReportSearchRef.downloadReport("DSR_DAY_REPORT");
					}else{
						var notifyObj={msg: '<b>DSR DAILY REPORT IS NOT AVAILABLE</b>',type: "error",position: "center",autohide: true};
						notif(notifyObj);
					}
				}else{
					dsrReportSearchRef.constructDSRReportTable(entityList);
				}
			}
		},
		constructDSRReportTable : function(dsrEntityList){
			var sNo = 1;
			var htmlArray=[];
			for(var index in dsrEntityList){
				var html ="";
				var dsrEntity=dsrEntityList[index];
				var gKey=dsrEntity['sNo'];
				var projectName=dsrEntity['projectName'];
				var resource=dsrEntity['resource'];
				var plannedTask=dsrEntity['plannedTask'];
				var accomplishedTask=dsrEntity['accomplishedTask'];
				var dsrDate=getDateValue(dsrEntity['dsrDate'],'dd/MM/yyyy',"/");
				var dsrStatus=dsrEntity['dsrStatus'];
				var remarks=dsrEntity['remarks'];
				var plannedHours=dsrEntity['plannedHours'];
				var spentHours=dsrEntity['spentHours'];
				indexDBObj.addData("dsr",dsrEntity);
				html += '<tr id="'+gKey+'">' ;
				html += '<td id="sNo">'+sNo+'</td>' ;
				html += '<td id="projectName">'+projectName+'</td>' ;
				html +=	'<td id="resource">'+resource+'</td>' ;
				html += '<td id="dsrDate">'+dsrDate+'</td>' ;
				html += '<td id="plannedTask">'+plannedTask+'</td>' ;
				html += '<td id="dsrStatus">'+dsrStatus+'</td>' ;
				html +=	'<td id="plannedHours">'+plannedHours+'</td>' ;
				html +=	'<td id="spentHours">'+spentHours+'</td>' ;
				html += '</tr>' ;
				htmlArray.push(html);
				sNo++;
			}
			
			if(null != dsrReportSearchRef.dsrTableRef){
				dsrReportSearchRef.dsrTableRef.destroy();
			}
			
			$('#dsr_table_id').find('tbody').empty();
			$('#dsr_table_id').find('tbody').html(htmlArray);
			dsrReportSearchRef.dsrTableRef = $('#dsr_table_id').DataTable({
				"responsive" : true,
				"processing": true
			});
			dsrReportSearchRef.hideLoader();
		},
		showLoader : function(){
			$('#dsrMainDiv').hide();
			$('#loader_div').show();
			$('#exportDSRReportButton').hide();
		},
		hideLoader : function(){
			$('#dsrMainDiv').show();
			$('#loader_div').hide();
			$('#exportDSRReportButton').show();
		},
		downloadReport : function(fileId){
			window.location.href=getApplicationRootPath()+"report_search/export_document/"+fileId;
		}
}