package forms;
//import main.UserDataManager; // Adjust this according to your package structure

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import main.BreathWatchApp.User;

public class LoginForm extends JDialog {
    private JPanel MainPanel;
    private JPanel LeftPanel;
    private JPanel RightPanel;
    private JLabel lblWelcome;
    private JLabel lblIcon;
    private JLabel lblTitle;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private User authenticatedUser;

    // New users map (username -> password)
    private Map<String, String> userDatabase;

    public LoginForm(JFrame parent) {
        super(parent);
        setContentPane(MainPanel);
        setTitle("BreathWatch");
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Load the icon image
        ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");
        setIconImage(icon.getImage());

        // Initialize user database
        userDatabase = new HashMap<>();
        userDatabase.put("patient", "123");
        userDatabase.put("clinician", "123");
        // Add additional patients
        userDatabase.put("patient2", "456");
        userDatabase.put("patient3", "789");

        // Action for the login button
        btnLogin.addActionListener(e -> authenticateUser());

        // Action for the cancel button
        btnCancel.addActionListener(e -> {
            authenticatedUser = null;
            dispose();
        });

        setVisible(true);
    }

    private void authenticateUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Authenticate using the userDatabase map
        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            String userType = username.equals("clinician") ? "clinician" : "patient";
            authenticatedUser = new User(userType, username);
            dispose(); // Close the dialog on successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }

    // Method to get the authenticated user
    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}