<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<spring:url	value="/resources/miminiumTheme/js/custom/custom_manage_roles.js" var="customManageRolesJS" />



<script src="${customManageRolesJS}"></script>

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">Role Details
			<!-- <a id="exportUserDetailsId" type="button" href="ser_det_page" class="btn  btn-3d btn-default pull-right" style="margin: 0px 5px;">Export</a> -->
			<!-- <a id="addRoleDetailsId" type="button" data-toggle="modal" data-target="" href="ser_det_page" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;">Add</a> -->
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> Users & Roles <span class="fa-angle-right fa"></span> Manage Roles
			</p>
		</div>
	</div>
</div>

<div id="alertcustom"></div>

<div id="loader_div" style="width: 149px;height: 149px; left: 54%;position: fixed; z-index: 1000;top: 42%;
    background: rgba( 255, 255, 255, .8 ) url('../resources/miminiumTheme/img/gears.gif') 50% 50% no-repeat;display:none;"></div>
    
<div class="col-md-12 top-20" id="rolesMainDiv" style="display:none;">
	<div class="panel" style="padding: 15px; padding-bottom: 20px;">
		<div class="table-responsive">
			
			<table class="table table-striped table-hover table-bordered listView-table" id="show-role-details-id">
				<thead id="thead_id">
					<tr>
						<th>S.No</th>
						<th>Role Name</th>
						<th>Role Description</th>				
					</tr>
				</thead>
				<tbody id="tbody_id"></tbody>
			</table>
			
	<!-- 		<div class="col-md-12 padding-0">
				<div class="pull-left">
					<button type="button" id="addUserRowButton"  onclick="addUserRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add Row">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
				</div>
			</div> -->
			
		</div>
	</div>
</div>
