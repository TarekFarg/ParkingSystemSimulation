
public class Car extends Thread {
    private int id; // Unique identifier for the car
    private int parkingDuration; // Duration the car stays parked (in milliseconds)
    private Gate gate; // The gate the car uses to enter the parking lot
    private Spot spotManager; // Reference to the Spot class for managing parking spots

    // Constructor
    public Car(int id, int parkingDuration, Gate gate, Spot spotManager) {
        this.id = id;
        this.parkingDuration = parkingDuration;
        this.gate = gate;
        this.spotManager = spotManager;
    }

    // Main thread method
    @Override
    public void run() {
        try {
            park(); // Attempt to park the car
            Thread.sleep(parkingDuration); // Simulate the car staying parked
            exit(); // Release the parking spot after parking
        } catch (InterruptedException e) {
            System.out.println("Car " + id + " was interrupted.");
        }
    }

    // Method to park the car
    private void park() {
        System.out.println("Car " + id + " is trying to park through Gate " + gate.getGateId());
        boolean parked = spotManager.acquireSpot(); // Attempt to acquire a spot
        if (parked) {
            System.out.println("Car " + id + " has parked.");
        } else {
            System.out.println("Car " + id + " couldn't park as no spots are available.");
        }
    }

    // Method to release the parking spot
    private void exit() {
        System.out.println("Car " + id + " is leaving.");
        spotManager.releaseSpot(); // Release the spot
    }
}
