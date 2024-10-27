import java.util.ArrayList;
import java.util.Iterator;

// RespiratoryData class
class RespiratoryData implements Iterable<Double> {
    private final ArrayList<Double> breathingRates;

    public RespiratoryData() {
        breathingRates = new ArrayList<>();
    }

    public void addBreathingRate(double rate) {
        breathingRates.add(rate);
    }

    public void clearBreathingRates() {
        breathingRates.clear();
    }

    @Override
    public Iterator<Double> iterator() {
        return breathingRates.iterator();
    }
}