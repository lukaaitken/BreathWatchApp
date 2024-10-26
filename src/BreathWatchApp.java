import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;

public class BreathWatchApp {
    public static void main(String[] args) {
        // User input for credentials
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter role (patient/clinician): ");
        String role = scanner.nextLine().trim().toLowerCase();

        // Decision based on user role
        MonitoringView view;
        RespiratoryData data = new RespiratoryData(); // Moved data declaration here

        if (role.equals("patient")) {
            view = new PatientView();
            DataConnection connection = DataConnection.getInstance();
            connection.connect();

            // Simulate breathing rates
            simulateBreathingRates(data);
            ((PatientView) view).setRespiratoryData(data); // Pass data to PatientView
        } else if (role.equals("clinician")) {
            view = new ClinicianView();
            DataConnection connection = DataConnection.getInstance();
            connection.connect();

            // Simulate breathing rates
            simulateBreathingRates(data);
            ((ClinicianView) view).setRespiratoryData(data); // Pass data to ClinicianView
        } else {
            System.out.println("Invalid role. Exiting.");
            return;
        }

        view.display(); // Display the view after data is processed
    }

    private static void simulateBreathingRates(RespiratoryData data) {
        for (int i = 0; i < 10; i++) {
            double rate = 15 + Math.random() * 10;  // Generate random breathing rates between 15 and 25
            data.addBreathingRate(rate);
        }
    }
}

// Custom JPanel for rendering the background image
class BackgroundPanel extends JPanel {
    private final String imagePath;

    public BackgroundPanel(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); // Ensure proper rendering
        ImageIcon img = new ImageIcon(imagePath);
        Image i = img.getImage();
        graphics.drawImage(i, 0, 0, this);
    }
}

// PatientView class updated to handle RespiratoryData and background
class PatientView implements MonitoringView {
    private RespiratoryData respiratoryData;

    public void setRespiratoryData(RespiratoryData data) {
        this.respiratoryData = data;
    }

    @Override
    public void display() {
        gatherData();
        formatData();
        render();
    }

    @Override
    public void gatherData() {
        System.out.println("Gathering data for Patient.");
    }

    @Override
    public void formatData() {
        System.out.println("Formatting data for Patient view.");
    }

    private void render() {
        JFrame frame = new JFrame("Patient View");
        BackgroundPanel panel = new BackgroundPanel("PatientImage.png"); // Path to the image
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("Displaying data for Patient:");
        Iterator<Double> iterator = respiratoryData.iterator();
        while (iterator.hasNext()) {
            System.out.println("Breathing Rate: " + iterator.next());
        }
    }
}

// ClinicianView class updated to handle RespiratoryData and background
class ClinicianView implements MonitoringView {
    private RespiratoryData respiratoryData;

    public void setRespiratoryData(RespiratoryData data) {
        this.respiratoryData = data;
    }

    @Override
    public void display() {
        gatherData();
        formatData();
        render();
    }

    @Override
    public void gatherData() {
        System.out.println("Gathering data for Clinician.");
    }

    @Override
    public void formatData() {
        System.out.println("Formatting data for Clinician view.");
    }

    private void render() {
        JFrame frame = new JFrame("Clinician View");
        BackgroundPanel panel = new BackgroundPanel("DoctorImage.png"); // Path to the image
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("Displaying data for Clinician:");
        Iterator<Double> iterator = respiratoryData.iterator();
        while (iterator.hasNext()) {
            System.out.println("Breathing Rate: " + iterator.next());
        }
    }
}

interface MonitoringView {
    void display();
    void gatherData();
    void formatData();
}


