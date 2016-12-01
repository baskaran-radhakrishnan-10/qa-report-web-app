<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/custom/custom_remainder_settings.js" var="customRemainderJS" />

<script src="${customRemainderJS}"></script>

<div class="modal fade" id="add_reminder_details" tabindex="-1" role="dialog" aria-labelledby="buildTestPlanModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="width: 124%;margin: -5px -118px;border-radius: 0px;">
			<div id="btp_modal_header_id" class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Reminder Details</h4>
				<div id="addReminderDetailsDiv"></div>
			</div>
		</div>
	</div>
</div>

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">Reminder Details
			<!-- <a id="exportUserDetailsId" type="button" href="ser_det_page" class="btn  btn-3d btn-default pull-right" style="margin: 0px 5px;">Export</a> -->
			<!-- <a id="addReminderDetailsId" type="button" data-toggle="modal" data-target="#add_reminder_details" href="" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;">Add</a> -->
			<a id="addReminderDetailsId" type="button"  data-target="#add_reminder_details" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add new Reminder">Add</a>
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> Operation <span class="fa-angle-right fa"></span> Reminder Settings
			</p>
		</div>
	</div>
</div>

<div id="alertcustom"></div>

<div id="loader_div" style="width: 93px;height: 88px; left: 54%;position: fixed; z-index: 1000;top: 61%;background: rgba( 255, 255, 255, .8 ) url('../resources/miminiumTheme/img/hourglass.gif') 50% 50% no-repeat;display:none;"></div>

<div class="col-md-12 top-20" id="reminderMainDiv" style="display:none;">
	<div class="panel" style="padding: 15px; padding-bottom: 20px;">
		<div class="table-responsive">
			
			<table class="table table-striped table-hover table-bordered listView-table" id="show-reminder-details-id">
				<thead id="thead_id">
					<tr>
						<th>S.No</th>
						<th>Reminder Date</th>
						<th>Reminder Time (24 Hrs)</th>
						<th>Reminder About</th>
					</tr>
				</thead>
				<tbody id="tbody_id"></tbody>
			</table>
			
			<div class="col-md-12 padding-0">
				<div class="pull-left">
				<!-- 	<button type="button" id="addUserRowButton"  onclick="addUserRows()" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="auto right" title="" data-original-title="Add Row">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button> -->
				</div>
			</div>
			
		</div>
	</div>
</div>

<input type="button" id="addReminderDetailsTrigger" data-toggle="modal" data-target="#add_reminder_details" style="display:none" />

<form id="addReminderDetailsForm" name="addReminderDetailsForm" role="form" class="form-horizontal" action="#" method="post" style="display:none;">
	
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
												<label class="col-sm-2 control-label">Reminder Date:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="remainderDateId" name="remainderDate" type="date" class="input-sm form-control imp" value="">
												</div>
												<label class="col-sm-2 control-label">Reminder Time:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="remainderTimeId" name="remainderTime" type="time" class="input-sm form-control imp" value="">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Reminder About:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="remainderAboutId" name="remainderAbout" type="text" class="input-sm form-control imp" value="" maxlength="100" data-toggle="tooltip" data-placement="auto right" data-original-title="Allows numbers, characters (max 100) and  single space only">
												</div>
											</div>
										</div>
									</div>
									
							<!-- 	</div> -->
								<!-- class="col-md-8" -->
								
						      <!-- class="col-md-4" -->
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
						<input id="save_button" onclick="addOrUpdateReminder()" type="button" class="btn btn-primary" value="Save" style="float: right; margin-right: 1em;">
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>

	</div>
</form>

