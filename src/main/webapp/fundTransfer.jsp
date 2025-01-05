<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Fund Transfer - MoneyManager</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div >
        
        <a href="dashboard.jsp" class="back-button">Back</a>
    </div>
    <div class="container">
        <h2>Fund Transfer</h2>
        <form action="MoneyManagerServlet" method="post">
        <input type="hidden" name="action" value="transferFunds" />
        <div class="ftrans">
            <div >
            <label for="fromAccount">From Account:</label>
            <input type="text" id="fromAccount" name="fromAccountId" required>
			</div>
			 <div >
            <label for="toAccount">To Account:</label>
            <input type="text" id="toAccount" name="toAccountId" required>
			</div>
			<div >
            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount" required>
			</div>
			<div >
            <button type="submit"> Send </button>
            </div>
            </div>
        </form>
    </div>
</body>
</html>
