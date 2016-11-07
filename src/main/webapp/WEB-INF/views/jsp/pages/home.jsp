<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url	value="/resources/miminiumTheme/css/plugins/fullcalendar.min.css" var="calendarCss" />
<spring:url	value="/resources/miminiumTheme/js/plugins/fullcalendar.min.js"	var="fullCalendarJs" />
<spring:url	value="/resources/miminiumTheme/js/plugins/jquery.vmap.min.js"	var="vmapMinJs" />

<spring:url	value="/resources/miminiumTheme/js/plugins/jquery.vmap.world.js" var="JmapWorldJs" />
<spring:url	value="/resources/miminiumTheme/js/plugins/jquery.vmap.sampledata.js" var="mapSampleDateJS" />
<spring:url value="/resources/miminiumTheme/js/plugins/Chart.js" var="chartMinJs" />
<spring:url value="/resources/miminiumTheme/js/plugins/moment.min.js" var="MomentJs" />

<spring:url value="/resources/miminiumTheme/img/bg2.jpg" var="bg2img" />
<%-- <spring:url	value="/resources/miminiumTheme/js/custom/dashboard_custom.js"	var="CustomJs" /> --%>
<spring:url	value="/resources/miminiumTheme/js/custom/custom_home.js"	var="customHomeJS" />
	
<spring:url value="/resources/miminiumTheme/img/customers.png" var="cust" />
<spring:url value="/resources/miminiumTheme/img/deliveryOrder.png" var="order" />
<spring:url value="/resources/miminiumTheme/img/driver.png" var="driver" />
<spring:url value="/resources/miminiumTheme/img/truck.png" var="truck" />	
<spring:url value="/resources/miminiumTheme/img/equiniti.ico" var="equIcon" />

<script src="${customHomeJS}"></script>	
<link href="${bg2img}" rel="icon" type="image/x-icon">
<link href="${cust}" rel="icon" type="image/x-icon">
<link href="${order}" rel="icon" type="image/x-icon">
<link href="${driver}" rel="icon" type="image/x-icon">
<link href="${truck}" rel="icon" type="image/x-icon">
<link href="${calendarCss}" rel="stylesheet" type="text/css">
<link href="${equIcon}" rel="shortcut icon" type="image/x-icon">

<!-- start: content -->

<div class="col-md-12" style="padding: 20px;">
	<!-- 	<div class="col-md-12 padding-0"> -->
	<!-- 		<div class="col-md-8 padding-0"> -->
	<div class="col-md-12 padding-0">
		<div class="col-md-3">
			<div class="panel box-v1" style="overflow-y: auto; height: 183px;">
				<div class="panel-heading bg-amber border-none" style="color: #FFF !important;">
					<div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
						<h4 class="text-left">Users</h4>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 text-right">
						<h4>
							<span class="icon-user icons icon text-right"></span>
						</h4>
					</div>
				</div>
				<div class="panel-body bg-amber text-center" style="color: #FFF !important;" id="hUsersId">
					
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="panel box-v1" style="overflow-y: auto; height: 183px;">
				<div class="panel-heading bg-success border-none" style="color: #FFF !important;">
					<div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
						<h4 class="text-left">Test Plan</h4>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 text-right">
						<h4>
							<span class="glyphicon glyphicon-edit icons icon text-right"></span>
						</h4>
					</div>
				</div>
				<div class="panel-body bg-success text-center" style="color: #FFF !important;" id="hBtpId">
					
				</div>
			</div>
		</div>
	
		<div class="col-md-3">
			<div class="panel box-v1" style="overflow-y: auto; height: 183px;">
				<div class="panel-heading bg-lime border-none" style="color: #FFF !important;">
					<div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
						<h4 class="text-left">KT Plan</h4>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 text-right">
						<h4>
							<span class="glyphicon glyphicon-bullhorn icons icon text-right"></span>
						</h4>
					</div>
				</div>
				<div class="panel-body bg-lime text-center" style="color: #FFF !important;" id="hKtPlanId">
					
				</div>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="panel box-v1" style="overflow-y: auto; height: 183px;">
				<div class="panel-heading bg-danger border-none" style="color: #FFF !important;">
					<div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
						<h4 class="text-left">Orders</h4>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 text-right">
						<h4>
							<span class="icon-basket-loaded icons icon text-right"></span>
						</h4>
					</div>
				</div>
				<div class="panel-body bg-danger text-center" style="color: #FFF !important;" id="hOrdersId">
				
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-md-12">
		<div class="panel box-v4">
			<div class="panel-heading bg-white border-none">
				<h4>
					<span class="glyphicon glyphicon-bell" style="color:red"></span> Reminders Today!
				</h4>
			</div>
			<div class="panel-body padding-0">
				<div class="col-md-12 col-xs-12 col-md-12 padding-0 box-v4-alert">
				<marquee behavior="scroll" direction="left" onmouseover="this.stop();" onmouseout="this.start();" id="remaindersTodayId">
				
				</marquee>					
				</div>
				<!-- 							<div class="calendar"></div> -->
			</div>
		</div>
	</div>

	<div class="col-md-12 card-wrap padding-0">
		<div class="col-md-6">
			<div class="panel">
				<div class="panel-heading bg-white border-none"
					style="padding: 20px;">
					<div class="col-md-6 col-sm-6 col-sm-12 text-left">
						<h4>Orders</h4>
					</div>
				</div>
				<div class="panel-body" style="padding-bottom: 50px;">
					<div id="canvas-holder1">
						<canvas id="chart1" class="bar-chart"></canvas>
					</div>
				</div>
			</div>
		</div>
		<!-- class="col-md-6" -->

		<div class="col-md-6">
			<div class="panel">
				<div class="panel-heading bg-white border-none"
					style="padding: 20px;">
					<div class="col-md-6 col-sm-6 col-sm-12 text-left">
						<h4>Truck Status</h4>
					</div>
				</div>
				<div class="panel-body" style="padding-bottom: 50px;">
					<div id="canvas-holder2">
						<canvas id="chart2" class="bar-chart"></canvas>
					</div>
				</div>
			</div>
		</div>
		<!-- class="col-md-6" -->
	</div>

</div>
<!-- end: content -->

<script src="${MomentJs}"></script>
<script src="${chartMinJs}"></script>
<%-- <script src="${CustomJs}"></script> --%>

<!-- end: Javascript -->
