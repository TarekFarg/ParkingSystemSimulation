import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParkingSpot parkingSpots = new ParkingSpot(4);

        Gate gate1 = new Gate(1);
        Gate gate2 = new Gate(2);
        Gate gate3 = new Gate(3);

        List<Gate> gates = new ArrayList<>();
        gates.add(gate1);
        gates.add(gate2);
        gates.add(gate3);

        String filePath = "src\\cars.txt";

        List<Car> carThreads = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Car car = parseCarDetails(line, parkingSpots, gates);
                if (car != null) {
                    carThreads.add(car);
                    car.start();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Wait for all car threads to finish
        for (Car car : carThreads) {
            try {
                car.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("------------------------");
        int totalCarsServed = gate1.getServed() + gate2.getServed() + gate3.getServed();
        System.out.println("Total cars served: " + totalCarsServed);
        System.out.println("Current Cars in Parking: " + (4 - parkingSpots.availableSpots()));
        System.out.println("Details:");
        for(int i = 0; i < gates.size(); i++){
            System.out.println("    Gate " + (i+1) + " served " + gates.get(i).getServed() + " cars." );
        }
    }
    private static Car parseCarDetails(String line, ParkingSpot parkingSpots, List<Gate> gates) {
        try {
            String[] parts = line.split(", ");
            int gateId = Integer.parseInt(parts[0].split(" ")[1]);
            int carNumber = Integer.parseInt(parts[1].split(" ")[1]);
            int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
            int duration = Integer.parseInt(parts[3].split(" ")[1]);

            // Validate gateId
            if (gateId < 1 || gateId > gates.size()) {
                throw new IllegalArgumentException("Invalid gate ID: " + gateId);
            }

            Gate gate = gates.get(gateId - 1);
            gate.incrementServed();

            return new Car(carNumber, arrivalTime, duration, gate, parkingSpots);
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line + " - " + e.getMessage());
            return null;
        }
    }
}
