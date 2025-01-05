package com.ConnServices;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionProvider 
{
	
		public static Connection createConnection() 
		{
			Connection con=null;
			try {
				FileInputStream fis= new FileInputStream(".//Resources//dbconfig.properties");
				Properties p= new Properties();
				p.load(fis);
				
				String url=p.getProperty("url");
				String userName=p.getProperty("userName");
				String passWord= p.getProperty("passWord");
				//Driver load
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Built connection
				con= DriverManager.getConnection(url,userName,passWord);
			
			
				}
			catch(Exception e) 
			{
				
				e.printStackTrace();
			}
	
				return con;
		}	
}
