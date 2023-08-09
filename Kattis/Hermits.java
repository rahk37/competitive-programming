import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hermits {
    public static void main(String[] args) throws IOException {
        //used buffer reader due to scanf being way to slow
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int amountStreets = Integer.parseInt(br.readLine());
        int[][] streets = new int[2][amountStreets];

        String[] amountsStr = br.readLine().split(" ");

        for (int i = 0; i < amountStreets; i++) {
            streets[0][i] = Integer.parseInt(amountsStr[i]);
            streets[1][i] = Integer.parseInt(amountsStr[i]);

        }

        int amountConnections = Integer.parseInt(br.readLine());

        for (int i = 0; i < amountConnections; i++) {
            
            String[] tempConnections = br.readLine().split(" ");
            int con1 = Integer.parseInt(tempConnections[0]) - 1;
            int con2 = Integer.parseInt(tempConnections[1]) - 1;

            streets[1][con1] += streets[0][con2];
            streets[1][con2] = streets[0][con1];
        }

        int minStreet = 0;
        for (int i = 0; i < amountStreets; i++) {

            if (streets[1][minStreet] > streets[1][i]){
                minStreet = i;

            } else if (streets[1][minStreet] == streets[1][i]){
                if (i < minStreet){
                    minStreet = i;
                }
            }
        }

        System.out.println(minStreet + 1);
    }
}