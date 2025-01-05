<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Account - MoneyManager</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="acccreate">
	
    <div >
    <a href="dashboard.jsp" class="back-button">Back</a>
    </div>

    <div class="container">
        <h2>Create New Account</h2>
        <form action="MoneyManagerServlet" method="post" class="create-account-form">
            <input type="hidden" name="action" value="createAccount" />
            <label for="holderName">Account Holder Name:</label>
            <input type="text" id="holderName" name="holderName" required>

            <label for="accountType">Account Type:</label>
            <select id="accountType" name="accountType" required>
                <option value="Savings">Savings</option>
                <option value="Current">Current</option>
            </select>

            <input type="submit" value="Create Account">
        </form>
    </div>
    
</body>
</html>
