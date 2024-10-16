import javafx.scene.image.Image;

/**
 * Represents a block in the game map.
 */
public class Block {
    private String type; // The type of the block (e.g., soil, top, border, lava, empty)
    private Image[] images; // The array of images associated with the block


    /**
     * Constructs a block with the specified type and images.
     *
     * @param type   The type of the block.
     * @param images The array of images associated with the block.
     */
    public Block(String type, Image[] images){
        this.type = type;
        this.images = images;
    }

    //Getters
    public String getType(){
        return type;
    }
    public Image[] getImages(){
        return images;
    }

    //Setters
    public void setType(String type){
        this.type = type;
    }
    public void setImages(Image[] images){
        this.images = images;
    }
}
