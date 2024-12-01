//import java.util.concurrent.Semaphore;

public class ParkingSpot {
    private Semaphore spots;

    public ParkingSpot(){
        this.spots = new Semaphore(4 ); // Default value in assignment as well
    }
    public ParkingSpot(int spots){
        this.spots = new Semaphore(spots );
    }

    public boolean tryToPark(){
        return this.spots.tryAcquire();
    }
    public void waitToPark() throws InterruptedException {
        spots.acquire();
    }

    public void leave(){
        spots.release();
    }

    public int availableSpots(){
        return spots.availablePermits();
    }
}
