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
	
<spring:url value="/resources/miminiumTheme/img/customers.png" var="cust" />
<spring:url value="/resources/miminiumTheme/img/deliveryOrder.png" var="order" />
<spring:url value="/resources/miminiumTheme/img/driver.png" var="driver" />
<spring:url value="/resources/miminiumTheme/img/truck.png" var="truck" />	
	
<link href="${bg2img}" rel="icon" type="image/x-icon">
<link href="${cust}" rel="icon" type="image/x-icon">
<link href="${order}" rel="icon" type="image/x-icon">
<link href="${driver}" rel="icon" type="image/x-icon">
<link href="${truck}" rel="icon" type="image/x-icon">
<link href="${calendarCss}" rel="stylesheet" type="text/css">

<!-- start: content -->

<div class="col-md-12" style="padding: 20px;">
	<!-- 	<div class="col-md-12 padding-0"> -->
	<!-- 		<div class="col-md-8 padding-0"> -->
	<div class="col-md-12 padding-0">
		<div class="col-md-3">
			<div class="panel box-v1">
				<div class="panel-heading bg-success border-none" style="color: #FFF !important;">
					<div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
						<h4 class="text-left">Vehicles</h4>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 text-right">
						<h4>
							<span class="fa fa-truck icons icon text-right"></span>
						</h4>
					</div>
				</div>
				<div class="panel-body bg-success text-center" style="color: #FFF !important;">
					<h1>2,310</h1>
					<p>Vehicles active</p>
					<hr style="margin-top: 0px; margin-bottom: 0px;">
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="panel box-v1">
				<div class="panel-heading bg-amber border-none" style="color: #FFF !important;">
					<div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
						<h4 class="text-left">Customers</h4>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 text-right">
						<h4>
							<span class="icon-user icons icon text-right"></span>
						</h4>
					</div>
				</div>
				<div class="panel-body bg-amber text-center" style="color: #FFF !important;">
					<h1>1,540</h1>
					<p>New Customers</p>
					<hr style="margin-top: 0px; margin-bottom: 0px;">
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="panel box-v1">
				<div class="panel-heading bg-lime border-none" style="color: #FFF !important;">
					<div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
						<h4 class="text-left">Vendors</h4>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 text-right">
						<h4>
							<span class="icon-briefcase icons icon text-right"></span>
						</h4>
					</div>
				</div>
				<div class="panel-body bg-lime text-center" style="color: #FFF !important;">
					<h1>1,540</h1>
					<p>New Vendors</p>
					<hr style="margin-top: 0px; margin-bottom: 0px;">
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="panel box-v1">
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
				<div class="panel-body bg-danger text-center" style="color: #FFF !important;">
					<h1>1,540</h1>
					<p>New Orders</p>
					<hr style="margin-top: 0px; margin-bottom: 0px;">
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="panel box-v4">
			<div class="panel-heading bg-white border-none">
				<h4>
					<span class="icon-envelope-letter icons"></span> Welcome
				</h4>
			</div>
			<div class="panel-body padding-0">
				<div class="col-md-12 col-xs-12 col-md-12 padding-0 box-v4-alert">
					<h2>Cargo Dispatched</h2>
					<p>Cargo Dispatched from Yard-2</p>
					<b><span class="icon-clock icons"></span> Today at 15:00</b>
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
