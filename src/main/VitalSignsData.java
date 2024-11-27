package main;

import java.util.ArrayList;
import java.util.List;

public class VitalSignsData {
    private List<Double> breathingRates = new ArrayList<>();
    private List<Double> heartRates = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    // Thresholds for alerting
    private static final double MIN_HEART_RATE = 40;
    private static final double MAX_HEART_RATE = 120;
    private static final double MIN_BREATHING_RATE = 12;
    private static final double MAX_BREATHING_RATE = 20;

    // Add observers (Patient, Clinician)
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove observers
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify observers with a message
    private void notifyObservers(String alertMessage) {
        for (Observer observer : observers) {
            observer.update(alertMessage);
        }
    }

    // Add Breathing Rate and check for alerts
    public void addBreathingRate(double rate) {
        breathingRates.add(rate);
        checkAlerts("Breathing Rate", rate);
    }

    // Add Heart Rate and check for alerts
    public void addHeartRate(double rate) {
        heartRates.add(rate);
        checkAlerts("Heart Rate", rate);
    }

    // Check for critical conditions and alert
    private void checkAlerts(String type, double value) {
        if (type.equals("Heart Rate")) {
            if (value < MIN_HEART_RATE || value > MAX_HEART_RATE) {
                notifyObservers("Critical Heart Rate: " + value);
            }
        } else if (type.equals("Breathing Rate")) {
            if (value < MIN_BREATHING_RATE || value > MAX_BREATHING_RATE) {
                notifyObservers("Critical Breathing Rate: " + value);
            }
        }
    }

    // Getters
    public List<Double> getBreathingRates() {
        return breathingRates;
    }

    public List<Double> getHeartRates() {
        return heartRates;
    }
}