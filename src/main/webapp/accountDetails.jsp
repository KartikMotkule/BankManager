<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Beans.Account" %>

<!DOCTYPE html>
<html>
<head>
    <title>Account Details - MoneyManager</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div >
       
        <a href="dashboard.jsp" class="back-button">Back</a>
    </div>
    <div class="container">
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null) {
%>
        <h2>Error</h2>
        <p>Account details could not be retrieved. Please try again.</p>
<%
    } 
    else {
%>
        <h2>Account Details</h2>
        <p>Account Number: <%= account.getUserId() %></p>
        <p>Account Holder: <%= account.getFullName() %></p>
        <p>Account Type: <%= account.getAccountType() %></p>
        <p>Balance: <%= account.getBal() %></p>
<%
    }
%>
    </div>
</body>
</html>
