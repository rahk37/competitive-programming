import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VariedAmusement {

    public static int a; //The amount of a attractions there is
    public static int b; //The amount of b attractions there is
    public static int c; //The amount of c attractions there is
    public static int n; //The amount of attractions they want to visit

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        n = Integer.parseInt(inputs[0]);
        a = Integer.parseInt(inputs[1]);
        b = Integer.parseInt(inputs[2]);
        c = Integer.parseInt(inputs[3]);

        System.out.println(totalAmount());

        Runtime rt = Runtime.getRuntime();
        long usedMiB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        System.out.println("Memory Usage (MiB): " + usedMiB);
    }

    /**
     * Calls the recursive function to get the amount of different paths
     * @return the total amount of paths modulus 10^9 + 7
     */
    public static long totalAmount() {
        long count;
        count = a * totalAmount(1, 0);
        count += b * totalAmount(1, 1);
        count += c * totalAmount(1, 2);

        return count % (1000000007);
    }

    /**
     * 0 is a
     * 1 is b
     * 2 is c
     *
     * This function recursively checks how many different options does the people have to check the three attractions
     * @param currentN The current number of attractions they visited
     * @param currentRide The current ride they are
     * @return the amount of different possibilities they have
     */
    public static long totalAmount(int currentN, int currentRide) {
        long count = 0;
        if (currentN >= n){ //If the limit has been achieved
            return 1;

        } else if (currentRide == 0){ //if they are in a
            count += b * totalAmount(currentN+1, 1);
            count += c * totalAmount(currentN+1, 2);

        } else if (currentRide == 1){ //If they are in b
            count += a * totalAmount(currentN+1, 0);
            count += c * totalAmount(currentN+1, 2);
            
        } else if (currentRide == 2){ //If they are in c
            count += b * totalAmount(currentN+1, 1);
            count += a * totalAmount(currentN+1, 0);
        }

        return count;
    }
}
