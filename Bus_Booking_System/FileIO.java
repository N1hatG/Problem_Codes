import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * Handles file input operations for the booking system.
 */
class FileIO{
    private List<String> input = new ArrayList<>();
    private List<String[]> parsedInputs = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();

    /**
     * Parses the input data and populates the parsedInputs list.
     */
    public void parseInputs(){
        for(String line : input){
            String[] parts = line.split("\t");
            parsedInputs.add(parts);
        }
    }
    
    //Getters
    public List<Command> getCommands(){
        return commands;
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

    /**
     * Parses the input data and populates the commands list.
     */
    public void parseCommand(){
        for(String[] parts : parsedInputs)
        {
            if(parts[0].equals("INIT_VOYAGE")){
                Command command = new InitCommand(parts);
                commands.add(command);
            }
            else{
                if(parts[0].equals("Z_REPORT")){
                    String type = parts[0];
                    Integer voyageID = 0;
                    Integer[] values = null;
                    if(parts.length > 1){
                        voyageID = -1;
                        values = null;
                    }
                    Command command = new Command(type, voyageID, values, parts);
                    commands.add(command);
                }
                else if(parts[0].equals("PRINT_VOYAGE") || parts[0].equals("CANCEL_VOYAGE")){
                    String type = parts[0];
                    Integer voyageID;
                    Integer[] values = null;
                    if(parts.length == 2){
                        try{
                            voyageID = Integer.parseInt(parts[1]);
                        }
                        catch(Exception e){
                            voyageID = null;
                        }
                    }
                    else{
                        voyageID = null;
                    }
                    Command command = new Command(type, voyageID, values, parts);
                    commands.add(command);
                }
                else if(parts[0].equals("SELL_TICKET") || parts[0].equals("REFUND_TICKET")){
                    String type = parts[0];
                    Integer voyageID = null;
                    Integer[] values = null;
                    if(parts.length == 3){
                        try{
                            voyageID = Integer.parseInt(parts[1]);
                            String[] valuesString = parts[2].split("_");
                            values = new Integer[valuesString.length];
                            for (int i = 0; i < valuesString.length; i++) {
                                values[i] = Integer.parseInt(valuesString[i]);
                            }
                        }
                        catch(Exception e){
                            voyageID = null;
                            values = null;
                        }
                    }
                    else{
                        voyageID = null;
                        values = null;
                    }
                    Command command = new Command(type, voyageID, values, parts);
                    commands.add(command);
                }
                else {
                    String type = parts[0];
                    Command command = new Command(type, null, null, parts);
                    commands.add(command);
                }
            }
        }
    }

}