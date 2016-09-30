<spring:url	value="/resources/miminiumTheme/js/common/common.js" var="commonJS" />

<spring:url	value="/resources/miminiumTheme/js/custom/custom_left_menu.js" var="leftMenuJS" />

<script src="${commonJS}"></script>

<script src="${leftMenuJS}"></script>

<!-- start:Left Menu -->
<div id="left-menu">

	<div class="sub-left-menu scroll bg-yellow">
		
		<ul class="nav nav-list">
		
			<li class="time" style="border-bottom: 1px solid #eee;"><h1></h1><p></p></li>
			
			<li id="dashboard_li"><a href="${sessionScope.baseUrl}dashboard"><span class="fa-tachometer fa"></span>Dashboard</a></li>
			
			<%-- <li id="operations_li" class="ripple"><a class="tree-toggle nav-header"> <span	class="fa-cogs fa"></span>Operations<span class="fa-angle-right fa right-arrow text-right"></span></a>
				<ul class="nav nav-list tree">
					<li><a href="${sessionScope.baseUrl}add_user">Add New User</a></li>
					<li><a href="${sessionScope.baseUrl}add_project">Add New Project</a></li>
					<li><a href="${sessionScope.baseUrl}edit_user">Edit User Details</a></li>
					<li><a href="${sessionScope.baseUrl}edit_project">Edit Project Details</a></li>
					<li><a href="${sessionScope.baseUrl}add_leave">Add Leave Details</a></li>
					<li><a href="${sessionScope.baseUrl}add_permission">Add Permission Details</a></li>
					<li><a href="${sessionScope.baseUrl}add_leave_plans">Add Leave Plans</a></li>
					<li><a href="${sessionScope.baseUrl}remainder_settings">Remainder Settings</a></li>
				</ul>
			</li>
			
			<li id="rbac_li" class="ripple"><a class="tree-toggle nav-header"><span class="fa-user-secret fa"></span> Users &amp; Roles <span class="fa-angle-right fa right-arrow text-right"></span></a>
				<ul class="nav nav-list tree">
					<li><a href="${sessionScope.baseUrl}manage_users">Manage Users</a></li>
					<li><a href="${sessionScope.baseUrl}manage_roles">Manage Roles</a></li>
					<li><a href="${sessionScope.baseUrl}manage_resources">Manage Resources</a></li>
				</ul>
			</li>
				
			<li id="report_search_li" class="ripple"><a class="tree-toggle nav-header"> <span	class="fa-search fa"></span> Report Search <span class="fa-angle-right fa right-arrow text-right"></span></a>
				<ul class="nav nav-list tree">
					<li><a href="${sessionScope.baseUrl}btp_serach">BTP Search</a></li>
					<li><a href="${sessionScope.baseUrl}dsr_search">DSR Search</a></li>
					<li><a href="${sessionScope.baseUrl}user_search">User Search</a></li>
					<li><a href="${sessionScope.baseUrl}leave_search">Leave Details Search</a></li>
					<li><a href="${sessionScope.baseUrl}permission_search">Permission Details Search</a></li>
					<li><a href="${sessionScope.baseUrl}leave_plan_search">Leave Plans Search</a></li>
				</ul>
			</li>	 --%>
				
			<li id="build_test_plan_li"><a href="${sessionScope.baseUrl}build_test_plan/show"> <span class="fa-pencil-square-o fa"></span> Build Test Plan </a></li>
			
			<%-- <li id="daily_status_report_li"><a href="${sessionScope.baseUrl}daily_status_report"> <span class="fa-calendar fa"></span> Daily Status Report </a></li>
			
			<li id="kt_plan_li"><a href="${sessionScope.baseUrl}kt_plan"> <span class="fa-bullhorn fa"></span> KT Plan </a></li>
			 --%>
			<%-- <c:choose>

				<c:when	test="${sessionScope.resourceMap.containsKey('dashboard_li')}">
					<c:out value="${sessionScope.resourceMap.get('dashboard_li')}"></c:out>
				</c:when>

			</c:choose>
			
			<c:choose>

				<c:when	test="${sessionScope.resourceMap.containsKey('dashboard_li')}">
					<c:out value="${sessionScope.resourceMap.get('dashboard_li')}" />
				</c:when>

			</c:choose>
			
			<c:choose>

				<c:when	test="${sessionScope.resourceMap.containsKey('rbac_li')}">
					<c:out value="${sessionScope.resourceMap.get('rbac_li')}" />
				</c:when>

			</c:choose>
			
			<c:choose>

				<c:when	test="${sessionScope.resourceMap.containsKey('report_search_li')}">
					<c:out value="${sessionScope.resourceMap.get('report_search_li')}" />
				</c:when>

			</c:choose>
			
			<c:choose>

				<c:when	test="${sessionScope.resourceMap.containsKey('build_test_plan_li')}">
					<c:out value="${sessionScope.resourceMap.get('build_test_plan_li')}" />
				</c:when>

			</c:choose>
			
			<c:choose>

				<c:when	test="${sessionScope.resourceMap.containsKey('daily_status_report_li')}">
					<c:out value='${sessionScope.resourceMap.get('daily_status_report_li')}' />
				</c:when>

			</c:choose>
			
			<c:choose>

				<c:when	test="${sessionScope.resourceMap.containsKey('kt_plan_li')}">
					<c:out value="${sessionScope.resourceMap.get('kt_plan_li')}" />
				</c:when>

			</c:choose> --%>
			
		</ul>
		
	</div>
	
</div>
<!-- end: Left Menu -->

