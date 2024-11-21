package com.accessmanagement.models;

import java.sql.*;

public class Request {
    private int id;
    private int userId;
    private int softwareId;
    private String accessType;
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public Request(int userId, int softwareId, String accessType, String reason, String status) {
        this.userId = userId;
        this.softwareId = softwareId;
        this.accessType = accessType;
        this.reason = reason;
        this.status = status;
    }

    public boolean save() {
        try (Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, userId);
            stmt.setInt(2, softwareId);
            stmt.setString(3, accessType);
            stmt.setString(4, reason);
            stmt.setString(5, status);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateStatus(int requestId, String status) {
        try (Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE requests SET status = ? WHERE id = ?")) {
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
