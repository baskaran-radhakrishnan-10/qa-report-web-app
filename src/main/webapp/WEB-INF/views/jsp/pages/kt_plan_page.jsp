<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/dt/jq-2.1.4,jszip-2.5.0,pdfmake-0.1.18,dt-1.10.9,af-2.0.0,b-1.0.3,b-colvis-1.0.3,b-html5-1.0.3,b-print-1.0.3,se-1.0.1/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/r/dt/jq-2.1.4,jszip-2.5.0,pdfmake-0.1.18,dt-1.10.9,af-2.0.0,b-1.0.3,b-colvis-1.0.3,b-html5-1.0.3,b-print-1.0.3,se-1.0.1/datatables.min.js"></script> -->

<!-- for pdf export -->
<!--  <script type="text/javascript" src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script> -->

<spring:url	value="/resources/miminiumTheme/js/custom/custom_kt_plan_page.js" var="customKTPlanJS" />

<%-- <spring:url value="/resources/miminiumTheme/js/jquery.min.js" var="jqueryJs" />
<spring:url value="/resources/miminiumTheme/js/jquery.multiselect.js" var="multiSelectJs" />
<spring:url value="/resources/miminiumTheme/css/jquery.multiselect.css" var="multiSelectCss" /> --%>

<script src="${customKTPlanJS}"></script>

<%-- <script src="${jqueryJs}"></script>
<script src="${multiSelectJs}"></script>
<link href="${multiSelectCss}" rel="stylesheet" type="text/css"> --%>

<div class="modal fade" id="add_kt_details" tabindex="-1" role="dialog" aria-labelledby="buildTestPlanModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="width: 124%;margin: -5px -118px;border-radius: 0px;">
			<div id="btp_modal_header_id" class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">KT Plan Details</h4>
				<div id="addKtDetailsDiv"></div>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="delete_rows_modal" tabindex="-1" role="dialog" aria-labelledby="buildTestPlanModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div id="row_delete_confirm_div" class="modal-content" style="top: 122px;width: 53%;left: 24%;display:none;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">(x)</button>
					<h2 class="modal-title" style="font-size: 19px;color: rgba(31, 33, 33, 0.52);">Delete KT Plan</h2>
				</div>
				<form role="form" class="form-horizontal" id="btp_report_export_form">
					<div class="modal-body">
						<h4 style="color: #2196f3;font-size: 17px;">Do you want to delete selected rows ?</h4>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="delete_rows">Yes</button>
						<button type="button" class="btn btn-danger" id="modal_close_button" data-dismiss="modal">No</button>
					</div>
				</form>
		</div>
	</div>
</div>


<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">KT Plan Details
			<!-- <a id="exportUserDetailsId" type="button" href="ser_det_page" class="btn  btn-3d btn-default pull-right" style="margin: 0px 5px;">Export</a> -->
			<!-- <a id="addKtDetailsId" type="button" data-toggle="modal" data-target="#add_kt_details" href="ser_det_page" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;">Add</a> -->
			<a id="addKtDetailsId" type="button"  data-target="#add_kt_details" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add new KT Plan Details">Add</a>
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> KT Plan
			</p>
		</div>
	</div>
</div>

<div id="loader_div" style="width: 149px;height: 149px; left: 54%;position: fixed; z-index: 1000;top: 42%;
    background: rgba( 255, 255, 255, .8 ) url('../resources/miminiumTheme/img/ripple.gif') 50% 50% no-repeat;display:none;"></div>

<div class="col-md-12 top-20" id="applyFilter"  style="display:none;">
	<div class="panel" style="padding: 50px 0px;padding-bottom: 73px;margin: 13px 0px;">
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Project</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_projectId" name="project" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">KT Type</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_trainingTypeId" name="trainingType" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Session</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_sessionId" name="session" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Location</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_locationId" name="location" class="input-sm form-control imp"></select>
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
<div id="alertcustom"></div>

<div class="col-md-12 top-20" id="ktMainDiv" style="display:none;">
	<div class="panel" style="padding: 15px; padding-bottom: 20px;">
		<div class="table-responsive">
			
			<table class="table table-striped table-hover table-bordered listView-table" id="show-kt-details-id">
				<thead id="thead_id">
					<tr>
						<th nowrap="nowrap">S.No</th>
