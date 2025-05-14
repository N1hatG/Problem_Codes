import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;


/**
 * Represents a mineral in the game.
 */
public class Mineral {
    private String name;
    private double price;
    private double weight;
    private Image image;

    /**
     * Constructs a mineral with the specified name, price, weight, and image.
     *
     * @param name   The name of the mineral.
     * @param price  The price of the mineral.
     * @param weight The weight of the mineral.
     * @param image  The image associated with the mineral.
     */
    public Mineral(String name, double price, double weight, Image image) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.image = image;
    }


    /**
     * Returns a list of predefined minerals.
     *
     * @return A list of predefined minerals.
     */
    public static List<Mineral> getMinerals() {
        List<Mineral> minerals = new ArrayList<>();

        // Add each mineral manually with its name, worth, weight, and image
        minerals.add(new Mineral("ironium", 30, 10, new Image("assets/underground/valuable_ironium.png")));
        minerals.add(new Mineral("bronzium", 60, 10, new Image("assets/underground/valuable_bronzium.png")));
        minerals.add(new Mineral("silverium", 100, 10, new Image("assets/underground/valuable_silverium.png")));
        minerals.add(new Mineral("goldium", 250, 20, new Image("assets/underground/valuable_goldium.png")));
        minerals.add(new Mineral("platinum", 750, 30, new Image("assets/underground/valuable_platinum.png")));
        minerals.add(new Mineral("einsteinium", 2000, 40, new Image("assets/underground/valuable_einsteinium.png")));
        minerals.add(new Mineral("emerald", 5000, 60, new Image("assets/underground/valuable_emerald.png")));
        minerals.add(new Mineral("ruby", 20000, 80, new Image("assets/underground/valuable_ruby.png")));
        minerals.add(new Mineral("diamond", 100000, 100, new Image("assets/underground/valuable_diamond.png")));
        minerals.add(new Mineral("amazonite", 500000, 120, new Image("assets/underground/valuable_amazonite.png")));

        return minerals;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
