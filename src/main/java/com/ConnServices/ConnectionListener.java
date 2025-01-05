package com.ConnServices;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent e) {
		Connection con = null;
		try {
			con = (Connection) e.getServletContext().getAttribute("DBConnection");
			con.close();
		} 
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent e) {
		Connection con = null;
		try {
			String driver = e.getServletContext().getInitParameter("driverclass");
			String url = e.getServletContext().getInitParameter("url");
			String user = e.getServletContext().getInitParameter("username");
			String pass = e.getServletContext().getInitParameter("pass");

			// biuld connection object
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			// put connection object in servlet context obj
			e.getServletContext().setAttribute("DBConnection",con);

		} 
		catch (Exception e1) 
		{ 
			System.out.println("Here");
			e1.printStackTrace();
		}
	}

}
