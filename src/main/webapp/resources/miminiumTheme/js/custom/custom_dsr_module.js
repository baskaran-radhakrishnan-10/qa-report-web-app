var dsrRef = null;

$(document).ready(function() {
	
	$('#apply_filter').on('click',function(){
		var isEmpty = true;
		$('#applyFilter :input').each(function(){
			var value = $(this).val();
			if(null != value && value.length > 0){
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
		dsrRef.applyDSRFilter(filterObject);
	});
	
	$('#clear_filter').on('click',function(){
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			if("button" != $(this).prop('type')){
				$(this).val(null);
			}
		})
		dsrRef.applyDSRFilter(filterObject);
	});

	$('#dsr_modal').on('mousedown', function() {
		var inputSet = $(this).find('#dsrForm :input');
		$(inputSet).each(function(){
			var input=$(this);
			var value=$(input).val();
			var id=$(this).attr('id');
			if($(this).hasClass('imp')){
				if($(this).hasClass('error')){
					if(null != value && value.length > 0 && typeof(value) != 'undefined'){
						$(this).removeClass('error');
					}
				}
				if(null == value || value.length == 0 || typeof(value) == 'undefined'){
					$(this).addClass('error');
				}
			}
			if(id.indexOf("hrs") != -1){
				if(!dsrRef.isNumber(value)){
					$(this).addClass('error');
				}else{
					$(this).removeClass('error');
				}
			}
		});
	});

	$('#dsr_modal').on('click',function(event){
		if('save_button' == event['target']['id']){
			var inputSet = $(this).find('#dsrForm :input');
			if(!$(inputSet).hasClass('error')){
				$(event['target']).attr('disabled',true);
				var formObject={};
				$(inputSet).each(function(){
					var input=$(this);
					var value=$(this).val();
					var name=$(this).attr('name');
					if('undefined' != typeof(name)){
						formObject[name]=value;
					}
				});
				dsrRef.saveDSRDetails(formObject);
			}
		}
	});

	$('#dsr_form_submit').on('click',function(){
		console.log('dsr_form_submit on click event');
	});

	$('#addDSRId').on('click',function(){
		$("#editDSRTrigger").trigger( "click" );
		dsrRef.constructDSRDetailsForm(-1);
	});

	$('#dsr_table_id tbody').on('click', 'tr', function () {
		var sNo=$(this).attr('id');
		$('#dsr_table_id tbody tr').removeClass('selected');
		$('#dsr_table_id tbody tr').css('background-color','');
		if (!$(this).hasClass('selected')){
			dsrRef.dsrTableRef.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
			$(this).css('background-color','#B0BED9 !important');
			$("#editDSRTrigger").trigger( "click" );
			dsrRef.constructDSRDetailsForm(sNo);
		}
	});

	if(null != indexDBObj){
		indexDBObj.openDatabase("QA_REPORT_DB","dsr","sNo");
	}

	dsrRef = new DSRClass();
	if (typeof(Worker) !== "undefined") {
		dsrRef.dsrWorker=new Worker(dsrRef.buildDSRWorkerScript());
	}
	dsrRef.setResourceArray();
	dsrRef.setProjectArray();
	dsrRef.onDocumentReady();

});

function DSRClass(){
	this.resourceArraySelectHtml=null,
	this.projectArraySelectHtml=null,
	this.statusArraySelectHtml=null,
	this.dsrTableRef=null,
	this.projectArray=[],
	this.resourceArray=[],
	this.dsrWorker=null,
	this.statusArray=['Completed','Deferred','In Progress','Planned','Abandoned'];
	this.setResourceArray = function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"project/getUniqueProjectList", 'json', null, DSRClass.prototype.fetchProjectNamesSuccess,true);
	},
	this.setProjectArray = function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"resource_details/getUniqueResources", 'json', null, DSRClass.prototype.fetchResourceNamesSuccess,true);
	}
}

