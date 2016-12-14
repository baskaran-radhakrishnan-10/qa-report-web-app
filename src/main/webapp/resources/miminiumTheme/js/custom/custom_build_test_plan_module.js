var defaultSearchEntryCount=10;

var btpServerData=null;

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

var statusArraySelectHtml="";

var itemDetailsObject={};

var resourceDeatilsObject={};

var btpDataTableRef = null;

var isFilterConstructed=false;

$(document).ready(function() {
	
	console.log("documet.ready build test plan module ready!!!");
	
	$('#buildTestPlanForm').hide();
	
	$('#resourceDeatilsParentDivId').hide();
	
	$('#addTestPlanDataId').on("click" ,function (event){
		buildTestPlanModalData(null);
	});
	
	$(document).on("click","#resource1Id",function(event){
		$('#resourceNameListId').addClass('error');
	});
	
	$(document).on("blur","#resourceMgmtTableId td#resourceNameTDId :input" , function (event) {
		var currentValue=$(this).val();
		var rowId=parseInt($(this).parent().parent().attr('id').replace('resourcetr_',''));
		if(null != currentValue && currentValue.length > 0){
			var isResourceNameExsist=false;
			var trArr=$('#resourceMgmtTableId').find('tbody').find('tr');
			for(var index=0;index<trArr.length;index++){
				var rowIndex = (index+1);
				if(rowId != rowIndex){
					var otherRowValue=$('#resourcetr_'+rowIndex).find('#resourceNameTDId').find(':input').val();
					if(otherRowValue == currentValue){
						isResourceNameExsist=true;
						break;
					}
				}
			}
			if(isResourceNameExsist){
				$(this).addClass('error');
				var notifyObj={msg: '<b> Resource : '+currentValue+' </b> is already selected ',type: "error",position: "center",autohide: true};
				notif(notifyObj);
			}else{
				$(this).removeClass('error');
			}
		}
	});
	
	$('#apply_filter').on('click',function(){
		var isEmpty = true;
		$('#applyFilter :input').each(function(){
			var value = $(this).val();
			if(null != value && value.length > 0 && "button" != $(this).prop('type')){
				isEmpty=false;
			}
		});
		if(isEmpty){
			var notifyObj={msg: '<b> Please choose filter you want to apply !!! </b>',type: "warning",position: "center",autohide: true};
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
		filterBTPData(filterObject);
	});
	
	$('#clear_filter').on('click',function(){
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			if("button" != $(this).prop('type')){
				$(this).val(null);
			}
		})
		filterBTPData(filterObject);
	});
	
	$(document).on("blur", "#buildTestPlanForm :input", function() {
		var value=$(this).val();
		var id=$(this).attr('id');
		if($(this).hasClass('imp')){
			if($(this).hasClass('error')){
				if(null != value && value.length > 0){
					$(this).removeClass('error');
				}
			}else{
				if(null == value || value.length == 0){
					$(this).addClass('error');
				}
			}
		}
		/*if(!$(this).hasClass('error')){*/
			if("startDateId" == id){
				
				var startDate = new Date(Date.parse(value));
				var endDate = new Date($('#endDateId').val());
				var isCurrentDate=false;
				
				if(typeof(endDate) != 'undefined' && null != endDate){
					if(startDate > endDate){
						var notifyObj={msg: '<b>Start Date should be lesser than End Date</b>',type: "warning",position: "center"};
						notif(notifyObj);
						$(this).addClass('error');
					}
				}
				
			}else if("endDateId" == id){
				var startDateStr=$('#startDateId').val();
				if(null == startDateStr || startDateStr.length == 0){
					var notifyObj={msg: '<b> Start Date should not be empty </b>',type: "error",position: "center",autohide: true};
					notif(notifyObj);
				}else{
					var revisedEndDate = null;
					var startDate=new Date(Date.parse(startDateStr));
					var endDate = new Date(Date.parse(value));
					if(null != $('#revisedEndDateId').val() && $('#revisedEndDateId').val().length > 0){
						revisedEndDate=new Date(Date.parse($('#revisedEndDateId').val()));
					}
					if(null != revisedEndDate){
						if(endDate > revisedEndDate){
							var notifyObj={msg: '<b> End Date should be lesser than the Revised End Date </b>',type: "error",position: "center",autohide: true};
							notif(notifyObj);
							$(this).addClass('error');
						}
					}
					if(startDate > endDate ){
						var notifyObj={msg: '<b> End Date should be greater than the Start Date </b>',type: "error",position: "center",autohide: true};
						notif(notifyObj);
						$(this).addClass('error');
					}
				}
			}else if("revisedEndDateId" == id){
				var endDateStr=$('#endDateId').val();
				if(null == endDateStr || endDateStr.length == 0){
					var notifyObj={msg: '<b> End Date should not be empty </b>',type: "error",position: "center",autohide: true};
					notif(notifyObj);
				}else{
					var endate=new Date(Date.parse(endDateStr));
					var revEndDate = new Date(Date.parse(value));
					if('Invalid Date' != revEndDate){
						if(endate < revEndDate){
							$(this).removeClass('error');
						}else{
							var notifyObj={msg: '<b> Revised End Date should be greater than the End Date </b>',type: "error",position: "center",autohide: true};
							notif(notifyObj);
							$(this).addClass('error');
						}
					}
				}
			}
		/*}*/
	});
	
	$('#build_test_plan_table_id tbody').on('dblclick', 'tr', function () {
		if("ROLE_SUPER_ADMIN" == $('#loggedInRoleId').val()){
			var btpNo=$(this).attr('id');
	        if ($(this).hasClass('selected')){
	            $(this).removeClass('selected');
	            $(this).css('background-color','');
	        }
	        else {
	            $(this).addClass('selected');
	            $(this).css('background-color','#B0BED9 !important');
	        }
	        var selectedRowList=$('#build_test_plan_table_id tbody').find('.selected')
	        if(selectedRowList.length > 0){
	        	$('#delete_btp_button').show();
	        }else{
	        	$('#delete_btp_button').hide();
	        }
		}
    });
	
	$('#delete_btp_button').on('click',function(){
		$("#showDeleteRowModal").trigger( "click" );
		$('#row_delete_confirm_div').show();
	});
	
	$('#delete_btp_rows').on("click",function(){
		var btpNoArray = [];
		var selectedRowList=$('#build_test_plan_table_id tbody').find('.selected');
		if(selectedRowList.length > 0){
			$.each(selectedRowList,function(index,row){
				btpNoArray.push(parseInt($(row).attr('id')));
			});
			if(btpNoArray.length > 0){
				deleteBtpRows(btpNoArray);
			}
		}
	});
	
	$('#modal_close_button').on("click",function(){
		$('#delete_btp_button').hide();
		$('#build_test_plan_table_id tbody tr').removeClass('selected');
		$('#build_test_plan_table_id tbody tr').css('background-color','');
	});
	
	$(document).on("keypress", "tr input", function(e) {
		var tdId = $(this).parent().attr('id');
		var tableId=$(this).parent().parent().parent().parent().attr('id');
		var charCode = e.which;
		if(tdId.indexOf("itemCount") != -1){
			return checkisNumber(charCode);
		}else if(tdId.indexOf("effortCost") != -1 ){
			return checkIsDecimelNumber(charCode);
		}else if(tableId.indexOf("resourceMgmtTableId") != -1){
			if(tdId.indexOf("Time") != -1){
				return checkIsDecimelNumber(charCode);
			}else{
				return checkisNumber(charCode);
			}
		}
		return true;
	});
	
	/*$(document).on('click', '#build_test_plan_table_id tbody tr', function () {
		var sNo=$(this).attr('id');
		$('#build_test_plan_table_id tbody tr').removeClass('selected');
		$('#build_test_plan_table_id tbody tr').css('background-color','');
		if (!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).css('background-color','#B0BED9 !important');
			buildTestPlanModalData(sNo);
		}else{
			$(this).removeClass('selected');
			$(this).css('background-color','');
		}
	});*/

	
	showLoader();
	
	fetchTestPlanEntries();
	
	fetchResourceNames();
	
	fetchProjectNames();
	
	fetchItemDescriptionList();
	
	fillStatusArrayHtml(statusArray);
	
});

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

function showLoader(){
	$('#btpMainDiv').hide();
	$('#applyFilter').hide();
	$('#loader_div').show();
}

function hideLoader(){
	$('#btpMainDiv').show();
	$('#applyFilter').show();
	$('#loader_div').hide();
}

function fillStatusArrayHtml(statusNameList){
	statusArraySelectHtml += '<select id="statusListId" name="statusList" class="input-sm form-control">';
	for(var index in statusNameList){
		var statusName=statusNameList[index];
		statusArraySelectHtml += '<option value="'+statusName+'">'+statusName+'</option>';
	}
	statusArraySelectHtml += '</select>';
}