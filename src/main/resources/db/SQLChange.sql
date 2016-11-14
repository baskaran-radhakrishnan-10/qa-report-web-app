update ISDQAReports..tbl_resources set resource_name='Reminder Settings' where resource_module='operations_li' and resource_name='Remainder Settings';
delete from ISDQAReports..tbl_role_resource where resource_id in (select gkey from ISDQAReports..tbl_resources where resource_url in ('operation/add_user','operation/edit_user'));
delete from ISDQAReports..tbl_resources where resource_url in ('operation/add_user','operation/edit_user');

delete from ISDQAReports..tbl_role_resource where resource_id in (select gkey from ISDQAReports..tbl_resources where resource_url='operation/edit_project');
delete from ISDQAReports..tbl_resources where resource_url='operation/edit_project';
update ISDQAReports..tbl_resources set resource_name='Manage Projects',resource_url='operation/manage_projects' where resource_url='operation/add_project';