<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/common/common.js" var="commonJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_manage_users.js" var="customUsersRolesJS" />

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

<input type="button" id="addUserDetailsTrigger" data-toggle="modal" data-target="#add_user_details" style="display:none" />


<!-- 
<form id="addUserDetailsForm" role="form" class="form-horizontal" action="#" method="post">
	
	<div id="alertcustom"></div>
	
	<input type="hidden" id="selectedRowKeyInput" value="" />
	
	<div class="form-element">
		<div class="col-md-12" style="padding-right: 2px;padding-left: 1px;width: 103%;margin: 16px -16px;">
			<div class="col-md-12 panel">
				<div class="col-md-12" id="accordion" style="margin-top: 10px;">
					<h5>
						<a href="#" class="" aria-expanded="true" style="cursor: text;"> </a>
					</h5>
					<div id="collapse1" class="panel-collapse collapse in" style="border-style: ridge; border-width: thin;" aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-12 padding-0">
								<div class="col-md-8 padding-0">
								
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Full Name:*</label>
												<div class="col-sm-3">
													<input id="fullNamemeId" name="fullName" type="text" class="input-sm form-control" value="">
												</div>
												<label class="col-sm-2 control-label">Email:*</label>
												<div class="col-sm-3">
													<input id="emailId" name="email" type="text" class="input-sm form-control" value="">
												</div>
												
											</div>
										</div>
									</div>
								
							
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">											
												<label class="col-sm-2 control-label">User ID:*</label>
												<div class="col-sm-3">
													<input id="userId" name="user" type="text" class="input-sm form-control" value="">
												</div>												
												<label class="col-sm-2 control-label">Role:*</label>
												<div class="col-sm-3">
													<select id="roleDescId" name="roleDesc" class="input-sm form-control"></select>
												</div>
											</div>
										</div>
									</div>
								
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Date Created:*</label>
												<div class="col-sm-3">
													<input id="createdOnId" name="createdOn" type="date" class="input-sm form-control" value="2016-09-21">
												</div>
											</div>
										</div>
									</div>
									
								</div>
								class="col-md-8"
								
								<div class="col-md-4 padding-0">
								
									<div class="table-responsive" >
										<table id="resourceMgmtTableId" class="table table-condensed" cellspacing="0" width="100%">
											<tbody>
												<tr id="resourcetr_1">
													<td><label class="control-label">Resource1:*</label></td>
													<td id="resourceNameTD"><input id="resourceId" name="do_nbr" type="text" class="form-control form-control3" value="DO001"></td>
													<td style="text-align: -webkit-center;"><a href="#"><span data-href="#" class="fa fa-plus" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Add Fields"></span></a> <span>&nbsp;</span><a href="#"><span class="fa fa-remove" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Remove Field"></span></a></td>
												</tr>
											</tbody>
										</table>
									</div>
									
								</div>
								class="col-md-4"
							</div>
							class="col-md-12"
						</div>
						class="panel-body"
					</div>
					
					<div id="itemDeatilsParentDivId" style="display:none;">
					<h5>
						<a href="#" class="" aria-expanded="true"> Item Details </a>
					</h5>
					<div id="collapse2" class="panel-collapse collapse in" style="border-style: ridge; border-width: thin;" aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-12">
								<div class="form-group">
									<div class="row">
										<div class="table-responsive">
										
												<div class="col-md-12 padding-0">
													<div class="pull-left" style="float: right!important;margin: 3px;top: -2px;right: 2px;position: relative;">
														<button type="button" id="addItemDetailRowButton" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add Item Detail Row" >
															<i class="fa fa-plus" aria-hidden="true"></i>
														</button>
													</div>
												</div>
												
												<table id="itemMgmtTableId" class="table table-striped table-hover table-bordered table-condensed" cellspacing="0" width="100%">
													 class="table table-striped table-bordered listView-table"
													<thead>
														<tr>
															<th>S.No</th>
															<th>Item Description</th>
															<th>Item Count</th>
															<th>Effort Cost</th>
															<th>Effort Actual</th>
															<th>Status</th>
															<th>Remarks</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<tr id="row_">
															<td id="sNo">1</td>
															<td id="itemDesc">new item description</td>
															<td id="itemCount">100</td>
															<td id="effortCost">3</td>
															<td id="effortActual">10</td>
															<td id="status">Active</td>
															<td id="remarks">Please do more test cases</td>
															<td style="text-align: -webkit-center;">
																<a id="itemRowEditId" href="#"> <span	class="glyphicon glyphicon-edit"></span></a> 
																<span>&nbsp;</span>
																<a id="itemRowSaveId" style="display:none;" href="#"> <span class="glyphicon glyphicon-check"></span></a>
																<span>&nbsp;</span>
																<a id="itemRowDeleteId" href="#"> <span	class="glyphicon glyphicon-trash"></span></a>
															</td>
														</tr>
													</tbody>

												</table>
												
												<div class="col-md-12 padding-0">
													<div class="pull-left">
														<button type="button" id="addItemDetailRowButton"  onclick="addItemDetailsRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add Row">
															<i class="fa fa-plus" aria-hidden="true"></i>
														</button>
													</div>
												</div>
											
										</div>
									</div>
								</div>

								<div id="plan" style="">
																		<div class="col-md-12 padding-0">
										<div class="pull-right">
											<button type="button" id="addDelOrder"
												class="btn btn-sm btn-primary" title="New Date">
												<i class="fa fa-calendar-plus-o" aria-hidden="true"></i>
											</button>
										</div>
									</div>
									
									<div class="col-md-12 padding-0">
										
									</div>

								</div>
								id="plan"
							</div>
							 class="col-md-12"
						</div>
					</div>
					</div>
					
					<div id="resourceDeatilsParentDivId" style="display:none;">
					<h5>
						<a href="#" class="" aria-expanded="true">Resource Details</a>
					</h5>
					<div id="collapse2" class="panel-collapse collapse in" style="border-style: ridge; border-width: thin;" aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-12">
								<div class="form-group">
									<div class="row">
										<div class="table-responsive">
										
												<table id="resourceMgmtTableId" class="table table-striped table-hover table-bordered table-condensed" cellspacing="0" width="100%">
														<thead>
															<tr>
																<th  style="width: 42px;">S.No</th>
																<th  style="width: 175px;">Resource Name</th>
																<th  style="width: 10px;">Item Count</th>
																<th  style="width: 10px;">Actual Time</th>
																<th  style="width: 10px;">Bugs Logged</th>
																<th  style="width: 10px;">Pass</th>
																<th  style="width: 10px;">Fail</th>
																<th  style="width: 10px;">Clarification</th>
																<th  style="width: 10px;">Unable to test</th>
																<th  style="width: 10px;">Pending</th>
																<th  style="width: 6px;">Blocked</th>
																<th  style="width: 50px;"></th>
															</tr>
														</thead>
														<tbody></tbody>
													</table>
													<div class="col-md-12 padding-0">
														<div id="pull-left" style="float: left!important;">
															<button type="button" id="addResourceDetRowButton" onclick="addResourceDetRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add Row">
																<i class="fa fa-plus" aria-hidden="true"></i>
															</button>
														</div>
														<div id="pull-right" style="float: right!important;">
															<button type="button" id="backToItemDeatilsButton" onclick="backToItemDeatils()" class="btn btn-sm btn-danger" title="">
																<i class="fa fa-reply-all" aria-hidden="true"></i>
															</button>
														</div>
													</div>
										</div>
									</div>
								</div>

								<div id="plan" style="">
									
									<div class="col-md-12 padding-0">
										
									</div>

								</div>
								id="plan"
							</div>
							 class="col-md-12"
						</div>
					</div>
					</div>
					

				</div>
				id="accordion"
				<div class="form-group" style="margin-bottom: 10px;">
					<div class="col-md-8" style="float: right; margin-right: 1em;">
						<input data-dismiss="modal" id="cancel_button" type="reset" class="btn btn-default" value="Cancel" style="float: right;"> <span></span> <input id="save_button" type="submit" class="btn btn-primary" value="Save" style="float: right; margin-right: 1em;">
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>

	</div>
</form>

 -->