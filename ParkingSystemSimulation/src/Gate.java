public class Gate {
    int id ;
    int servedCars;

    public Gate(int id){
        this.id = id;
    }
    public int getId()
    {
        return id;
    }

    public void incrementServed(){
        this.servedCars++;
    }
    public int getServed(){
        return this.servedCars;
    }
    public void letCarIn(Car c)
    {
        System.out.println("Car " + c.getID() + " from Gate " + this.getId() + " arrived at time " + c.getArriveAT() + "s.");
    }
    public void letCarOut(Car c)
    {
        System.out.println("Car " + c.getID() + " from Gate " + this.getId() + " left after " + c.getParkDuration() + "s.");
    }
}
