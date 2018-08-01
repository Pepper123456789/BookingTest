package cloud9;

public class Booking
{
    String origin;
    String destination;
    String seat;
    String flightClass;

    public Booking(String origin, String destination, String seat, String flightClass)
    {
        this.origin = origin;
        this.destination = destination;
        this.seat = seat;
        this.flightClass = flightClass;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getSeat() {
        return seat;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
}
