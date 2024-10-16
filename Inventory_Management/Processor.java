import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Processor<T extends Command> {
    private String inputFilePath;
    private String outputFilePath;
    private List<T> commands;
    private List<Product> products;

    public Processor(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.commands = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public void parseCommands() throws IOException, Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                T command = createCommand(parts);
                commands.add(command);
            }
        }
    }

    private T createCommand(String[] parts) throws Exception {
        if (parts.length == 0) {
            throw new Exception("Empty command");
        }

        String commandType = parts[0];
        switch (commandType) {
            case "ADD":
                if (parts.length == 6) {
                    return (T) new AddCommand(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));
                } else {
                    throw new Exception("Incorrect number of arguments for ADD Command");
                }
            case "REMOVE":
                if (parts.length == 2) {
                    return (T) new RemoveCommand(Integer.parseInt(parts[1]));
                } else {
                    throw new Exception("Incorrect number of arguments for REMOVE command");
                }
            case "SEARCHBYBARCODE":
                if (parts.length == 2) {
                    return (T) new SearchByBarcodeCommand(Integer.parseInt(parts[1]));
                } else {
                    throw new Exception("Incorrect number of arguments for SEARCHBYBARCODE command");
                }
            case "SEARCHBYNAME":
                if (parts.length == 2) {
                    return (T) new SearchByNameCommand(parts[1]);
                } else {
                    throw new Exception("Incorrect number of arguments for SEARCHBYNAME command");
                }
            case "DISPLAY":
                if (parts.length == 1) {
                    return (T) new DisplayCommand();
                } else {
                    throw new Exception("Incorrect number of arguments for DISPLAY command");
                }
            default:
                throw new Exception("Invalid command: " + commandType);
        }
    }

    public void executeCommands() {
        for (T command : commands) {
            List<String> output = new ArrayList<>();
            try{
                output = command.execute(products);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            if(output != null)
                writeOutputToFile(output);
        }
    }

    public int BinarySearchBarcode(int barcode, List<Product> products) {
        Comparator<Product> comparator = Comparator.comparingInt(Product::getBarcode);
        Collections.sort(products, (p1, p2) -> p1.getBarcode() - p2.getBarcode());
        int index = Collections.binarySearch(products, new Product("", "", "", barcode, 0.0), comparator);
        return index;
    }

    private void writeOutputToFile(List<String> output) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            for (String line : output) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}