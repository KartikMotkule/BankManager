<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.Beans.Account" %> <!-- Import the Account class -->

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - MoneyManager</title>
    <link rel="stylesheet" href="styles.css">
    <script src="scripts.js"></script>
</head>
<body>
    <!-- Navbar Section -->
    <div class="navbar">
        <h1>MoneyManage₹</h1>
        <span id="current-time"></span>
    </div>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <ul>
            <li><a href="createAccount.jsp">Create Account</a></li>
            <li><a href="accountDetails.jsp">Account Details</a></li>
            <li><a href="fundTransfer.jsp">Fund Transfer</a></li>
            <li><a href="balanceEnquiry.jsp">Balance Enquiry</a></li>
            <li><a href="transactionHistory.jsp">Transaction History</a></li>
            <li><a href="logout.jsp" class="logout-sidebar">Logout</a></li>
        </ul>
    </div>

    <!-- Content Section -->
    <div class="content">
        <div class="greeting">
            <% 
                // Get the Account object from the session
                HttpSession sess = request.getSession(false);
                Account account = (Account) sess.getAttribute("account"); 
                
                if (account != null) {
                    
                    String fullName = account.getFullName(); 
            %>
                <h2>Welcome, <%= fullName %></h2>
            <% 
                } 
                else {
               
                    out.println("<h2>Welcome, Guest</h2>");
                }
            %>
            
        </div>

       <div class="image-gallery">
		    <img src="images/pic1.jpg" alt="Scheme 1" class="gallery-image ">
		    <img src="images/pic2.jpg" alt="Scheme 2" class="gallery-image ">
		    <img src="images/pic3.jpg" alt="Scheme 3" class="gallery-image ">
		    <img src="images/pic4.jpg" alt="Scheme 4" class="gallery-image ">
		    <img src="images/pic5.jpg" alt="Scheme 5" class="gallery-image ">
		    <img src="images/pic6.jpg" alt="Scheme 6" class="gallery-image ">
		</div>
        
    </div>
</body>
</html>
