import java.util.ArrayList;
import java.util.Iterator;

public class RespiratoryData {
    private final ArrayList<Double> breathingRates;

    public RespiratoryData() {
        breathingRates = new ArrayList<>();
    }

    public void addBreathingRate(double rate) {
        breathingRates.add(rate);
    }

    public Iterator<Double> iterator() {
        return breathingRates.iterator();
    }
}