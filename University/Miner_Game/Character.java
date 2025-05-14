import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents the character in the game.
 */
public class Character extends ImageView{
    private int x;                  // The x-coordinate of the character
    private int y;                  // The y-coordinate of the character
    private final int GRID_WIDTH;   // The width of the grid
    private final int GRID_HEIGHT;  // The height of the grid
    private final int CELL_SIZE;    // The size of each cell in the grid


    /**
     * Constructs a character with the specified grid width, grid height, and cell size.
     *
     * @param GRID_WIDTH  The width of the grid.
     * @param GRID_HEIGHT The height of the grid.
     * @param CELL_SIZE   The size of each cell in the grid.
     */
    public Character(int GRID_WIDTH, int GRID_HEIGHT, int CELL_SIZE) {
        super(new Image("assets/drill/drill_01.png"));
        this.GRID_WIDTH = GRID_WIDTH;
        this.GRID_HEIGHT = GRID_HEIGHT;
        this.CELL_SIZE = CELL_SIZE;
        setFitWidth(CELL_SIZE);
        setFitHeight(CELL_SIZE);
        x = 2;
        y = 1;
        // Call updatePosition() after initializing x and y
        updatePosition();
    }
    public int GetX(){
        return x;
    }
    public int GetY(){
        return y;
    }

    /**
     * Moves the character up if possible.
     *
     * @param grid The grid representing the game map.
     * @return True if the character successfully moves up, otherwise false.
     */
    public boolean moveUp(Object[][] grid) {
        if (y > 0) {
            setImage(new Image("assets/drill/drill_25.png"));
            if(grid[y-1][x] instanceof Block){
                Block block = (Block) grid[y - 1][x]; // Get the block below the character
                if (block.getType().equals("empty") || block.getType().equals("sky")) { // Check if it's not a border block
                    y--;
                    updatePosition();
                    return true;
                }
            }
        }
        return false;
            
    }
    
    /**
     * Moves the character down if possible.
     *
     * @param grid The grid representing the game map.
     * @return True if the character successfully moves down, otherwise false.
     */
    public boolean moveDown(Object[][] grid) {
        if (y < GRID_HEIGHT - 1) {
            setImage(new Image("assets/drill/drill_40.png"));
            if(grid[y+1][x] instanceof Block){
                Block block = (Block) grid[y + 1][x]; // Get the block below the character
                if (block.getType().equals("sky") || block.getType().equals("empty")) { // Check if it's not a border block
                    y++;
                    updatePosition();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Moves the character left if possible.
     *
     * @param grid The grid representing the game map.
     * @return True if the character successfully moves left, otherwise false.
     */
    public boolean moveLeft(Object[][] grid) {
        if (x>0) {
            setImage(new Image("assets/drill/drill_01.png"));
            if(grid[y][x-1] instanceof Block){
                Block block = (Block) grid[y][x-1]; // Get the block below the character
                if (block.getType().equals("sky") || block.getType().equals("empty")){
                    x--;
                    updatePosition();
                    return true;
                }
            }
            else{
                x--;
                updatePosition();
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the character right if possible.
     *
     * @param grid The grid representing the game map.
     * @return True if the character successfully moves right, otherwise false.
     */
    public boolean moveRight(Object[][] grid) {
        if (x < GRID_WIDTH - 1) {
            setImage(new Image("assets/drill/drill_55.png"));
            if(grid[y][x+1] instanceof Block){
                Block block = (Block) grid[y][x+1]; // Get the block below the character
                if (block.getType().equals("sky") || block.getType().equals("empty")){
                    x++;
                    updatePosition();
                    return true;
                }
            }
            else{
                x++;
                updatePosition();
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the position of the character on the screen.
     */
    private void updatePosition() {
        setLayoutX(x * CELL_SIZE);
        setLayoutY(y * CELL_SIZE);
    }


    /**
     * Checks if the cell is movable.
     *
     * @param grid The grid representing the game map.
     * @param y    The y-coordinate of the cell.
     * @param x    The x-coordinate of the cell.
     * @return True if the cell is movable, otherwise false.
     */
    public boolean isCellMovable(Object[][] grid, int y, int x){
        Block block = (Block) grid[y-1][x];
        return block.getType().equals("empty") || block.getType().equals("sky");
    }

}