<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/common/common.js" var="commonJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_manage_users.js" var="customUsersRolesJS" />

<spring:url	value="/resources/miminiumTheme/js/plugins/notify/notifIt.js"	var="notifyJs" />

<script src="${notifyJs}"></script>

<script src="${commonJS}"></script>

<script src="${customUsersRolesJS}"></script>

<div class="modal fade" id="add_user_details" tabindex="-1" role="dialog" aria-labelledby="buildTestPlanModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="width: 124%;margin: -5px -118px;border-radius: 0px;">
			<div id="btp_modal_header_id" class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Add User</h4>
				<div id="addUserDetailsDiv"></div>
			</div>
		</div>
	</div>
</div>

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">User Details
			<!-- <a id="exportUserDetailsId" type="button" href="ser_det_page" class="btn  btn-3d btn-default pull-right" style="margin: 0px 5px;">Export</a> -->
			<!-- <a id="addUserDetailsId" type="button" data-toggle="modal" data-target="" href="ser_det_page" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;">Add</a> -->
			</h3>
			<!-- <p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> Serial Numbers
			</p> -->
		</div>
	</div>
</div>

<div id="alertcustom"></div>

<div class="col-md-12 top-20">
	<div class="panel" style="padding: 15px; padding-bottom: 20px;">
		<div class="table-responsive">
			
			<table class="table table-striped table-hover table-bordered listView-table" id="show-user-details-id">
				<thead id="thead_id">
					<tr>
						<th>S.No</th>
						<th>Name</th>
						<th>User ID</th>
						<th>Email ID</th>
						<th>Role</th>
						<th>Active</th>
						<th>Created On</th>	
						<th></th>											
					</tr>
				</thead>
				<tbody id="tbody_id"></tbody>
			</table>
			
			<div class="col-md-12 padding-0">
				<div class="pull-left">
					<button type="button" id="addUserRowButton"  onclick="addUserRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add Row">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
				</div>
			</div>
			
		</div>
	</div>
</div>
