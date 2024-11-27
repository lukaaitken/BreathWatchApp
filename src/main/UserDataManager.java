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