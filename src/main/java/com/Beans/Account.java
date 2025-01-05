package com.Beans;

public class Account 
{
    private String userName;
    private int userId;
    private String fullName;
    private String accountType;
    private double bal;

    // Constructor
    public Account() {}

    public Account(String userName, int userId, String fullName, String accountType, double bal) {
        this.userName = userName;
        this.userId = userId;
        this.fullName = fullName;
        this.accountType = accountType;
        this.bal = bal;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBal() {
        return bal;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }

    // toString() method 
    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", bal=" + bal +
                '}';
    }
}

