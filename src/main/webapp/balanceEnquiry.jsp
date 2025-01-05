<%@ page import="java.sql.Connection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.moneymanager.dao.MoneyManagerDAO" %>
<%@ page import="com.moneymanager.dao.MoneyManagerDAOImpl" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.Beans.Account" %>

<%
    // Checking if the session exists and is valid
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("name") == null) {
        response.sendRedirect("login.jsp"); // Redirect if session is invalid
        return;
    }

    // Retrieve the username and account ID from the session
    String userName = (String) sess.getAttribute("name");
    Connection con = (Connection) getServletContext().getAttribute("DBConnection");
    MoneyManagerDAO dao = new MoneyManagerDAOImpl(con, sess);

    // Fetch the account details for the user
    Account account = dao.getAccountDetails(userName);
    if (account == null) {
        out.print("Account details not found.");
        return;
    }

    double balance = account.getBal();  // Assuming the Account object has a method getBalance
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Balance Enquiry - MoneyManager</title>
    <link rel="stylesheet" href="styles.css">
    
</head>
<body>

    <a href="dashboard.jsp" class="back-button">Back</a>

    <div class="container">
        <h2>Balance Enquiry</h2>
        <p>Your current account balance is:</p>
        <div class="balance">
            Rs. <%= String.format("%.2f", balance) %>
        </div>
    </div>

   

</body>
</html>
