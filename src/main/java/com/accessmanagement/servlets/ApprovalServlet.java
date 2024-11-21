package com.accessmanagement.servlets;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.accessmanagement.models.Request;

public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String status = request.getParameter("status");

        boolean isSuccess = Request.updateStatus(requestId, status); // Update request status.

        if (isSuccess) {
            response.sendRedirect("pendingRequests.jsp?success=true");
        } else {
            request.setAttribute("error", "Failed to update request.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("pendingRequests.jsp");
            dispatcher.forward(request, response);
        }
    }
}
