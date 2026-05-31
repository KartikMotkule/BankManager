package com.Beans;

public class User {

    // Instance variables
    private String userName;
    private String password;
    private String secQue;
    private String secAns;


    // Constructor
    public User(String userName, String password, String secQue, String secAns) {
        this.userName = userName;
        this.password = password;
        this.secQue = secQue;
        this.secAns = secAns;
    }

    // Default constructor
    public User()
    {
    	
    }
    

    // Getter and Setter for userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for secQue
    public String getSecQue() {
        return secQue;
    }

    public void setSecQue(String secQue) {
        this.secQue = secQue;
    }

    // Getter and Setter for secAns
    public String getSecAns() {
        return secAns;
    }

    public void setSecAns(String secAns) {
        this.secAns = secAns;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", secQue='" + secQue + '\'' +
                ", secAns='" + secAns + '\'' +
                '}';
    }
}

