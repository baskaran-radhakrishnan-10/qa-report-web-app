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
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','BTP Report','report_search/btp_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','DSR Report','report_search/dsr_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','User Report','report_search/user_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','Leave Report','report_search/leave_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','Permission Report','report_search/permission_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO [ISDQAReports].[dbo].[tbl_resources] (resource_module,resource_name,resource_url,resource_module_icon,created_by,modified_by,created_on,modified_on) VALUES ('report_search_li','Leave Plans Report','report_search/leave_plan_report_search','fa-search',null,null,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
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

insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Ashfak Peer','eics\ashfakp','sgpbaRwRHfI=','ashfak.peer@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Ashwin Ravichandran','isd\arn','sgpbaRwRHfI=','ashwin.ravichandran@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Aswin Ramachandran','isd\ajn','sgpbaRwRHfI=','aswin.ramachandran@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Balaji Murugan','isd\bmn','sgpbaRwRHfI=','balaji.murugan@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Deepa Shanmugam','eics\deepas','sgpbaRwRHfI=','deepa.shanmugam@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Divya','eics\divyaj','sgpbaRwRHfI=','divya@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Gobikarthick','eics\gobikarthickr','sgpbaRwRHfI=','gobikarthick@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Harini','eics\hthangamari','sgpbaRwRHfI=','harini@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Imran','eics\imranhv','sgpbaRwRHfI=','imran@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Jagan Ramasamy','eics\jaganathanr','sgpbaRwRHfI=','jagan.ramasamy@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Kamal','eics\kamalr','sgpbaRwRHfI=','kamal@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('karthick','eics\karthickv','sgpbaRwRHfI=','karthick@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Naveen Kandoji','eics\naveenk','sgpbaRwRHfI=','naveen.kandoji@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Prabhu Rajagopal','isd\prl','sgpbaRwRHfI=','prabhu.rajagopal@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Prabhu Srinivasan','eics\prabhus','sgpbaRwRHfI=','prabhu.srinivasan@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Prabu Rajasekar','eics\prabu','sgpbaRwRHfI=','prabu.rajasekar@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Pramothkumar Moorthi','eics\pramothkumarm','sgpbaRwRHfI=','pramothkumar.moorthi@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Prasanna Venkatesh','isd\pvh','sgpbaRwRHfI=','prasanna.venkatesh@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Prem Anandakrishnan','isd\pan','sgpbaRwRHfI=','prem.anandakrishnan@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Rajan Apparsamy','isd\ray','sgpbaRwRHfI=','rajan.apparsamy@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Ram Kumar','eics\ramk','sgpbaRwRHfI=','ram.kumar@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Ramesh','eics\rameshpa','sgpbaRwRHfI=','ramesh@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Ramya Chandrasekar','eics\ramyac','sgpbaRwRHfI=','ramya.chandrasekar@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('SampathkumarM','eics\sampathkumarm','sgpbaRwRHfI=','sampathkumarm@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('SampathM','eics\sampath','sgpbaRwRHfI=','sampathm@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Saranya Rajendran','eics\saranyar','sgpbaRwRHfI=','saranya.rajendran@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Saravanakumar','eics\saravanakumars','sgpbaRwRHfI=','saravanakumar@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Satheesh','eics\satheeshkumart','sgpbaRwRHfI=','satheesh@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Satheeshkumar.A','eics\satheeshkumara','sgpbaRwRHfI=','satheeshkumar.a@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('sdsds','sdsdsds','sgpbaRwRHfI=','sdsds@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Senthil Chelliah','isd\sch','sgpbaRwRHfI=','senthil.chelliah@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Shashi Kumar','isd\skr','sgpbaRwRHfI=','shashi.kumar@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Sindhu Ravi','eics\sindhur','sgpbaRwRHfI=','sindhu.ravi@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Suresh Kannan','eics\sureshk','sgpbaRwRHfI=','suresh.kannan@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('SureshKumar D','eics\sureshkde','sgpbaRwRHfI=','sureshkumar.d@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Venkadesh','eics\venkadeshr','sgpbaRwRHfI=','venkadesh@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Vidya','eics\vidyab','sgpbaRwRHfI=','vidya@equiniti.com',1,4,1,GETDATE());
insert into ISDQAReports..tbl_users (name,user_id,password,email_id,is_active,role_id,first_login,created_on) values ('Vinoth Karunakaran','eics\vinothk','sgpbaRwRHfI=','vinoth.karunakaran@equiniti.com',1,4,1,GETDATE());

ALTER TABLE LEAVEDETAILS ADD gKey int identity(1,1);

ALTER TABLE LEAVEPLANSINFO ADD gKey int identity(1,1);

ALTER TABLE PERMISSIONDETAILS ADD gKey int identity(1,1);
