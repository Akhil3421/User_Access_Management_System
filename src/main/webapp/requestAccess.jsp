<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.accessmanagement.models.Software" %>
<!DOCTYPE html>
<html>
<head>
    <title>Request Access</title>
</head>
<body>
    <h2>Request Access to Software</h2>
    <form action="RequestServlet" method="post">
        <label for="softwareId">Select Software:</label>
        <select name="softwareId" id="softwareId" required>
            <%
                List<Software> softwareList = (List<Software>) request.getAttribute("softwareList");
                if (softwareList != null && !softwareList.isEmpty()) {
                    for (Software software : softwareList) {
            %>
            <option value="<%= software.getId() %>"><%= software.getName() %> - <%= software.getDescription() %></option>
            <%
                    }
                } else {
            %>
            <option value="">No Software Available</option>
            <%
                }
            %>
        </select><br><br>

        <label for="accessType">Access Type:</label>
        <select name="accessType" id="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br><br>

        <label for="reason">Reason for Request:</label>
        <textarea name="reason" id="reason" rows="4" required></textarea><br><br>

        <button type="submit">Submit Request</button>
    </form>

    <div>
        <% if (request.getParameter("success") != null) { %>
            <p style="color: green;">Request submitted successfully!</p>
        <% } %>
    </div>
</body>
</html>
