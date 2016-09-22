package com.equiniti.qa_report.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectMSSQLServer
{
	public void dbConnect(String db_connect_string,String db_userid,String db_password){
		try {
			//db_password="baski";
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Properties dbProp=new Properties();
			dbProp.setProperty("user", "boss");
			dbProp.setProperty("password", "Pass123");
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://CHEQL0075TEMP;databaseName=ISDQAReports;integratedSecurity=true;",dbProp);
			System.out.println("connected");
			Statement statement = conn.createStatement();
			String queryString = "select * from sysobjects where type='u'";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		ConnectMSSQLServer connServer = new ConnectMSSQLServer();
		connServer.dbConnect("jdbc:sqlserver://localhost/SQLEXPRESS/Databases/qa_report_db:1433;", "CHEQL0075TEMP/BaskaranR","");
	}
}
