<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/js/common/common.js" var="commonJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_manage_password.js" var="customManagePasswordJS" />

<spring:url	value="/resources/miminiumTheme/js/plugins/notify/notifIt.js"	var="notifyJs" />

<script src="${notifyJs}"></script>

<script src="${commonJS}"></script>

<script src="${customManagePasswordJS}"></script>

<div class="panel box-shadow-none content-header">
	<div class="panel-body">
		<div class="col-md-12">
			<h3 style="margin-top: 0px;">Reset Password
			<!-- <a id="exportUserDetailsId" type="button" href="ser_det_page" class="btn  btn-3d btn-default pull-right" style="margin: 0px 5px;">Export</a> -->
			<!-- <a id="addUserDetailsId" type="button" data-toggle="modal" data-target="" href="ser_det_page" class="btn  btn-3d btn-success pull-right" style="margin: 0px 5px;">Add</a> -->
			</h3>
			<p style="margin-bottom: 0px;">
				Home <span class="fa-angle-right fa"></span> Users & Roles <span class="fa-angle-right fa"></span> Manage Password
			</p>
		</div>
	</div>
</div>

<form id="addUserDetailsForm" role="form" autocomplete="off" class="form-horizontal" action="#" method="post">
	
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
							<!-- 	<div class="col-md-8 padding-0"> -->
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">											
																								
												<label class="col-sm-2 control-label">User ID:<font color="red">*</font></label>
												<div class="col-sm-3">
													<select id="userId" name="user" class="input-sm form-control imp"></select>
												</div>
												
												<label class="col-sm-2 control-label">New Password:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="newPasswordId" name="newPassword" type="password" autocomplete="off" class="input-sm form-control imp" value="">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<label class="col-sm-2 control-label">Confirm Password :<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="confirmPasswordId" name="confirmPassword" type="password" autocomplete="off" class="input-sm form-control imp">
												</div>
												<label class="col-sm-2 control-label">Date Modified:<font color="red">*</font></label>
												<div class="col-sm-3">
													<input id="modifiedOnId" name="modifiedOn" type="text" class="input-sm form-control imp" readonly="readonly" >
												</div>
												
											</div>
										</div>
									</div>
							</div>
							
						</div>
						
					</div>
					

				</div>
				
				<div class="form-group" style="margin-bottom: 10px;">
					<div class="col-md-8" style="float: right; margin-right: 1em;">
					<input id="reset_password_button" type="button" class="btn btn-primary" value="Reset Password" style="float: right; margin-right: 1em;">
						<!-- <input data-dismiss="modal" id="cancel_button" type="reset" class="btn btn-default" value="Cancel" style="float: right;"> <span></span> <input id="reset_button" type="submit" class="btn btn-primary" value="Reset" style="float: right; margin-right: 1em;"> -->
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>

	</div>
</form>

