
public class Car extends Thread {
    private int id; // Unique identifier for the car
    private int parkingDuration; // Duration the car stays parked (in milliseconds)
    private Gate gate; // The gate the car uses to enter the parking lot
    private ParkingSpot spotManager; // Reference to the Spot class for managing parking spots
    private int arriveAT ;
    // Constructor
    public Car(int id,int arriveAT, int parkingDuration, Gate gate , ParkingSpot spotManager) {
        this.id = id;
        this.arriveAT = arriveAT ;
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

    // get id
    public int getID()
    {
        return id ;
    }
    //get gateId
    public int getGate()
    {
        return gate.getId() ;
    }
    // get arriveAt
    public int getArriveAT()
    {
        return arriveAT;
    }
    public int getParkDuration() {
        return parkingDuration;
    }
    // Method to park the car
    private void park() throws InterruptedException {
        gate.letCarIn(this);
        System.out.println("Car " + id + " from Gate " + gate.getId() + " is waiting to park.");
        spotManager.park(); // Block until a spot is available
        System.out.println("Car " + id + " from Gate " + gate.getId() + " parked " +
                "(Parking Status: " + (4 - spotManager.availableSpots()) + " spots occupied)");
    }

    // Method to release the parking spot
    private void exit() {
        gate.letCarOut(this);
        spotManager.leave(); // Release the spot
    }
}
