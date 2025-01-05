package com.moneymanager.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.Beans.Account;
import com.Beans.Transaction;

public interface MoneyManagerDAO {
    // Account Details
	public Account getAccountDetails(String name);

    // Fund Transfer
    boolean transferFunds(int fromAccountId, int toAccountId, double amount) throws SQLException;

   // Transaction History
    List<Transaction> getTransactionHistory(int accountId) throws SQLException;

    
    //to create account
    public int createAccount(String holderName, String accountType,String username);
    public boolean addTransaction(int fromAccountId, int toAccountId, double amount) throws SQLException;

	double getBalance() throws SQLException;
}

