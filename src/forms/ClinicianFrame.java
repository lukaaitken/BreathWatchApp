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
        setTitle("BreathWatch - Clinician");
        setSize(900, 700);
        setVisible(true); // Make sure the frame is visible before fetching data
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

        // Use SwingWorker to fetch data asynchronously
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (ResultSet rs = dbManager.fetchAllPatientData()) {
                    while (rs.next()) {
                        // Get the data from the ResultSet
                        String patientId = rs.getString("patient_id");
                        String symptoms = rs.getString("symptoms");
                        double breathingRate = rs.getDouble("breathing_rate");
                        double heartRate = rs.getDouble("heart_rate");
                        String timestamp = rs.getString("timestamp");

                        // Update the JTextArea with the patient data
                        publish("Patient ID: " + patientId);
                        publish("Symptoms: " + symptoms);
                        publish("Breathing Rate: " + breathingRate);
                        publish("Heart Rate: " + heartRate);
                        publish("Timestamp: " + timestamp);
                        publish("------------------------------");

                        // Example check for abnormal values
                        if (breathingRate < 10 || breathingRate > 30) {
                            showAlert(patientId, "Abnormal Breathing Rate: " + breathingRate);
                        }
                        if (heartRate < 50 || heartRate > 120) {
                            showAlert(patientId, "Abnormal Heart Rate: " + heartRate);
                        }
                    }
                } catch (SQLException e) {
                    publish("Error fetching patient data: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String text : chunks) {
                    patientDataArea.append(text + "\n"); // Update the text area with the data
                }
            }

            @Override
            protected void done() {
                // Called when the worker is done
                // You can show a final message or additional logic here if needed
            }
        };

        // Start the background task
        worker.execute();
    }

    private void showAlert(String patientId, String message) {
        // Display alert message to the clinician
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                "Patient ID: " + patientId + "\n" + message, "Alert", JOptionPane.WARNING_MESSAGE));
    }

    @Override
    public void display() {
        setVisible(true); // Display the frame
    }
}