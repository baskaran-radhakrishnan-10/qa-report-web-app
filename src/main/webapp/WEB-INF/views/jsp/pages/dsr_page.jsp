<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/custom/custom_dsr_module.js" var="customDSRJS" />

<spring:url	value="/resources/miminiumTheme/js/workers/dsr.js" var="dsrWorkerJS" />

<script src="${customDSRJS}"></script>

<script id="dsrWorker" src="${dsrWorkerJS}"></script>



<div class="modal fade" id="dsr_modal" tabindex="-1" role="dialog" aria-labelledby="dsrModal" aria-hidden="true" data-backdrop="static" data-keyboard="false" data-href="">	
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="width: 124%;margin: 124px -105px;border-radius: 0px;">
			<div id="btp_modal_header_id" class="modal-header">
				<button type="button"  class="close close_modal" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">DSR Details</h4>
				<div id="dsrDiv"></div>
			</div>
		</div>
	</div>
</div>

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">Daily Status Report
				<a id="addDSRId" type="button"  data-target="#dsr_modal" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;" data-toggle="tooltip" data-placement="auto left" title="" data-original-title="Add new DSR details">Add</a>
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> DSR
			</p>
		</div>
	</div>
</div>

<div id="loader_div" style="width: 149px;height: 149px; left: 54%;position: fixed; z-index: 1000;top: 42%;
    background: rgba( 255, 255, 255, .8 ) url('../resources/miminiumTheme/img/ripple.gif') 50% 50% no-repeat;display:none;"></div>
    
<div class="col-md-12 top-20" id="applyFilter" style="display:none;">
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

<div class="col-md-12 top-20" id="dsrMainDiv">
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
						<!-- <th>Accomplished Task</th> -->
						<th>Status</th>
						<!-- <th>Remarks</th> -->
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

<input type="button" id="editDSRTrigger" data-toggle="modal" data-target="#dsr_modal" style="display:none" />

<form id="dsrForm" name="dsrForm" role="form" class="form-horizontal" action="#" method="post" style="display:none;">
	
	<div id="alertcustom"></div>
	
	<input type="hidden" id="selectedRowKeyInput" value="">
	
	<div class="form-element">
		<div class="col-md-12" style="padding-right: 2px;padding-left: 1px;width: 103%;margin: 16px -16px;">
			<div class="col-md-12 panel">
				<div class="col-md-12" id="accordion" style="margin-top: 10px;">
					<h5>
						<a href="#" class="" aria-expanded="true" style="cursor: text;"> Daily Status Report </a>
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
													<select id="projectId" name="projectName" class="input-sm form-control imp"></select>
												</div>
												<label class="col-sm-2 control-label">Resource:*</label>
												<div class="col-sm-3">
													<select id="resourceId" name="resource" class="input-sm form-control imp"></select>
												</div>
											</div>
										</div>
									</div>
									
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												
												<label class="col-sm-2 control-label">Status:*</label>
												<div class="col-sm-3">
													<select id="statusId" name="dsrStatus" class="input-sm form-control imp"></select>
												</div>
											</div>
										</div>
									</div>
									
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Hrs Planned:</label>
												<div class="col-sm-3">
													<input id="hrsPlannedId" name="plannedHours" type="text" class="input-sm form-control" value="">
												</div>
												<label class="col-sm-2 control-label">Hrs Spent:</label>
												<div class="col-sm-3">
													<input id="hrsSpentId" name="spentHours" type="text" class="input-sm form-control" value="">
												</div>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Date:*</label>
												<div class="col-sm-3">
													<input id="dsrDateId" name="dsrDate" type="date" class="input-sm form-control imp">
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- class="col-md-8" -->
  
								<div class="col-md-4 padding-0" style="width: 33.333333%;">
									<div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label" style="width: 29.666667%;text-align: right;margin: 0px -38px;">Planned Task:*</label>
												<div class="col-sm-3" style="width: 81%;">
													<textarea name="plannedTask" id="plannedTaskId" cols="38" rows="2" class="input-sm form-control imp" style="margin: 0px 30px;"></textarea>
												</div>
											</div>
										</div>
									</div><div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label" style="width: 29.666667%;text-align: right;margin: 0px -41px;">Remarks:</label>
												<div class="col-sm-3" style="width: 81%;">
													<textarea name="remarks" id="remarksId" cols="38" rows="2" class="input-sm form-control" style="margin: 0px 36px;"></textarea>
												</div>
											</div>
										</div>
									</div><div class="form-group" id="important">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label" style="text-align: right;width: 23.666667%;margin: 0px -17px;">Accomplished Task:</label>
												<div class="col-sm-3" style="width: 81%;">
													<textarea name="accomplishedTask" id="accomplishedTaskId" cols="38" rows="2" class="input-sm form-control" style="margin: 0px 13px;"></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
  
							</div>
							<!-- class="col-md-12" -->
						</div>
						<!-- class="panel-body" -->
					</div>
				</div>
				<!-- id="accordion" -->
				
				<div class="form-group" style="margin-bottom: 10px;">
					<div class="col-md-8" style="float: right;margin-right: 1.2em;margin-top: 1em;">
						<input data-dismiss="modal" id="cancel_button" type="reset" class="btn btn-default close_modal" value="Cancel" style="float: right;"> 
						<span></span> 
						<input id="save_button" type="button" class="btn btn-primary close_modal" value="Save" style="float: right; margin-right: 1em;">
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>

	</div>
</form>

<div class="col-md-12 top-20" id="applyPagination" style="display:none;background-color: rgb(232, 232, 232);">
	<ul id="pagination_ul" class="pagination pagination-lg" style="top: -14px;left: 23%;position: relative;">
		<li class="disabled" id="prevPageLink"><a href="#"><i class="fa fa-angle-left"></i></a></li>
		<li class="active" ><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#">6</a></li>
		<li><a href="#">7</a></li>
		<li><a href="#">8</a></li>
		<li><a href="#">9</a></li>
		<li><a href="#">10</a></li>
		<li id="nextPageLink"><a href="#"><i class="fa fa-angle-right"></i></a></li>
	</ul>
	<p id="size_info" style="text-align: center;color: #989898;margin: -25px 0px;">Totally 10886 Entries Available</p>
</div>
