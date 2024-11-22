
public class Car extends Thread {
    private int id;
    private int parkingDuration;
    private Gate gate;
    private ParkingSpot spotManager;
    private int arriveAT ;
    public Car(int id,int arriveAT, int parkingDuration, Gate gate , ParkingSpot spotManager) {
        this.id = id;
        this.arriveAT = arriveAT ;
        this.parkingDuration = parkingDuration;
        this.gate = gate;
        this.spotManager = spotManager;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(arriveAT * 1000);
            park();
            Thread.sleep(parkingDuration);
            exit();
        } catch (InterruptedException e) {
            System.out.println("Car " + id + " was interrupted.");
        }
    }

    public int getID()
    {
        return id ;
    }

    public int getGate()
    {
        return gate.getId() ;
    }

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
        long waitStartTime = System.currentTimeMillis();

        if (spotManager.tryToPark()) {
            long waitEndTime = System.currentTimeMillis();
            System.out.println("Car " + id + " from Gate " + gate.getId() +
                    " parked immediately (Parking Status: " + (4 - spotManager.availableSpots()) +
                    " spots occupied, Wait Time: " + (waitEndTime - waitStartTime) + " ms)");
        } else {
            System.out.println("Car " + id + " from Gate " + gate.getId() + " is waiting to park.");
            spotManager.waitToPark(); // Block until a spot is available
            long waitEndTime = System.currentTimeMillis();
            System.out.println("Car " + id + " from Gate " + gate.getId() + " parked after waiting " +
                    (waitEndTime - waitStartTime) + " ms (Parking Status: " +
                    (4 - spotManager.availableSpots()) + " spots occupied)");
        }
    }


    private void exit() {
        gate.letCarOut(this);
        spotManager.leave();
    }
}
