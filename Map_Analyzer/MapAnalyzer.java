public class MapAnalyzer {
    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        Graph graph = new Graph(inputFilePath, outputFilePath);
        graph.sortRoadsByLengthAndID();
        graph.findTheShortestPath();
        graph.createBarelyConnectedMap();
        graph.findPathInBarelyConnectedMap();
        graph.WriteOutputToTheFile();
    }
}