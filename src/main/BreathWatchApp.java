package main;

import javax.swing.*;
import forms.LoginForm;
import forms.PatientFrame;
import forms.ClinicianFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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