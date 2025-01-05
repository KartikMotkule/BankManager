package com.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Beans.User;
import com.DaoServices.DaoOperations;

public class DaoOperationImpl implements DaoOperations 
{
	PreparedStatement pstm;
	ResultSet rs;
	int count;
	@Override
	public boolean addUser(User user,Connection con)
	{
		boolean flag=false;
		try {
			 pstm=con.prepareStatement("insert into user values(?,?,?,?)");
			 pstm.setString(1,user.getUserName());
			 pstm.setString(2, user.getPassword());
			 pstm.setString(3, user.getSecQue());
			 pstm.setString(4, user.getSecAns());
			 
			 count=pstm.executeUpdate();
			 if(count>0)
			 {
				 flag=true;
			 }
			 
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

}
