import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class Main {

    public static List<Purchase> parsePurchases(List<String> lines, String Output){
        List<Purchase> purchases = new ArrayList<>();
        List<String> outputs = new ArrayList<>();
        for(String line : lines){
            String[] parts = line.split("\t");
            if (parts.length == 4){
                String type = parts[0];
                String[] moneys = parts[1].split("\\s+");
                String wish = parts[2];
                int value = Integer.parseInt(parts[3]);
                int money = 0;
                for(String m : moneys){
                    money += Integer.parseInt(m);
                }
                Purchase purchase = new Purchase(type, money, wish, value, line);
                purchases.add(purchase);
            }
            else{
                outputs.add("Invalid line format: " + line);
            }
        }
        writeToFile(Output, outputs, true, true);
        return purchases;
    }

    public static List<Product> parseProducts(List<String> lines, String Output) {
        List<Product> products = new ArrayList<>();
        List<String> outputs = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\t");
            if (parts.length == 3) { // Ensure correct format
                String name = parts[0];
                String[] nutrients = parts[2].split(" "); // Split the nutrient field
                if (nutrients.length == 3) { // Ensure correct nutrient values
                    try {
                        double price = Double.parseDouble(parts[1]);
                        double protein = Double.parseDouble(nutrients[0]);
                        double carbohydrate = Double.parseDouble(nutrients[1]);
                        double fat = Double.parseDouble(nutrients[2]);
                        double calorie = Math.round(4 * protein + 4 * carbohydrate + 9 * fat);
                        Product product = new Product(name, price, protein, carbohydrate, fat, calorie);
                        products.add(product);
                    } catch (NumberFormatException e) {
                        outputs.add("Invalid nutrient format: " + line);
                    }
                } else {
                    outputs.add("Invalid nutrient format: " + line);
                }
            } else {
                outputs.add("Invalid line format: " + line);
            }
        }
        writeToFile(Output, outputs, true, true);
        return products;
    }

    public static List<String> readFile(String path, boolean discardEmptyLines, boolean trim) {
        /**
         * Reads the file at the given path and returns contents of it in a list of strings.
         *
         * @param path              Path to the file that is going to be read.
         * @param discardEmptyLines If true, discards empty lines; else, it takes all the lines from the file.
         * @param trim              Trim status; if true, trims each line; else, it leaves each line as-is.
         * @return Contents of the file as a list of strings, returns null if there is no such a file or this program does not have sufficient permissions to read that file.
         */
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!discardEmptyLines || !line.trim().isEmpty()) {
                    lines.add(trim ? line.trim() : line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return lines;
    }

    public static void writeToFile(String path, List<String> content, boolean append, boolean newLine) {
        /**
         * This method writes given content to file at given path.
         *
         * @param path    Path for the file content is going to be written.
         * @param content Content that is going to be written to file.
         * @param append  Append status, true if wanted to append to file if it exists, false if wanted to create file from zero.
         * @param newLine True if wanted to append a new line after content, false if vice versa.
         */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, append))) {
            int size = content.size();
            for (int i = 0; i < size; i++) {
                String line = content.get(i);
                if (!line.isEmpty() || i < size - 1) { // Check if line is not empty or it's not the last line
                    writer.write(line);
                    if (newLine || i < size - 1) { // Append newline if specified or it's not the last line
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer processor(Purchase purchase, List<Product> products,GMM vendingMachine,  List<String> Input_purchase, String Output){
        List<String> outputs = new ArrayList<>();
        Product[][] slots = vendingMachine.getSlots();

        int val, money;
        String output;
        Product product;
        boolean error = false;
        double price;
        int count = 0;
        output = "INPUT: " + purchase.getline();
        outputs.add(output);
        int number_of_products;
        boolean done;

        if(purchase.getWish().equals("PROTEIN")){
            done = false;
            for (int row = 0; row < 6; row++){
                for (int col = 0; col < 4; col++){
                    number_of_products = vendingMachine.getNumberOfProducts(row, col);
                    val = purchase.getValue();
                    money = purchase.getCash();
                    if(slots[row][col] != null){
                        product = slots[row][col];
                        price = product.getPrice();
                    }
                    else{
                        continue;
                    }
                    if(val + 5 >= product.getProtein() && val - 5 <= product.getProtein()){
                        //Purchase
                        if(money>= price){
                            output = "PURCHASE: You have bought one " + product.getName() + "\nRETURN: Returning your change: " + ((int) (money - price)) + " TL";
                            outputs.add(output);
                            vendingMachine.setNumberOfProducts(number_of_products - 1, row, col);
                            int number_of_products_new = vendingMachine.getNumberOfProducts(row, col);
                            if(number_of_products_new == 0){
                                vendingMachine.setProduct(null, row, col);
                            }
                            done = true;
                            break;
                        }
                        //Insufficent money
                        else{
                            output = "INFO: Insufficient money, try again with more money." + "\nRETURN: Returning your change: " + (int) money + " TL";
                            outputs.add(output);
                            done = true;
                            error = true;
                            break;
                        }
                    }
                }
                if(done)
                break;
            }   
            if(!done){
                money = purchase.getCash();
                output = "INFO: Product not found, your money will be returned." + "\nRETURN: Returning your change: " + (int) money + " TL";
                outputs.add(output);
                error = true;
            }
        }

        else if(purchase.getWish().equals("CARB")){
            done = false;
            for(int row = 0; row <6 ; row++){
                for(int col = 0; col < 4; col ++){
                    number_of_products = vendingMachine.getNumberOfProducts(row, col);
                    val = purchase.getValue();
                    money = purchase.getCash();
                    if(slots[row][col] != null){
                        product = slots[row][col];
                        price = product.getPrice();
                    }
                    else{
                        continue;
                    }
                    if(val + 5 >= product.getCarbohydrate() && val - 5 <= product.getCarbohydrate()){
                        //purchase
                        if(money >= price){
                            output = "PURCHASE: You have bought one " + product.getName() + "\nRETURN: Returning your change: " + ((int) (money - price)) + " TL";
                            outputs.add(output);
                            vendingMachine.setNumberOfProducts(number_of_products - 1, row, col);
                            int number_of_products_new = vendingMachine.getNumberOfProducts(row, col);
                            if(number_of_products_new == 0){
                                vendingMachine.setProduct(null, row, col);
                            }
                            done = true;
                            break;
                        }
                        //Insufficent money
                        else{
                            output = "INFO: Insufficient money, try again with more money." + "\nRETURN: Returning your change: " + (int) money + " TL";
                            outputs.add(output);
                            done = true;
                            error = true;
                            break;
                        }
                    }
                
                }
                if(done)
                    break;
            }
            if(!done){
                money = purchase.getCash();
                output = "INFO: Product not found, your money will be returned." + "\nRETURN: Returning your change: " + (int) money + " TL";
                outputs.add(output);
                error = true;
            }
        }
        
        else if(purchase.getWish().equals("FAT")){
            done = false;
            for(int row = 0; row < 6; row ++){
                for(int col = 0; col < 4; col++){
                    number_of_products = vendingMachine.getNumberOfProducts(row, col);
                    val = purchase.getValue();
                    money = purchase.getCash();
                    if(slots[row][col] != null){
                        product = slots[row][col];
                        price = product.getPrice();
                    }
                    else{
                        continue;
                    }
                    if(val + 5 >= product.getFat() && val - 5 <= product.getFat()){
                        //purchase
                        if(money >= price){
                            output = "PURCHASE: You have bought one " + product.getName() + "\nRETURN: Returning your change: " + (int) (money - price) + " TL";
                            outputs.add(output);
                            vendingMachine.setNumberOfProducts(number_of_products - 1, row, col);
                            int number_of_products_new = vendingMachine.getNumberOfProducts(row, col);
                            if(number_of_products_new == 0){
                                vendingMachine.setProduct(null, row, col);
                            }
                            done = true;
                            break;
                        }
                        //Insufficent money
                        else{
                            output = "INFO: Insufficient money, try again with more money." + "\nRETURN: Returning your change: " + (int) money + " TL";
                            outputs.add(output);
                            done = true;
                            error = true;
                            break;
                        }
                    }
                }
                if(done)
                    break;
            }
            if(!done){
                money = purchase.getCash();
                output = "INFO: Product not found, your money will be returned." + "\nRETURN: Returning your change: " + (int) money + " TL";
                outputs.add(output);
                error = true;
            }
            
        }
        
        else if(purchase.getWish().equals("CALORIE")){
            done = false;
            for(int row = 0; row < 6; row ++){
                for(int col = 0; col < 4; col++){
                    number_of_products = vendingMachine.getNumberOfProducts(row, col);
                    val = purchase.getValue();
                    money = purchase.getCash();
                    if(slots[row][col] != null){
                        product = slots[row][col];
                        price = product.getPrice();
                    }
                    else{
                        continue;
                    }
                    if(val + 5 >= product.getCalorie() && val - 5 <= product.getCalorie()){
                        //purchase
                        if(money >= price){
                            output = "PURCHASE: You have bought one " + product.getName() + "\nRETURN: Returning your change: " + (int) (money - price) + " TL";
                            outputs.add(output);
                            vendingMachine.setNumberOfProducts(number_of_products - 1, row, col);
                            done = true;
                            int number_of_products_new = vendingMachine.getNumberOfProducts(row, col);
                            if(number_of_products_new == 0){
                                vendingMachine.setProduct(null, row, col);
                            }
                            break;
                        }
                        //Insufficent money
                        else{
                            output = "INFO: Insufficient money, try again with more money." + "\nRETURN: Returning your change: " + (int) money + " TL";
                            outputs.add(output);
                            done = true;
                            error = true;
                            break;
                        }
                    }
                
                }
                if(done)
                    break;
            }
            if(!done){
                money = purchase.getCash();
                output = "INFO: Product not found, your money will be returned." + "\nRETURN: Returning your change: " + (int) money + " TL";
                outputs.add(output);
                error = true;
            }
            
        }
        
        else if(purchase.getWish().equals("NUMBER")){
            val = purchase.getValue();
            int row = val/4;
            int col = val%4;
            int i = row;
            int j = col;
            money = purchase.getCash();
            if(val <= 23 && val >=0){
                number_of_products = vendingMachine.getNumberOfProducts(row, col);
                if(slots[row][col] != null){
                    product = slots[row][col];
                    price = product.getPrice();
                    
                if(number_of_products != 0){
                    if(money >= price){
                        output = "PURCHASE: You have bought one " + product.getName() + "\nRETURN: Returning your change: " + (int) (money - price) + " TL";
                        outputs.add(output);
                        vendingMachine.setNumberOfProducts(number_of_products - 1, i, j);
                        int number_of_products_new = vendingMachine.getNumberOfProducts(row, col);
                        if(number_of_products_new == 0){
                            vendingMachine.setProduct(null, row, col);
                        }
                    }
                    else{
                        output = "INFO: Insufficient money, try again with more money." + "\nRETURN: Returning your change: " + (int) money + " TL";
                        outputs.add(output);
                        error = true;
                    }
                }
                else{
                    output = "INFO: This slot is empty, your money will be returned." + "\nRETURN: Returning your change: " + (int) money + " TL";
                    outputs.add(output);
                    error = true;
                }

                }
                else{
                    outputs.add("INFO: This slot is empty, your money will be returned." + "\nRETURN: Returning your change: " + (int) money + " TL");
                    error = true;
                }
            }
            else{
                output = "INFO: Number cannot be accepted. Please try again with another number." + "\nRETURN: Returning your change: " + (int) money + " TL";
                outputs.add(output);
                error = true;
            }
        }

        writeToFile(Output, outputs, true, true);
        if(error)
            return -1;
        return 0;
    }
    
    private static void initializeVendingMachine(GMM vendingMachine, List<Product> products, String outputFilePath) {
        List<String> outputs = new ArrayList<>();
        for (Product product : products) {
            if (vendingMachine.fill(product, outputFilePath) == -1) {
                if (vendingMachine.machineFullyFull() == -1) {
                    break;
                }
            }
        }
        List<String> machine = vendingMachine.printVendingMachine();
        for (String slot : machine)
            outputs.add(slot);
        writeToFile(outputFilePath, outputs, true, true);
    }


    /**
 * Parses product and purchase data from input files, initializes a vending machine,
 * processes purchases, and writes the updated vending machine state to an output file.
 *
 * @param PathToProduct  Path to the file containing product data.
 * @param PathToPurchase Path to the file containing purchase data.
 * @param PathToOutput   Path to the output file where the updated vending machine state will be written.
 */
public static void ParserAndInitializer(String PathToProduct, String PathToPurchase, String PathToOutput) {
    // Initialize a new vending machine
    GMM vendingMachine = new GMM();

    // Read product and purchase data from files
    List<String> Input_purchase = readFile(PathToPurchase, true, true);
    List<String> Input_product = readFile(PathToProduct, true, false);

    // Parse product and purchase data
    List<Product> products = parseProducts(Input_product, PathToOutput);
    List<Purchase> purchases = parsePurchases(Input_purchase, PathToOutput);

    // Initialize outputs list
    List<String> outputs = new ArrayList<>();

    // Fill vending machine with products
    for (Product product : products) {
        if (vendingMachine.fill(product, PathToOutput) == -1) {
            if (vendingMachine.machineFullyFull() == -1) {
                break;
            }
        }
    }

    // Print vending machine state before processing purchases
    List<String> machine = vendingMachine.printVendingMachine();
    for (String slot : machine) {
        outputs.add(slot);
    }
    // Write vending machine state to output file
    writeToFile(PathToOutput, outputs, true, true);

    // Process purchases and update vending machine
    for (Purchase purchase : purchases) {
        processor(purchase, products, vendingMachine, Input_purchase, PathToOutput);
    }

    // Print vending machine state after processing purchases
    machine = vendingMachine.printVendingMachine();
    outputs = new ArrayList<>();
    for (String slot : machine) {
        outputs.add(slot);
    }
    // Write final vending machine state to output file
    writeToFile(PathToOutput, outputs, true, true);

    // To reduce memory consumption, set Input_purchase to null
    Input_purchase = null;
}

    public static void main(String[] args) {
        
        String PathToProduct = args[0];
        String PathToPurchase = args [1];
        String PathToOutput = args[2];

        ParserAndInitializer(PathToProduct, PathToPurchase, PathToOutput);

    }
}