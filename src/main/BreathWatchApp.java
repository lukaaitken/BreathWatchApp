package main;

import javax.swing.*;
import forms.LoginForm;
import forms.PatientFrame;
import forms.ClinicianFrame;

public class BreathWatchApp {
    public static void main(String[] args) {
        // Set up the application icon in the taskbar
        ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");

        // Show login form
        LoginForm loginForm = new LoginForm(null); // Use 'null' for the parent frame if not required
        User authenticatedUser = loginForm.getAuthenticatedUser();

        // Exit if authentication fails
        if (authenticatedUser == null) {
            System.out.println("Authentication failed. Exiting application.");
            System.exit(0);
        }

        String role = authenticatedUser.getUserType(); // Get the user type
        VitalSignsData data = new VitalSignsData(); // Use VitalSignsData
        DataConnection connection = DataConnection.getInstance();
        connection.connect();
        simulateVitalSigns(data); // Simulate both breathing rates and heart rates

        HealthFrame healthFrame;

        // Determine which frame to display based on user role
        if ("patient".equals(role)) {
            healthFrame = new PatientFrame(data, authenticatedUser.getUsername()); // Pass username to PatientFrame
        } else if ("clinician".equals(role)) {
            healthFrame = new ClinicianFrame(data); // Pass data to ClinicianFrame
        } else {
            System.out.println("Invalid role. Exiting.");
            return;
        }

        healthFrame.display(); // Display the selected frame
    }

    private static void simulateVitalSigns(VitalSignsData data) {
        for (int i = 0; i < 10; i++) {
            double breathingRate = 15 + Math.random() * 10; // Simulate a breathing rate
            data.addBreathingRate(breathingRate); // Add to VitalSignsData

            double heartRate = 60 + Math.random() * 40; // Simulate a heart rate
            data.addHeartRate(heartRate); // Add to VitalSignsData
        }
    }

    public static class User {
        private String userType;
        private String username; // Add username field

        public User(String userType, String username) {
            this.userType = userType;
            this.username = username; // Set the username
        }

        public String getUserType() {
            return userType;
        }

        public String getUsername() {
            return username; // Return the username
        }
    }
}


/*package main;

import javax.swing.*;
import forms.LoginForm;
import forms.PatientFrame;
import forms.ClinicianFrame;

public class BreathWatchApp {
    public static void main(String[] args) {
        // Set up the application icon in the taskbar
        ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");

        // Show login form
        LoginForm loginForm = new LoginForm(null); // Use 'null' for the parent frame if not required
        User authenticatedUser = loginForm.getAuthenticatedUser();

        // Exit if authentication fails
        if (authenticatedUser == null) {
            System.out.println("Authentication failed. Exiting application.");
            System.exit(0);
        }

        String role = authenticatedUser.getUserType(); // Get the user type
        VitalSignsData data = new VitalSignsData(); // Use VitalSignsData
        DataConnection connection = DataConnection.getInstance();
        connection.connect();
        simulateVitalSigns(data); // Simulate both breathing rates and heart rates

        HealthFrame healthFrame;

        // Determine which frame to display based on user role
        if ("patient".equals(role)) {
            healthFrame = new PatientFrame(data, authenticatedUser.getUsername()); // Pass username to PatientFrame
        } else if ("clinician".equals(role)) {
            healthFrame = new ClinicianFrame(data); // Pass data to ClinicianFrame
        }else {
            System.out.println("Invalid role. Exiting.");
            return;
        }
        /*if ("patient".equals(role)) {
            healthFrame = new PatientFrame(data); // Pass data to PatientFrame
        } else if ("clinician".equals(role)) {
            healthFrame = new ClinicianFrame(data); // Pass data to ClinicianFrame
        } else {
            System.out.println("Invalid role. Exiting.");
            return;
        }

        healthFrame.display(); // Display the selected frame
    }

    private static void simulateVitalSigns(VitalSignsData data) {
        for (int i = 0; i < 10; i++) {
            double breathingRate = 15 + Math.random() * 10; // Simulate a breathing rate
            data.addBreathingRate(breathingRate); // Add to VitalSignsData

            double heartRate = 60 + Math.random() * 40; // Simulate a heart rate
            data.addHeartRate(heartRate); // Add to VitalSignsData
        }
    }
    public static class User {
        private String userType;
        private String username; // Add username field

        public User(String userType, String username) {
            this.userType = userType;
            this.username = username; // Set the username
        }

        public String getUserType() {
            return userType;
        }

        public String getUsername() {
            return username; // Return the username
        }
    }*/

    /*public static class User {
        private String userType;

        public User(String userType) {
            this.userType = userType;
        }

        public String getUserType() {
            return userType;
        }
    }*/


