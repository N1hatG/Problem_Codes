import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileIO {
    private String inputFilePath;
    private String departure;
    private String destination;
    private Set<String> points;
    public FileIO(String inputFilePath){
        this.inputFilePath = inputFilePath;
        this.points = new HashSet<>();
    }

    /**
     * Reads data from a file and adds it to the items list.
     * @param path The path to the file to be read.
     * @param discardEmptyLines Flag indicating whether to discard empty lines.
     * @param trim Flag indicating whether to trim each line.
     * @param ifitems Flag indicating whether the data is items or decorations to be read.
     */
    public List<Road> readFileAndParseInput (boolean discardEmptyLines, boolean trim){
        List<Road> roads = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            line = reader.readLine();
            String[] parts = line.split("\t");
            departure = parts[0];
            destination = parts[1];
            while ((line = reader.readLine()) != null) {
                parts = line.split("\t");
                if(parts.length == 4)
                {
                    String startPoint = parts[0];
                    String endPoint = parts[1];
                    int length = Integer.parseInt(parts[2]);
                    int id = Integer.parseInt(parts[3]);
                    Road road = new Road(startPoint, endPoint, length, id);
                    roads.add(road);
                    points.add(startPoint);
                    points.add(endPoint);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roads;
    }

    /**
     * Write the provided lines to the specified file.
     * @param outputFilePath The path to the file to write.
     * @param lines The lines to write to the file.
     */
    public void writeToFile(String outputFilePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i));
                if (i < lines.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDeparture(){
        return departure;
    }
    public String getDestination(){
        return destination;
    }
    public Set<String> getPoints(){
        return points;
    }
}
