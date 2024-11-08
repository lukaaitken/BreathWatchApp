package forms;

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


