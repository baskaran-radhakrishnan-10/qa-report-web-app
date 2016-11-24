var btpReportSearchRef=null;

$(document).ready(function() {

	btpReportSearchRef=new BTPReportSearch();
	btpReportSearchRef.setProjectArray();
	btpReportSearchRef.setBtpYearArray();
	btpReportSearchRef.onDocumentReady();
	
	$('#selectExportType').on('input',function(event){
		console.log($(this));
		if("BTP Monthly Report" == $(this).val()){
			$('#timePeriodDiv').show();
		}else{
			$('#timePeriodDiv').hide();
		}
	});
	
	$("#exportBTPReportButtonTrigger").on('click',function(){
		$('#selectExportType option:eq(0)').prop('selected', true)
	});

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
			}
		});
		
		btpReportSearchRef.filterBTPReport(filterObject);
		
	});

	$('#clear_filter').on('click',function(){
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			if("button" != $(this).prop('type')){
				$(this).val(null);
			}
		});
		$('#btpMainDiv').hide();
		$('#exportBTPReportButton').hide();
	});
	
	$('#exportBTPReportButton').on('click',function(){
		$("#exportBTPReportButtonTrigger").trigger( "click" );
		$('#multi_btp_report_modal_content').show();
        $('#single_btp_report_modal_content').hide();
        $('#timePeriodDiv').hide();
	});
	
	$('#export_file_button').on("click",function(){
		if("BTP_MONTHLY" == $('#selectExportType option:selected').attr('id')){
			var year = parseInt($('#yearSelector').val());
			var month = parseInt($('#monthSelector').find('option:selected').attr('id'));
			var startDate = year+'-'+(month+1)+'-'+1+' 00:00:00';
			var endDate = year+'-'+(month+1)+'-'+getDaysInMonth(month,year)+' 23:59:59';
			var dataObj = {};
			dataObj['startDate'] = startDate;
			dataObj['endDate'] = endDate;
			btpReportSearchRef.btpMonthlyReport(dataObj);
		}else{
			$('#export_file_button').attr('disabled', true);
			btpReportSearchRef.downloadReport($('#selectExportType option:selected').attr('id'));
			$('#export_file_button').attr('disabled', false);
		}
		
	});
	
	$('#btp_report_search_table_id tbody').on('dblclick', 'tr', function () {
		var btpNo=$(this).attr('id');
		$('#btp_report_search_table_id tbody tr').css('background-color','');
        if ($(this).hasClass('selected')){
            $(this).removeClass('selected');
        }
        else {
        	btpReportSearchRef.btpReportTableRef.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            btpReportSearchRef.exportSelectedBTP(btpNo);
            $(this).css('background-color','#B0BED9 !important');
            $("#exportBTPReportButtonTrigger").trigger( "click" );
            $('#multi_btp_report_modal_content').hide();
            $('#single_btp_report_modal_content').show();
        }
    });
	
	$('#selected_new_export_button').on('click',function(){
		btpReportSearchRef.downloadReport("SINGLE_BTP_ROW");
	});

});

function BTPReportSearch(){
	this.planArray=['Automation Plan','Build Test Plan','Test Design Plan'];
	this.statusArray=['Completed','Deferred','In Progress','Planned','Abandoned'];
	this.projectArray=[];
	this.btpYearArray=[];
	this.btpReportTableRef=null;
	this.setProjectArray=function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"project/getUniqueProjectList", 'json', null, BTPReportSearch.prototype.fetchProjectNamesSuccess,true);
	};
	this.setBtpYearArray=function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"build_test_plan/getUniqueBtpYearList", 'json', null, BTPReportSearch.prototype.fetchBtpYearSuccess,true);
	}
}

BTPReportSearch.prototype.onDocumentReady = function(){
	fillSelectDropDown('filter_planId',btpReportSearchRef.planArray,"");
	fillSelectDropDown('filter_statusId',btpReportSearchRef.statusArray,"");
}

BTPReportSearch.prototype.fetchProjectNamesSuccess = function(serverData){
	if('ERROR' != serverData['STATUS']){
		var projectNameObjList=serverData['SERVER_DATA'];
		for(var index in projectNameObjList){
			var projectNameObj=projectNameObjList[index];
			btpReportSearchRef.projectArray.push(projectNameObj['projectname']);
		}
		fillSelectDropDown('filter_projectId',btpReportSearchRef.projectArray,"");
	}
}

BTPReportSearch.prototype.fetchBtpYearSuccess = function(serverData){
	if('ERROR' != serverData['STATUS']){
		var yearList=serverData['SERVER_DATA'];
		for(var index in yearList){
			var yearObj=yearList[index];
			btpReportSearchRef.btpYearArray.push(yearObj['btpYear']);
		}
		fillSelectDropDown('yearSelector',btpReportSearchRef.btpYearArray,btpReportSearchRef.btpYearArray[0]);
	}
}

