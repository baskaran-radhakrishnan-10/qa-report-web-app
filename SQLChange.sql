ALTER TABLE tbl_users ADD name VARCHAR(50) DEFAULT 'temp' FOR name;
ALTER TABLE tbl_users ADD DEFAULT 'false' FOR first_login;
ALTER TABLE ItemTable ADD gKey int identity(1,1);
ALTER TABLE ResourceTable ADD gKey int identity(1,1);
ALTER TABLE BtpTable ALTER  COLUMN  btpremarks varchar(1000);
UPDATE tbl_users SET  first_login = 'false';

ALTER TABLE DSRTable ALTER  COLUMN  plannedtask varchar(1000);
ALTER TABLE DSRTable ALTER  COLUMN  accomplishedtask varchar(1000);
ALTER TABLE DSRTable ALTER  COLUMN  remarks varchar(1000);