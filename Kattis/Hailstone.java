import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hailstone {
    public static void main(String[] args) throws IOException {
	//Get input from user and store in buffered reader because it is fast
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Use a long instead of an integer because we will be using large numbers
	long input = Long.parseLong(br.readLine());
        int sum = 0;
        hail(input, sum);
    }

    private static void hail(long input, int sum) {
	//Test each case
	//If the input is 1 then we are done and we print
        if (input == 1) {
            System.out.println(sum + 1);
	//If the number is even then we apply the given function
        } else if (input % 2 == 0) {
            hail(input / 2, sum + 1);
	//Otherwise we apply the other given function
        } else {
            hail(3 * input + 1, sum + 1);
        }
    }
}