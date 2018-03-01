public class Vehicle {
    public int currentX;
    public int currentY;

    public List<Ride> rides;

    public Vehicle() {
        this.rides = new ArrayList<Ride>();
    }

    public void addRide(Ride ride) {
        this.rides.add(ride);
    }
}