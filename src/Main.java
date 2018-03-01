import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Main(args[0], args[1]);
    }

    public Main(String inputFileName, String outputFileName) {

    }

    public void loadFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Get the information about grid/vehicles/bonus/steps
            String[] information = reader.readLine().split(" ");

            int rows = Integer.parseInt(information[0]);
            int columns = Integer.parseInt(information[1]);
            int vehicles = Integer.parseInt(information[2]);
            int rides = Integer.parseInt(information[3]);
            int bonus = Integer.parseInt(information[4]);
            int steps = Integer.parseInt(information[5]);

            // Read in the rides

        } catch (IOException ex) {

        }
    }
}