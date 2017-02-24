<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/custom/custom_build_test_plan_module.js" var="customBuilTestPlanModuleJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_build_test_plan.js" var="customBuilTestPlanJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_item_details.js" var="customItemDetailsJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_resource_table.js" var="customResourceDetailsJS" />

<script src="${customBuilTestPlanModuleJS}"></script>

<script src="${customBuilTestPlanJS}"></script>

<script src="${customItemDetailsJS}"></script>

<script src="${customResourceDetailsJS}"></script>

<div class="modal fade" id="build_test_plan" tabindex="-1" role="dialog" aria-labelledby="buildTestPlanModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="width: 124%;margin: -5px -118px;border-radius: 0px;">
			<div id="btp_modal_header_id" class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Build Test Plan Details</h4>
				<div id="buildTestPlanDiv"></div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="delete_rows_modal" tabindex="-1" role="dialog" aria-labelledby="buildTestPlanModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div id="row_delete_confirm_div" class="modal-content" style="top: 122px;width: 53%;left: 24%;display:none;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">(x)</button>
					<h2 class="modal-title" style="font-size: 19px;color: rgba(31, 33, 33, 0.52);">Delete BTP Rows</h2>
				</div>
				<form role="form" class="form-horizontal" id="btp_report_export_form">
					<div class="modal-body">
						<h4 style="color: #2196f3;font-size: 17px;">Do you want to delete selected rows ?</h4>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="delete_btp_rows">Yes</button>
						<button type="button" class="btn btn-danger" id="modal_close_button" data-dismiss="modal">No</button>
					</div>
				</form>
		</div>
	</div>
</div>

		

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">Build Test Plan
				<!-- <a id="exportTestPlanDataId" type="button" class="btn  btn-3d btn-default pull-right" style="margin: 0px 5px;">Export</a> -->
				<a id="addTestPlanDataId" type="button"  data-target="#build_test_plan" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add new BTP details">Add</a>
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> Build Test Plan
			</p>
		</div>
	</div>
</div>

<div id="loader_div" style="width: 149px;height: 149px; left: 54%;position: fixed; z-index: 1000;top: 42%;
    background: rgba( 255, 255, 255, .8 ) url('../resources/miminiumTheme/img/gears.gif') 50% 50% no-repeat;display:none;"></div>

<div class="col-md-12 top-20" id="applyFilter" style="display:none;">
	<div class="panel" style="padding: 50px 0px;padding-bottom: 73px;margin: 13px 0px;">
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Project</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_projectId" name="projectName" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Plan</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_planId" name="btpPlan" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Build No</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<input id="filter_buildNoId" name="buildNo" type="text" class="input-sm form-control imp" value="">
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Status</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_statusId" name="btpStatus" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Start Date</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<input id="filter_startDateId" name="startDate" type="date" class="input-sm form-control imp">
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">End Date</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<input id="filter_endDateId" name="endDate" type="date" class="input-sm form-control imp">
		</div>
		<div class="col-md-3 top-20" style="margin-top: 11px;margin-left: 0px;">
			<input id="apply_filter" type="button" class="btn btn-success" value="Filter" style="margin-right: 13px;"> 
  			<input id="clear_filter" type="button" class="btn btn-default" value="Clear" style="">
		</div>
	</div>
</div>    


<div class="col-md-12 top-20" id="btpMainDiv" style="display:none;">
	<div class="panel" style="padding: 15px; padding-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-striped table-hover table-bordered listView-table" id="build_test_plan_table_id">
				<thead id="thead_id">
					<tr>
						<th>S.No</th>
						<th>Project</th>
						<th>Plan</th>
						<th>Build No</th>
						<th>Status</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Revised End Date</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody_id">
				</tbody>
			</table>
		</div>
		
		<c:choose>
 			<c:when	test='${sessionScope.roleId.equals("ROLE_SUPER_ADMIN")}'>
 				<input id="delete_btp_button" type="button" value="Delete" class="btn btn-danger" style="margin-right: 13px;display:none;">
 			</c:when>
 	    </c:choose>
		
	
	</div>
</div>

