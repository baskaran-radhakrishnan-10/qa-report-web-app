function fetchResourceByBtpItemNo(btpNo,itemNo){
	var data={};
	data['btpNo']=btpNo;
	data['itemNo']=itemNo;
	data=JSON.stringify(data);
	ajaxHandler("POST", data, "application/json", getApplicationRootPath()+"resource_details/getResourceDetails", 'json', null, fetchResourceByBtpItemNoSuccess,true);
}

function fetchResourceByBtpItemNoSuccess(serverData){
	if('ERROR' != serverData['STATUS']){
		var resourceObjList=serverData['SERVER_DATA'];
		for(var index in resourceObjList){
			var resourceObj=resourceObjList[index];
		}k
	}
}
