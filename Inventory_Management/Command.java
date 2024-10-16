import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public interface Command {
    List<String> execute(List<Product> products) throws Exception;
}

class AddCommand implements Command {
    private String type;
    private String spec;
    private String name;
    private int barcode;
    private double price;

    public AddCommand(String type, String name, String spec, int barcode, double price) {
        this.type = type;
        this.spec = spec;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    @Override
    public List<String> execute(List<Product> products) throws Exception {
        try{
            Product product = new Product(type, name, spec, barcode, price);
            products.add(product);
        } catch(Exception e){
            throw new Exception("Can not add product with this info:" + type +" " + name +" " + spec +" "+barcode +" "+ price);
        }
        return null;
    }
}

class RemoveCommand implements Command {
    private int barcode;

    public RemoveCommand(int barcode) {
        this.barcode = barcode;
    }

    @Override
    public List<String> execute(List<Product> products) {
        List<String> output = new ArrayList<>();
        int index = SearchByBarcodeCommand.BinarySearchBarcode(barcode, products);
        output.add("REMOVE RESULTS:");
        if(index >= 0){
            products.remove(index);
            output.add("Item is removed.");
        }
        else    
            output.add("Item is not found.");
        output.add("------------------------------");
        return output;
    }
}

class DisplayCommand implements Command {
    @Override
    public List<String> execute(List<Product> products) {
        List<String> output = new ArrayList<>();
        List<String> booksOutput = new ArrayList<>();
        List<String> toysOutput = new ArrayList<>();
        List<String> stationeryOutput = new ArrayList<>();
        output.add("INVENTORY:");
        for(Product product : products){
            if(product.getType().equals("Book"))
                booksOutput.add(product.toString());
            else if(product.getType().equals("Toy"))
                toysOutput.add(product.toString());
            else if(product.getType().equals("Stationery"))
                stationeryOutput.add(product.toString());
        }
        output.addAll(booksOutput);
        output.addAll(toysOutput);
        output.addAll(stationeryOutput);
        output.add("------------------------------");
        return output;
    }
}

class SearchByBarcodeCommand implements Command {
    private int barcode;

    public SearchByBarcodeCommand(int barcode) {
        this.barcode = barcode;
    }

    @Override
    public List<String> execute(List<Product> products) throws Exception {
        List<String> output = new ArrayList<>();
        int index = BinarySearchBarcode(barcode, products);
        output.add("SEARCH RESULTS:");
        if(index >= 0){
            Product product = products.get(index);
            output.add(product.toString());
            
        }else{
            output.add("Item is not found.");
        }

        output.add("------------------------------");
        return output;
    }

    public static int BinarySearchBarcode(int barcode, List<Product> products) {
        Comparator<Product> comparator = Comparator.comparingInt(Product::getBarcode);
        Collections.sort(products, (p1, p2) -> p1.getBarcode() - p2.getBarcode());
        int index = Collections.binarySearch(products, new Product("", "", "", barcode, 0.0), comparator);
        return index;
    }

}

class SearchByNameCommand implements Command {
    private String name;

    public SearchByNameCommand(String name) {
        this.name = name;
    }

    @Override
    public List<String> execute(List<Product> products) {
        List<String> output = new ArrayList<>();
        output.add("SEARCH RESULTS:");
        String productInfo = "Item is not found.";
        for(Product product : products){
            if(product.getName().equals(name)){
                productInfo = product.toString();
                break;
            }
        }
        output.add(productInfo);
        output.add("------------------------------");
        return output;
    }
}

