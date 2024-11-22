import java.util.concurrent.Semaphore;

public class ParkingSpot {
    private Semaphore spots;

    public ParkingSpot(){
        this.spots = new Semaphore(4, true); // Default value in assignment as well
    }
    public ParkingSpot(int spots){
        this.spots = new Semaphore(spots, true);
    }

    public void park() throws InterruptedException {
        spots.acquire();
    }

    public void leave(){
        spots.release();
    }

    public int availableSpots(){
        return spots.availablePermits();
    }
}
