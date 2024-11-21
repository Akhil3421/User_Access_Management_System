package com.accessmanagement.servlets;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.accessmanagement.models.User;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = User.authenticate(username, password); // Authenticate user.

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            switch (user.getRole()) {
                case "Employee":
                    response.sendRedirect("requestAccess.jsp");
                    break;
                case "Manager":
                    response.sendRedirect("pendingRequests.jsp");
                    break;
                case "Admin":
                    response.sendRedirect("createSoftware.jsp");
                    break;
            }
        } else {
            request.setAttribute("error", "Invalid credentials.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
