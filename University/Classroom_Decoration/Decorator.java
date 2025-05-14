import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Decorator class is responsible for decorating classrooms and writing the decoration information to a file.
 */
public class Decorator {
    private List<String> outputs;
    private List<Classroom> classrooms;

    /**
     * Constructor to initialize Decorator with classrooms.
     * 
     * @param fileIO FileIO object containing classrooms.
     */
    public Decorator(FileIO fileIO){
        this.classrooms = fileIO.getClassrooms();
    }

    /**
     * Rounds a double value to the nearest integer.
     * 
     * @param value The double value to be rounded.
     * @return The rounded integer value.
     */
    public int customRound(double value) {
        if (value == (int) value) {
            // If the value is already an integer, return it as is
            return (int) value;
        } else {
            // If the value has a decimal part, round up
            return (int) Math.ceil(value);
        }
    }
    
    /**
     * Decorates classrooms and writes the decoration information to a file.
     * 
     * @param filePath The path of the file where the decoration information will be written.
     */
    public void Decorate(String filePath){
        outputs = new ArrayList<>();
        int totalcost = 0;
        double areaFloor, areaWall;
        double wallCount, floorCount, cost;
        for(Classroom classroom : this.classrooms){
            areaFloor = 0;
            areaWall = 0;
            wallCount = 0;
            floorCount = 0;
            cost = 0;
            if(classroom.getType().equals("Circle")){
                areaFloor = (classroom.getLength()*classroom.getLength()/4)*Math.PI;
                areaWall = classroom.getLength()*Math.PI*classroom.getHeight();
                floorCount = customRound(areaFloor/classroom.getFlooringDecoration().getAreaPerUnit());
                if(classroom.getWallDecoration().getType().equals("Tile")){
                    wallCount = customRound(areaWall/classroom.getWallDecoration().getAreaPerUnit());
                }
                else{
                    wallCount = areaWall;
                }
                cost = customRound(wallCount*classroom.getWallDecoration().getPrice()+ floorCount*classroom.getFlooringDecoration().getPrice());
            }
            else{
                areaFloor = classroom.getLength()*classroom.getWidth();
                areaWall = 2*(classroom.getHeight()*classroom.getWidth() + classroom.getHeight()*classroom.getLength());
                floorCount = customRound(areaFloor/classroom.getFlooringDecoration().getAreaPerUnit());
                if(classroom.getWallDecoration().getType().equals("Tile")){
                    wallCount = customRound(areaWall/classroom.getWallDecoration().getAreaPerUnit());
                }
                else{
                    wallCount = areaWall;
                }
                cost = customRound(wallCount*classroom.getWallDecoration().getPrice()+ floorCount*classroom.getFlooringDecoration().getPrice());
            }
            if(classroom.getWallDecoration().getType().equals("Tile")){
                outputs.add("Classroom " + classroom.getName() + " used " + (int) customRound(wallCount) + " Tiles for walls and used " + (int) floorCount + " Tiles for flooring, these costed " + (int) cost +"TL.");
            }
            else{
                outputs.add("Classroom " + classroom.getName() + " used " + (int) customRound(wallCount) + "m2 of "+ classroom.getWallDecoration().getType()+ " for walls and used " + (int) floorCount + " Tiles for flooring, these costed " + (int) cost +"TL.");
            }
            totalcost += cost;
        }
            
        outputs.add("Total price is: " + totalcost + "TL.");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < outputs.size(); i++) {
                writer.write(outputs.get(i));
                if (i < outputs.size() - 1) { // Add newline except for the last line
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

}
