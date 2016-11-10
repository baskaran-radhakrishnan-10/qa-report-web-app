var leftMenuRef = null;

$(document).ready(function(event) {

	$(document).on("mouseenter", "li", function(event) {
		if(!$(this).parent().hasClass("user-nav")){
			$(this).css('background-color','#FFEB3B');
		}
	});

	$(document).on("mouseleave", "li", function() {
		$(this).css('background-color','');
	});

	leftMenuRef = new LeftMenuObject();
	leftMenuRef.onDocumentReady();
});

function LeftMenuObject(){
	this.baseUrl = null;
}

LeftMenuObject.prototype = {

		constructor : LeftMenuObject ,

		onDocumentReady : function(){
			var data={};
			data=JSON.stringify(data);
			ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"rbac/getRoleResourcesInfo", 'json', null, LeftMenuObject.prototype.fetchResourceInfoSuccess,true);
		},
		fetchResourceInfoSuccess : function(serverData){
			if('ERROR' != serverData['STATUS']){
				var roleResourceInfoList = serverData['SERVER_DATA']['ROLE_RESOURCE_INFO'];
				leftMenuRef.baseUrl =  serverData['SERVER_DATA']['baseUrl'];
				var resourceInfoObject = {};
				for(var index in roleResourceInfoList){
					var subMenuObj = {};
					var roleResourceInfo = roleResourceInfoList[index];
					var resourceObject = roleResourceInfo['resourceId'];
					var resourceModule = resourceObject['resourceModule'];
					var resourceUrl = resourceObject['resourceUrl'];
					var resourceName = resourceObject['resourceName'];
					if(null != resourceInfoObject[resourceModule]){
						subMenuObj = resourceInfoObject[resourceModule]
						subMenuObj[resourceName]=resourceUrl;
					}else{
						subMenuObj[resourceName]=resourceUrl;
					}
					resourceInfoObject[resourceModule] = subMenuObj;
				}
				leftMenuRef.constructLeftMenu(resourceInfoObject);
			}
		},
		constructLeftMenu : function(leftMenuObject){
			var leftMenuObjectArray=_.keys(leftMenuObject);
			var leftMenuLiArray = $('#leftMenuUL').find('li');
			var leftMenuIdArray = [];
			for(var index=0 ; index<leftMenuLiArray.length;index++){
				var liMenu = leftMenuLiArray[index];
				var id = $(liMenu).attr('id');
				if(typeof(id) != 'undefined' && id.indexOf('time_li') == -1){
					leftMenuIdArray.push(id);
				}
			}
			for(var index in leftMenuObject){
				var mainMenu = index;
				var subMenu = leftMenuObject[index];
				if(_.keys(subMenu).length > 1){
					var html = "";
					for(var resource in subMenu){
						var url = leftMenuRef.baseUrl + subMenu[resource];
						html += '<li><a href="'+url+'">'+resource+'</a></li>';
					}
					$('#'+mainMenu).find('ul').html(html);
				}
				$('#'+mainMenu).show();
			}
			for(var index=0;index<leftMenuIdArray.length;index++){
				var id= leftMenuIdArray[index];
				if(typeof(id) != 'undefined' && id.indexOf('time_li') == -1){
					if(!_.contains(leftMenuObjectArray,id)){
						$('#'+id).remove();
					}	
				}
			}
		}
}