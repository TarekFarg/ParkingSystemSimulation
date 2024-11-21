import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {



        // read input
        String filePath = "src\\cars.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] split = line.split(", ");
                for(int i = 0 ; i < 4 ; i++)
                {
                    String[] temp = split[i].split(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}