<!-- 						<th nowrap>KT ID</th> -->
						<th nowrap>Project</th>
						<th hidden="true">KT Type</th>
						<th hidden="true">Session</th>
						<th nowrap>KT Title</th>
						<th hidden="true">Trainer(s)</th>
						<th hidden="true">Attendee(s)</th>
						<th nowrap>Location</th>
						<th nowrap>Start Date</th>
						<th nowrap>End Date</th>
						<th hidden="true">Duration(Hrs)</th>
						<th hidden="true">Remarks</th>
						<th hidden="true">Feedback</th>	
						<th></th>
						
					</tr>
				</thead>
				<tbody id="tbody_id"></tbody>
			</table>
			
			<div class="col-md-12 padding-0">
				<div class="pull-left">
					<!-- <button type="button" id="addUserRowButton"  onclick="addUserRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add Row">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button> -->
				</div>
			</div>
		</div>
			<c:choose>
 			<c:when	test='${sessionScope.roleId.equals("ROLE_SUPER_ADMIN")}'>
 				<input id="delete_button" type="button" value="Delete" class="btn btn-danger" style="margin-right: 13px;display:none;">
 			</c:when>
 	    </c:choose>
	</div>
</div>
<input type="button" id="addKtDetailsTrigger" data-toggle="modal" data-target="#add_kt_details" style="display:none" />
<input type="button" id="showDeleteRowModal" data-toggle="modal" data-target="#delete_rows_modal" style="display:none" />

<form id="ktDetailsForm" name="ktDetailsForm" role="form" class="form-horizontal" action="#" method="post" style="display:none;">
	
	<div id="alertcustom"></div>
	
	<input type="hidden" id="selectedRowKeyInput" value="" />
	
	<div class="form-element">
		<div class="col-md-12" style="padding-right: 2px;padding-left: 1px;width: 103%;margin: 16px -16px;">
			<div class="col-md-12 panel">
				<div class="col-md-12" id="accordion" style="margin-top: 10px;">
					<h5>
						<a href="#" class="" aria-expanded="true" style="cursor: text;"> <font color="red"> * </font> Indicates Mandatory Field </a>
					</h5>
					<div id="collapse1" class="panel-collapse collapse in" style="border-style: ridge; border-width: thin;" aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-12 padding-0">
	<!-- <div class="col-md-8 padding-0"> -->
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Project:<font color="red">*</font></label>
												<div class="col-sm-3">
													<select id="projectId" name="project" class="input-sm form-control imp"></select>
												</div>
												<label class="col-sm-2 control-label">KT Type:<font color="red">*</font></label>
												<div class="col-sm-3">
													<select id="ktTypeId" name="kttype" class="input-sm form-control imp"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">KT Title:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="ktTitleId" name="kttitle" type="text" class="input-sm form-control imp" value="" data-toggle="tooltip" data-placement="auto right" data-original-title="Allows only 0-9,a-z,A-Z,( )- and 1 Space">
												</div>
												<label class="col-sm-2 control-label">Session:<font color="red">*</font></label>
												<div class="col-sm-3">
													<select id="sessionId" name="session" class="input-sm form-control imp"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Trainer(s):<font color="red">*</font></label>
												<div class="col-sm-3">
													<select id="trainerId" multiple="multiple" name="trainer" class="input-sm form-control imp"></select>
												</div>
												<label class="col-sm-2 control-label">Attendee(s):<font color="red">*</font></label>
												<div class="col-sm-3">
													<select id="attendeeId" multiple="multiple" name="attendee" class="input-sm form-control imp"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Location:<font color="red">*</font></label>
												<div class="col-sm-3">
													<select id="locationId" name="location" class="input-sm form-control imp"></select>
												</div>
												<label class="col-sm-2 control-label">Start Date:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="startDateId" name="startDate" type="date" class="input-sm form-control imp">
												</div>												
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												
												<label class="col-sm-2 control-label">End Date:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="endDateId" name="endDate" type="date" class="input-sm form-control imp">
												</div>
												<label class="col-sm-2 control-label">Duration(Hrs):<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="durationId" name="duration" type="text" class="input-sm form-control imp" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Enter duration in hours or .(dot) mins">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												
												
												<label class="col-sm-2 control-label">Remarks:</label>
												<div class="col-sm-3">
													<input id="remarksId" name="remarks" type="text" class="input-sm form-control" value="">
												</div>
												<label class="col-sm-2 control-label">Feedback:</label>
												<div class="col-sm-3">
													<input id="feedbackId" name="feedback" type="text" class="input-sm form-control">
												</div>
											</div>
										</div>
									</div>
									
				<!-- </div> -->
			<!-- class="col-md-8" -->
				
								
							</div>
							<!-- class="col-md-12" -->
							
						</div>
						<!-- class="panel-body" -->
						
					</div>
					

				</div>
				<!-- id="accordion" -->
				<div class="form-group" style="margin-bottom: 10px;">
					<div class="col-md-8" style="float: right;margin-right: 1.2em;margin-top: 1em;" >
						<input data-dismiss="modal" id="cancel_button" type="reset" class="btn btn-default" value="Cancel" style="float: right;"> 
						<span></span> 
						<input id="save_button" onclick="addOrUpdateKTPlan()" type="button" class="btn btn-primary" value="Save" style="float: right; margin-right: 1em;">
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>

	</div>
</form>

<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>