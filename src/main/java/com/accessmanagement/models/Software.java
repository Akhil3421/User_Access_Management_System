package com.accessmanagement.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Software {
    private int id;
    private String name;
    private String description;
    private String accessLevels;

    // Constructor

    public Software()
    {

    }

    public Software(String name, String description, String accessLevels) {
        this.name = name;
        this.description = description;
        this.accessLevels = accessLevels;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAccessLevels() {
        return accessLevels;
    }

    // Save method for inserting a new software record into the database
    public boolean save() {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, accessLevels);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccessLevels(String accessLevels) {
        this.accessLevels = accessLevels;
    }

    public static List<Software> getAllSoftware() {
        List<Software> softwareList = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id, name, description, access_levels FROM software");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Software software = new Software();
                software.setId(rs.getInt("id"));
                software.setName(rs.getString("name"));
                software.setDescription(rs.getString("description"));
                software.setAccessLevels(rs.getString("access_levels"));
                softwareList.add(software);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return softwareList;
    }

}
