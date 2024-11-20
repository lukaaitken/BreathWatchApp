package forms;

import main.HealthFrame;
import main.VitalSignsData;
import main.DatabaseManager;

import javax.swing.*;
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

    /*public PatientFrame(VitalSignsData vitalSignsData) {
        super(); // Call the parent constructor
        initComponents(); // Initialize GUI components

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
    }*/

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




/*package forms;

import main.HealthFrame;
import main.VitalSignsData; // Ensure you have this import
import main.DatabaseManager; // Make sure this import exists for DatabaseManager
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientFrame extends HealthFrame {

    private JTextField patientIdField; // Field for patient ID
    private JTextField symptomsField; // Field for symptoms
    private JTextField breathingRateField; // Field for breathing rate
    private JTextField heartRateField; // Field for heart rate
    private JButton submitButton; // Button for submitting data

    // Constructor that accepts VitalSignsData
    public PatientFrame(VitalSignsData vitalSignsData) {
        super(); // Call the parent constructor
        initComponents(); // Initialize GUI components

        // Logic for what happens when the submit button is pressed
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
            }
        });
    }

    // Initialize GUI components
    private void initComponents() {
        patientIdField = new JTextField();
        symptomsField = new JTextField();
        breathingRateField = new JTextField();
        heartRateField = new JTextField();
        submitButton = new JButton("Submit");

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
        add(submitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjust the frame size to fit the components
    }

    @Override
    public void display() {

    }
}
*/







/*package forms;

import main.HealthFrame;
import main.VitalSignsData;


import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class PatientFrame extends HealthFrame {
    private VitalSignsData data; // Change to VitalSignsData
    private JTextArea symptomInput; // Text area for symptom input
    private JButton submitButton; // Button to submit symptoms
    private JPanel dataPanel; // Panel for data and user input
    private forms.PatientFrame.BackgroundPanel backgroundPanel; // Panel for background image

    public PatientFrame(VitalSignsData data) {
        this.data = data; // Initialize vital signs data
        initializeUI(); // Set up UI components
    }

    private void initializeUI() {
        // Create the background panel
        backgroundPanel = new forms.PatientFrame.BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout()); // Use BorderLayout for better control

        // Create a panel for the data and user input
        dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS)); // Vertical stacking for data panel

        // Create and set up the symptom input area
        symptomInput = new JTextArea(5, 30); // 5 rows, 30 columns
        symptomInput.setLineWrap(true); // Allow line wrapping
        symptomInput.setWrapStyleWord(true); // Wrap on word boundaries

        // Wrap JTextArea in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(symptomInput);
        scrollPane.setPreferredSize(new Dimension(300, 100)); // Set preferred size for the scroll pane

        // Create the submit button
        submitButton = new JButton("Submit Symptoms");
        submitButton.setPreferredSize(new Dimension(150, 30)); // Set a preferred size for the button

        // Set up action listener for the button
        submitButton.addActionListener(e -> submitSymptoms());

        // Add components to the data panel
        displayData(dataPanel); // Add vital signs data to the panel
        dataPanel.add(Box.createVerticalGlue()); // Push components to the bottom
        dataPanel.add(new JLabel("Enter your symptoms:")); // Add a label for better UX
        dataPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between label and text area
        dataPanel.add(scrollPane); // Add the scroll pane containing the text area
        dataPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between text area and button
        dataPanel.add(submitButton);

        // Add both panels to the main frame
        backgroundPanel.add(dataPanel, BorderLayout.CENTER); // Add data panel to the center
        this.add(backgroundPanel); // Add background panel to the frame
        this.pack(); // Resize frame to fit components
        this.setVisible(true); // Make the frame visible
    }

    private void submitSymptoms() {
        String symptoms = symptomInput.getText();
        // Process the symptoms input (e.g., save to a file, send to server)
        System.out.println("Symptoms submitted: " + symptoms);
        symptomInput.setText(""); // Clear the input area
    }

    // Override the drawBackground method from HealthFrame
    @Override
    protected void drawBackground(Graphics graphics) {
        backgroundPanel.repaint(); // Request a repaint for the background panel
    }

    // Custom panel to draw the background image
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon("src/images/PatientImage.png").getImage();
            setLayout(new OverlayLayout(this)); // Use OverlayLayout to stack components
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw background image
        }
    }

    // Display vital signs data on the provided panel
    private void displayData(JPanel breathingRatePanel) {
        breathingRatePanel.removeAll(); // Clear previous entries
        if (data != null) {
            Iterator<Double> breathingIterator = data.iterator();
            while (breathingIterator.hasNext()) {
                breathingRatePanel.add(new JLabel("Breathing Rate: " + breathingIterator.next())); // Add labels for breathing rates
            }
            Iterator<Double> heartRateIterator = data.getHeartRates().iterator();
            while (heartRateIterator.hasNext()) {
                breathingRatePanel.add(new JLabel("Heart Rate: " + heartRateIterator.next())); // Add labels for heart rates
            }
        } else {
            breathingRatePanel.add(new JLabel("No vital signs available.")); // Inform user if no data is available
        }
        breathingRatePanel.revalidate(); // Refresh the panel to show new data
        breathingRatePanel.repaint(); // Repaint the panel
    }
}*/
