import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles reading and parsing data from files.
 */
class FileIO {
    private List<Classroom> classrooms;
    private List<Decoration> decorations;
    private List<String> items = new ArrayList<>();
    private List<String> decorate = new ArrayList<>();
    private List<Map<Classroom, List<Decoration>>> decorationOrders = new ArrayList<>();


    /**
     * Parses decorations from the input data and populates the decorations list.
     */
    public void parseDecorations(){
        this.decorations = new ArrayList<>();
        for (String line : this.items) {
            String[] parts = line.split("\t");
            if (parts[0].equals("DECORATION")) {
                ItemBuilder builderDecoration = new DecorationBuilder(); // Create a new instance for each decoration
                if (parts.length == 4) {
                    builderDecoration.buildName(parts[1]);
                    builderDecoration.buildType(parts[2]);
                    builderDecoration.buildPrice(Double.parseDouble(parts[3]));
                } else if (parts.length == 5) {
                    builderDecoration.buildName(parts[1]);
                    builderDecoration.buildType(parts[2]);
                    builderDecoration.buildPrice(Double.parseDouble(parts[3]));
                    builderDecoration.buildAreaPerUnit(Double.parseDouble(parts[4]));
                }
                decorations.add((Decoration) builderDecoration.getItem());
            }
        }
    }
    
    /**
     * Parses classrooms from the input data and populates the classrooms list.
     */
    public void parseClassrooms() {
        this.classrooms = new ArrayList<>();
        for (String line : this.items) {
            String[] parts = line.split("\t");
            if (parts[0].equals("CLASSROOM")) {
                ItemBuilder builderClassroom = new ClassroomBuilder(); // Create a new instance for each classroom
                builderClassroom.buildName(parts[1]);
                builderClassroom.buildType(parts[2]);
                builderClassroom.buildDimensions(Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
                for(String order : this.decorate){
                    String[] orderParts = order.split("\t");
                    if(orderParts[0].equals(parts[1])){
                        if(getDecorationByName(orderParts[1]) != null){
                            builderClassroom.buildWallDecoration(getDecorationByName(orderParts[1]));
                        
                        }else {
                            System.out.println("wall decoration not found");
                        }
                        if(getDecorationByName(orderParts[2]) != null)
                        {
                            builderClassroom.buildFlooringDecoration(getDecorationByName(orderParts[2]));
                        }else {
                            System.out.println("Floor decoration not found");
                        }
                        
                    }
                }
                classrooms.add((Classroom) builderClassroom.getItem());
            } 
        }
    }
    
    
    /**
     * Reads data from a file and adds it to the items list.
     * @param path The path to the file to be read.
     * @param discardEmptyLines Flag indicating whether to discard empty lines.
     * @param trim Flag indicating whether to trim each line.
     * @param ifitems Flag indicating whether the data is items or decorations to be read.
     */
    public void readFile(String path, boolean discardEmptyLines, boolean trim, boolean ifitems) {
        if(ifitems){
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!discardEmptyLines || !line.trim().isEmpty()) {
                        this.items.add(trim ? line.trim() : line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!discardEmptyLines || !line.trim().isEmpty()) {
                        this.decorate.add(trim ? line.trim() : line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Getters
    public List<Classroom> getClassrooms(){
        return classrooms;
    }
    public List<Decoration> getDecorations(){
        return decorations;
    }
    public List<String> getDecorate(){
        return decorate;
    }
    public List<Map<Classroom, List<Decoration>>> getDecorationOrders(){
        return decorationOrders;
    }
    public Decoration getDecorationByName(String name){
        for(Decoration decoration : this.decorations){
            if(decoration.getName().equals(name)){
                return decoration;
            }
        }
        return null;
    }
    public Classroom getClassroomByName(String name){
        classrooms = getClassrooms();
        for(Classroom classroom : this.classrooms){
            if(classroom.getName().equals(name)){
                return classroom;
            }
        }
        return null;
    }
    
    //Setters
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Classrooms:\n");
        for (Classroom classroom : this.classrooms) {
            result.append(classroom.toString()).append("\n");
        }
        result.append("Decorations:\n");
        for (Decoration decoration : this.decorations) {
            result.append(decoration.toString()).append("\n");
        }
        return result.toString();
    }
}
