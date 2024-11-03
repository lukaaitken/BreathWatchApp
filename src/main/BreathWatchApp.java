package main;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
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

// Patient Frame (Template Pattern)
class PatientFrame extends HealthFrame {
    private RespiratoryData data;

    public PatientFrame(RespiratoryData data) {
        this.data = data; // Initialize respiratory data
    }

    @Override
    protected void drawBackground(Graphics graphics) {
        ImageIcon img = new ImageIcon("src\\images\\PatientImage.png");
        if (img.getImageLoadStatus() != MediaTracker.COMPLETE) {
            graphics.setColor(Color.RED);
            graphics.drawString("Error loading patient image!", 50, 50);
            return;
        }
        Image i = img.getImage();
        graphics.drawImage(i, 0, 0, this); // Draw background image
        graphics.setColor(Color.CYAN); // Set text colour
        graphics.drawString("Welcome Patient!", 50, 50); // Display welcome message
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