public class Car extends Thread {
    private final int carId;
    private final String gate;
    private final int stayDuration;
    private final ParkingSpot parkingSpot;

    public Car(int carId, String gate, int stayDuration, ParkingSpot parkingSpot) {
        this.carId = carId;
        this.gate = gate;
        this.stayDuration = stayDuration;
        this.parkingSpot = parkingSpot;
    }

    private void leave() {
        parkingSpot.leave();
        // Log the status of parking after the car leaves
        System.out.println("Car " + carId + " left after " + stayDuration + " units of time. (Parking Status: "
                + (4 - parkingSpot.availableSpots()) + " spots occupied)");
    }

    @Override
    public void run() {
        System.out.println("Car " + carId + " from " + gate + " arriving.");

        if (parkingSpot.tryToPark()) {
            System.out.println("Car " + carId + " parked. (Parking Status: " + (4 - parkingSpot.availableSpots()) + " spots occupied)");

            try {
                Thread.sleep(stayDuration * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Car " + carId + " was interrupted while parking.");
            }
            leave();
        } else {

            try {
                System.out.println("Car " + carId + " from " + gate + " waiting for a spot.");
                parkingSpot.acquire(); // Block until the car can park
                System.out.println("Car " + carId + " parked after waiting. (Parking Status: " + (4 - parkingSpot.availableSpots()) + " spots occupied)");

                try {
                    Thread.sleep(stayDuration * 1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Car " + carId + " was interrupted while waiting to park.");
                }
                leave();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Car " + carId + " was interrupted while waiting for a spot.");
            }
        }
    }
}
