package main;

import javax.swing.*; // Make sure you import necessary Swing components
import java.awt.*; // For layout management

public abstract class HealthFrame extends JFrame {
    // Constructor
    public HealthFrame() {
        // Set up the frame properties
        setTitle("BreathWatch");
        ImageIcon icon = new ImageIcon("src/images/BreathWatchLogo2.png");
        setIconImage(icon.getImage());
        //setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null); // Center the frame on the screen

        // Initialize your GUI components here
        initializeComponents();
    }

    // Abstract method to display the frame content
    public abstract void display();

    // Method to initialize components (can be overridden)
    protected void initializeComponents() {
        // Example: Create a panel and add it to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
    }
}

