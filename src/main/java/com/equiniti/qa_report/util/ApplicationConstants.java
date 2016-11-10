package com.equiniti.qa_report.util;

import java.io.File;

public class ApplicationConstants {

	public static final String IS_LOGGED_IN="IS_LOGGED_IN";

	public static final String BASE_URL = "baseUrl";

	public static final String LOGIN_PAGE="loginpage";

	public static final String REDIRECT_LOGIN_PAGE="redirect:/login";

	public static final String HOME_PAGE="homepage";

	public static final String REDIRECT_HOME_PAGE="redirect:/home";

	public static final String USER_OBJ="USER_OBJ";
	
	public static final String USER_ID="USER_ID";

	public static final String RESOURCE_MAP="resourceMap";

	public static final String DASHBOARD_LI="dashboard_li";

	public static final String OPERATIONS_LI="operations_li";

	public static final String RBAC_LI="rbac_li";

	public static final String REPORT_SEARCH_LI="report_search_li";

	public static final String BUILD_TEST_LI="build_test_plan_li";

	public static final String DAILY_STATUS_REPORT_LI="daily_status_report_li";

	public static final String KT_PLAN_LI="kt_plan_li";

	public static final String OBJECT_MAPPER_TESTPLAN="TestPlanMapper";

	public static final String STATUS="STATUS";

	public static final String SUCCESS="SUCCESS";

	public static final String ERROR="ERROR";

	public static final String SERVER_DATA="SERVER_DATA";
	
	public static final String USER_DATA="USER_DATA";
	
	public static final String APP_CONFIG_FOLDER_PATH = System.getProperty("jboss.home.dir")+File.separator+"appconfig";
	
	public static final String DEFAULT_LOGIN_PASSWORD="root123";
	
	public static final String DSR_SUMMARY_REPORT="DSR_SUMMARY_REPORT";
	
	public static final String BTP_SUMMARY_REPORT="BTP_SUMMARY_REPORT";
	
	public static final String SELECTED_BTP_REPORT="SELECTED_BTP_REPORT";
	
	public static final String BTP_MONTHLY_REPORT = "BTP_MONTHLY_REPORT";
	
	public static final String BTP_WEEKLY_REPORT = "BTP_WEEKLY_REPORT";
	
	public static final String DSR_CACHE_ITEM="DSR_CACHE_ITEM";
	
	public static final String DSR_RECORDS_COUNT="DSR_RECORDS_COUNT";
	
	public static final String PAGED_DSR_CACHE_ITEM="PAGED_DSR_CACHE_ITEM";
	
	public static final String SIZE="SIZE";
	
	public static final String PAGE_NO="PAGE_NO";
	
	public static final String ROLE_RESOURCE_INFO="ROLE_RESOURCE_INFO";
	
	public static final String CURRENT_ACTION_PATH="currentActionPath";
	
	public static final String USER_SUMMARY_REPORT = "USER_SUMMARY_REPORT";
	
	public static final String REPORT_DATA= "REPORT_DATA";
	
	public static final String REPORT_TYPE = "REPORT_TYPE";
	
	public static final String BTP_MONTHLY_REPORT_CONSTRUCTED = "isBtpMonthlyReportConstructed";
	
	public static final String DSR_DAILY_REPORT_CONSTRUCTED = "isDsrDailyReportConstructed";
	
}
