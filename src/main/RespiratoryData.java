package main;

import java.util.ArrayList;
import java.util.Iterator;

// (Iterator Pattern)
// Class to manage and iterate over a collection of respiratory breathing rates
class RespiratoryData implements Iterable<Double> {
    private final ArrayList<Double> breathingRates;

    // Constructor to initialize the breathingRates list
    public RespiratoryData() {
        breathingRates = new ArrayList<>();
    }

    // Method to add a new breathing rate to the list
    public void addBreathingRate(double rate) {
        breathingRates.add(rate);
    }

    // Method to clear all recorded breathing rates
    public void clearBreathingRates() {
        breathingRates.clear();
    }

    // Method to return an iterator for the breathingRates list
    @Override
    public Iterator<Double> iterator() {
        return breathingRates.iterator();
    }
}