/*package main;

import javax.swing.*;
import forms.LoginForm;
import forms.PatientFrame;
import forms.ClinicianFrame;

public class BreathWatchApp {
    public static void main(String[] args) {
        // Set up the application icon in the taskbar
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");
        frame.setIconImage(icon.getImage());

        // Show login form
        LoginForm loginForm = new LoginForm(frame);
        User authenticatedUser = loginForm.getAuthenticatedUser();

        // Exit if authentication fails
        if (authenticatedUser == null) {
            System.out.println("Authentication failed. Exiting application.");
            System.exit(0);
        }

        String role = authenticatedUser.getUserType(); // Get the user type
        VitalSignsData data = new VitalSignsData(); // Use VitalSignsData
        DataConnection connection = DataConnection.getInstance();
        connection.connect();
        simulateVitalSigns(data); // Simulate both breathing rates and heart rates

        HealthFrame healthFrame;

        // Determine which frame to display based on user role
        if ("patient".equals(role)) {
            healthFrame = new PatientFrame(data); // Pass data to PatientFrame
        } else if ("clinician".equals(role)) {
            healthFrame = new ClinicianFrame(data); // Pass data to ClinicianFrame
        } else {
            System.out.println("Invalid role. Exiting.");
            return;
        }

        healthFrame.display(); // Display the selected frame
        frame.dispose();
    }

    private static void simulateVitalSigns(VitalSignsData data) {
        for (int i = 0; i < 10; i++) {
            double breathingRate = 15 + Math.random() * 10; // Simulate a breathing rate
            data.addBreathingRate(breathingRate); // Add to VitalSignsData

            double heartRate = 60 + Math.random() * 40; // Simulate a heart rate
            data.addHeartRate(heartRate); // Add to VitalSignsData
        }
    }

    public static class User {
        private String userType;

        public User(String userType) {
            this.userType = userType;
        }

        public String getUserType() {
            return userType;
        }
    }
}
*/


