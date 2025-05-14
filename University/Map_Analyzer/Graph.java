import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph {
    // List to store all roads in the graph
    private List<Road> roads;
    // List to store the path of the shortest way on the barely connected map
    private List<Road> path;
    // Set to store all unique points (vertices) in the graph
    private Set<String> points;
    // Total distance of roads in the original map
    private double totalDistanceForOriginalMap;
    // Total distance of roads in the barely connected map
    private double totalDistanceForBCM;
    // Shortest distance of the fastest route in the barely connected map
    private double shortestDistanceForBCM;
    // Shortest distance of the fastest route in the original map
    private double shortestDistanceForOriginalMap;
    // Departure point for navigation
    private String departure;
    // Destination point for navigation
    private String destination;
    // List to store the output for writing to a file
    private List<String> theWholeOutput;
    // Path to the output file
    private String outputFilePath;
    // Object for handling file input/output operations
    private FileIO fileIO;
    // List to store the roads in the barely connected map
    private List<Road> barelyConnectedMap;
    // Map to store the previous road for each point during shortest path calculation
    private Map<String, Road> previousRoad;


    /**
     * Constructs a Graph object with input and output file paths.
     * 
     * @param inputFilePath  The path to the input file.
     * @param outputFilePath The path to the output file.
     */
    public Graph(String inputFilePath, String outputFilePath){
        this.fileIO = new FileIO(inputFilePath);
        // Read and parse input file to initialize roads list and points set
        roads = fileIO.readFileAndParseInput(true, true);
        points = fileIO.getPoints();
        previousRoad = new HashMap<>();
        theWholeOutput = new ArrayList<>();
        totalDistanceForBCM = 0;
        totalDistanceForOriginalMap = 0;
        shortestDistanceForOriginalMap = 0;
        shortestDistanceForBCM = 0;
        this.outputFilePath = outputFilePath;
        barelyConnectedMap = new ArrayList<>();
        this.departure = fileIO.getDeparture();
        this.destination = fileIO.getDestination();
    }

    /**
     * Sorts roads by length and ID.
     */
    public void sortRoadsByLengthAndID() {
        List<Road> sortedRoads = new ArrayList<>();
        String startPoint;
        // Find roads connected to the startPoint
        for (Road road : roads) {
            totalDistanceForOriginalMap += road.getLength();
            startPoint = road.getStartPoint();
            if (road.getStartPoint().equals(startPoint)) {
                sortedRoads.add(road);
            }
        }

        // Sort roads by length, with IDs as tiebreakers
        sortedRoads.sort(Comparator.comparingInt(Road::getLength)
                .thenComparingInt(Road::getID));
        roads = sortedRoads;
    }

    /**
     * Creates the barely connected map using Prim's algorithm.
     */
    public void createBarelyConnectedMap() {
        // Sort points alphabetically
        List<String> sortedPoints = new ArrayList<>(points);
        Collections.sort(sortedPoints);

        // Priority queue to store edges sorted by their length (and ID as tiebreaker)
        PriorityQueue<Road> edgeQueue = new PriorityQueue<>(Comparator.comparingInt(Road::getLength)
                                                                      .thenComparingInt(Road::getID));
        Map<String, Integer> pointIndexMap = new HashMap<>();

        // Assign each point an index
        int index = 0;
        for (String point : sortedPoints) {
            pointIndexMap.put(point, index++);
        }

        // Add all edges to the queue
        edgeQueue.addAll(roads);

        // Union-Find data structure to track connected components
        UnionFind unionFind = new UnionFind(sortedPoints.size());

        //Algorithm to construct the barely connected map
        while (!edgeQueue.isEmpty() && barelyConnectedMap.size() < points.size() - 1) {
            // Get and remove the smallest road from the queue
            Road currentRoad = edgeQueue.poll();
            int point1Index = pointIndexMap.get(currentRoad.getStartPoint());
            int point2Index = pointIndexMap.get(currentRoad.getEndPoint());

            if (unionFind.find(point1Index) != unionFind.find(point2Index)) {
                // Add the road to the barely connected map
                barelyConnectedMap.add(currentRoad);
                unionFind.union(point1Index, point2Index);
            }
        }

        // Output the barely connected map in the order of addition
        theWholeOutput.add("Roads of Barely Connected Map is:");
        for (Road road : barelyConnectedMap) {
            theWholeOutput.add(road.toString());
            totalDistanceForBCM += road.getLength();
        }
    }
    
    /**
     * Finds the shortest path on the original map.
     */
    public void findTheShortestPath() {
        // Initialize distances
        Map<String, Integer> distances = new HashMap<>();
        for (String point : points) {
            distances.put(point, Integer.MAX_VALUE);
        }
        distances.put(departure, 0);
    
        // PriorityQueue to store points sorted by the shortest known distance
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
    
        // Add departure to the queue
        queue.offer(departure);
    
        // Shortest path algorithm
        while (!queue.isEmpty()) {
            String current = queue.poll();
            
            // Find roads connected to the current point in the order of the sorted roads list
            for (Road road : roads) {
                if (road.getStartPoint().equals(current)) {
                    String nextPoint = road.getEndPoint();
                    int newDistance = distances.get(current) + road.getLength();
                    if (newDistance < distances.get(nextPoint)) {
                        distances.put(nextPoint, newDistance);
                        previousRoad.put(nextPoint, road);
                        queue.offer(nextPoint);
                    }
                }
                else if(road.getEndPoint().equals(current)){
                    String nextPoint = road.getStartPoint();
                    int newDistance = distances.get(current) + road.getLength();
                    if(newDistance < distances.get(nextPoint)){
                        distances.put(nextPoint, newDistance);
                        previousRoad.put(nextPoint, road);
                        queue.offer(nextPoint);
                    }
                }
            }
        }
    
        // Reconstruct the shortest path
        List<Road> shortestPath = new ArrayList<>();
        String currentPoint = destination;
        while (previousRoad.containsKey(currentPoint)) {
            Road road = previousRoad.get(currentPoint);
            shortestPath.add(road);
            shortestDistanceForOriginalMap += road.getLength();
            // Check if the current point is the start or end point of the road
            if (currentPoint.equals(road.getStartPoint())) {
                // If the current point is the start point, move to the end point of the road
                currentPoint = road.getEndPoint();
            } else {
                // If the current point is the end point, move to the start point of the road
                currentPoint = road.getStartPoint();
            }
        }
    
        // Reverse the shortest path to get the correct order
        Collections.reverse(shortestPath);
    
        // Output the shortest path
        theWholeOutput.add("Fastest Route from " + departure + " to " + destination +" (" + (int) shortestDistanceForOriginalMap +" KM):" );
        for (Road road : shortestPath) {
            theWholeOutput.add(road.toString());
        }
    }

    /**
     * Finds the path in the barely connected map
     */
    public void findPathInBarelyConnectedMap() {
        path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        if (dfs(departure, destination, path, visited)) {
            for(Road road : path){
                shortestDistanceForBCM += road.getLength();
            }
        }
    }

    /**
     * Depth-first search (DFS) algorithm to find a path between two points.
     * @param current The current point being visited.
     * @param target The target destination point.
     * @param path The list to store the path.
     * @param visited The set of visited points.
     * @return True if a path is found, false otherwise.
     */
    private boolean dfs(String current, String target, List<Road> path, Set<String> visited) {
        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        for (Road road : barelyConnectedMap) {
            String nextPoint = null;
            if (road.getStartPoint().equals(current) && !visited.contains(road.getEndPoint())) {
                nextPoint = road.getEndPoint();
            } else if (road.getEndPoint().equals(current) && !visited.contains(road.getStartPoint())) {
                nextPoint = road.getStartPoint();
            }

            if (nextPoint != null) {
                path.add(road);
                if (dfs(nextPoint, target, path, visited)) {
                    return true;
                }
                path.remove(path.size() - 1); // Backtrack if no path found
            }
        }

        visited.remove(current); // Allow re-visiting during other paths
        return false;
    }
    
    /**
     * Adds a road to the graph.
     * @param startPoint The starting point of the road.
     * @param endPoint The ending point of the road.
     * @param length The length of the road.
     * @param id The ID of the road.
     */
    public void addRoad(String startPoint, String endPoint, int length, int id){
        Road road = new Road(startPoint, endPoint, length, id);
        roads.add(road);
    }


    /**
     * Adds a point to the graph.
     * @param point The point to add.
     */
    public void addPoint(String point){
        points.add(point);
    }
   
    /**
     * Sets the departure and destination points for the navigation.
     * @param departure The departure point.
     * @param destination The destination point.
     */
    public void setDepartureAndDestination(String departure, String destination){
        this.departure = departure;
        this.destination = destination;
    }

    /**
     * Writes the output to the specified file.
     */
    public void WriteOutputToTheFile(){
        theWholeOutput.add("Fastest Route from " + departure + " to " + destination +" on Barely Connected Map (" + (int) shortestDistanceForBCM +" KM):");
        for(Road road: path){
            theWholeOutput.add(road.toString());
        }
        double ratioMaterial = (double) totalDistanceForBCM / totalDistanceForOriginalMap;
        String formattedMaterialRatio = String.format("%.2f", ratioMaterial); 

        double ratioFastestRoute = (double) shortestDistanceForBCM / shortestDistanceForOriginalMap;
        String formattedRouteRatio = String.format("%.2f", ratioFastestRoute);

        theWholeOutput.add("Analysis:");
        theWholeOutput.add("Ratio of Construction Material Usage Between Barely Connected and Original Map: " + formattedMaterialRatio);
        theWholeOutput.add("Ratio of Fastest Route Between Barely Connected and Original Map: " + formattedRouteRatio);
        fileIO.writeToFile(outputFilePath, theWholeOutput);
    }

    /**
     * Represents a disjoint-set data structure for union-find operations.
     */
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        /**
         * Initializes the UnionFind data structure with the specified size.
         * @param n The size of the data structure.
         */
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        /**
         * Finds the root of the given element.
         * @param x The element to find.
         * @return The root of the element.
         */
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * Unites two sets represented by their root elements.
         * @param x The representative element of the first set.
         * @param y The representative element of the second set.
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