<input type="button" id="editBuildTestPlanTrigger" data-toggle="modal" data-target="#build_test_plan" style="display:none" />

<input type="button" id="addBuildTestPlanTrigger" data-toggle="modal" data-target="#build_test_plan" style="display:none" />

<input type="button" id="showDeleteRowModal" data-toggle="modal" data-target="#delete_rows_modal" style="display:none" />

<input type="hidden" id="selectedBtpGKey" />

<input type="hidden" id="selectedItemNo" />

<input type="hidden" id="selectedResourceGKey" />

<input type="hidden" id="itemCountId" />

<input type="hidden" id="accEffortId" />

<form id="buildTestPlanForm" name="buildTestPlanForm" role="form" class="form-horizontal" action="#" method="post" style="display:none;">
	
	<div id="alertcustom"></div>
	
	<input type="hidden" id="selectedRowKeyInput" value="" />
	
	<div class="form-element">
		<div class="col-md-12" style="padding-right: 2px;padding-left: 1px;width: 103%;margin: 16px -16px;">
			<div class="col-md-12 panel">
				<div class="col-md-12" id="accordion" style="margin-top: 10px;">
					<h5>
						<a href="#" class="" aria-expanded="true" style="cursor: text;">Build Test Plan</a>
					</h5>
					<div id="collapse1" class="panel-collapse collapse in" style="border-style: ridge; border-width: thin;" aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-12 padding-0">
								<div class="col-md-8 padding-0">
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Project:*</label>
												<div class="col-sm-3">
													<select id="projectId" name="project" class="input-sm form-control imp"></select>
												</div>
												<label class="col-sm-2 control-label">Phase:*</label>
												<div class="col-sm-3">
													<select id="phaseId" name="phase" class="input-sm form-control imp"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Plan:*</label>
												<div class="col-sm-3">
													<select id="planId" name="plan" class="input-sm form-control imp"></select>
												</div>
												<label class="col-sm-2 control-label">Status:*</label>
												<div class="col-sm-3">
													<select id="statusId" name="status" class="input-sm form-control imp"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Cycle:*</label>
												<div class="col-sm-3">
													<input id="cycleId" name="cycle" type="text" class="input-sm form-control imp" value="">
												</div>
												<label class="col-sm-2 control-label">Sprint/Iteration:*</label>
												<div class="col-sm-3">
													<input id="iteration_id" name="iteration" type="text" class="input-sm form-control imp" value="">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Build No:*</label>
												<div class="col-sm-3">
													<input id="buildNoId" name="buildNo" type="text" class="input-sm form-control imp" value="">
												</div>
												<label class="col-sm-2 control-label">Remarks:</label>
												<div class="col-sm-3">
													<input id="remarksId" name="remarks" type="text" class="input-sm form-control" value="">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Start Date:*</label>
												<div class="col-sm-3">
													<input id="startDateId" name="startDate" type="date" class="input-sm form-control imp">
												</div>
												<label class="col-sm-2 control-label">End Date:*</label>
												<div class="col-sm-3">
													<input id="endDateId" name="endDate" type="date" class="input-sm form-control imp">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Revised End Date:</label>
												<div class="col-sm-3">
													<input id="revisedEndDateId" name="revisedEndDate" type="date" class="input-sm form-control">
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- class="col-md-8" -->
								
								<div class="col-md-4 padding-0"     style="overflow-y: auto;height: 237px;">
									<div class="table-responsive" >
										<table id="resourceMgmtTableId" class="table table-condensed" cellspacing="0" width="100%">
											<tbody>
												<tr id="resourcetr_1">
													<td><label class="control-label">Resource1:*</label></td>
													<td id="resourceNameTD"><input id="resource1Id" name="do_nbr" type="text" class="form-control form-control3 imp" value="DO001"></td>
													<td style="text-align: -webkit-center;"><a href="#"><span data-href="#" class="fa fa-plus" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add Fields"></span></a> <span>&nbsp;</span><a href="#"><span class="fa fa-remove" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="Remove Field"></span></a></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- class="col-md-4" -->
							</div>
							<!-- class="col-md-12" -->
						</div>
						<!-- class="panel-body" -->
					</div>
					
					<div id="itemDeatilsParentDivId" style="display:none;">
					<h5>
						<a href="#" class="" aria-expanded="true"> Item Details </a>
						<span class="glyphicon glyphicon-edit" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="EDIT ITEM" style="left: 86%;"></span>
						<span class="glyphicon glyphicon-check" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="SAVE ITEM" style="left: 82%;"></span>
						<span class="glyphicon glyphicon-trash" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="DELETE ITEM" style="left: 85%;"></span>
						<span class="glyphicon glyphicon-link" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="SHOW LINKED RESOURCE" style="left: 76%;"></span>
						<span class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="DISCARD ITEM" style="left: 72%;"></span>
					</h5>
					<div id="collapse2" class="panel-collapse collapse in" style="border-style: ridge; border-width: thin;" aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-12">
								<div class="form-group">
									<div class="row">
										<div class="table-responsive">
										
												<!-- <div class="col-md-12 padding-0">
													<div class="pull-left" style="float: right!important;margin: 3px;top: -2px;right: 2px;position: relative;">
														<button type="button" id="addItemDetailRowButton" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add Item Detail Row" >
															<i class="fa fa-plus" aria-hidden="true"></i>
														</button>
													</div>
												</div> -->
												
												<table id="itemMgmtTableId" class="table table-striped table-hover table-bordered table-condensed" cellspacing="0" width="100%">
													<!--  class="table table-striped table-bordered listView-table" -->
													<thead>
														<tr>
															<th>S.No</th>
															<th>Item Description</th>
															<th>Item Count</th>
															<th>Effort Cost</th>
															<th>Effort Actual</th>
															<th>Status</th>
															<th>Remarks</th>
															<th style="width: 111px;"></th>
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
														<button type="button" id="addItemDetailRowButton"  onclick="addItemDetailsRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add item details row">
															<i class="fa fa-plus" aria-hidden="true"></i>
														</button>
													</div>
												</div>
											
										</div>
									</div>
								</div>

								<div id="plan" style="">
									<!-- 									<div class="col-md-12 padding-0">
										<div class="pull-right">
											<button type="button" id="addDelOrder"
												class="btn btn-sm btn-primary" title="New Date">
												<i class="fa fa-calendar-plus-o" aria-hidden="true"></i>
											</button>
										</div>
									</div> -->
									
									<div class="col-md-12 padding-0">
										
									</div>

								</div>
								<!-- id="plan" -->
							</div>
							<!--  class="col-md-12" -->
						</div>
					</div>
					</div>
					
					<div id="resourceDeatilsParentDivId" style="display:none;">
					<h5>
						<a href="#" class="" aria-expanded="true">Resource Details</a>
						<span class="glyphicon glyphicon-edit" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="EDIT RESOURCE" style="left: 83%;"></span>
						<span class="glyphicon glyphicon-check" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="SAVE RESOURCE" style="left: 79%;"></span>
						<span class="glyphicon glyphicon-trash" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="DELETE RESOURCE" style="left: 82%;"></span>
						<span class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="auto top" title="" data-original-title="DISCARD RESOURCE" style="left: 73%;"></span>
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
															<button type="button" id="addResourceDetRowButton" onclick="addResourceDetRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add new resource detail">
																<i class="fa fa-plus" aria-hidden="true"></i>
															</button>
														</div>
														<div id="pull-right" style="float: right!important;">
															<button type="button" id="backToItemDeatilsButton" onclick="backToItemDeatils()" class="btn btn-sm btn-danger" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Back to item details">
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
								<!-- id="plan" -->
							</div>
							<!--  class="col-md-12" -->
						</div>
					</div>
					</div>
					

				</div>
				<!-- id="accordion" -->
				<div class="form-group" style="margin-bottom: 10px;">
					<div class="col-md-8" style="float: right;margin-right: 1.2em;margin-top: 1em;" >
						<input data-dismiss="modal" id="cancel_button" type="reset" class="btn btn-default" value="Cancel" style="float: right;"> 
						<span></span> 
						<input id="save_button" onclick="addOrUpdateBtp()" class="btn btn-primary" value="Save" style="float: right; margin-right: 1em;width: 77px;">
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>

	</div>
</form>

