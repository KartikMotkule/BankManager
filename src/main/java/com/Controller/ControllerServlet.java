package com.Controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Beans.Account;
import com.Beans.User;
import com.DaoImpl.DaoOperationImpl;
import com.Validation.UserValidation;
import com.Validation.UserValidationImpl;
import com.moneymanager.dao.*;

public class ControllerServlet extends HttpServlet {
    
	private Connection con = null;

    public void init() {
        System.out.println("Servlet init method called");
        con = (Connection) getServletConfig().getServletContext().getAttribute("DBConnection");
        if (con == null) {
            System.out.println("Null connection - Database connection is not initialized");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
    	String action = request.getParameter("action");
        User user = new User();
        HttpSession session = null;
        RequestDispatcher rd = null;

        try {
            if ("home".equals(action)) {
                // Retrieve user credentials from the request
                String userName = request.getParameter("username");
                String password = request.getParameter("password");

                user.setUserName(userName);
                user.setPassword(password);

                System.out.println("Attempting login for user: " + userName);//log

                // Validate user credentials
                UserValidation validation = new UserValidationImpl();
                if (validation.validateuser(con, user)) {
                    // Create or retrieve existing session
                    session = request.getSession(true);
                    session.setAttribute("name", userName);
                    
                    System.out.println("Session ID: " + session.getId()); //log
                    
                    // Retrieve account details from DAO
                    MoneyManagerDAOImpl dao = new MoneyManagerDAOImpl(con,session);
                    
                    Account account = dao.getAccountDetails(userName); //  userName as the key
                    
                    if (account != null)
                    {
                    	session.setAttribute("account", account);
                    	Account acc =(Account) session.getAttribute("account");
                    	int accountId = acc.getUserId();
                    	session.setAttribute("userId", accountId);
                        System.out.println("Account details retrieved successfully for user: " + userName);
                        System.out.println("Account in session: " + session.getAttribute("account"));

                        
                    } 
                    else
                    {
                        System.out.println("No account details found for user: " + userName);
                        session.setAttribute("accountError", "Account details not available.");
                    }

                    // Forward to dashboard
                    rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
                    rd.forward(request, response);
                }
                
                else
                {
                    System.out.println("Invalid credentials for user: " + userName);
                    rd = getServletContext().getRequestDispatcher("/Home.html");
                    rd.forward(request, response);
                }
            }
            
        else if ("signup".equals(action))
        {
                // Handle signup action
                String userName = request.getParameter("username");
                String password = request.getParameter("password");
                String secQue = request.getParameter("security-question");
                String secAns = request.getParameter("security-answer");

                user.setUserName(userName);
                user.setPassword(password);
                user.setSecQue(secQue);
                user.setSecAns(secAns);

                // Save the new user
                DaoOperationImpl opr = new DaoOperationImpl();
                if (opr.addUser(user, con)) {
                    System.out.println("User added successfully: " + userName);
                    rd = getServletContext().getRequestDispatcher("/Home.html");
                    rd.forward(request, response);
                } else {
                    System.out.println("Failed to add user: " + userName);
                    response.getWriter().println("Signup failed. Please try again.");
                }
            } else {
                response.getWriter().println("Invalid action.");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().println("An error occurred. Please try again later.");
        }
    }
}
