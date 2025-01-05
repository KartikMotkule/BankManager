package com.moneymanager.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.servlet.http.HttpSession;
import com.Beans.Account;
import com.Beans.Transaction;

public class MoneyManagerDAOImpl implements MoneyManagerDAO {
   
	Account acc;
    Connection con;
    HttpSession session;
    public MoneyManagerDAOImpl(Connection con, HttpSession session) 
    {
		this.con=con;
		this.session=session;
	}

	

    @Override
    public Account getAccountDetails(String userName) {
        Account account = null;
        try {
            String query = "SELECT * FROM accounts WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                account = new Account();
                account.setUserId(rs.getInt("account_id"));
                account.setFullName(rs.getString("holder_name"));
                account.setAccountType(rs.getString("account_type"));
                account.setBal(rs.getDouble("balance"));
                account.setUserName(userName); // Ensure userName is set in the Account object
            }
            // Added account in session
            session.setAttribute("account", account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
  
    public boolean transferFunds(int fromAccountId, int toAccountId, double amount) throws SQLException {
        boolean flag = false;
        con.setAutoCommit(false);
        try {
            // Deduct from sender
            String deductQuery = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            try (PreparedStatement ps = con.prepareStatement(deductQuery)) {
                ps.setDouble(1, amount);
                ps.setInt(2, fromAccountId);
                ps.executeUpdate();
            }

            // Add to receiver
            String addQuery = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
            try (PreparedStatement ps = con.prepareStatement(addQuery)) {
                ps.setDouble(1, amount);
                ps.setInt(2, toAccountId);
                ps.executeUpdate();
            }
            
            // Fetch the updated balance for the account and update the session object
            Account acc = (Account) session.getAttribute("account");
            if (acc != null) {
                String query = "SELECT balance FROM accounts WHERE username = ?";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, acc.getUserName());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        acc.setBal(rs.getDouble("balance")); // Update balance in the session object
                    } else {
                        System.out.println("No data found");
                    }
                }
            } 
            else {
                System.out.println("Account not found in session.");
            }
            addTransaction( fromAccountId, toAccountId,  amount);
            con.commit();
            flag = true;
        }
        catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }
        finally {
            con.setAutoCommit(true);
        }
        getBalance();
        return flag;
    }


    @Override
    public double getBalance() throws SQLException {
    	Account acc = (Account) session.getAttribute("account");
    	int accountId=acc.getUserId();
    	System.out.println(accountId);
        String query = "SELECT balance FROM accounts WHERE account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	System.out.println(rs.getDouble("balance"));
                    return rs.getDouble("balance");
                }
            }
        }
        return 0.0;
    }

    @Override
 
    public List<Transaction> getTransactionHistory(int accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT from_account_id, to_account_id, amount, transaction_date FROM transactions WHERE from_account_id = ? OR to_account_id = ? ORDER BY transaction_date DESC";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, accountId);
            ps.setInt(2, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();

                  
                    transaction.setFromAccountId(rs.getInt("from_account_id"));
                    transaction.setToAccountId(rs.getInt("to_account_id"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

                    transactions.add(transaction);
                }
            }
        }

        return transactions;
    }


  
  public int createAccount(String holderName, String accountType,String userName)
    {
        String query = "INSERT INTO accounts (holder_name, account_type, balance,username) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, holderName);
            ps.setString(2, accountType);
            ps.setDouble(3, 0.0); // Initial balance is 0
            ps.setString(4, userName);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                 if (rs.next())
                    {
                        return rs.getInt(1); // Returning the generated account ID
                    }
                }
            }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
     return -1; // Return -1 if account creation failed
    }
  
  public boolean addTransaction(int fromAccountId, int toAccountId, double amount) throws SQLException {
	    String query = "INSERT INTO transactions (from_account_id, to_account_id, amount) VALUES (?, ?, ?)";
	    try (PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, fromAccountId);
	        ps.setInt(2, toAccountId);
	        ps.setDouble(3, amount);
	        return ps.executeUpdate() > 0;
	    }
	}


}
