package forms;
//import main.UserDataManager; // Adjust this according to your package structure

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.BreathWatchApp.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
public class LoginForm extends JDialog {
    private JPanel MainPanel;      // Main panel
    private JPanel LeftPanel;      // Left panel
    private JPanel RightPanel;     // Right panel
    private JLabel lblWelcome;     // Welcome label
    private JLabel lblIcon;        // Icon label
    private JLabel lblTitle;       // Title label
    private JLabel lblUsername;    // Username label
    private JLabel lblPassword;    // Password label
    private JTextField txtUsername; // Username text field
    private JPasswordField txtPassword; // Password text field
    private JButton btnLogin;      // Login button
    private JButton btnCancel;     // Cancel button
    private User authenticatedUser; // Holds the authenticated user
    private UserDataManager userManager; // User data manager

    public LoginForm(JFrame parent) {
        super(parent);
        setContentPane(MainPanel); // Set the main panel
        setTitle("BreathWatch"); // Set title for the dialog
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Load the icon image
        ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");
        setIconImage(icon.getImage());

        userManager = new UserDataManager(); // Initialize user data manager

        // Action for the login button
        btnLogin.addActionListener(e -> authenticateUser());

        // Action for the cancel button
        btnCancel.addActionListener(e -> {
            authenticatedUser = null; // Ensure no user is authenticated
            dispose(); // Close the dialog
        });

        // Make dialog visible
        setVisible(true);
    }

    private void authenticateUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Hash the password for comparison
        String hashedPassword = hashPassword(password);

        // Check if the user exists in the data store
        if (userManager.userExists(username, hashedPassword)) {
            authenticatedUser = new User(username); // Store authenticated user
            dispose(); // Close the dialog on successful login
        } else {
            // If user doesn't exist, register the user
            userManager.addUser(username, hashedPassword); // Register the new user
            JOptionPane.showMessageDialog(this, "User registered successfully!", "Registration", JOptionPane.INFORMATION_MESSAGE);
            authenticatedUser = new User(username); // Store authenticated user
            dispose(); // Close the dialog on successful login
        }
    }

    // Method to hash the password
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString(); // Return the hashed password as a hex string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // Handle exception if algorithm is not found
        }
    }

    // Method to get the authenticated user
    public User getAuthenticatedUser() {
        return authenticatedUser; // Return the authenticated user
    }
}
*/



public class LoginForm extends JDialog {
    private JPanel MainPanel;      // Main panel
    private JPanel LeftPanel;      // Left panel
    private JPanel RightPanel;     // Right panel
    private JLabel lblWelcome;     // Welcome label
    private JLabel lblIcon;        // Icon label
    private JLabel lblTitle;       // Title label
    private JLabel lblUsername;    // Username label
    private JLabel lblPassword;    // Password label
    private JTextField txtUsername; // Username text field
    private JPasswordField txtPassword; // Password text field
    private JButton btnLogin;      // Login button
    private JButton btnCancel;     // Cancel button
    private User authenticatedUser; // Holds the authenticated user

    public LoginForm(JFrame parent) {
        super(parent);
        setContentPane(MainPanel); // Set the main panel
        setTitle("BreathWatch"); // Set title for the dialog
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Load the icon image
        ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");
        setIconImage(icon.getImage());

        // Action for the login button
        btnLogin.addActionListener(e -> authenticateUser());

        // Action for the cancel button
        btnCancel.addActionListener(e -> {
            authenticatedUser = null; // Ensure no user is authenticated
            dispose(); // Close the dialog
        });

        // Make dialog visible
        setVisible(true);
    }

    /*private void authenticateUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Simple authentication logic
        if ("patient".equals(username) && "patient123".equals(password)) {
            authenticatedUser = new User("patient"); // Store authenticated user
            dispose(); // Close the dialog on successful login
        } else if ("clinician".equals(username) && "clinician123".equals(password)) {
            authenticatedUser = new User("clinician"); // Store authenticated user
            dispose(); // Close the dialog on successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }*/
    private void authenticateUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Simple authentication logic
        if ("patient".equals(username) && "patient123".equals(password)) {
            authenticatedUser = new User("patient", username); // Store authenticated user and username
            dispose(); // Close the dialog on successful login
        } else if ("clinician".equals(username) && "clinician123".equals(password)) {
            authenticatedUser = new User("clinician", username); // Store authenticated user and username
            dispose(); // Close the dialog on successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }


    // Method to get the authenticated user
    public User getAuthenticatedUser() {
        return authenticatedUser; // Return the authenticated user
    }
}



