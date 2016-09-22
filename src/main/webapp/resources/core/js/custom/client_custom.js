$(document).ready(function($) {
	$('#clientList').DataTable({
		scrollY:        '65vh',
		"scrollX": true,
        scrollCollapse: true,
		"paging": false,
		info: false
    } );
	
	$('.edit_button').on('click', function() {
        // Get the record's ID via attribute
        var url = $(this).attr('data-href');

        $.ajax({
            url: getApplicationRootPath() + url,
            method: 'GET',
            dataType : 'json',
        }).success(function(response) {
        	var client = response;
            // Populate the form fields with the data returned from server
        	$('#nameField').val(client['name']);
        	$('#phoneField').val(client['phone']);
        	$('#emailField').val(client['email']);
        	$('#clientIdField').val(client['clientId']);
        	$('#address1Field').val(client['address1']);
        	$('#cityField').val(client['city']);
        	$('#address2Field').val(client['address2']);
        	$('#stateField').val(client['state']);
        	$('#countryField').val(client['country']);
        	$('#pincodeField').val(client['pincode']);
        	$('#faxField').val(client['fax']);
        	$('#websiteField').val(client['website']);
        	$('#notesField').val(client['notes']);
        	$('#myModal').modal('show');
        });
    });
});

function getApplicationRootPath(){
	var href=window.location.href;
	var rootPath=href.substring(0,href.lastIndexOf("/")+1);
	return rootPath;
}

