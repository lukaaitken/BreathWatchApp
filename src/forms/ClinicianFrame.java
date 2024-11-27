package forms;

import main.HealthFrame;
import main.VitalSignsData;
import main.UserDataManager;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ClinicianFrame extends HealthFrame {

    private JTextArea patientDataArea; // Area to display patient data
    private JButton refreshButton; // Button to refresh data

    // Constructor that accepts VitalSignsData
    public ClinicianFrame(VitalSignsData vitalSignsData) {
        super(); // Call the parent constructor
        initComponents(); // Initialize GUI components
        setSize(900, 700);
        fetchPatientData(); // Fetch and display patient data initially
    }

    // Initialize GUI components
    private void initComponents() {
        patientDataArea = new JTextArea();
        patientDataArea.setEditable(false); // Make the area read-only

        // Set up the refresh button
        refreshButton = new JButton("Refresh Data");
        refreshButton.addActionListener(e -> fetchPatientData()); // Refresh the data when clicked

        // Set layout and add components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(patientDataArea), BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH); // Add refresh button at the bottom

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER); // Add the panel with the data area and button

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjust the frame size to fit the components
    }

    // Fetch patient data from the database and display it
    private void fetchPatientData() {
        patientDataArea.setText("Fetching patient data...\n");

        // Fetch all data ordered by timestamp
        UserDataManager dbManager = new UserDataManager();
        try (ResultSet rs = dbManager.fetchAllPatientData()) {
            while (rs.next()) {
                // Get the data from the ResultSet
                String patientId = rs.getString("patient_id");
                String symptoms = rs.getString("symptoms");
                double breathingRate = rs.getDouble("breathing_rate");
                double heartRate = rs.getDouble("heart_rate");
                String timestamp = rs.getString("timestamp");

                // Display the patient data
                patientDataArea.append("Patient ID: " + patientId + "\n");
                patientDataArea.append("Symptoms: " + symptoms + "\n");
                patientDataArea.append("Breathing Rate: " + breathingRate + "\n");
                patientDataArea.append("Heart Rate: " + heartRate + "\n");
                patientDataArea.append("Timestamp: " + timestamp + "\n");
                patientDataArea.append("------------------------------\n");

                // Example check for abnormal values
                if (breathingRate < 10 || breathingRate > 30) {
                    showAlert(patientId, "Abnormal Breathing Rate: " + breathingRate);
                }
                if (heartRate < 50 || heartRate > 120) {
                    showAlert(patientId, "Abnormal Heart Rate: " + heartRate);
                }
            }
        } catch (SQLException e) {
            patientDataArea.setText("Error fetching patient data: " + e.getMessage());
        }
    }

    private void showAlert(String patientId, String message) {
        // Display alert message to the clinician
        JOptionPane.showMessageDialog(this, "Patient ID: " + patientId + "\n" + message, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void display() {
        setVisible(true); // Display the frame
    }
}