public class Gate {
    int id ;
    public int getId()
    {
        return id;
    }
    public void letCarIn(Car c)
    {
        System.out.println("Car " + c.getID() + " from Gate " + c.getID() + " arrived at time "+ c.getArriveAT());
    }
    public void letCarOut(Car c)
    {
        System.out.println("Car " + c.getID() + " from Gate " + c.getID() + " left after .?????");
    }
}
