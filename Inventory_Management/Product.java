public class Product {
    private String type;
    private String name;
    private String spec; // for Book its author information, for Toy it is color, for Stationery it is kind
    private double price;
    private int barcode;

    public Product(String type, String name, String spec, int barcode, Double price){

        this.type = type;
        this.name = name;
        this.spec = spec; 
        this.price = price;
        this.barcode = barcode;
    }

    // Getter methods
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSpec() {
        return spec;
    }

    public double getPrice() {
        return price;
    }

    public int getBarcode() {
        return barcode;
    }
    @Override
    public String toString(){
        String output = null;
        if(type.equals("Book")){
            output =  "Author of the " + name + " is " + spec;
        
        }
        else if(type.equals("Toy")){
            output = "Color of the " + name + " is " + spec;
        }
        else if(type.equals("Stationery")){
            output = "Kind of the " + name + " is " + spec;
        }
        output += ". Its barcode is " + barcode + " and its price is " + price;
        return output;
    }
}
