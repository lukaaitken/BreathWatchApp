import java.util.Iterator;
import java.util.ArrayList;

public class BreathWatchApp {
    public static void main(String[] args) {
        DataConnection connection = DataConnection.getInstance();
        connection.connect();

        RespiratoryData data = new RespiratoryData();
        data.addBreathingRate(18.5);
        data.addBreathingRate(20.1);
        data.addBreathingRate(19.4);

        System.out.println("Breathing Rates:");
        Iterator<Double> iterator = data.iterator();
        while (iterator.hasNext()) {
            System.out.println("Breathing Rate: " + iterator.next());
        }

        MonitoringView patientView = new PatientView();
        patientView.display();

        MonitoringView clinicianView = new ClinicianView();
        clinicianView.display();
    }
}

class ClinicianView implements MonitoringView {
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
        System.out.println("Displaying data for Clinician.");
    }
}

class PatientView implements MonitoringView {
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
        System.out.println("Displaying data for Patient.");
    }
}

interface MonitoringView {
    void display();
    void gatherData();
    void formatData();
}


