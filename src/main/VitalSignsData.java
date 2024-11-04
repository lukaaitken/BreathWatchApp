package main;

import java.util.ArrayList;
import java.util.List;

public class VitalSignsData {
    private List<Double> breathingRates;
    private List<Double> heartRates;

    // Constructor
    public VitalSignsData() {
        breathingRates = new ArrayList<>();
        heartRates = new ArrayList<>();
    }

    // Method to add a breathing rate
    public void addBreathingRate(double rate) {
        breathingRates.add(rate);
    }

    // Method to add a heart rate
    public void addHeartRate(double rate) {
        heartRates.add(rate);
    }

    // Method to get all breathing rates
    public List<Double> getBreathingRates() {
        return breathingRates;
    }

    // Method to get all heart rates
    public List<Double> getHeartRates() {
        return heartRates;
    }
}







/*
// (Iterator Pattern)
// Class to manage and iterate over a collection of vital signs (breathing rates and heart rates)
public class VitalSignsData implements Iterable<Double> {
    private final ArrayList<Double> breathingRates;
    private final ArrayList<Double> heartRates;

    // Constructor to initialize the lists
    public VitalSignsData() {
        breathingRates = new ArrayList<>();
        heartRates = new ArrayList<>();
    }

    // Method to add a new breathing rate
    public void addBreathingRate(double rate) {
        breathingRates.add(rate);
    }

    // Method to add a new heart rate
    public void addHeartRate(double rate) {
        heartRates.add(rate);
    }

    // Method to clear all recorded vital signs
    public void clearVitalSigns() {
        breathingRates.clear();
        heartRates.clear();
    }

    // Method to return an iterator for breathing rates
    @Override
    public Iterator<Double> iterator() {
        return breathingRates.iterator(); // You can change this to iterate over heartRates if needed
    }

    // Additional method to get heart rates as an iterable
    public Iterable<Double> getHeartRates() {
        return heartRates;
    }
}
*/