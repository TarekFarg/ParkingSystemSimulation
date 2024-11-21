
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

    // Method to park the car
    private void park() {
        gate.letCarIn(this);
        boolean parked = spotManager.tryToPark(); // Attempt to acquire a spot
        if (parked) {
            System.out.println("Car " + id + " from Gate " + gate.getId() + " parked");
            System.out.println("(Parking Status: " + (4-spotManager.availableSpots()) + " spots occupied)");
        } else {
            System.out.println("Car " + id + " couldn't park as no spots are available.");
        }
    }

    // Method to release the parking spot
    private void exit() {
        gate.letCarOut(this);
        spotManager.leave(); // Release the spot
    }
}