/*
class ClinicianFrame extends HealthFrame {
    private VitalSignsData data;
    private JPanel vitalSignsPanel;
    private JPanel alertsPanel;
    private JTextArea symptomsLog;
    private JLabel connectionStatusLabel;

    public ClinicianFrame(VitalSignsData data) {
        this.data = data;
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());

        // Panel for real-time vital signs display
        vitalSignsPanel = new JPanel();
        vitalSignsPanel.setLayout(new BoxLayout(vitalSignsPanel, BoxLayout.Y_AXIS));
        displayVitalSigns(vitalSignsPanel);

        // Panel for alerts and warnings
        alertsPanel = new JPanel();
        alertsPanel.setLayout(new BoxLayout(alertsPanel, BoxLayout.Y_AXIS));
        displayAlerts();

        // Log for submitted symptoms
        symptomsLog = new JTextArea(10, 30); // Display up to 10 lines of logged symptoms
        symptomsLog.setEditable(false); // Make log read-only for the clinician
        JScrollPane scrollPane = new JScrollPane(symptomsLog);

        // Connection status label
        connectionStatusLabel = new JLabel("Connection: Active");

        // Add components to main layout
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(vitalSignsPanel, BorderLayout.CENTER);
        leftPanel.add(alertsPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(new JLabel("Symptoms Log"), BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(connectionStatusLabel, BorderLayout.SOUTH);

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
    }

    // Display the vital signs in real time
    private void displayVitalSigns(JPanel panel) {
        panel.removeAll();
        panel.add(new JLabel("Breathing Rate: " + data.getLatestBreathingRate()));
        panel.add(new JLabel("Heart Rate: " + data.getLatestHeartRate()));
        panel.revalidate();
        panel.repaint();
    }

    // Display alerts based on threshold breaches
    private void displayAlerts() {
        alertsPanel.removeAll();
        if (data.isBreathingRateCritical()) {
            JLabel alertLabel = new JLabel("Critical Breathing Rate Alert!");
            alertLabel.setForeground(Color.RED);
            alertsPanel.add(alertLabel);
        }
        if (data.isHeartRateCritical()) {
            JLabel alertLabel = new JLabel("Critical Heart Rate Alert!");
            alertLabel.setForeground(Color.RED);
            alertsPanel.add(alertLabel);
        }
        alertsPanel.revalidate();
        alertsPanel.repaint();
    }

    // Method to update symptoms log with new symptoms from the patient
    public void updateSymptomsLog(String symptom) {
        symptomsLog.append(symptom + "\n"); // Append each symptom entry with a new line
        symptomsLog.setCaretPosition(symptomsLog.getDocument().getLength()); // Auto-scroll to the latest entry
    }

    // Method to update the connection status
    public void updateConnectionStatus(boolean isConnected) {
        connectionStatusLabel.setText("Connection: " + (isConnected ? "Active" : "Inactive"));
        connectionStatusLabel.setForeground(isConnected ? Color.GREEN : Color.RED);
    }

    @Override
    protected void drawBackground(Graphics graphics) {
        // Custom background drawing if needed
    }
}
*/

/*
// Clinician Frame (Template Pattern)
class ClinicianFrame extends HealthFrame {
    private VitalSignsData data; // Change to VitalSignsData

    public ClinicianFrame(VitalSignsData data) {
        this.data = data; // Initialize vital signs data
    }

    @Override
    protected void drawBackground(Graphics graphics) {
        ImageIcon img = new ImageIcon("src\\images\\DoctorImage.png");
        if (img.getImageLoadStatus() != MediaTracker.COMPLETE) {
            graphics.setColor(Color.RED);
            graphics.drawString("Error loading clinician image!", 50, 50);
            return;
        }
        Image i = img.getImage();
        graphics.drawImage(i, 0, 0, this); // Draw background image
        graphics.setColor(Color.BLACK); // Set text color
        graphics.drawString("Welcome Clinician!", 50, 50); // Display welcome message
        displayData(graphics); // Display vital signs data
    }

    // Display vital signs on the frame
    private void displayData(Graphics graphics) {
        int y = 100; // Start Y position for text
        Iterator<Double> breathingIterator = data.iterator();
        while (breathingIterator.hasNext()) {
            graphics.drawString("Breathing Rate: " + breathingIterator.next(), 50, y); // Display each breathing rate
            y += 20; // Move down for the next line
        }
        Iterator<Double> heartRateIterator = data.getHeartRates().iterator(); // Get heart rates
        while (heartRateIterator.hasNext()) {
            graphics.drawString("Heart Rate: " + heartRateIterator.next(), 50, y); // Display each heart rate
            y += 20; // Move down for the next line
        }
    }
}
*/

/*
class PatientFrame extends HealthFrame {
    private VitalSignsData data; // Change to VitalSignsData
    private JTextArea symptomInput; // Text area for symptom input
    private JButton submitButton; // Button to submit symptoms
    private JPanel dataPanel; // Panel for data and user input
    private BackgroundPanel backgroundPanel; // Panel for background image

    public PatientFrame(VitalSignsData data) {
        this.data = data; // Initialize vital signs data
        initializeUI(); // Set up UI components
    }

    private void initializeUI() {
        // Create the background panel
        backgroundPanel = new BackgroundPanel();
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
}
*/
