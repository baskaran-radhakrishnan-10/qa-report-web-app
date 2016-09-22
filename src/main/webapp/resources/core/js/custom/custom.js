var openTags = { Ordered_List: '<ol>', Unordered_List: '<ul>', List: '<li>'};

var closeTags = { Ordered_List: '</ol>', Unordered_List: '</ul>', List: '</li>'};

/*$(function() {

	$('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
//Sets the min-height of #page-wrapper to window size
$(function() {
	$(window).bind("load resize", function() {
		topOffset = 50;
		width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
		if (width < 768) {
			$('div.navbar-collapse').addClass('collapse');
			topOffset = 100; // 2-row-menu
		} else {
			$('div.navbar-collapse').removeClass('collapse');
		}

		height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
		height = height - topOffset;
		if (height < 1) height = 1;
		if (height > topOffset) {
			$("#page-wrapper").css("min-height", (height) + "px");
		}
	});

	var url = window.location;
	var element = $('ul.nav a').filter(function() {
		return this.href == url || url.href.indexOf(this.href) == 0;
	}).addClass('active').parent().parent().addClass('in').parent();
	if (element.is('li')) {
		element.addClass('active');
	}
});
*/
function outputMessage(status,message){
	
	var className = "";
	if(status == 'Success'){
		className = 'alert alert-success';
	}else{
		className = 'alert alert-warning';
	}
	var htmlCode="";
	htmlCode ='<div style="padding-left: 5px; padding-right: 5px;">'
		+'<div class="'+className+' fade in">'
		+' <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
		+'<strong>'+status+'! </strong>'+ message
		+'</div>'
		+'</div>';

//	$('#alertcustom').find('#alertcustomvalue').html(htmlCode);
	document.getElementById("alertcustom").innerHTML =htmlCode;
}

function fetchPageContent(pageName,async){
	var data = {}
//	data["page"] = "Quiz Instruction Page";
	data["page"] = pageName;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : getApplicationRootPath()+"loadContent",
		data : JSON.stringify(data),
		dataType : 'json',
		async : async,
		success : function(data) {
			console.log("SUCCESS: ", data);
			constructPageData(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function constructPageData(data){

	var contentList = [];
	contentList = data;

	for (var i = 0; i < contentList.length; i++) {
		var viewType = "";
		var htmlData = "";
		var placeHolder = "";
		viewType = contentList[i]['contentViewType'];
		placeHolder = contentList[i]['placeHolder'];

		if(viewType == "Text"){
			htmlData = contentList[i]['contentsGkey']['text'];
		} else {
			htmlData = htmlCodeForList(contentList[i]['contentsGkey']['text'],".",viewType)
		}
		document.getElementById(placeHolder).innerHTML=document.getElementById(placeHolder).innerHTML+htmlData;
	}
	
}

function htmlCodeForList(data,splitBy,viewType){

	var htmlCode = "";
	htmlCode = openTags[viewType];
	var dataArray = [];
	dataArray = data.split(splitBy);
	for (var i = 0; i < dataArray.length; i++) {
		if(typeof dataArray[i] != 'undefined' && null != dataArray[i] && "".intern != dataArray[i]){
			htmlCode = htmlCode + openTags['List']+ dataArray[i] + closeTags['List'];
		}
	}
	htmlCode = htmlCode + closeTags[viewType];

	return htmlCode;

}

function getApplicationRootPath(){
	var href=window.location.href;
	var rootPath=href.substring(0,href.lastIndexOf("/")+1);
	return rootPath;
}

$("#add_button").click(function() {
	 var url = $(this).attr('data-href');
	$.ajax({
        url: getApplicationRootPath() + url,
        method: 'GET',
        dataType : "text",
        async : false,
        success : function(data) {
			console.log("SUCCESS: ", data);
			$('#myModal').modal('show');
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
    });
});

//$("#edit_button").click(function() {
//	$('#myModal').modal('show');
//});


$("#cancel_button").click(function() {
//	var value = $(this).attr('data-href');
//	window.document.location = getApplicationRootPath()+$(this).data("href");
	$('#myModal').modal('hide');
});

$('#myModal').on('hidden.bs.modal', function(){
	var url = $(this).data("href");
	if(null != url){
		window.document.location = getApplicationRootPath()+url;
	}
});

$('#myModal').on('shown.bs.modal', function () {
    $(this).find('.modal-dialog').css({height:'auto', 
                              'max-height':'100%'});
});
