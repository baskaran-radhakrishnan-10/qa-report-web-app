ALTER TABLE tbl_users ADD name VARCHAR(50) DEFAULT 'temp' FOR name;
ALTER TABLE tbl_users ADD DEFAULT 'false' FOR first_login;
ALTER TABLE ItemTable ADD gKey int identity(1,1);
ALTER TABLE ResourceTable ADD gKey int identity(1,1);
ALTER TABLE BtpTable ALTER  COLUMN  btpremarks varchar(1000);
UPDATE tbl_users SET  first_login = 'false';

ALTER TABLE DSRTable ALTER  COLUMN  plannedtask varchar(1000);
ALTER TABLE DSRTable ALTER  COLUMN  accomplishedtask varchar(1000);
ALTER TABLE DSRTable ALTER  COLUMN  remarks varchar(1000);


ALTER TABLE KTTable ALTER COLUMN title varchar(1000);
ALTER TABLE KTTable ALTER COLUMN trainers varchar(1000);
ALTER TABLE KTTable ALTER COLUMN attendees varchar(1000);
ALTER TABLE KTTable ALTER COLUMN remarks varchar(1000);
ALTER TABLE KTTable ALTER COLUMN feedback varchar(1000);

ALTER TABLE ISDQAReports.KTTable ADD CONSTRAINT PK_TRAINING_ID PRIMARY KEY CLUSTERED (tri_id); 

INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('dashboard_li','Dashboard','dashboard','fa-tachometer',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('rbac_li','Manage Users','rbac/showUser','fa-user-secret',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('rbac_li','Manage Roles','rbac/showRoles','fa-user-secret',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('rbac_li','Manage Password','rbac/managePassword','fa-user-secret',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('build_test_plan_li','Build Test Plan','build_test_plan/show','fa-pencil-square-o',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('daily_status_report_li','Daily Status Report','dsr/show','fa-calendar',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('kt_plan_li','KT Plan','kt_plan/ktPlan','fa-bullhorn',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','BTP Report Search','report_search/btp_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','DSR Report Search','report_search/dsr_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','User Report Search','report_search/user_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','Leave Report Search','report_search/leave_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','Permission Report Search','report_search/permission_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','Leave Plans Report Search','report_search/leave_plan_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Add New User','operation/add_user','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Add New Project','operation/add_project','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Edit User Details','operation/edit_user','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Edit Project Details','operation/edit_project','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Add Leave Details','operation/add_leave','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Add Permission Details','operation/add_permission','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Add Leave Plans','operation/add_leave_plans','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('operations_li','Remainder Settings','operation/remainder_settings','fa-cogs',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO [ISDQAReports].[dbo].[tbl_roles](created_on, modified_on, role_desc, role_name, created_by, modified_by)VALUES (current_timestamp , current_timestamp, 'Super Admin', 'ROLE_SUPER_ADMIN', null, null);

INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,1,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,2,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,3,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,4,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,5,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,6,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,7,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,8,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,9,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,10,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,11,null,null);
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,12,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,13,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,14,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,15,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,16,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,17,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,18,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,19,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,20,null,null);
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,5,21,null,null);

INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,4,1,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,4,18,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,4,19,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,4,20,null,null);
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,4,21,null,null);

INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,1,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,5,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,6,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,7,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,8,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,9,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,10,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,11,null,null);
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,12,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,13,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,14,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,15,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,16,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,17,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,18,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,19,null,null); 
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,20,null,null);
INSERT INTO [ISDQAReports].[dbo]. [tbl_role_resource] (created_on,modified_on,role_id,resource_id,created_by,modified_by) values(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,3,21,null,null);

INSERT INTO [ISDQAReports].[dbo]. [tbl_users] VALUES (CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,'super.admin@equiniti.com','sgpbaRwRHfI=','super_admin',null,null,5,'Super Admin',0);
