package com.Validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Beans.User;


public class UserValidationImpl implements UserValidation {
	
PreparedStatement pstm;
ResultSet rs;
    
    public boolean validateuser(Connection con,User user) {
    	
    	String username=user.getUserName();
    	String password=user.getPassword();
    	
    	boolean isValid= validation(con,username,password);
    	return isValid;
    }

	 boolean validation(Connection con, String username, String password) 
	 {
	 boolean isValid = false;

		try {
			pstm = con.prepareStatement("select * from user where username=? AND password=?");

			pstm.setString(1, username);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			if (rs.next()) {
				isValid = true;
			}
		} 
		catch (SQLException e) {

			e.printStackTrace();
		}
		return isValid;
	 }
	
}
