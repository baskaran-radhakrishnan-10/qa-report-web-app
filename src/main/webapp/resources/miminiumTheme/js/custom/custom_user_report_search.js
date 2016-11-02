var userReportSearchRef = null;

$(document).ready(function(){
	
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
		userReportSearchRef.filterUserReport(filterObject);
	});

	$('#clear_filter').on('click',function(){
		var filterObject = {};
		$('#applyFilter :input').each(function(){
			if("button" != $(this).prop('type')){
				$(this).val(null);
			}
		});
		$('#userMainDiv').hide();
		$('#exportUserReportButton').hide();
	});
	
	$('#exportUserReportButton').on("click",function(){
		userReportSearchRef.downloadReport('USER_SUMMARY');
	});
	
	userReportSearchRef = new UserReportSearch();
	userReportSearchRef.onDocumentReady();
	
});

function UserReportSearch(){
	this.userTableRef=null,
	this.projectArray=[],
	this.resourceArray=[],
	this.setResourceArray = function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"project/getUniqueProjectList", 'json', null, UserReportSearch.prototype.fetchProjectNamesSuccess,true);
	},
	this.setProjectArray = function(){
		var data={};
		data=JSON.stringify(data);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"resource_details/getUniqueResources", 'json', null, UserReportSearch.prototype.fetchResourceNamesSuccess,true);
	}
}

UserReportSearch.prototype = {
	constructor : 	UserReportSearch ,
	onDocumentReady : function(){
		userReportSearchRef.setResourceArray();
		userReportSearchRef.setProjectArray();
	},
	fetchProjectNamesSuccess : function(serverData){
		if('ERROR' != serverData['STATUS']){
			var projectNameObjList=serverData['SERVER_DATA'];
			for(var index in projectNameObjList){
				var projectNameObj=projectNameObjList[index];
				userReportSearchRef.projectArray.push(projectNameObj['projectname']);
			}
		}
		userReportSearchRef.constructFilterForm();
	},
	fetchResourceNamesSuccess : function(serverData){
		if('ERROR' != serverData['STATUS']){
			var resourceNameObjList=serverData['SERVER_DATA'];
			for(var index in resourceNameObjList){
				var resourceNameObj=resourceNameObjList[index];
				userReportSearchRef.resourceArray.push(resourceNameObj['resourcename']);
			}
		}
		userReportSearchRef.constructFilterForm();
	},
	constructFilterForm : function(){
		fillSelectDropDown('filter_projectId',userReportSearchRef.projectArray,"");
		fillSelectDropDown('filter_resourceId',userReportSearchRef.resourceArray,"");
	},filterUserReport : function(filterObject){
		userReportSearchRef.showLoader();
		var data={};
		data=JSON.stringify(filterObject);
		ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"report_search/filter_user_report", 'json', null, UserReportSearch.prototype.filterUserReportSuccess,true);
	},
	filterUserReportSuccess : function(serverData,inputData){
		console.log(serverData);
		if('ERROR' != serverData['STATUS']){
			var objectList = serverData['SERVER_DATA'];
			console.log(objectList);
			userReportSearchRef.constructUserReportTable(objectList);
		}
	},
	constructUserReportTable : function(objectList){
		var sNo = 1;
		var htmlArray=[];
		for(var index in objectList){
			var html ="";
			var object=objectList[index];
			
			var projectName=object['projectName'];
			var resourceName=object['resourceName'];
			var actualTimeTaken=object['actualTimeTaken'];
			
			html += '<tr>' ;
			html += '<td id="sNo">'+sNo+'</td>' ;
			html += '<td id="projectName">'+projectName+'</td>' ;
			html +=	'<td id="resource">'+resourceName+'</td>' ;
			html += '<td id="timeTaken">'+actualTimeTaken+' hours </td>' ;
			html += '</tr>' ;
			htmlArray.push(html);
			sNo++;
		}
		if(null != userReportSearchRef.userTableRef){
			userReportSearchRef.userTableRef.destroy();
		}
		
		$('#user_table_id').find('tbody').empty();
		$('#user_table_id').find('tbody').html(htmlArray);
		userReportSearchRef.userTableRef = $('#user_table_id').DataTable({
			"responsive" : true,
			"processing": true
		});
		userReportSearchRef.hideLoader();
	},
	showLoader : function(){
		$('#userMainDiv').hide();
		$('#loader_div').show();
		$('#exportUserReportButton').hide();
	},
	hideLoader : function(){
		$('#userMainDiv').show();
		$('#loader_div').hide();
		$('#exportUserReportButton').show();
	},
	downloadReport : function(fileId){
		window.location.href=getApplicationRootPath()+"report_search/export_document/"+fileId;
	}
}