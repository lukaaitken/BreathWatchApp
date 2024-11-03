package main;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import forms.LoginForm;

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
        RespiratoryData data = new RespiratoryData();
        DataConnection connection = DataConnection.getInstance();
        connection.connect();
        simulateBreathingRates(data);

        HealthFrame healthFrame;

        // Determine which frame to display based on user role
        if ("patient".equals(role)) {
            healthFrame = new PatientFrame(data);
        } else if ("clinician".equals(role)) {
            healthFrame = new ClinicianFrame(data);
        } else {
            System.out.println("Invalid role. Exiting.");
            return;
        }

        healthFrame.display(); // Display the selected frame
        frame.dispose(); // Dispose of the initial frame
    }

    private static void simulateBreathingRates(RespiratoryData data) {
        for (int i = 0; i < 10; i++) {
            double rate = 15 + Math.random() * 10;
            data.addBreathingRate(rate); // Add generated rate to the data
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

// Abstract HealthFrame class for the Template Pattern
abstract class HealthFrame extends JFrame {
    public HealthFrame() {
        setTitle("BreathWatch");
        ImageIcon icon = new ImageIcon("src\\images\\BreathWatchLogo2.png");
        setIconImage(icon.getImage());

        setPreferredSize(new Dimension(900, 800)); // Set preferred size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
    }

    // Template method to display the frame
    public final void display() {
        pack(); // Adjusts the frame size based on preferred size
        this.setVisible(true); // Make frame visible
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics); // Call superclass's paint method
        drawBackground(graphics); // Draw specific background
    }

    // Abstract method for drawing background, to be implemented by subclasses
    protected abstract void drawBackground(Graphics graphics);
}

// Clinician Frame (Template Pattern)
class ClinicianFrame extends HealthFrame {
    private RespiratoryData data;

    public ClinicianFrame(RespiratoryData data) {
        this.data = data; // Initialize respiratory data
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
        displayData(graphics); // Display breathing data
    }

    // Display breathing rates on the frame
    private void displayData(Graphics graphics) {
        int y = 100; // Start Y position for text
        Iterator<Double> iterator = data.iterator();
        while (iterator.hasNext()) {
            graphics.drawString("Breathing Rate: " + iterator.next(), 50, y); // Display each rate
            y += 20; // Move down for the next line
        }
    }
}


class PatientFrame extends HealthFrame {
    private RespiratoryData data;
    private JTextArea symptomInput; // Text area for symptom input
    private JButton submitButton; // Button to submit symptoms
    private JPanel dataPanel; // Panel for data and user input
    private BackgroundPanel backgroundPanel; // Panel for background image

    public PatientFrame(RespiratoryData data) {
        this.data = data; // Initialize respiratory data
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
        displayData(dataPanel); // Add breathing data to the panel
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
        // This method might not be needed anymore since the background is handled in BackgroundPanel
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

    // Display breathing rates on the provided panel
    private void displayData(JPanel breathingRatePanel) {
        breathingRatePanel.removeAll(); // Clear previous entries
        if (data != null && data.iterator().hasNext()) { // Check if data is not null and has elements
            Iterator<Double> iterator = data.iterator();
            while (iterator.hasNext()) {
                breathingRatePanel.add(new JLabel("Breathing Rate: " + iterator.next())); // Add labels to panel
            }
        } else {
            breathingRatePanel.add(new JLabel("No breathing rates available.")); // Inform user if no data is available
        }
        breathingRatePanel.revalidate(); // Refresh the panel to show new data
        breathingRatePanel.repaint(); // Repaint the panel
    }
}
