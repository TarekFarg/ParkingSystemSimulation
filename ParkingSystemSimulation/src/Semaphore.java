public class Semaphore {
    int spotsAvailable;
    public Semaphore(int spotsAvailable)
    {
        this.spotsAvailable = spotsAvailable;
    }

    public synchronized void acquire() throws InterruptedException {
        while (spotsAvailable ==0)
        {
            wait();
        }
        spotsAvailable--;
    }

    public synchronized void release()
    {
        spotsAvailable++;
        notify();
    }


    public synchronized boolean tryAcquire()
    {
        if(spotsAvailable>0)
        {
            spotsAvailable--;
            return true;
        }
        return false;
    }


    public int availablePermits()
    {
        return spotsAvailable;
    }
}
