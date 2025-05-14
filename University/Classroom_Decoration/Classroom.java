
// Product
public class Classroom {
    private String name;
    private String type;
    private double width;
    private double length;
    private double height;
    private Decoration wallDecoration;
    private Decoration flooringDecoration;
    FileIO fileIO = new FileIO();

    public Classroom(String name, String type, double width, double length, double height) {
        this.name = name;
        this.type = type;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    //Setters
    public void setWallDecoration(Decoration wallDecoration) {
        this.wallDecoration = wallDecoration;
    }
    public void setFlooringDecoration(Decoration flooringDecoration) {
        this.flooringDecoration = flooringDecoration;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public void setLength(double length){
        this.length = length;
    }
    public void setHeight(double height){
        this.height = height;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public double getWidth() {
        return width;
    }
    public double getLength() {
        return length;
    }
    public double getHeight() {
        return height;
    }
    public Decoration getWallDecoration() {
        return wallDecoration;
    }
    public Decoration getFlooringDecoration() {
        return flooringDecoration;
    }


    
    
    @Override
    public String toString() {
        String wallDecorString = (wallDecoration != null) ? wallDecoration.toString() : "No wall decoration";
        String floorDecorString = (flooringDecoration != null) ? flooringDecoration.toString() : "No flooring decoration";
        
        return "Classroom{name='" + name + "', type='" + type + "', width=" + width + ", length=" + length +
                ", height=" + height + ", wallDecoration=" + wallDecorString + ", flooringDecoration=" + floorDecorString + "}";
    }    
}