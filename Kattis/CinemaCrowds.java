import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class involves group(s) of people are not able to attend the theatre if
 * the capacity of the theatre has been already met. We need to figure how many
 * groups are not able to attend the theatre, this is the output.
 */
public class CinemaCrowds {
    public static void main(String[] args) {
        // Receive input from console and store in Buffered Reader because it is fast.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Store in string variable because readLine() returns a string and not an int
            String firstLine = br.readLine();
            // Store the numbers as different strings in a String array
            String[] firstLineSplit = firstLine.split(" ");
            // First input is theatre capacity
            int numSeats = Integer.parseInt(firstLineSplit[0]);
            // Second input is number of groups attempting to go to the theatre
            int numGroups = Integer.parseInt(firstLineSplit[1]);
            // Read in the second line which shows amount of people in the groups
            String secondLine = br.readLine();
            String[] secondLineSplit = secondLine.split(" ");
            int madGroups = 0;
            int tmpVal = 0;
            // Iterate through the number of groups
            for (int i = 0; i < numGroups; i++) {
                // Get the individual group sizes
                int groupSize = Integer.parseInt(secondLineSplit[i]);
                // If the group size is less than or equal to the theatre capacity - the num of
                // people in the theatre, add the group size to the tmpVal variable,
                // otherwise increment to the mad groups which are groups who cannot attend.
                if (groupSize <= (numSeats - tmpVal)) {
                    tmpVal += groupSize;
                } else {
                    madGroups += 1;
                }
            }

            System.out.println(madGroups);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

