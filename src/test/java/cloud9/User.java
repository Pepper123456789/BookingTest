package cloud9;

import java.util.ArrayList;

public class User
{
    String fName;
    String lName;
    String email;
    String password;
    ArrayList<Booking> bookings = new ArrayList<>();

    public User(String fName, String lName, String email, String password)
    {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public void addBooking(String origin, String destination, String seat, String flightClass)
    {
        bookings.add(new Booking(origin, destination, seat, flightClass));
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}
