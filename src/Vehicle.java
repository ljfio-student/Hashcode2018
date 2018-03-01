public class Vehicle {
    public int id;

    public int currentX;
    public int currentY;

    public List<Ride> rides;

    public Vehicle(int id) {
        this.id = id;
        this.rides = new ArrayList<Ride>();
    }

    public void addRide(Ride ride) {
        this.rides.add(ride);
    }
}