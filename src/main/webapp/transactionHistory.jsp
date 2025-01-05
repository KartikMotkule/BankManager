<%@ page import="java.util.List" %>
<%@ page import="com.moneymanager.dao.MoneyManagerDAO" %>
<%@ page import="com.moneymanager.dao.MoneyManagerDAOImpl" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.Beans.Transaction" %>

<%
    // Retrieve userId from the session (or request if set in request)
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("name") == null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    // User's account info
    String userName = (String) sess.getAttribute("userName");
    Integer userId = (Integer) sess.getAttribute("userId"); 

    if (userId == null) {
        out.print("null id ");
        return;
    }

    // Get the database connection from the servlet context
    Connection con = (Connection) getServletContext().getAttribute("DBConnection");
    MoneyManagerDAO dao = new MoneyManagerDAOImpl(con, sess);

    // Fetch transaction history using the userId
    List<Transaction> transactions = dao.getTransactionHistory(userId);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Transaction Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body class="container">
	<a href="dashboard.jsp" class="back-button">Back</a>
<!-- Transaction History Table -->
    <h2 class="table-h2">Transaction History</h2>
    <table border="1">
        <thead>
            <tr>
                <th>From Account</th>
                <th>To Account</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Transaction transaction : transactions) {
            %>
                <tr>
                    <td><%= transaction.getFromAccountId() %></td>
                    <td><%= transaction.getToAccountId() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getTransactionDate() %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