BTPReportSearch.prototype.filterBTPReport=function(filterObject){
	btpReportSearchRef.showLoader();
	filterObject=JSON.stringify(filterObject);
	ajaxHandler("POST", filterObject, "application/json", getApplicationRootPath()+"build_test_plan/filterData", 'json', null, BTPReportSearch.prototype.filterBTPReportSuccess,true);
}

BTPReportSearch.prototype.filterBTPReportSuccess=function(serverData){
	if('ERROR' != serverData['STATUS']){
		var filterReportList=serverData['SERVER_DATA'];
		btpReportSearchRef.constructBTPReportTable(filterReportList);
	}
	btpReportSearchRef.hideLoader();
}

BTPReportSearch.prototype.constructBTPReportTable=function(btpReportList){
	
	$('#btpMainDiv').show();
	if(null != btpReportSearchRef.btpReportTableRef){
		btpReportSearchRef.btpReportTableRef.destroy();
	}
	
	var sNo = 1;
	var htmlArray=new Array();
	for(var i=0;i<btpReportList.length;i++){
		
		var html ="";
		var btpReport=btpReportList[i];
		var gKey=btpReport['gKey'];
		var projectName=btpReport['projectName'];
		var btpPlan=btpReport['btpPlan'];
		var buildNo=btpReport['buildNo'];
		var btpStatus=btpReport['btpStatus'];
		
		var startDate=getDateValue(btpReport['startDate'],'dd/MM/yyyy',"/");
		var endDate=getDateValue(btpReport['endDate'],'dd/MM/yyyy',"/");
		var revisedEndDate=getDateValue(btpReport['revisedEndDate'],'dd/MM/yyyy',"/");
		
		html += '<tr id="'+gKey+'">' ;
		html += '<td>'+sNo+'</td>' ;
		html += '<td>'+projectName+'</td>' ;
		html +=	'<td>'+btpPlan+'</td>' ;
		html += '<td>'+buildNo+'</td>' ;
		html += '<td>'+btpStatus+'</td>' ;
		html +=	'<td>'+startDate+'</td>' ;
		html += '<td>'+endDate+'</td>' ;
		html +=	'<td>'+revisedEndDate+'</td>' ;
		/*html += '<td id="testPlanEditRowId" onclick="buildTestPlanModalData('+gKey+')"><span><a href="#" class="glyphicon glyphicon-edit"></a></span><span>&nbsp;</span></td>' ;*/
		html += '</tr>' ;
		
		htmlArray.push(html);
		
		sNo++;
	}
	
	$('#btp_report_search_table_id').find('#tbody_id').empty();
	$('#btp_report_search_table_id').find('#tbody_id').html(htmlArray);
	
	btpReportSearchRef.btpReportTableRef = $('#btp_report_search_table_id').DataTable({
		"responsive" : false,
		"processing": true,
		"autoWidth": true
	});
	
	if(btpReportList.length > 0){
		$('#exportBTPReportButton').show();
	}else{
		$('#exportBTPReportButton').hide();
	}
	
}

BTPReportSearch.prototype.showLoader=function(){
	$('#btpMainDiv').hide();
	$('#loader_div').show();
}

BTPReportSearch.prototype.hideLoader=function(){
	$('#btpMainDiv').show();
	$('#loader_div').hide();
}

BTPReportSearch.prototype.downloadReport=function(fileId){
	window.location.href=getApplicationRootPath()+"report_search/export_document/"+fileId;
}

BTPReportSearch.prototype.exportSelectedBTP=function(btpNo){
	var data={};
	data['btpNo']=btpNo;
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"report_search/exportBTPRow", 'json', null, BTPReportSearch.prototype.exportSelectedBTPSuccess,true);
}

BTPReportSearch.prototype.btpMonthlyReport=function(dataObj){
	var data={};
	data=JSON.stringify(dataObj);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"report_search/buildBtpMonthlyReport", 'json', null, BTPReportSearch.prototype.btpMonthlyReportSuccess,true);
}

BTPReportSearch.prototype.btpMonthlyReportSuccess=function(serverData){
	if('ERROR' != serverData['STATUS']){
		btpReportSearchRef.downloadReport("BTP_MONTHLY");
		$('#export_file_button').attr('disabled', false);
	}else{
		var notifyObj={msg: '<b>BTP MONTHLY REPORT IS NOT AVAILABLE</b>',type: "error",position: "center",autohide: true};
		notif(notifyObj);
	}
}

BTPReportSearch.prototype.exportSelectedBTPSuccess=function(serverData){
	if('ERROR' != serverData['STATUS']){}
}
