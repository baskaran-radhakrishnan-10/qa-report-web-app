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

	$('#orgNameIdField').on('input', function() {
		var input=$(this);
		var is_name=input.val();
		$(input).attr("placeholder", "Organisation Name");
		if(is_name){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});

	$('#nameIdField').on('input', function() {
		var input=$(this);
		var is_name=input.val();
		$(input).attr("placeholder", "User Name");
		if(is_name){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});

	$('#phoneIdField').on('input', function() {
		var input=$(this);
		var is_name=input.val();
		$(input).attr("placeholder", "Contact Number");
		if(is_name){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});
	
	$('#emailIdField').on('click', function() {
		var input=$(this);
		if(input.val().indexOf("Invalid Email Id") != -1){
			input.val("");
			$(input).attr("placeholder", "Email Id is required");
		}
	});

	$('#emailIdField').on('input', function() {
		var input=$(this);
		$(input).attr("placeholder", "Email Id");
		var is_email=isValidEmail(input.val());
		if(is_email){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});

	$("#submitField").click(function(event){
		var form_data=$("#client_registration_form").serializeArray();
		var error_free=true;
		for (var input in form_data){
			var element=$("#"+form_data[input]['name']+"IdField");
			//console.log($(element).prop('placeholder'));
			if(element['selector'].indexOf('#acceptIdField') == -1){
				var placeholder=$(element).prop('placeholder');
				var valid=element.hasClass("valid");
				var input=$(element).val();
				console.log("placeholder :"+placeholder);
				console.log("valid :"+valid);
				console.log("input :"+input);
				if (!valid && !input){
					error_free=false;
				}
				if(element['selector'].indexOf('#emailIdField') != -1 && !valid && input){
					console.log("inside email validator*******");
					valid=isValidEmail($(element).val());
					if(!valid){
						$(element).val("Invalid Email Id");
						$('#errorMessageUl').find('span').text("");
						$(element).css('background-color','rgba(255, 0, 0, 0.33)');
						error_free=false;
					}
				}
				else if(!valid && !input){
					console.log("inside empty text validator*******");
					if($(element).attr("placeholder").indexOf(" is required") == -1){
						$(element).attr("placeholder", placeholder+" is required");
					}
					$('#errorMessageUl').find('span').text("");
					$(element).css('background-color','rgba(255, 0, 0, 0.33)');
				}else{
					$(element).css('background-color','#FFFFFF');
				}
			}
		}
		if (!error_free){
			event.preventDefault(); 
		}
		else{
			if(!$('#acceptCheckBox').prop('checked')){
				$('#errorMessageUl').find('span').text("Please accept Terms & Conditions");
				event.preventDefault(); 
			}
		}
	});


});
