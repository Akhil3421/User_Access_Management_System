<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Software</title>
</head>
<body>
    <h2>Create New Software</h2>
    <form action="SoftwareServlet" method="post">
        <label for="softwareName">Software Name:</label>
        <input type="text" name="softwareName" id="softwareName" required><br><br>

        <label for="description">Description:</label>
        <textarea name="description" id="description" rows="4" required></textarea><br><br>

        <label for="accessLevels">Access Levels:</label><br>
        <input type="checkbox" name="accessLevels" value="Read"> Read<br>
        <input type="checkbox" name="accessLevels" value="Write"> Write<br>
        <input type="checkbox" name="accessLevels" value="Admin"> Admin<br><br>

        <button type="submit">Create Software</button>
    </form>

    <div>
        <% if (request.getParameter("success") != null) { %>
            <p style="color: green;">Software created successfully!</p>
        <% } %>
    </div>
</body>
</html>
