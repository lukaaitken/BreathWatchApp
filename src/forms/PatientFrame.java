package forms;

import main.HealthFrame;
import main.VitalSignsData;
import main.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientFrame extends HealthFrame {

    private JTextField patientIdField; // Field for patient ID
    private JTextField symptomsField; // Field for symptoms
    private JTextField breathingRateField; // Field for breathing rate
    private JTextField heartRateField; // Field for heart rate
    private JButton submitButton; // Button for submitting data
    private JButton generateButton; // Button to generate random values

    public PatientFrame(VitalSignsData vitalSignsData, String username) {
        super(); // Call the parent constructor
        initComponents(); // Initialize GUI components

        // Set the patient ID field to the username
        patientIdField.setText(username); // Set the username as the patient ID
        patientIdField.setEditable(false); // Make it non-editable

        // Logic for submitting data
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientId = patientIdField.getText();
                String symptoms = symptomsField.getText();
                double breathingRate = Double.parseDouble(breathingRateField.getText());
                double heartRate = Double.parseDouble(heartRateField.getText());

                // Create DatabaseManager instance and insert data
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.insertPatientData(patientId, symptoms, breathingRate, heartRate);

                // Example check for abnormal values
                StringBuilder alertMessage = new StringBuilder();

                if (breathingRate < 10 || breathingRate > 30) {
                    alertMessage.append("Abnormal Breathing Rate: " + breathingRate + "\n");
                }
                if (heartRate < 50 || heartRate > 120) {
                    alertMessage.append("Abnormal Heart Rate: " + heartRate + "\n");
                }

                // If there are any abnormal conditions, show an alert
                if (alertMessage.length() > 0) {
                    showAlert(alertMessage.toString());
                }
            }
        });

        // Logic for generating random values
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double randomBreathingRate = 15 + Math.random() * 10;
                double randomHeartRate = 60 + Math.random() * 40;

                breathingRateField.setText(String.valueOf(randomBreathingRate));
                heartRateField.setText(String.valueOf(randomHeartRate));
            }
        });
    }
    private void showAlert(String message) {
        // Create a new dialog for the alert
        JDialog alertDialog = new JDialog(this, "Alert", true); // 'true' makes it modal
        alertDialog.setLayout(new BorderLayout());

        // Create a JTextArea to display the alert message (allows multiple lines)
        JTextArea alertTextArea = new JTextArea();
        alertTextArea.setText(message);
        alertTextArea.setForeground(Color.RED); // Red text color
        alertTextArea.setFont(new Font("Arial", Font.BOLD, 14)); // Font and size
        alertTextArea.setBackground(Color.YELLOW); // Background color for the alert
        alertTextArea.setEditable(false); // Make it non-editable
        alertTextArea.setWrapStyleWord(true); // Ensure words are wrapped properly
        alertTextArea.setLineWrap(true); // Enable line wrapping

        // Make sure the JTextArea doesn't resize unnecessarily
        alertTextArea.setPreferredSize(new Dimension(280, 100));

        // Add the JTextArea to a scroll pane in case the message is too long
        JScrollPane scrollPane = new JScrollPane(alertTextArea);

        // Create a close button for the dialog
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> alertDialog.dispose()); // Close the dialog when clicked

        // Add components to the dialog
        alertDialog.add(scrollPane, BorderLayout.CENTER); // Add the scrollable message
        alertDialog.add(closeButton, BorderLayout.SOUTH); // Add the close button at the bottom

        // Set the size and position of the dialog
        alertDialog.setSize(300, 150);
        alertDialog.setLocationRelativeTo(this); // Center it relative to the main frame

        // Make the dialog visible
        alertDialog.setVisible(true);
    }

    // Initialize GUI components
    private void initComponents() {
        patientIdField = new JTextField();
        symptomsField = new JTextField();
        breathingRateField = new JTextField();
        heartRateField = new JTextField();
        submitButton = new JButton("Submit");
        generateButton = new JButton("Generate Random Values");

        // Set layout and add components to the frame
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Patient ID:"));
        add(patientIdField);
        add(new JLabel("Symptoms:"));
        add(symptomsField);
        add(new JLabel("Breathing Rate:"));
        add(breathingRateField);
        add(new JLabel("Heart Rate:"));
        add(heartRateField);
        add(generateButton);  // Add the generate button
        add(submitButton);  // Add the submit button

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjust the frame size to fit the components
    }

    @Override
    public void display() {
        setVisible(true); // Display the frame
    }
}