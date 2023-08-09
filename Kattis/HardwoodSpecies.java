import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Hardwood Species - 'solution' gives the correct answer but causes a TLE
 */
public class HardwoodSpecies {

    /**
     * Main routine to solve the problem 'hardwood species'
     *    https://open.kattis.com/problems/hardwoodspecies
     *    Causes a TLE
     * @param args - command line args - not used
     * @throws Exception - I/O related
     */
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        double total = 0.0;
        HashMap<String, Integer> hashMap = new HashMap<>();

        String map = in.readLine();
        long start = System.nanoTime();

    
        while(map != null && !map.trim().equals("")) {

            if(hashMap.containsKey(map)) {
                hashMap.put(map, hashMap.get(map) + 1);
            } else {
                hashMap.put(map, 1);
            }

            total++;
            map = in.readLine();
        }

        List<String> sortedList = new ArrayList<>(hashMap.keySet());
        Collections.sort(sortedList);

        for(String key : sortedList) {
            pw.printf("%s %.6f\n", key, hashMap.get(key) / total * 100);
        }
        
        long runtime = System.nanoTime() - start;
        pw.println("Runtime: " + runtime);
        pw.close();
    }
}