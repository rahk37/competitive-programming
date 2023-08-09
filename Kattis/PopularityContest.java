import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Popularity Contest
 *
 * This problem was about using a formula they implied over a set of data.
 * The problem stated that there is a list of people who are friends with each other.
 * Those friends are listed from 1 to n, n being the number of friends in the list,
 * and the list is organized by their likelihood of success. We want to know what the marketability rate of each friend,
 * to find this we take the number of friends minus the success rate.
 */
public class PopularityContest {

    public static void main(String args[]) throws IOException {

        //used buffer reader due to scanf being way to slow
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        //created array based off of user input and split it up based on spaces
        String[] nums = line.split(" ");
        //first element is number of friends
        int numFriends = Integer.parseInt(nums[0]);
        //next element is number of connections
        int numConnections = Integer.parseInt(nums[1]);

        int[] connections = new int[numFriends];
        //parse through each value of the connections array
        for (int i = 0; i < numConnections; i++) {

            line = br.readLine();
            nums = line.split(" ");
            int f1 = Integer.parseInt(nums[0]) - 1;
            int f2 = Integer.parseInt(nums[1]) - 1;
            connections[f1]++;
            connections[f2]++;
        }

        //figure out marketablity coefeciant and print out results
        for (int i = 0; i < numFriends; i++) {
            System.out.print(connections[i] - (i+1) + " ");
        }
    }
}