

// Decoration
public class Decoration {
    private String name;
    private String type;
    private double price;
    private double areaPerUnit;
    FileIO fileIO = new FileIO();
    public Decoration(String name, String type, double price, double areaPerUnit) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.areaPerUnit = areaPerUnit;
    }
    
    //Getters
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public double getPrice(){
        return price;
    }
    public double getAreaPerUnit(){
        return areaPerUnit;
    }
    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setAreaPerUnit(double areaPerUnit){
        this.areaPerUnit = areaPerUnit;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Type: " + type + ", Price: $" + price + ", Area Per Unit: " + areaPerUnit;
    }
}
