import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// Main application class
public class BreathWatchApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter role (patient/clinician): ");
        String role = scanner.nextLine().trim().toLowerCase();

        RespiratoryData data = new RespiratoryData();
        DataConnection connection = DataConnection.getInstance();
        connection.connect();
        simulateBreathingRates(data);

        User user = new User(role); // Create User object
        HealthFrame frame;

        // Determine which frame to display based on user role
        if (user.getUserType().equals("patient")) {
            frame = new PatientFrame(data);
        } else if (user.getUserType().equals("clinician")) {
            frame = new ClinicianFrame(data);
        } else {
            System.out.println("Invalid role. Exiting.");
            return;
        }

        frame.display(); // Display the selected frame
    }

    // Simulate breathing rates for the data
    private static void simulateBreathingRates(RespiratoryData data) {
        for (int i = 0; i < 10; i++) {
            double rate = 15 + Math.random() * 10;
            data.addBreathingRate(rate); // Add generated rate to the data
        }
    }
}

// User class to represent user type
class User {
    private String userType; // "Clinician" or "Patient"

    public User(String userType) {
        this.userType = userType; // Initialize user type
    }

    public String getUserType() {
        return userType; // Return user type
    }
}

// Abstract HealthFrame class for the Template Pattern
abstract class HealthFrame extends JFrame {
    public HealthFrame() {
        setTitle("Health Monitoring System"); // Set title for the frame
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

// Clinician Frame
class ClinicianFrame extends HealthFrame {
    private RespiratoryData data;

    public ClinicianFrame(RespiratoryData data) {
        this.data = data; // Initialize respiratory data
    }

    @Override
    protected void drawBackground(Graphics graphics) {
        ImageIcon img = new ImageIcon("DoctorImage.png");
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

// Patient Frame
class PatientFrame extends HealthFrame {
    private RespiratoryData data;

    public PatientFrame(RespiratoryData data) {
        this.data = data; // Initialize respiratory data
    }

    @Override
    protected void drawBackground(Graphics graphics) {
        ImageIcon img = new ImageIcon("PatientImage.png");
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