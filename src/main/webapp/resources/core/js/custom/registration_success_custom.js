$(document).ready(function($) {
	$('#triggerModalDialog').trigger("click");
	$('#clientRegistrationModal').on("click",function(event){
		window.location.href=getApplicationRootPath()+"client_registeration";
	});
	$('#changePasswordModal').on("click",function(event){
		window.location.href=getApplicationRootPath()+"login";
	});
});
    
