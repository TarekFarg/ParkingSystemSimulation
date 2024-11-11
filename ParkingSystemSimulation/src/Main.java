import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Create a parking lot with 4 parking spots
        ParkingSpot parkingSpot = new ParkingSpot(4);
        String currentDirectory = System.getProperty("user.dir");

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(currentDirectory, "ParkingSystemSimulation/src/cars.txt").toString()))) {
            String line;
            long lastArrivalTime = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String gate = parts[0];
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int stayDuration = Integer.parseInt(parts[3].split(" ")[1]);

                // Ensure the delay is non-negative
                long delay = (arrivalTime - lastArrivalTime) * 1000;
                if (delay < 0) {
                    delay = 0;
                }

                // Simulate the delay based on the arrival time
                Thread.sleep(delay);
                lastArrivalTime = arrivalTime;

                // Start the car thread
                Car car = new Car(carId, gate, stayDuration, parkingSpot);
                car.start();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
