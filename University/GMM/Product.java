import java.util.Objects;

public class Product {

    private String name;
    private double price;
    private double fat;
    private double carbohydrate;
    private double protein;
    private double calorie;

    public Product(String name, double price, double protein, double carbohydrate, double fat, double calorie) {
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.calorie = calorie;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public Double getFat() {
        return fat;
    }

    public Double getCalorie() {
        return calorie;
    }
    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCalorie(double protein, double carbohydrate, double fat) {
        this.calorie = Math.round(4 * protein + 4 * carbohydrate + 9 * fat);
    }

    @Override
    public String toString() {
        return name + '\t'
                + price + "\t"
                + protein + " "
                + carbohydrate + " "
                + fat + " "
                + calorie;
    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return Double.compare(product.price, price) == 0 &&
                Double.compare(product.fat, fat) == 0 &&
                Double.compare(product.carbohydrate, carbohydrate) == 0 &&
                Double.compare(product.protein, protein) == 0 &&
                name.equals(product.name);
    }

   
    public int hashCode() {
        return Objects.hash(name, price, fat, carbohydrate, protein);
    }
}