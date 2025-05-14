import java.util.ArrayList;
import java.util.List;

public class GMM {
    private static final byte ROWS = 6;
    private static final byte COLUMNS = 4;
    private static final byte CAPACITY_PER_SLOT = 10;
    
    private Product[][] slots;
    private int[][] number_of_products;
    
    //Getters
    public Product[][] getSlots(){
        return slots;
    }
    public Integer getNumberOfProducts(Integer row, Integer col){
        return number_of_products[row][col];
    }
    public Product getProduct(Integer row, Integer col){
        return slots[row][col];
    }
    //Setters
    public void setNumberOfProducts(Integer number, Integer row, Integer col){
        number_of_products[row][col] = number;
    }
    public void setProduct(Product product, Integer row, Integer col){
        slots[row][col] = product;
    }
    

    //writer
    public void writeToFileGMM(String path, List<String> content, boolean append, boolean newLine){
        Main.writeToFile(path, content, append, newLine);
    }
    public GMM() {
        slots = new Product[ROWS][COLUMNS];
        number_of_products = new int[ROWS][COLUMNS]; // Initialize number_of_products
    }

    public Integer fill(Product product, String Output) {
        boolean filled = false;
        List<String> outputs = new ArrayList<>();
        outerloop:
        for (byte row = 0; row < ROWS; row++) {
            for (byte col = 0; col < COLUMNS; col++) {
                if (slots[row][col] == null) {
                    slots[row][col] = product;
                    filled = true;
                    number_of_products[row][col] = 1;
                    break outerloop;
                } else if (slots[row][col].equals(product)) {
                    if (number_of_products[row][col] < CAPACITY_PER_SLOT) {
                        slots[row][col] = product;
                        number_of_products[row][col] += 1;
                        filled = true;
                        break outerloop;
                    }
                }
            }
        }
        if (!filled) {
            outputs.add("INFO: There is no available place to put " + product.getName());
            if(machineFullyFull() == -1)
                outputs.add("INFO: The machine is full!");
                writeToFileGMM(Output, outputs, true, true);
            return -1;
        }
        return 0;
    }

    public Integer machineFullyFull(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 4; col++){
                if(slots[row][col] == null || number_of_products[row][col] != 10)
                    return 0;
            }
        }
        return -1;
    }

    public List<String> printVendingMachine() {
        List<String> output = new ArrayList<>();
        output.add("-----Gym Meal Machine-----");
        for (byte row = 0; row < ROWS; row++) {
            StringBuilder rowContent = new StringBuilder();
            for (byte col = 0; col < COLUMNS; col++) {
                Product product = slots[row][col];
                if (product != null) {
                    double calorie = product.getCalorie();
                    rowContent.append(product.getName())
                            .append("(").append((int) calorie).append(", ")
                            .append(number_of_products[row][col]).append(")").append("___");
                } else {
                    rowContent.append("___(0, 0)___");
                }
            }
            output.add(rowContent.toString());
        }
        output.add("----------");
        return output;
    }

}
