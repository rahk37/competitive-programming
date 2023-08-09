import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Massive {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        br.readLine();
        st = new StringTokenizer(br.readLine(), " ");

        HashMap<Long, Long> numMap = new HashMap<>();
        while(st.hasMoreTokens()){
            long currentNum = Long.parseLong(st.nextToken());
            long tempAmount = numMap.getOrDefault(currentNum, 0L);
            numMap.put(currentNum, tempAmount + 1);
        }

        long maxGuesses = Long.parseLong(br.readLine());
        PrintWriter printWriter = new PrintWriter(System.out);

        for (long i = 0; i < maxGuesses; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            long min = Long.parseLong(st.nextToken());
            long max = Long.parseLong(st.nextToken());

            long count = 0L;
            for (long j = min; j <= max ; j++) {
                count += numMap.getOrDefault(j, 0L);
            }
            printWriter.println(count);
        }
        printWriter.flush();
    }
}

