import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public List<Ride> rides;
    public List<Vehicle> vehicles;

    public int bonusScore;
    public int stepCount;

    public static void main(String[] args) {
        new Main(args[0], args[1]);
    }

    public Main(String inputFileName, String outputFileName) {
        rides = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    public void loadFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Get the information about grid/vehicles/bonus/steps
            String[] information = reader.readLine().split(" ");

            int rows = Integer.parseInt(information[0]); // lol this is pointless
            int columns = Integer.parseInt(information[1]);

            int vehicleCount = Integer.parseInt(information[2]);
            int rideCount = Integer.parseInt(information[3]);

            bonusScore = Integer.parseInt(information[4]);
            stepCount = Integer.parseInt(information[5]);

            // Create the vehciles
            for (int v = 0; v < vehicleCount; v++) {
                vehicles.add(new Vehicle(v));
            }

            // Read in the rides
            for (int r = 0; r < rideCount; r++) {
                String[] rideInformation = reader.readLine().split(" ");

                // Start / End location
                int startX = Integer.parseInt(rideInformation[0]);
                int startY = Integer.parseInt(rideInformation[1]);
                int endX = Integer.parseInt(rideInformation[2]);
                int endY = Integer.parseInt(rideInformation[3]);

                // Start / Finish times
                int earliestStart = Integer.parseInt(rideInformation[0]);
                int latestFinish = Integer.parseInt(rideInformation[0]);

                rides.add(new Ride(r, startX, startY, endX, endY, earliestStart, latestFinish));
            }

            reader.close();
        } catch (IOException ex) {

        }
    }
}