package com.accessmanagement.servlets;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.accessmanagement.models.User;

public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password, "Employee");
        boolean isSuccess = user.save();

        if (isSuccess) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Sign-up failed. Try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
        }
    }
}
