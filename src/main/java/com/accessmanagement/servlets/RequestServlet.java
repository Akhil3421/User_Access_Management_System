package com.accessmanagement.servlets;

import java.io.IOException;

import com.accessmanagement.models.Request;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.accessmanagement.models.Software;
import java.util.List;

public class RequestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Software> softwareList = Software.getAllSoftware();

        if (softwareList != null && !softwareList.isEmpty()) {
            System.out.println("Software list contains " + softwareList.size() + " items.");
        } else {
            System.out.println("Software list is empty or null.");
        }

        request.setAttribute("softwareList", softwareList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/requestAccess.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int softwareId = Integer.parseInt(request.getParameter("softwareId"));
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        Request req = new Request(userId, softwareId, accessType, reason, "Pending");
        boolean isSuccess = req.save();

        if (isSuccess) {
            response.sendRedirect("requestAccess.jsp?success=true");
        } else {
            request.setAttribute("error", "Failed to submit request.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("requestAccess.jsp");
            dispatcher.forward(request, response);
        }
    }

}
