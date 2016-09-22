<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/common/common.js" var="commonJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_build_test_plan.js" var="customBuilTestPlanJS" />

<script src="${commonJS}"></script>

<script src="${customBuilTestPlanJS}"></script>

<!-- Modal -->
<div class="modal fade" id="build_test_plan" tabindex="-1" role="dialog" aria-labelledby="buildTestPlanModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="build_test_plan/show">	
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="width: 124%;margin: -5px -118px;border-radius: 0px;">
			<div id="btp_modal_header_id" class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Build Test Plan Details</h4>
				<div id="buildTestPlanDiv"></div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">Build Test Plan
			<a id="addTestPlanDataId" type="button" href="ser_det_page" class="btn  btn-3d btn-default pull-right" style="margin: 0px 5px;">Export</a>
			<a id="exportTestPlanDataId" type="button" data-toggle="modal" data-target="#build_test_plan" href="ser_det_page" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;">Add</a>
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
						<th>Revised Date</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="tbody_id"></tbody>
			</table>
		</div>
	</div>
</div>

<input type="button" id="editBuildTestPlanTrigger" data-toggle="modal" data-target="#build_test_plan" style="display:none" />

<form id="buildTestPlanForm" role="form" class="form-horizontal" action="#" method="post">
	
	<div id="alertcustom"></div>
	
	<div class="form-element">
		<div class="col-md-12" style="padding-right: 2px;padding-left: 1px;width: 103%;margin: 16px -16px;">
			<div class="col-md-12 panel">
				<div class="col-md-12" id="accordion" style="margin-top: 10px;">
					<h5>
						<a href="#" class="" aria-expanded="true" style="cursor: text;"> Build Test Plan Details </a>
					</h5>
					<div id="collapse1" class="panel-collapse collapse in" style="border-style: ridge; border-width: thin;" aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-12 padding-0">
								<div class="col-md-8 padding-0">
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Project:*</label>
												<div class="col-sm-3">
													<select id="projectId" name="project" class="input-sm form-control"></select>
												</div>
												<label class="col-sm-2 control-label">Phase:*</label>
												<div class="col-sm-3">
													<select id="phaseId" name="phase" class="input-sm form-control"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Plan:*</label>
												<div class="col-sm-3">
													<select id="planId" name="plan" class="input-sm form-control"></select>
												</div>
												<label class="col-sm-2 control-label">Status:*</label>
												<div class="col-sm-3">
													<select id="statusId" name="status" class="input-sm form-control"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Cycle:*</label>
												<div class="col-sm-3">
													<input id="cycleId" name="cycle" type="text" class="input-sm form-control" value="">
												</div>
												<label class="col-sm-2 control-label">Sprint/Iteration:*</label>
												<div class="col-sm-3">
													<input id="iteration_id" name="iteration" type="text" class="input-sm form-control" value="">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Build No:*</label>
												<div class="col-sm-3">
													<input id="buildNoId" name="buildNo" type="text" class="input-sm form-control" value="">
												</div>
												<label class="col-sm-2 control-label">Remarks:</label>
												<div class="col-sm-3">
													<input id="remarksId" name="remarks" type="text" class="input-sm form-control" value="">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Start Date:*</label>
												<div class="col-sm-3">
													<input id="startDateId" name="startDate" type="date" class="input-sm form-control" value="2016-09-21">
												</div>
												<label class="col-sm-2 control-label">End Date:*</label>
												<div class="col-sm-3">
													<input id="endDateId" name="endDate" type="date" class="input-sm form-control" value="2016-09-21">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Revised End Date:*</label>
												<div class="col-sm-3">
													<input id="revisedEndDateId" name="revisedEndDate" type="date" class="input-sm form-control" value="2016-09-21">
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- class="col-md-8" -->
								
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
								<!-- class="col-md-4" -->
							</div>
							<!-- class="col-md-12" -->
						</div>
						<!-- class="panel-body" -->
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
					<!-- <h5>
						<a href="DO_plan_page"> Planning Details <span class="fa fa-external-link"></span>
						</a>
					</h5> -->

				</div>
				<!-- id="accordion" -->
				<div class="form-group" style="margin-bottom: 10px;">
					<div class="col-md-8" style="float: right; margin-right: 1em;">
						<input id="cancel_button" type="reset" class="btn btn-default" value="Cancel" style="float: right;"> <span></span> <input id="save_button" type="submit" class="btn btn-primary" value="Save" style="float: right; margin-right: 1em;">
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>

	</div>
</form>
