<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/custom/custom_dsr_report_search.js" var="customDSRReportSearchJS" />

<script src="${customDSRReportSearchJS}"></script>

<div class="modal fade" id="dsr_report_search" tabindex="-1" role="dialog" aria-labelledby="dsrReportSearchModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		
		<div id="multi_dsr_report_modal_content" class="modal-content" style="top: 122px;width: 53%;left: 24%;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">(x)</button>
				<h2 class="modal-title" style="font-size: 22px;color: rgba(0, 0, 0, 0.55);">Export BTP Report</h2>
			</div>
			<form role="form" class="form-horizontal" id="dsr_report_export_form">
				<div class="modal-body">
					<div class="form-group"	style="margin-right: -27px; margin-left: -15px;">
						<div class="col-sm-8" style="width: 81.666667%;">
							<select name="selector1" id="selectExportType" class="form-control1" style="margin: 14px 120px; width: 50%; height: 37px; cursor: pointer;">
								<option id="DSR_SUMMARY">DSR Summary Report</option>
								<option id="DSR_DAY_REPORT">DSR Day Report</option>
							</select>
						</div>
					</div>
					<div id="timePeriodDiv" class="form-group" style="margin-right: -27px; margin-left: -15px;display: none;">
						<div class="col-sm-8" style="width: 80.666667%;">
  							<label class="col-sm-2 control-label" style="margin: -21px -5%;padding-top: 11px;padding-left: 50px;text-align: center;width: 47.666667%;">Accomplished Date</label>
							<div class="col-md-2 top-20" style="margin: 14px 4%;width: 50.666667%;">
								<input id="filter_dateFromId" name="accomplishedDate" type="date" class="input-sm form-control imp" placeholder="Accomplished Date">
							</div>
							<label class="col-sm-2 control-label" style="margin: -69px 59%;padding-top: 7px;padding-left: 40px;text-align: left;width: 47.666667%;">Planned Date</label>
							<div class="col-md-2 top-20" style="margin: -39px 66%;width: 50.666667%;">
								<input id="filter_dateToId" name="plannedDate" type="date" class="input-sm form-control imp" placeholder="Planned Date">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="export_file_button">Export</button>
					<button type="button" class="btn btn-default" id="modal_close_button" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
		
		<!-- <div id="multi_dsr_report_modal_content" class="modal-content" style="top: 122px;width: 53%;left: 24%;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">(x)</button>
				<h2 class="modal-title" style="font-size: 22px;color: rgba(0, 0, 0, 0.55);">Export DSR Report</h2>
			</div>
			<form role="form" class="form-horizontal" id="dsr_report_export_form">
				<div class="modal-body">
					<div class="form-group" style="margin-right: -30px;margin-left: -15px;margin-bottom: -7px;">
						<div class="col-sm-8" style="width: 80.666667%;">
  						<label class="col-sm-2 control-label" style="margin: -21px -5%;padding-top: 11px;padding-left: 50px;text-align: center;width: 47.666667%;">Accomplished Date</label>
							<div class="col-md-2 top-20" style="margin: 14px 4%;width: 50.666667%;">
								<input id="filter_dateFromId" name="accomplishedDate" type="date" class="input-sm form-control imp" placeholder="Accomplished Date">
							</div>
							<label class="col-sm-2 control-label" style="margin: -69px 59%;padding-top: 7px;padding-left: 40px;text-align: left;width: 47.666667%;">Planned Date</label>
							<div class="col-md-2 top-20" style="margin: -39px 66%;width: 50.666667%;">
								<input id="filter_dateToId" name="plannedDate" type="date" class="input-sm form-control imp" placeholder="Planned Date">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="export_file_button">Export</button>
					<button type="button" class="btn btn-default" id="modal_close_button" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div> -->
	</div>
</div>

<input type="button" id="exportDSRReportButtonTrigger" data-toggle="modal" data-target="#dsr_report_search" style="display:none" />

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">DSR Report Search
				<a id="exportDSRReportButton" type="button"  data-target="#dsr_report_search" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;display:none;" data-toggle="tooltip" data-placement="auto left" >Export Report</a>
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> Report Search <span class="fa-angle-right fa"></span> DSR Report Search
			</p>
		</div>
	</div>
</div>

<div id="loader_div" style="width: 93px;height: 88px; left: 54%;position: fixed; z-index: 1000;top: 61%;background: rgba( 255, 255, 255, .8 ) url('../resources/miminiumTheme/img/hourglass.gif') 50% 50% no-repeat;display:none;"></div>

<div class="col-md-12 top-20" id="applyFilter">
	<div class="panel" style="padding: 50px 0px;padding-bottom: 73px;margin: 13px 0px;">
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Project</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_projectId" name="projectName" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Resource</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_resourceId" name="resource" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Status</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<select id="filter_statusId" name="dsrStatus" class="input-sm form-control imp"></select>
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Date From</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<input id="filter_dateFromId" name="startDate" type="date" class="input-sm form-control imp">
		</div>
		<label class="col-sm-2 control-label" style="margin: -42px -23px;padding-top: 7px;padding-left: 40px;text-align: left;">Date To</label>
		<div class="col-md-2 top-20" style="margin-top: -12px;">
			<input id="filter_dateToId" name="endDate" type="date" class="input-sm form-control imp">
		</div>
		<div class="col-md-3 top-20" style="margin-top: 11px;margin-left: 0px;">
			<input id="apply_filter" type="button" class="btn btn-success" value="Filter" style="margin-right: 13px;"> 
  			<input id="clear_filter" type="button" class="btn btn-default" value="Clear" style="">
		</div>
	</div>
</div>     

<div class="col-md-12 top-20" id="dsrMainDiv" style="display:none;">
	<div class="panel" style="padding: 15px; padding-bottom: 20px;">
		<div class="table-responsive">
			<table class="table table-striped table-hover table-bordered listView-table" id="dsr_table_id">
				<thead id="thead_id">
					<tr>
						<th>S.No</th>
						<th>Project</th>
						<th>Resource</th>
						<th>Date</th>
						<th>Planned Task</th>
						<th>Status</th>
						<th>Hrs(Planned)</th>
						<th>Hrs(Spent)</th>
					</tr>
				</thead>
				<tbody id="tbody_id">
				</tbody>
			</table>
		</div>
	</div>
</div>