import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SpaceMail {
    /**
     * Used to initialize the distances between planets
     */
    private static class Edge implements Comparable<Edge> {
        public int src;
        public int tgt;
        public double weight;

        public Edge(int s, int t, double w) {
            src = s;
            tgt = t;
            weight = w;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(weight, other.weight);
        }
    }

    /**
     * Planet object that stores location of a planet
     */
    private static class Planet {
        int x;
        int y;
        int z;

        public Planet(int i, int j, int k) {
            x = i;
            y = j;
            z = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numPlanets = Integer.parseInt(reader.readLine());

        // Populate an array with Planets (x, y, z coordinates)
        Planet[] planets = populatePlanets(numPlanets, reader);

        // Populate an ArrayList with edges that contain the distances between the planets
        ArrayList<Edge> distanceEdges = populateEdges(numPlanets, planets);

        Collections.sort(distanceEdges);

        // Array list to hold all the edges in the minimal spanning tree
        LinkedList<Edge> mst = new LinkedList<>();
        // Initialize priority queue and cost list
        int[] costs = new int[numPlanets];
        for (int i = 0; i < numPlanets; i++) {
            costs[i] = Integer.MAX_VALUE;
        }
        costs[0] = 0;
        PriorityQueue<Edge> openList = new PriorityQueue<>();
        openList.addAll(distanceEdges);

        // Prim's algorithm based on BFS
        while(!openList.isEmpty()) {
            Edge curr = openList.poll();

            if(costs[curr.tgt] != 0) {
                mst.add(curr);
                costs[curr.tgt] = 0;
                
                for(Edge edge : distanceEdges) {
                    if(costs[edge.tgt] > edge.weight) {
                        openList.add(edge);
                        costs[edge.tgt] = (int) edge.weight;
                    }
                }
            }
        }


        // Print out the smallest tank that can be used to travel to all the planets
        PrintWriter writer = new PrintWriter(System.out);
        writer.println((int) Math.ceil(mst.removeLast().weight));
        writer.close();

    }

    /**
     * Finds the distance between planets
     * @param p1 planet one
     * @param p2 planet two
     * @return the distance as a double
     */
    public static double planetDistance(Planet p1, Planet p2) {
        return Math.sqrt((Math.pow((p1.x - p2.x), 2)) + (Math.pow((p1.y - p2.y), 2)) + (Math.pow((p1.z - p2.z), 2)));
    }

    /**
     * Helper method that populates the distances between planets
     * @param numPlanets number of planets
     * @param planets planets array
     * @return an array list of the distances as edges
     */
    private static ArrayList<Edge> populateEdges(int numPlanets, Planet[] planets) {
        // Build an array of edges - distance between outputs indicates edge weight
        ArrayList<Edge> distanceEdges = new ArrayList<>();
        for(int i = 0; i < numPlanets-1; i++) {
            for(int j = i+1; j < numPlanets; j++) {
                distanceEdges.add(new Edge(i, j, planetDistance(planets[i], planets[j])));
            }
        }
        return distanceEdges;
    }

    /**
     * Populates an array with planet objects
     * @param numPlanets number of planets in the array
     * @param reader used to read input
     * @return planet array
     * @throws IOException thrown with BufferedReader
     */
    private static Planet[] populatePlanets(int numPlanets, BufferedReader reader) throws IOException {
        // Build a list of planets
        Planet[] planets = new Planet[numPlanets];
        for(int i = 0; i < numPlanets; i++) {
            StringTokenizer planetsToken = new StringTokenizer(reader.readLine(), " ");
            planets[i] = new Planet(Integer.parseInt(planetsToken.nextToken()), Integer.parseInt(planetsToken.nextToken()), Integer.parseInt(planetsToken.nextToken()));
        }
        return planets;
    }
}