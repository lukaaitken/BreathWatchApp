package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // Import for PreparedStatement
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;

public class DatabaseManager {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:your_database_file.db"; // Replace with your actual database file path
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Method to insert patient data into the database
    public void insertPatientData(String patientID, String symptoms, double breathingRate, double heartRate) {
        String sql = "INSERT INTO Patients(patientID, symptoms, breathingRate, heartRate) VALUES(?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Using PreparedStatement
            pstmt.setString(1, patientID);
            pstmt.setString(2, symptoms);
            pstmt.setDouble(3, breathingRate);
            pstmt.setDouble(4, heartRate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to fetch patient data and display it in a JTextArea
    public void fetchPatientData(JTextArea textArea) {
        String sql = "SELECT * FROM Patients"; // Adjust the SQL query as needed
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            StringBuilder data = new StringBuilder(); // Use StringBuilder for efficient string concatenation
            while (rs.next()) {
                data.append("Patient ID: ").append(rs.getString("patientID"))
                        .append(", Symptoms: ").append(rs.getString("symptoms"))
                        .append(", Breathing Rate: ").append(rs.getDouble("breathingRate"))
                        .append(", Heart Rate: ").append(rs.getDouble("heartRate"))
                        .append(", Timestamp: ").append(rs.getString("timestamp"))
                        .append("\n");
            }
            textArea.setText(data.toString()); // Set the fetched data to the JTextArea
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}




