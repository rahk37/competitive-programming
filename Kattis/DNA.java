import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DNA {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // Initialize DNA character Array
        char[] dnaArr = new char[n];

        // Populate the DNA character Array
        StringTokenizer planetsToken = new StringTokenizer(reader.readLine(),"");
        String line = planetsToken.nextToken();
        for (int i = 0; i < n; i++) {
            dnaArr[i] = line.charAt(i);
        }

        // Get the number of mutations by solving the problem
        int mutations = sovleDNA(dnaArr, n);

        PrintWriter writer = new PrintWriter(System.out);
        writer.println(mutations);
        writer.close();
    }

    private static int sovleDNA(char[] dnaArr, int n) {
        int numberMutations = 0;
        boolean isDNA = true;

        // Iterate backwards through the DNA strand
        for(int i = n - 1; i >= 0; --i) {

            // Check the DNA char for A or B and if it is A keep going if it is false and B keep going
            if(isDNA && dnaArr[i] == 'A') continue;
            if(!isDNA && dnaArr[i] == 'B') continue;

            // When you reach the end increment the number of mutations
            if (i == 0) {
                numberMutations++;
                continue;
            }

            numberMutations++;

            // If the DNA is true and is the next strand is not equal to A then it is now false vice versa for B
            if (isDNA) {
                if (dnaArr[i - 1] != 'A') {
                    isDNA = false;
                }
            } else {
                if (dnaArr[i - 1] != 'B') {
                    isDNA = true;
                }
            }
        }
        return numberMutations;
    }
}
