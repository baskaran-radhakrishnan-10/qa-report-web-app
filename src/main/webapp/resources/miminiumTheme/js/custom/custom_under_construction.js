$(document).ready(function(){
	var currentActionPath = $('#pathValue').val();
	var pathArray = currentActionPath.split("/");
	if(pathArray.length > 1){
		$('#page_path_id').html('Home <span class="fa-angle-right fa"></span> '+pathArray[0]+' <span class="fa-angle-right fa"></span> '+pathArray[1]+'');
	}else{
		$('#page_path_id').html('Home <span class="fa-angle-right fa"></span> '+pathArray[0]+'');
	}
});