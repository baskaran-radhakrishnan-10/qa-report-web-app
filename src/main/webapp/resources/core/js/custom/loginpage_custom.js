$(document).ready(function($) {

	var obj=$('form').find('span');

	for(var i=0 ; i < obj.length; i++){

		var span=obj[i];

		console.log(span);

		var errorMessage=$(span).html().split('<br>')[0];

		$(span).html(errorMessage);

		$(span).css("color","red");

	}

	var loginView = $("#loginView").val();

	if(typeof loginView != 'undefined' && null != loginView && "" != loginView){

		$('.login').css('display','none');
		$('.exit').css('display','');
		fetchPageContent("Quiz Exit Page",false);

	}else{
		$('.login').css('display','');
		$('.exit').css('display','none');
	}

	$('#accountIdField').on('input', function() {
		$(this).css('background-color','rgba(0, 0, 0, 0)');
	//	$(this).attr("placeholder", "Account ID");
		checkInput(this);
	});

	$('#userIdField').on('input', function() {
		$(this).css('background-color','rgba(0, 0, 0, 0)');
	//	$(this).attr("placeholder", "User ID");
		checkInput(this);
	});

	$('#passwordIdField').on('input', function() {
		$(this).css('background-color','rgba(0, 0, 0, 0)');
	//	$(this).attr("placeholder", "Password");
		checkInput(this);
	});

	$('#oldPasswordIdField').on('input', function() {
		$(this).css('background-color','rgba(0, 0, 0, 0)');
		$(this).attr("placeholder", "Current Password");
		checkInput(this);
	});

	$('#newPasswordIdField').on('input', function() {
		$(this).css('background-color','rgba(0, 0, 0, 0)');
		$(this).attr("placeholder", "New Password");
		checkInput(this);
	});

	$('#confirmPasswordPasswordIdField').on('input', function() {
		$(this).css('background-color','rgba(0, 0, 0, 0)');
		$(this).attr("placeholder", "Confirm Password");
		checkInput(this);
	});

	$("#submitField").click(function(event){
		var form_data=$("#loginForm").serializeArray();
		var error_free=true;
		var newPasswordText=null;
		var confirmPasswordText=null;
		for (var input in form_data){
			var element=$("input[name='"+form_data[input]['name']+"']");
			var valid=element.hasClass("valid");
			var input=$(element).val();
			var placeholder=$(element).attr("placeholder");
			if(element['selector'].indexOf("input[name='newPassword']") != -1){
				newPasswordText=input;
			}
			if(element['selector'].indexOf("input[name='confirmPassword']") != -1){
				confirmPasswordText=input;
			}
			if(!valid && !input){
				if($(element).attr("placeholder").indexOf(" is required") == -1){
					$(element).attr("placeholder", placeholder+" is required");
				}
				$('#errorMessageUl').find('span').text("");
				$(element).css('background-color','rgba(255, 0, 0, 0.33)');
			}else{
				$(element).css('background-color','rgba(0, 0, 0, 0)');
			}
			if (!valid && !input){
				error_free=false;
			}
		}
		if (!error_free){
			event.preventDefault(); 
		}else{
			console.log("newPasswordText :"+newPasswordText);
			console.log("confirmPasswordText :"+confirmPasswordText);
			if(null != newPasswordText && null != confirmPasswordText && newPasswordText != confirmPasswordText){
				$('#errorMessageUl').find('span').text("New Password & Confirm Password ​must ​match​");
				event.preventDefault(); 
			}else{
				/*if (!$("#newPasswordIdField").filter(function () {
					//$\S*(?=\S{8,})(?=\S*[a-z])(?=\S*[A-Z])(?=\S*[\d])(?=\S*[\W])\S*$
					///^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=\S*[\W])[0-9a-zA-Z]{6,8}$/
			        return this.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,8}$/);
			    })) {
					$('#errorMessageUl').find('span').text("New Password should have <br> 6 to 8 Characters length  <br> 1 Uppercase Letter &amp; 1 Lowercase Letter <br> 1 Special Character &amp; 1 Numeric Letter");
					event.preventDefault(); 
			    } */
				$("#newPasswordIdField").each(function () {
			        var validated =  true;
			        if(!(this.value.length <= 8 && this.value.length >= 6)){
			            validated = false;
			        }
			        if(!/\d/.test(this.value)){
			        	validated = false;
			        }
			        if(!/[a-z]/.test(this.value)){
			        	validated = false;
			        }
			        if(!/[A-Z]/.test(this.value)){
			        	 validated = false;
			        }
			        if(/[^0-9a-zA-Z]/.test(this.value)){
			        	 validated = false;
			        }
			        if(!validated){
			        	//event.preventDefault(); 
			        }
			    });
			}
		}
	});


});

function checkInput(thisObj){
	var input=$(thisObj);
	var is_name=input.val();
	if(is_name){input.removeClass("invalid").addClass("valid");}
	else{input.removeClass("valid").addClass("invalid");}
}
