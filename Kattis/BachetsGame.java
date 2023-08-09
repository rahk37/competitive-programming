import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BachetsGame {
    public static void main(String[] args) throws IOException {
        //used buffer reader due to scanf being way to slow
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (br.readLine() != null) { //Loop through all numbers
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken()) - 1; //Amount of Rocks
            int m = Integer.parseInt(stringTokenizer.nextToken()); //Amount of options of numbers that a player can take
            long[] nums = new long[m]; //Initialize numbers array'
            boolean[] stones = new boolean[1000000];

            //Iterate and place number of rock options in a list
            for (int i = 0; i < m; i++) {
                nums[i] = Integer.parseInt(stringTokenizer.nextToken()); //add to the numbers array
            }
            for (int i = 1; i <= n; i++) { // Loop through the stones array
                stones[i] = false; // Set the stone as false initially
                for (int j = 0; j < m; j++) {
                    if (nums[j] <= i) { // If the jth value of the numbers is less than or equal to i the value is in favor of stan
                        stones[i] = true;
                        break; // break from the loop
                    }
                }
            }
            if (stones[n]) {
                System.out.println("Stan wins");
            } else {
                System.out.println("Ollie wins");
            }
        }
    }
}
