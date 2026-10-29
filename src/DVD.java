import java.util.Objects;

/**
 * class DVD
 * Defines the state and behavior of a DVD item offered by the library.
 * Inherits Item abstract class.
 */

public class DVD extends Item {
    private double duration;

    public DVD(String callNumber, String title, boolean available, double duration) {
        super(callNumber, title, available);
        setDuration(duration);
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    public double getDuration() {
        return duration;
    }

    /**
     * Setter of DVD duration with input validation.
     * @param duration of DVD
     */
    public void setDuration(double duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration of DVD must be a positive number.");
        }
        this.duration = duration;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDuration: " + duration + " minutes";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DVD dvd)) return false;
        return this.getCallNumber().equals(dvd.getCallNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCallNumber());
    }
}