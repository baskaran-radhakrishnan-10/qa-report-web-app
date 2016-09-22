$(document).ready(function($) {

	$("#add_button").on( "click", function( event ) {
		console.log("add new company clicked !!!!");
		createNewCompany();
	});

//	$("#save_button").on( "click", function( event ) {
//		console.log("save button clicked !!!!");
//		saveCompanyData();
//	});

	$("#cancel_button").on( "click", function( event ) {
		console.log("cancel clicked !!!!");
		fetchCompanyDetails();
	});

	$('[data-toggle="addcompanytooltip"]').tooltip(); 

	$("#accountId").change(function(){  
		var accId = $(this).val();  
		if(accId != ""){
			$("#accId").val(accId);
		}else{
			$("#accId").val("");;
		}

	}); 

	$("#userId").change(function(){  
		var uname = $("#accountId").val() + $(this).val();  
		if(uname != ""){
			if(uname != userId){	
				if(uname.length >= 3){  
					$(".status").html("<font color=gray> Checking availability.</font>");  
					checkComapnyUserNameAvailability(uname);    
				}  
				else{  
					$(".status").html("<font color=red> < <b>4</b> character.</font>");  
				}  
			}else{
				$(".status").html("");  
			}
		}else{
			$(".status").html("<font color=red>Empty is invalid.</font>");
		}

	});

//	fetchCompanyDetails();
});

function checkComapnyUserNameAvailability(uname){
	var data = {}
	data["userId"] = uname;
	$.ajax({  
		type : "POST",
		contentType : "application/json",
		url : getApplicationRootPath()+"checkComapnyUserNameAvailability",
		data : JSON.stringify(data),
		dataType : 'json',
        success: function(msg){  
                $(".status").html(msg['MSG']);  
        },
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}  
    });
}

function fetchCompanyDetails(){
	var data = {}
	data["gkey"] = 1003;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : getApplicationRootPath()+"companyDetails",
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			constructCompanyData(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function createNewCompany(){
	var data = {}
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : getApplicationRootPath()+"newCompany",
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			newCompanyData();
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function newCompanyData(){

	$('#name').val("");
	$('#accountId').val("");
	$("#accId").val("");
	$('#userId').val("");
	$('#address_line1').val("");
	$('#address_line2').val("");
	$('#city').val("");
	$('#state').val("");
	$('#country').val("");
	$('#pincode').val("");
	$('#phone_no').val("");
	$('#fax').val("");
	$('#email_id').val("");
	$('#website').val("");
	$('#notes').val("");
}

function constructCompanyData(serverdata){

	var company = serverdata;
	var location = company['primaryLocation']
	var user = company['user']
	if(typeof company != 'undefined' && null != company){
		$('#name').val(company['name']);
		$('#accountId').val(company['accountId']);
		$("#accId").val(company['accountId']);

		if(typeof user != 'undefined' && null != user){
			$('#userId').val(company['userId']);
		}

		if(typeof location != 'undefined' && null != location){
			$('#address_line1').val(location['address1']);
			$('#address_line2').val(location['address2']);
			$('#city').val(location['city']);
			$('#state').val(location['state']);
			$('#country').val(location['country']);
			$('#pincode').val(location['pincode']);
			$('#phone_no').val(location['phone']);
			$('#fax').val(location['fax']);
			$('#email_id').val(location['email']);
			$('#website').val(location['website']);
		}
		$('#notes').val(company['notes']);
	}
}


function saveCompanyData(){

	var data = {}
	data["name"] = $('#name').val();
	data["accountId"] = $('#accountId').val();
	data["userId"] = $('#userId').val();
	data["address_line1"] = $('#address_line1').val();
	data["address_line2"] = $('#address_line2').val();
	data["city"] = $('#city').val();
	data["state"] = $('#state').val();
	data["country"] = $('#country').val();
	data["pincode"] = $('#pincode').val();
	data["phone_no"] = $('#phone_no').val();
	data["fax"] = $('#fax').val();
	data["email_id"] = $('#email_id').val();
	data["website"] = $('#website').val();
	data["notes"] = $('#notes').val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : getApplicationRootPath()+"saveCompanyDetails",
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			outputMessage("Success",$('#name').val()+" details saved sucessfully.");;
		},
		error : function(e) {
			console.log("ERROR: ", e);
			outputMessage("Error",$('#name').val()+" details failed to be saved.");
		},
		done : function(e) {
			console.log("DONE");
		}
	});

}



function getApplicationRootPath(){
	var href=window.location.href;
	var rootPath=href.substring(0,href.lastIndexOf("/")+1);
	return rootPath;
}

