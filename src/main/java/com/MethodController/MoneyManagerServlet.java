package com.MethodController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import com.Beans.Account;
import com.Beans.Transaction;
import com.moneymanager.dao.*;

public class MoneyManagerServlet extends HttpServlet {
    private Connection con;

    @Override
    public void init() {
        
        System.out.println("money manger servlet called");
        con = (Connection) getServletConfig().getServletContext().getAttribute("DBConnection");
        if (con == null) {
            System.out.println("Null connection");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        if (session == null || session.getAttribute("name") == null) {
            response.sendRedirect("login.jsp"); // Redirecting to login if the session is invalid
            return;
        }

        String action = request.getParameter("action");
        MoneyManagerDAO dao = new MoneyManagerDAOImpl(con, session);

        try {
            switch (action) {
                case "createAccount":
                    createAccount(request, response, dao, session);
                    break;
                case "transferFunds":
                    transferFunds(request, response, dao);
                    break;
                case "getBalance":
                	getBalance(request, response, dao);
                    break;
                case "getTransactionHistory":
                    getTransactionHistory(request, response, dao);
                    break;
                
                default:
                    response.sendRedirect("error.jsp"); // Redirect if action is invalid
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response, MoneyManagerDAO dao, HttpSession session) throws ServletException, IOException {
        String userName = (String) session.getAttribute("name");
        String holderName = request.getParameter("holderName");
        String accountType = request.getParameter("accountType");

        int generatedId = dao.createAccount(holderName, accountType, userName);

        if (generatedId > 0) {
            request.setAttribute("message", "Account created successfully! Your Account ID is: " + generatedId);
        } else {
            request.setAttribute("message", "Failed to create account. Please try again.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);
    }

    private void transferFunds(HttpServletRequest request, HttpServletResponse response, MoneyManagerDAO dao) throws ServletException, IOException {
        int fromAccountId = Integer.parseInt(request.getParameter("fromAccountId"));
        int toAccountId = Integer.parseInt(request.getParameter("toAccountId"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        boolean success;
		try {
			success = dao.transferFunds(fromAccountId, toAccountId, amount);
			request.setAttribute("message", success ? "Funds transferred successfully!" : "Failed to transfer funds.");
	        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
	        rd.forward(request, response);
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}

        
    }

    private void getBalance(HttpServletRequest request, HttpServletResponse response, MoneyManagerDAO dao) throws ServletException, IOException {
        
    	HttpSession session=request.getSession(false);
    	
    	 double balance;
		try {
			balance = dao.getBalance();
			System.out.println(balance);
			request.setAttribute("balance", balance);

	        RequestDispatcher rd = request.getRequestDispatcher("balance.jsp");
	        rd.forward(request, response);
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
        
    }

    private void getTransactionHistory(HttpServletRequest request, HttpServletResponse response, MoneyManagerDAO dao) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        
        Account account =(Account) session.getAttribute("account");
    	int accountId = account.getUserId();

        List<Transaction> transactions;
		try {
			transactions = dao.getTransactionHistory(accountId);
			request.setAttribute("transactions", transactions);
			System.out.println("Passing id: accountId");
			session.setAttribute("userId", accountId);
			
	        RequestDispatcher rd = request.getRequestDispatcher("transactions.jsp");
	        rd.forward(request, response);
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
        
    }

    
}
