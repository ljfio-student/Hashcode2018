import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public List<Ride> rides;
    public List<Vehicle> vehicles;

    public int bonusScore;
    public int stepCount;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Input and ouput file/directory are requried as arguments");
            return;
        }

        File inputPath = new File(args[0]);
        File outputPath = new File(args[1]);

        // Check to make sure the directory or file exists
        if (!inputPath.exists()) {
            System.err.println("Input file/directory does not exist");
        }

        // Create an output directory if the input is a directory and the output directory doesn't exist
        if (!outputPath.exists() && inputPath.isDirectory()) {
            outputPath.mkdir();
        }

        // If we are in a directory iterate over each file and run the program
        if (inputPath.isDirectory()) {
            File[] files = inputPath.listFiles();

            for (File file : files) {
                new Main(file.getAbsolutePath(), outputPath.getAbsolutePath() + File.separatorChar + file.getName() + ".out");
            }
        } else {
            new Main(inputPath.getAbsolutePath(), outputPath.getAbsolutePath());
        }
    }

    public Main(String inputFileName, String outputFileName) {
        rides = new ArrayList<>();
        vehicles = new ArrayList<>();

        loadFile(inputFileName);

        System.out.printf("Rides: %d\n", rides.size());
        System.out.printf("Vehicles: %d\n", vehicles.size());

        int maximumTime = 0;
        boolean allocated = true;

        while (maximumTime < stepCount && allocated) {
            allocated = false;

            for (int v = 0; v < vehicles.size(); v++) {
                Vehicle vehicle = vehicles.get(v);

                Optional<Ride> foundRide = rides.stream()
                    .filter(r -> !r.allocated && vehicle.currentTime + r.distance(vehicle) + r.distance() <= r.latestFinish)
                    .sorted((a, b) -> a.distance(vehicle) - b.distance(vehicle))
                    .findFirst();

                if (foundRide.isPresent()) {
                    Ride ride = foundRide.get();

                    int startTime = Integer.max(ride.earliestStart, vehicle.currentTime + ride.distance(vehicle));

                    vehicle.addRide(ride, startTime);
                    allocated = true;

                    maximumTime = Integer.max(maximumTime, startTime + ride.distance());
                }
            }
        }

        // Iterate over list of vehicles
        String output = vehicles.stream()
            .map(v -> v.output())
            .collect(Collectors.joining("\n"));

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

            writer.write(output);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {

        }
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
                int earliestStart = Integer.parseInt(rideInformation[4]);
                int latestFinish = Integer.parseInt(rideInformation[5]);

                rides.add(new Ride(r, startX, startY, endX, endY, earliestStart, latestFinish));
            }

            reader.close();
        } catch (IOException ex) {

        }
    }
}