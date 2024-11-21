import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ParkingSpot spots = new ParkingSpot(4) ;

        // read input
        String filePath = "src\\cars.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] split = line.split(", ");
                int carNum =0, arriveAt=0 , duration=0 ;
                Gate gate = new Gate() ;
                for(int i = 0 ; i < 4 ; i++)
                {
                    String[] temp = split[i].split(" ");
                    if(i==0)
                        gate.id = Integer.parseInt(temp[1]);
                    else if(i==1)
                        carNum = Integer.parseInt(temp[1]);
                    else if(i==2)
                        arriveAt = Integer.parseInt(temp[1]);
                    else
                        duration = Integer.parseInt(temp[1]);
                }
                Car c = new Car(carNum,arriveAt,duration,gate,spots) ;
                c.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}