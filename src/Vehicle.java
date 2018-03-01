import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    public int id;

    public int currentX;
    public int currentY;

    public int currentTime;

    public List<Ride> rides;

    public Vehicle(int id) {
        this.id = id;
        this.rides = new ArrayList<Ride>();
    }

    public void addRide(Ride ride, int startTime) {
        this.rides.add(ride);

        this.currentX = ride.end.getLeft();
        this.currentY = ride.end.getRight();

        this.currentTime = startTime + ride.distance();
    }
}