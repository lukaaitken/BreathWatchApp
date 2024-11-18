package main;

import java.sql.*;

public class UserDataManager {

    private static final String DATABASE_URL = "jdbc:sqlite:sample.db"; // Adjust with your database path

    // Method to get the database connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    // Insert or update patient data
    public void insertOrUpdatePatientData(String patientId, String symptoms, double breathingRate, double heartRate) {
        String query = "SELECT * FROM PatientData WHERE patient_id = ?";
        String updateQuery = "UPDATE PatientData SET symptoms = ?, breathing_rate = ?, heart_rate = ?, timestamp = CURRENT_TIMESTAMP WHERE patient_id = ?";
        String insertQuery = "INSERT INTO PatientData (patient_id, symptoms, breathing_rate, heart_rate, timestamp) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = getConnection()) {
            // Check if the patient already exists
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, patientId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Patient exists, update their data and set the timestamp to current
                    try (PreparedStatement updatePs = connection.prepareStatement(updateQuery)) {
                        updatePs.setString(1, symptoms);
                        updatePs.setDouble(2, breathingRate);
                        updatePs.setDouble(3, heartRate);
                        updatePs.setString(4, patientId);
                        updatePs.executeUpdate();
                    }
                } else {
                    // Patient doesn't exist, insert a new record
                    try (PreparedStatement insertPs = connection.prepareStatement(insertQuery)) {
                        insertPs.setString(1, patientId);
                        insertPs.setString(2, symptoms);
                        insertPs.setDouble(3, breathingRate);
                        insertPs.setDouble(4, heartRate);
                        insertPs.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch all patient data ordered by timestamp (most recent last)
    public ResultSet fetchAllPatientData() throws SQLException {
        String query = "SELECT * FROM PatientData ORDER BY timestamp ASC"; // Get the most recent data at the bottom
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }
}


// this code works with database
/*package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDataManager {

    private static final String DB_URL = "jdbc:sqlite:sample.db"; // Path to your SQLite database

    // Establish connection to the database
    public Connection connect() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish and return connection
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Insert patient data into the database
    public void insertPatientData(String patientId, String symptoms, double breathingRate, double heartRate) {
        String sql = "INSERT INTO patient_data (patient_id, symptoms, breathing_rate, heart_rate) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the values in the prepared statement
            pstmt.setString(1, patientId);
            pstmt.setString(2, symptoms);
            pstmt.setDouble(3, breathingRate);
            pstmt.setDouble(4, heartRate);

            // Execute the insert statement
            pstmt.executeUpdate();
            System.out.println("Patient data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/

/*package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    private static final String USER_FILE = "src/data/users.txt"; // Path to user data file

    public UserDataManager() {
        // Ensure the user data file exists
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it does not exist
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    // Method to add a user to the file
    public void addUser(String username, String hashedPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(username + ":" + hashedPassword);
            writer.newLine(); // Write new line after each entry
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    // Method to check if a user exists
    public boolean userExists(String username, String hashedPassword) {
        List<String> users = readUsersFromFile(); // Read existing users
        return users.contains(username + ":" + hashedPassword); // Check if the user exists
    }

    // Method to read all users from the file
    private List<String> readUsersFromFile() {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line); // Add each user to the list
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
        return users; // Return the list of users
    }
}*/