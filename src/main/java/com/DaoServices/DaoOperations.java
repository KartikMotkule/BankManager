package com.DaoServices;

import java.sql.Connection;

import com.Beans.User;

public interface DaoOperations
{
	public boolean addUser(User user,Connection con);
	
}
