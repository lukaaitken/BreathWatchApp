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
                // Get the data from the ResultSet and display it in the JTextArea
                String patientId = rs.getString("patient_id");
                String symptoms = rs.getString("symptoms");
                double breathingRate = rs.getDouble("breathing_rate");
                double heartRate = rs.getDouble("heart_rate");
                String timestamp = rs.getString("timestamp");

                // Format the data nicely in the JTextArea
                patientDataArea.append("Patient ID: " + patientId + "\n");
                patientDataArea.append("Symptoms: " + symptoms + "\n");
                patientDataArea.append("Breathing Rate: " + breathingRate + "\n");
                patientDataArea.append("Heart Rate: " + heartRate + "\n");
                patientDataArea.append("Timestamp: " + timestamp + "\n");
                patientDataArea.append("------------------------------\n");
            }
        } catch (SQLException e) {
            patientDataArea.setText("Error fetching patient data: " + e.getMessage());
        }
    }

    @Override
    public void display() {
        setVisible(true); // Display the frame
    }
}


// this code works with database
/*package forms;

import main.HealthFrame; // Ensure this import is present
import main.VitalSignsData; // Ensure you have this import
import main.UserDataManager; // Import UserDataManager for data fetching
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

        // Manually create a connection
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            String query = "SELECT * FROM PatientData"; // SQL query to get all patient data
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    // Get the data from the ResultSet and display it in the JTextArea
                    String patientId = rs.getString("patient_id");
                    String symptoms = rs.getString("symptoms");
                    double breathingRate = rs.getDouble("breathing_rate");
                    double heartRate = rs.getDouble("heart_rate");

                    // Format the data nicely in the JTextArea
                    patientDataArea.append("Patient ID: " + patientId + "\n");
                    patientDataArea.append("Symptoms: " + symptoms + "\n");
                    patientDataArea.append("Breathing Rate: " + breathingRate + "\n");
                    patientDataArea.append("Heart Rate: " + heartRate + "\n");
                    patientDataArea.append("------------------------------\n");
                }
            }
        } catch (SQLException e) {
            patientDataArea.setText("Error fetching patient data: " + e.getMessage());
        }
    }

    @Override
    public void display() {
        setVisible(true); // Display the frame
    }
}
*/


/*package forms;

import main.HealthFrame; // Ensure this import is present
import main.VitalSignsData; // Ensure you have this import
import javax.swing.*;
import java.awt.*;

public class ClinicianFrame extends HealthFrame {

    private JTextArea patientDataArea; // Area to display patient data

    // Constructor that accepts VitalSignsData
    public ClinicianFrame(VitalSignsData vitalSignsData) {
        super(); // Call the parent constructor
        initComponents(); // Initialize GUI components
       // ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");
       // setIconImage(icon.getImage());
        setSize(900, 700);
        // Logic for fetching and displaying patient data
        fetchPatientData(vitalSignsData);
    }

    // Initialize GUI components
    private void initComponents() {
        patientDataArea = new JTextArea();
        patientDataArea.setEditable(false); // Make the area read-only

        // Set layout and add components to the frame
        setLayout(new BorderLayout());
        add(new JScrollPane(patientDataArea), BorderLayout.CENTER); // Add scrollable area

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjust the frame size to fit the components
    }

    // Fetch patient data (this is just a placeholder method; implement as needed)
    private void fetchPatientData(VitalSignsData vitalSignsData) {
        // Example implementation to display patient data
        // Replace this with actual data fetching logic
        patientDataArea.setText("Displaying patient data...\n");

        // Simulate displaying data
        for (double rate : vitalSignsData.getBreathingRates()) {
            patientDataArea.append("Breathing Rate: " + rate + "\n");
        }
        for (double rate : vitalSignsData.getHeartRates()) {
            patientDataArea.append("Heart Rate: " + rate + "\n");
        }
    }

    @Override
    public void display() {
        setVisible(true); // Display the frame
    }
}
*/

