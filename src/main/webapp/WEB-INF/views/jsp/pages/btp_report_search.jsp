<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/custom/custom_btp_report_search.js" var="customBTPReportSearchJS" />

<script src="${customBTPReportSearchJS}"></script>

<div class="modal fade" id="btp_report_search" tabindex="-1" role="dialog" aria-labelledby="btpReportSearchModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div id="multi_btp_report_modal_content" class="modal-content" style="top: 122px;width: 53%;left: 24%;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">(x)</button>
				<h2 class="modal-title" style="font-size: 22px;color: rgba(0, 0, 0, 0.55);">Export BTP Report</h2>
			</div>
			<form role="form" class="form-horizontal" id="btp_report_export_form">
				<div class="modal-body">
					<div class="form-group"	style="margin-right: -27px; margin-left: -15px;">
						<div class="col-sm-8" style="width: 81.666667%;">
							<select name="selector1" id="selectExportType" class="form-control1" style="margin: 14px 120px; width: 50%; height: 37px; cursor: pointer;">
								<option id="BTP_SUMMARY">BTP Summary Report</option>
								<option id="BTP_WEEKLY">BTP Weekly Report</option>
								<option id="BTP_MONTHLY">BTP Monthly Report</option>
							</select>
						</div>
					</div>
					<div id="timePeriodDiv" class="form-group" style="margin-right: -27px; margin-left: -15px;display: none;">
						<div class="col-sm-8" style="width: 36.666667%;cursor: pointer;">
							<select name="yearSelector" id="yearSelector" class="form-control1" style="margin: -21px 119px;width: 50%;height: 37px;cursor: pointer;">
								<option id="BTP_SUMMARY">2013</option>
								<option id="BTP_WEEKLY">2014</option>
								<option id="BTP_MONTHLY">2015</option>
							</select>
						</div>
						<div class="col-sm-8" style="width: 47.666667%;cursor: pointer;">
							<select name="monthSelector" id="monthSelector" class="form-control1" style="margin: -9px 24px;width: 50%;height: 37px;cursor: pointer;">
								<option id="0">January</option>
								<option id="1">February</option>
								<option id="2">March</option>
								<option id="3">April</option>
								<option id="4">May</option>
								<option id="5">June</option>
								<option id="6">July</option>
								<option id="7">August</option>
								<option id="8">September</option>
								<option id="9">October</option>
								<option id="10">November</option>
								<option id="11">December</option>
							</select>
						</div>
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="export_file_button">Export</button>
					<button type="button" class="btn btn-default" id="modal_close_button" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
		<div id="single_btp_report_modal_content" class="modal-content" style="top: 122px;width: 53%;left: 24%;display:none;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">(x)</button>
				<h2 class="modal-title" style="font-size: 19px;color: rgba(31, 33, 33, 0.52);">Export BTP Report</h2>
			</div>
			<form role="form" class="form-horizontal" id="btp_report_export_form">
				<div class="modal-body">
					<h4 style="color: #2196f3;font-size: 17px;">Do you want to export selected row data ?</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="selected_new_export_button">Yes</button>
					<button type="button" class="btn btn-danger" id="modal_close_button" data-dismiss="modal">No</button>
				</div>
			</form>
		</div>
	</div>
</div>

<input type="button" id="exportBTPReportButtonTrigger" data-toggle="modal" data-target="#btp_report_search" style="display:none" />

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">BTP Report Search
				<a id="exportBTPReportButton" type="button"  data-target="#btp_report_search" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;display:none;" data-toggle="tooltip" data-placement="auto left" >Export Report</a>
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> Report Search <span class="fa-angle-right fa"></span> BTP Report Search
			</p>
		</div>
	</div>
</div>

<div id="loader_div" style="width: 93px;height: 88px; left: 54%;position: fixed; z-index: 1000;top: 61%;background: rgba( 255, 255, 255, .8 ) url('../resources/miminiumTheme/img/hourglass.gif') 50% 50% no-repeat;display:none;"></div>

<div class="col-md-12 top-20" id="applyFilter" >
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
			<table class="table table-striped table-hover table-bordered listView-table" id="btp_report_search_table_id">
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
					</tr>
				</thead>
				<tbody id="tbody_id">
				</tbody>
			</table>
		</div>
	</div>
</div>