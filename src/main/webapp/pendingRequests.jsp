<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.accessmanagement.models.Request" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Access Requests</title>
</head>
<body>
    <h2>Pending Access Requests</h2>
    <table border="1">
        <tr>
            <th>Employee Name</th>
            <th>Software Name</th>
            <th>Access Type</th>
            <th>Reason</th>
            <th>Action</th>
        </tr>
        <%-- Retrieve the list of pending requests from the request attribute --%>
        <%
            List<Request> pendingRequests = (List<Request>) request.getAttribute("pendingRequests");
            if (pendingRequests != null && !pendingRequests.isEmpty()) {
                for (Request req : pendingRequests) {
        %>
            <tr>
                <td><%= req.getUserId() %></td> <!-- Assuming you want to show user ID (or you can fetch user name using the userId) -->
                <td><%= req.getSoftwareId() %></td> <!-- Assuming you want to show software ID (you can fetch the software name if needed) -->
                <td><%= req.getAccessType() %></td>
                <td><%= req.getReason() %></td>
                <td>
                    <form action="ApprovalServlet" method="post" style="display: inline;">
                        <input type="hidden" name="requestId" value="<%= req.getId() %>">
                        <input type="hidden" name="status" value="Approved">
                        <button type="submit">Approve</button>
                    </form>
                    <form action="ApprovalServlet" method="post" style="display: inline;">
                        <input type="hidden" name="requestId" value="<%= req.getId() %>">
                        <input type="hidden" name="status" value="Rejected">
                        <button type="submit">Reject</button>
                    </form>
                </td>
            </tr>
        <%
                }
            }
        %>
    </table>

    <div>
        <% if (request.getParameter("success") != null) { %>
            <p style="color: green;">Request updated successfully!</p>
        <% } %>
    </div>
</body>
</html>
