import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SnowPlowSolution {
    private static class Intersection {
        private int north, south, east, west;

        public Intersection(int N, int S, int E, int W) {
            this.north = N;
            this.south = S;
            this.east = E;
            this.west = W;
        }
    }

    private static class Plow {
        int currentIntersection, currentDirection;
        public Plow(int intersect, int dir) {
            this.currentDirection = dir;
            this.currentIntersection = intersect;
        }

        public void setDirection(int dir) {
            this.currentIntersection = dir;
        }

        public int getDirection() {
            return currentDirection;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numIntersections = Integer.parseInt(reader.readLine());
        HashMap<Integer, Intersection> neighborhood = new HashMap<>();
        int[] intersectionsVisited = new int[numIntersections];
        Plow plow = new Plow(1, 2);

        for (int i = 0; i < numIntersections; i++) {
            StringTokenizer tkn = new StringTokenizer(reader.readLine(), " ");
            neighborhood.put(i, new Intersection(Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken())));
        }
        removeSnow(plow, neighborhood, intersectionsVisited);

    }

    private static void removeSnow(Plow plow, HashMap<Integer, Intersection> neighborhood, int[] intersectionsVisited) {
        for (int i = 0; i < neighborhood.size(); i++) {
            System.out.print(neighborhood.get(i) + " ");
        }
    }
}
