package com.Beans;

import java.util.Date;

public class Transaction {

    
    private int fromAccountId;
    private int toAccountId;
    private double amount;
    private Date transactionDate;
  

    // Default constructor
    public Transaction() {
    }

    // Parameterized constructor
    public Transaction(int transactionId, int fromAccountId, int toAccountId, double amount, Date transactionDate, String description) {
        
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
       
    }

    // Getters and Setters
    
    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

   

    // Optional: toString method for debugging and logging
    @Override
    public String toString() {
        return "Transaction{" +
               ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}