DSRClass.prototype = {

		constructor : DSRClass,

		onDocumentReady : function(){

			dsrRef.showLoader();

			var data={};
			data=JSON.stringify(data);
			ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"dsr/getData", 'json', null, DSRClass.prototype.fetchDSREntriesSuccess,true);

			/*if(indexDBObj.isDataAvailable("dsr")){
			dsrRef.constructDSRTable(indexDBObj.getAllData("dsr"));
		}else{
			var data={};
			data=JSON.stringify(data);
			ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"dsr/getData", 'json', null, DSRClass.prototype.fetchDSREntriesSuccess,true);
		}*/

		},
		fetchDSREntriesSuccess : function(serverData){
			if('ERROR' != serverData['STATUS']){
				dsrRef.communicateDSRWorker(serverData['SERVER_DATA']);
				/*for(var index in serverData['SERVER_DATA']){
				var dsrEntity = serverData['SERVER_DATA'][index];
				if(0 == index){
					if(!indexDBObj.getDataByKey("dsr",dsrEntity['sNo'])){
						indexDBObj.addData("dsr",dsrEntity);
					}
				}
			}*/
				//indexDBObj.getAllData("dsr");
			}
		},
		fetchProjectNamesSuccess : function(serverData){
			if('ERROR' != serverData['STATUS']){
				var projectNameObjList=serverData['SERVER_DATA'];
				dsrRef.projectArraySelectHtml += '<select id="projectNameListId" name="projectName" class="input-sm form-control">';
				for(var index in projectNameObjList){
					var projectNameObj=projectNameObjList[index];
					dsrRef.projectArray.push(projectNameObj['projectname']);
					dsrRef.projectArraySelectHtml += '<option value="'+projectNameObj['projectname']+'">'+projectNameObj['projectname']+'</option>';
				}
				dsrRef.projectArraySelectHtml += '</select>';
			}
			dsrRef.constructFilterForm();
		},
		fetchResourceNamesSuccess : function(serverData){
			if('ERROR' != serverData['STATUS']){
				var resourceNameObjList=serverData['SERVER_DATA'];
				dsrRef.projectArraySelectHtml += '<select id="resourceNameListId" name="resource" class="input-sm form-control">';
				for(var index in resourceNameObjList){
					var resourceNameObj=resourceNameObjList[index];
					dsrRef.resourceArray.push(resourceNameObj['resourcename']);
					dsrRef.projectArraySelectHtml += '<option value="'+resourceNameObj['resourcename']+'">'+resourceNameObj['resourcename']+'</option>';
				}
				dsrRef.projectArraySelectHtml += '</select>';
			}
			dsrRef.constructFilterForm();
		},
		communicateDSRWorker : function(dsrEntityList){
			/*if(null != dsrRef.dsrWorker){
				dsrRef.dsrWorker.addEventListener('message', function(e) {
					//console.log('got message from worker script');
					//console.lWorkerog(e.data);
					var data = e.data;
					var htmlArray = JSON.parse(data);
					if(htmlArray.length > 0){
						$('#dsr_table_id').find('tbody').empty();
						$('#dsr_table_id').find('tbody').html(htmlArray);
						dsrRef.dsrTableRef = $('#dsr_table_id').DataTable({
							"responsive" : true,
							"processing": true
						});
						dsrRef.hideLoader();
					}
				}, false);
				console.log('going to send message worker');
				var workObject={};
				workObject['indexDBObj']=indexDBObj;
				workObject['entityList']=dsrEntityList;
				dsrRef.dsrWorker.postMessage(JSON.stringify(workObject));
			}*/
			dsrRef.constructDSRTable(dsrEntityList,false);
		},
		buildDSRWorkerScript : function(dsrEntityList){

			var blobURL = URL.createObjectURL(new Blob(['(',function(){
				self.addEventListener('message', function(e) {
					var data = e.data;
					var workObject = JSON.parse(data);
					console.log(workObject);
					var dsrEntityList = workObject['entityList'];
					var indexDBObj = workObject['indexDBObj'];
					indexDBObj.addData("dsr",dsrEntity);
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
						var dsrDate=hi();
						var dsrStatus=dsrEntity['dsrStatus'];
						var remarks=dsrEntity['remarks'];
						var plannedHours=dsrEntity['plannedHours'];
						var spentHours=dsrEntity['spentHours'];
						html += '<tr id="'+gKey+'">' ;
						html += '<td>'+sNo+'</td>' ;
						html += '<td>'+projectName+'</td>' ;
						html +=	'<td>'+resource+'</td>' ;
						html += '<td>'+dsrDate+'</td>' ;
						html += '<td>'+plannedTask+'</td>' ;
						/*html +=	'<td>'+accomplishedTask+'</td>' ;*/
						html += '<td>'+dsrStatus+'</td>' ;
						/*html +=	'<td>'+remarks+'</td>' ;*/
						html +=	'<td>'+plannedHours+'</td>' ;
						html +=	'<td>'+spentHours+'</td>' ;
						html += '</tr>' ;
						htmlArray.push(html);
						sNo++;
					}
					self.postMessage(JSON.stringify(htmlArray));
				}, false);
				function hi(){
					return "hi";
				}
			}.toString(),')()'],{type:'application/javascript' } ) );
			return blobURL;
		},
		constructDSRTable : function(dsrEntityList,isFilter){
			
			var sNo = 1;
			var htmlArray=[];
			for(var index in dsrEntityList){

				var html ="";

				var dsrEntity=dsrEntityList[index];
				if(!isFilter){
					indexDBObj.addData("dsr",dsrEntity);
				}
				
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


				html += '<tr id="'+gKey+'">' ;
				html += '<td id="sNo">'+sNo+'</td>' ;
				html += '<td id="projectName">'+projectName+'</td>' ;
				html +=	'<td id="resource">'+resource+'</td>' ;
				html += '<td id="dsrDate">'+dsrDate+'</td>' ;
				html += '<td id="plannedTask">'+plannedTask+'</td>' ;
				/*html +=	'<td>'+accomplishedTask+'</td>' ;*/
				html += '<td id="dsrStatus">'+dsrStatus+'</td>' ;
				/*html +=	'<td>'+remarks+'</td>' ;*/
				html +=	'<td id="plannedHours">'+plannedHours+'</td>' ;
				html +=	'<td id="spentHours">'+spentHours+'</td>' ;
				html += '</tr>' ;

				htmlArray.push(html);

				sNo++;
			}

			/*if(dsrEntityList.length > 0){*/
				$('#dsr_table_id').find('tbody').empty();
				$('#dsr_table_id').find('tbody').html(htmlArray);
				dsrRef.dsrTableRef = $('#dsr_table_id').DataTable({
					"responsive" : true,
					"processing": true
				});
				dsrRef.hideLoader();
			/*}*/

		},
		constructDSRDetailsForm : function(sNo){
			$('#dsrDiv').html($('#dsrForm'));
			$('#dsrForm').show();
			$('#dsrForm :input').removeClass('error');
			var request = indexDBObj.db.transaction(["dsr"],"readwrite").objectStore("dsr").get(parseInt(sNo));
			request.onsuccess = function(event){
				var dsrEntity=request.result;
				fillSelectDropDown('projectId',dsrRef.projectArray, 'undefined' != typeof(dsrEntity) ? dsrEntity['projectName'] : "");
				fillSelectDropDown('resourceId',dsrRef.resourceArray, 'undefined' != typeof(dsrEntity) ? dsrEntity['resource'] : "");
				fillSelectDropDown('statusId',dsrRef.statusArray, 'undefined' != typeof(dsrEntity) ? dsrEntity['dsrStatus'] : "");
				$('#hrsPlannedId').val( 'undefined' != typeof(dsrEntity) ? dsrEntity['plannedHours'] : "");
				$('#hrsSpentId').val( 'undefined' != typeof(dsrEntity) ? dsrEntity['spentHours'] : "");
				$('#plannedTaskId').val( 'undefined' != typeof(dsrEntity) ? dsrEntity['plannedTask'] : "");
				$('#remarksId').val( 'undefined' != typeof(dsrEntity) ? dsrEntity['remarks'] : "");
				$('#accomplishedTaskId').val( 'undefined' != typeof(dsrEntity) ? dsrEntity['accomplishedTask'] : "");
				$('#dsrDateId').val( 'undefined' != typeof(dsrEntity) ? getDateValue(dsrEntity['dsrDate'],'yyyy-MM-dd',"-") : "");
				$('#selectedRowKeyInput').val('undefined' != typeof(dsrEntity) ? dsrEntity['sNo'] : "");
			};
		},
		saveDSRDetails : function(dsrFormObject){
			var sNo = $('#selectedRowKeyInput').val();
			if("" == sNo){
				dsrRef.addDSRDetails(dsrFormObject);
			}else{
				var request = indexDBObj.db.transaction(["dsr"],"readwrite").objectStore("dsr").get(parseInt(sNo));
				request.onsuccess = function(event){
					var dsrEntity=request.result;
					_.mapObject(dsrFormObject,function(value,key){
						dsrEntity[key]=dsrFormObject[key];
					});
					dsrRef.updateDSRDetails(dsrEntity);
				}
			}
		},
		updateDSRDetails : function(dsrEntity){
			ajaxHandler("POST", JSON.stringify(dsrEntity), "application/json", getApplicationRootPath()+"dsr/updateData", 'json', null, DSRClass.prototype.updateDSRDetailsSuccess,true);
		},
		updateDSRDetailsSuccess : function(serverData,inputData){
			if('SUCCESS' == serverData['STATUS']){
				var objectStore = indexDBObj.db.transaction(["dsr"],"readwrite").objectStore("dsr");
				var request = objectStore.get(parseInt(inputData['sNo']));
				request.onsuccess = function(event){
					objectStore.put(inputData);
				};
				$('#save_button').attr('disabled',false);
				var notifyObj={msg: "<b>Success:</b> SNo : "+inputData['sNo']+" Succesfully Updated !!!",type: "success",position: "center",autohide: true};
				notif(notifyObj);
				_.mapObject(inputData,function(value,key){
					$('#dsr_table_id').find('tbody').find('#'+inputData['sNo']).find('#'+key).text(value);
				});
			}
		},
		addDSRDetails : function(dsrEntity){
			ajaxHandler("POST", JSON.stringify(dsrEntity), "application/json", getApplicationRootPath()+"dsr/addData", 'json', null, DSRClass.prototype.addDSRDetailsSuccess,true);
		},
		addDSRDetailsSuccess : function(serverData,inputData){
			if('SUCCESS' == serverData['STATUS']){
				var sNo = parseInt(serverData['SERVER_DATA']);
				inputData['sNo']=sNo;
				indexDBObj.addData("dsr",inputData);
				$('#save_button').attr('disabled',false);
				var notifyObj={msg: "<b>Success:</b> New DSR Detail Succesfully Added !!!",type: "success",position: "center",autohide: true};
				notif(notifyObj);
				$('#cancel_button').trigger( "click" );
			}
		},
		constructFilterForm : function(){
			fillSelectDropDown('filter_projectId',dsrRef.projectArray, "");
			fillSelectDropDown('filter_resourceId',dsrRef.resourceArray,"");
			fillSelectDropDown('filter_statusId',dsrRef.statusArray,"");
		},
		applyDSRFilter : function(filterObj){
			
			var filteredDsrDataList = [];
			var filterKeys=Object.keys(filterObj);
			var isStartDate=_.contains(filterKeys, 'startDate');
			var filterStartDate = isStartDate ? getFormatedDateByTime(new Date(filterObj['startDate']),0,0,0,0) : null ;
			var isEndDate=_.contains(filterKeys, 'endDate');
			var filterEndDate   = isEndDate ?  getFormatedDateByTime(new Date(filterObj['endDate']),23,59,59,999) : null;
			
			if('undefined' == typeof(indexDBObj.db)){
				indexDBObj.openDatabase("QA_REPORT_DB","dsr","sNo");
			}
			
			if(indexDBObj.isConnectionReady){
				var objectStore = indexDBObj.db.transaction("dsr").objectStore("dsr");
				objectStore.openCursor().onsuccess = function(event) {
					var cursor = event.target.result;
					if (cursor) {
						var canFilter = true;
						var dsrEntity=cursor['value'];
						var dsrDate=new Date(getDateValue(dsrEntity['dsrDate'],'yyyy-MM-dd',"-"));
						
						if(isStartDate && isEndDate){
							if(!(dsrDate >= filterStartDate) || !(dsrDate <= filterEndDate)){
								canFilter = false ;
							}
						}else if(isStartDate){
							if(!(dsrDate >= filterStartDate)){
								canFilter = false ;
							}
						}else if(isEndDate){
							if(!(dsrDate <= filterEndDate)){
								canFilter = false ;
							}
						}
						if(canFilter){
							for(var index in filterKeys){
								var filterKey = filterKeys[index];
								var filterValue = filterObj[filterKey];
								if(filterKey.indexOf('startDate') == -1 && filterKey.indexOf('endDate') == -1){
									var dsrValue = dsrEntity[filterKey];
									if(dsrValue != filterValue){
										canFilter = false;
										break;
									}
								}
							}
						}
						if(canFilter){
							filteredDsrDataList.push(dsrEntity);
						}
						cursor.continue();
					}
					if(null == cursor){
						dsrRef.dsrTableRef.destroy();
						dsrRef.constructDSRTable(filteredDsrDataList,true);
					}
				};
			}
		},
		clearDSRFilter : function(filterData){
			console.log(filterData);
		},
		showLoader : function(){
			$('#dsrMainDiv').hide();
			$('#loader_div').show();
			$('#applyFilter').hide();
		},
		hideLoader : function(){
			$('#dsrMainDiv').show();
			$('#loader_div').hide();
			$('#applyFilter').show();
		},
		isNumber : function (input){
			return !isNaN(parseFloat(input)) && isFinite(input);
		}
}

