package com.accessmanagement.servlets;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.accessmanagement.models.Software;

public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("softwareName");
        String description = request.getParameter("description");
        String accessLevels = String.join(",", request.getParameterValues("accessLevels"));

        Software software = new Software(name, description, accessLevels);
        boolean isSuccess = software.save(); // Assuming Software.save() saves to DB.

        if (isSuccess) {
            response.sendRedirect("createSoftware.jsp?success=true");
        } else {
            request.setAttribute("error", "Failed to add software.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("createSoftware.jsp");
            dispatcher.forward(request, response);
        }
    }
}
