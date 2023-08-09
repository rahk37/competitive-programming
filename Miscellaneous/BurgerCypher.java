import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BurgerCypher {
    /**
     * Possibility of word data structure capacity
     */
    private static final int CAP = 50;

    /**
     * Integer division threshold
     */
    private static final double THRES = 0.7;

    /**
     * This class defines a clue as a node
     */
    private static class Clue {
        String child;
        String parent;

        public Clue(String child, String parent) {
            this.child = child;
            this.parent = parent;
        }
    }

    /**
     * This class analyzes the word clues within the hashmap
     */
    private static class AnalyzeClues {
        // Initialize a map for all the edges (maps have constant search)
        private final Map<String, Set<String>> edges;

        /**
         * Constructor for the Analyze Clues helper class
         * @param n number of clues
         * @param reader input from user
         */
        public AnalyzeClues(int n, Scanner reader) {
            this.edges = new HashMap<>(CAP);

            // Iterate through the clues at each line
            for (int i = 0; i < n; i++) {
                String[] line = reader.nextLine().split(" ");

                // If there are 2 or more words than continue
                if (line.length >= 2) {
                    this.edges.putIfAbsent(line[0], new HashSet<>(CAP));

                    // Iterate through the given line of clues
                    for (int j = 1; j < line.length; j++) {
                        // Add the words to a hash set
                        this.edges.putIfAbsent(line[j], new HashSet<>(CAP));
                        this.edges.get(line[0]).add(line[j]);
                        this.edges.get(line[j]).add(line[0]);
                    }
                }
            }
        }

        /**
         * This method returns the size of the hash map which is just
         * used as a helper method
         * @return number of neighbors/edges
         */
        public int numNeighbors() {
            return this.edges.size();
        }

        /**
         * This method returns the all the neighbors as a data structure
         * @param word given word clue
         * @return the data structure
         */
        public Iterable<String> neighbors(String word) {
            return this.edges.getOrDefault(word, new HashSet<>());
        }
    }

    /**
     * This class performs a Breadth First Search tailored to this problem.
     */
    private static class BFS {
        private final Map<String, String> phrases;
        private String endStr;

        /**
         * BFS constructor
         * @param guess guessing word
         * @param src source word
         * @param dst target word
         */
        public BFS(AnalyzeClues guess, String src, String dst) {
            // Create a new Hashmap of the size of the guessing word divided by the threshold
            this.phrases = new HashMap<>((int)(guess.numNeighbors() / THRES));
            this.endStr = null;
            this.searchClues(guess, src, dst);
        }

        /**
         * Convert the answer to a returnable String
         * @return the answer
         */
        @Override
        public String toString() {
            // If there isn't an answer return print impossible
            if (this.endStr == null) {
                return "impossible";
            }
            // Initialize the edges and also initialize the potential word answer
            Stack<String> trail = new Stack<>();
            String ans = this.endStr;

            // While there are still words add them to the stack
            while (ans.length() > 0) {
                trail.push(ans);
                ans = phrases.get(ans);
            }

            // Initialize the word as a string builder to start appending to
            StringBuilder word = new StringBuilder(this.phrases.size());
            word.append(trail.pop());

            // Add to the string builder until it is empty
            while (!trail.isEmpty()) {
                word.append(' ').append(trail.pop());
            }

            return word.toString();
        }

        /**
         * This method searches for clues contained in the data structure
         * @param guess guessed clue
         * @param src source word
         * @param dst destination word
         */
        private void searchClues(AnalyzeClues guess, String src, String dst) {
            // Initialize the a linked list for the possible clues
            Queue<Clue> frn = new LinkedList<>();
            frn.add(new Clue(src, ""));
            // Initialize a set for possible flags
            Set<String> flags = new HashSet<>(CAP);

            // Iterate until there are not any words
            while (!frn.isEmpty()) {
                // Get the first word in the queue
                Clue currWord = frn.poll();

                // If the flagged words don't contain the child of the current word add it
                if (!flags.contains(currWord.child)) {
                    flags.add(currWord.child);
                    this.phrases.put(currWord.child, currWord.parent);

                    // Iterate through the neighbors of the guessed words children
                    for (String neighbor : guess.neighbors(currWord.child)) {

                        // If the flags do not have the neighbor yet and the neighbor is the same as the
                        // destination word then you need to add it to the phrases hash map
                        // Otherwise add it to the queue of possible clues
                        if (!flags.contains(neighbor)) {
                            if (neighbor.equals(dst)) {
                                this.phrases.put(neighbor, currWord.child);
                                this.endStr = neighbor;
                                return;
                            }

                            frn.add(new Clue(neighbor, currWord.child));
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        System.out.println(new BFS(new AnalyzeClues(Integer.parseInt(in.nextLine()), in), in.next(), in.next()));
    }
}
