import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileIO {
    private  Map<String, List<String>> grammar;
    private List<String> input;

    public FileIO(){
        this.input = new ArrayList<>();
        this.grammar = new HashMap<>();
    }
    public void parseInputs() {
        for (String line : input) {
            String[] parts = line.split("->");
            String[] productionParts = parts[1].split("\\|"); // Split the right-hand side by '|'
            List<String> value = new ArrayList<>(Arrays.asList(productionParts));
            grammar.put(parts[0], value);
        }
    }

    public Map<String, List<String>> getGrammar(){
        return grammar;
    }

    /**
     * This method writes given content to file at given path.
     *
     * @param path    Path for the file content is going to be written.
     * @param content Content that is going to be written to file.
     * @param append  Append status, true if wanted to append to file if it exists, false if wanted to create file from zero.
     * @param newLine True if wanted to append a new line after content, false if vice versa.
     */
    public void writeToFile(String path, String content, boolean append, boolean newLine) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, append))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads data from a file and adds it to the items list.
     * @param path The path to the file to be read.
     * @param discardEmptyLines Flag indicating whether to discard empty lines.
     * @param trim Flag indicating whether to trim each line.
     * @param ifitems Flag indicating whether the data is items or decorations to be read.
     */
    public void readFile(String path, boolean discardEmptyLines, boolean trim) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!discardEmptyLines || !line.trim().isEmpty()) {
                    this.input.add(trim ? line.trim() : line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
