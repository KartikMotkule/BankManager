package com.Validation;

import java.sql.Connection;

import com.Beans.User;

public interface UserValidation {
	
	boolean validateuser(Connection con,User user);
}
