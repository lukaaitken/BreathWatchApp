package main;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:sample.db"; // Path to your SQLite database

    // Method to create a database connection
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL); // Connect to the SQLite database
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Method to create the PatientData table (if not already created)
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS PatientData (" +
                "patient_id TEXT PRIMARY KEY," +
                "symptoms TEXT," +
                "breathing_rate REAL," +
                "heart_rate REAL);";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql); // Execute the table creation query
            System.out.println("PatientData table created (if it didn't exist).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert patient data into the PatientData table
    public void insertPatientData(String patientId, String symptoms, double breathingRate, double heartRate) {
        String sql = "INSERT INTO PatientData (patient_id, symptoms, breathing_rate, heart_rate) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patientId);
            pstmt.setString(2, symptoms);
            pstmt.setDouble(3, breathingRate);
            pstmt.setDouble(4, heartRate);

            pstmt.executeUpdate(); // Execute the insert statement
            System.out.println("Patient data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearDatabase() {
        String sql = "DELETE FROM PatientData"; // SQL query to delete all rows

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql); // Execute the SQL statement to delete all data
            System.out.println("All patient data has been deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}




