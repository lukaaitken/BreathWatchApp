package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.BreathWatchApp.User;

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

    private void authenticateUser() {
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
    }

    // Method to get the authenticated user
    public User getAuthenticatedUser() {
        return authenticatedUser; // Return the authenticated user
    }
}

