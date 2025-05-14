import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.util.Duration;
import java.util.List;
import java.util.Random;

/**
 * The main class for the game application.
 * This class handles the initialization of the game, including setting up the map, character,
 * and event handling for character movement. It also manages fuel consumption, money collection,
 * and weight tracking.
 */
public class Main extends Application {

    private int red = 128;
    private int green = 64;
    private int blue = 0;

    // Create a custom color for rotted iron
    private Color rottedIron = Color.rgb(red, green, blue);
    private static final int CELL_SIZE = 50;
    private static final int GRID_WIDTH = 850 / CELL_SIZE;
    private static final int GRID_HEIGHT = 750 / CELL_SIZE;
    private int totalMoneyCollected = 0;
    private int totalWeight = 0;
    private double fuel = 10000;
    private Text moneyText = new Text("Money : " + totalMoneyCollected);
    private Text weightText = new Text("Haul : " + totalWeight);
    private Text fuelText = new Text("Fuel : " + fuel);
    private Object[][] grid = createMap();
    private GridPane root = new GridPane();
    private Timeline fuelTimeline;
    private Scene scene = new Scene(root, 850, 750, Color.DEEPSKYBLUE);

    /**
     * Initializes and starts the game application.
     *
     * @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        // Display the map
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                ImageView imageView;
                if (grid[i][j] != null && grid[i][j] instanceof Block) {
                    Block block = (Block) grid[i][j];
                    imageView = createRandomImageView(block.getImages());
                } else if (grid[i][j] != null && grid[i][j] instanceof Mineral) {
                    Mineral mineral = (Mineral) grid[i][j];
                    imageView = new ImageView(mineral.getImage());
                } else {
                    continue;
                }
                imageView.setFitWidth(CELL_SIZE);
                imageView.setFitHeight(CELL_SIZE);
                root.add(imageView, j, i);
            }
        }

        // Add the character to the root Pane
        Character character = new Character(GRID_WIDTH, GRID_HEIGHT, CELL_SIZE);
        Pane characterPane = new Pane(character);
        root.getChildren().add(characterPane);

        Pane overlayPane = new Pane();
        
        // Set the font properties
        Font font = Font.font("Arial", FontWeight.BOLD, 12); // Adjust the font family, weight, and size as needed
        moneyText.setFont(font);
        weightText.setFont(font);
        fuelText.setFont(font);

        // Set the text color
        Color textColor = Color.PURPLE; // Adjust the color as needed
        moneyText.setFill(textColor);
        weightText.setFill(textColor);
        fuelText.setFill(textColor);

        // Set the position of text nodes
        moneyText.setX(10); // Adjust the X position as needed
        moneyText.setY(20); // Adjust the Y position as needed
        weightText.setX(10); // Adjust the X position as needed
        weightText.setY(40); // Adjust the Y position as needed
        fuelText.setX(10);
        fuelText.setY(60);

        // Add text nodes to the overlay pane
        overlayPane.getChildren().addAll(moneyText, weightText, fuelText);
        
        // Add overlay pane to the root pane
        root.getChildren().add(overlayPane);

        fuelTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> decreaseFuel(primaryStage))
        );

        fuelTimeline.setCycleCount(Timeline.INDEFINITE);
        fuelTimeline.play();

        Timeline gravityTimeline = new Timeline(
            new KeyFrame(Duration.seconds(0.3), event -> {
                if(!isDrillable(character.GetY()+1, character.GetX())) {
                    character.moveDown(grid);
                }
            })
        );

        gravityTimeline.setCycleCount(Timeline.INDEFINITE);
        // Set up event handling for character movement
        scene.setOnKeyPressed(event -> {
            gravityTimeline.stop();
            switch (event.getCode()) {
                case W:    
                    character.moveUp(grid);
                    fuel -= 100;
                    break;
                case S:
                    if(isDrillable(character.GetY()+1, character.GetX()))
                        drill(character.GetX(), character.GetY()+1, primaryStage);
                    character.moveDown(grid);
                    fuel -= 100;
                    break;
                case A:
                    if(isDrillable(character.GetY(), character.GetX()-1))
                        drill(character.GetX()-1, character.GetY(), primaryStage);
                    character.moveLeft(grid);
                    fuel -= 100;
                    break;
                case D:
                    if(isDrillable(character.GetY(), character.GetX()+1))
                        drill(character.GetX()+1, character.GetY(), primaryStage);
                    character.moveRight(grid);
                    fuel -= 100;
                    break;
                default:
                    
                    break;
            }
        });
        scene.setOnKeyReleased(event -> {
            gravityTimeline.play();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Creates the initial map layout for the game.
     *
     * @return a 2D array representing the game map
     */
    private Object[][] createMap() {
        Object[][] grid = new Object[GRID_HEIGHT][GRID_WIDTH];

        Image[] soilImages = {
                new Image("assets/underground/soil_01.png"),
                new Image("assets/underground/soil_02.png"),
                new Image("assets/underground/soil_03.png"),
                new Image("assets/underground/soil_04.png"),
                new Image("assets/underground/soil_05.png")
        };

        Image[] topSoilImages = {
                new Image("assets/underground/top_01.png"),
                new Image("assets/underground/top_02.png")
        };

        Image[] obstacleImages = {
                new Image("assets/underground/obstacle_01.png"),
                new Image("assets/underground/obstacle_02.png"),
                new Image("assets/underground/obstacle_03.png")
        };

        Image[] lavaImages = {
                new Image("assets/underground/lava_01.png"),
                new Image("assets/underground/lava_02.png"),
                new Image("assets/underground/lava_03.png")
        };

        // Create a Rectangle with DEEPSKYBLUE color and dimensions of 50x50 pixels
        Rectangle rectangle = new Rectangle(50, 50, Color.DEEPSKYBLUE);
        // Convert the Rectangle to an ImageView
        ImageView imageViewTemp = new ImageView(rectangle.snapshot(null, null));
        // Create skyImages array and assign the ImageView to its first element
        Image[] skyImages = new Image[1];
        skyImages[0] = imageViewTemp.getImage();

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (i < 2) {
                    // Create sky blocks
                    Block sky = new Block("sky", skyImages);
                    grid[i][j] = sky;
                } else if (i == 2) {
                    // Create top soil blocks
                    Block topSoil = new Block("top", topSoilImages);
                    grid[i][j] = topSoil;
                } else if (i == GRID_HEIGHT - 1 || j == 0 || j == GRID_WIDTH - 1) {
                    // Create border blocks
                    Block border = new Block("border", obstacleImages);
                    grid[i][j] = border;
                } else {
                    // Create soil blocks
                    Block soil = new Block("soil", soilImages);
                    grid[i][j] = soil;
                }
            }
        }

        List<Mineral> minerals = Mineral.getMinerals();
        // Randomly assign minerals and lava
        grid = randomlyAssignMinerals(grid, minerals, lavaImages);

        return grid;
    }


    /**
     * Randomly assigns minerals and lava to the soil blocks on the map.
     *
     * @param grid       the current game map
     * @param minerals   a list of available minerals
     * @param lavaImages an array of lava images
     * @return the updated game map with minerals and lava assigned
     */
    private Object[][] randomlyAssignMinerals(Object[][] grid, List<Mineral> minerals, Image[] lavaImages) {
        Random random = new Random();
        int soilCount = 0;
        int mineralCount = 0;
        int lavaCount = 0;
        // Count the number of soil blocks
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null && grid[i][j] instanceof Block && ((Block) grid[i][j]).getType().equals("soil")) {
                    soilCount++;
                }
            }
        }
    
        // Randomly assign minerals to soil blocks
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null && grid[i][j] instanceof Block && ((Block) grid[i][j]).getType().equals("soil")) {
                    if (random.nextDouble() < 0.15 && mineralCount < soilCount / 4) { // Adjust the probability as needed
                        // Randomly select a mineral
                        Mineral mineral = minerals.get(random.nextInt(minerals.size()));
                        grid[i][j] = mineral;
                        mineralCount++;
                    }
                }
                if (grid[i][j] != null && grid[i][j] instanceof Block && ((Block) grid[i][j]).getType().equals("soil")){
                    if(random.nextDouble() < 0.1 && lavaCount < (soilCount - mineralCount)/3 && mineralCount < soilCount/4){
                        Block lava = new Block("lava", lavaImages);
                        grid[i][j] = lava;
                        lavaCount++;
                    }
                }
            }
        }
        return grid;
    }

    /**
     * Creates a random ImageView with a barrier image.
     *
     * @param images an array of barrier images
     * @return a randomly selected ImageView with a barrier image
     */
    private ImageView createRandomImageView(Image[] images) {
        Random random = new Random();
        int index = random.nextInt(images.length);
        ImageView imageView = new ImageView(images[index]);
        return imageView;
    }


    /**
     * Retrieves the ImageView at the specified grid position.
     *
     * @param x The x-coordinate of the grid position.
     * @param y The y-coordinate of the grid position.
     * @return The ImageView at the specified position, or null if not found.
     */
    private ImageView getImageViewAt(int x, int y) {
        for (Node node : root.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y && node instanceof ImageView) {
                return (ImageView) node;
            }
        }
        return null;
    }

    /**
     * Checks if a block at the specified position is drillable.
     *
     * @param y The y-coordinate of the block.
     * @param x The x-coordinate of the block.
     * @return True if the block is drillable, false otherwise.
     */
    public boolean isDrillable(int y, int x){
        if(grid[y][x] instanceof Block){
            Block block = (Block) grid[y][x];
            if(block.getType().equals("empty"))
                return false;
        }
        return true;
    }
    
    /**
     * Drills a block at the specified position.
     *
     * @param x            The x-coordinate of the block.
     * @param y            The y-coordinate of the block.
     * @param primaryStage The primary stage for the JavaFX application.
     */
    private void drill(int x, int y, Stage primaryStage) {
        // Remove the existing ImageView at the specified position
        ImageView imageView = getImageViewAt(x, y);
        if(grid[y][x] instanceof Block){
            Block block = (Block) grid[y][x];
            if(block.getType().equals("lava")){
                Pane endGamePane = endGameScreen(Color.RED, false);
                endGamePane.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight());
                primaryStage.setScene(new Scene(endGamePane));
            }
            if (imageView != null && !block.getType().equals("empty") && !block.getType().equals("sky") && !block.getType().equals("border")) {
                root.getChildren().remove(imageView);
                
                // Create a brown rectangle
                Rectangle brownRectangle = new Rectangle(CELL_SIZE, CELL_SIZE, rottedIron);
                brownRectangle.setOpacity(0.5); // Adjust opacity as needed
                brownRectangle.setBlendMode(BlendMode.SRC_OVER); // Ensure that the rectangle doesn't affect the character's color
                root.add(brownRectangle, x, y);
                block.setType("empty");
            }
        }
        else if(grid[y][x] instanceof Mineral){
            Mineral mineral = (Mineral) grid[y][x];
            if(imageView != null){
                grid[y][x] = new Block("empty", null);
                root.getChildren().remove(imageView);
                totalMoneyCollected += mineral.getPrice();
                totalWeight += mineral.getWeight();
                // Create a brown rectangle
                Rectangle brownRectangle = new Rectangle(CELL_SIZE, CELL_SIZE, rottedIron);
                brownRectangle.setOpacity(0.5); // Adjust opacity as needed
                brownRectangle.setBlendMode(BlendMode.SRC_OVER); // Ensure that the rectangle doesn't affect the character's color
                root.add(brownRectangle, x, y);
                Texter();
            }
        }
    }


    /**
     * Decreases the fuel level and updates the fuel text.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    private void decreaseFuel(Stage primaryStage) {
        fuel -= 3.2;
        fuelText.setText("Fuel : " + String.format("%.2f", fuel));
        if (fuel <= 0) {
            Pane endGamePane = endGameScreen(Color.GREEN, true);
            endGamePane.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.setScene(new Scene(endGamePane));
            fuelTimeline.stop();
        }
    }

    /**
     * Updates the money and weight text.
    */
    private void Texter() {
        moneyText.setText("Total Money Collected: " + totalMoneyCollected);
        weightText.setText("Total Weight: " + totalWeight);
    }

    /**
     * Creates the end game screen with the specified background color.
     *
     * @param backgroundColor The background color of the end game screen.
     * @param includeMoney    Indicates whether to include money collected in the end game screen.
     * @return A Pane representing the end game screen.
     */
    private Pane endGameScreen(Color backgroundColor, boolean includeMoney) {
        Pane endGamePane = new Pane();
        endGamePane.setPrefSize(1000, 1000);
        endGamePane.setStyle("-fx-background-color: " + toHex(backgroundColor) + ";");
    
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gameOverText.setFill(Color.WHITE);
        gameOverText.setLayoutX(300);
        gameOverText.setLayoutY(350);
        if(includeMoney){
            Text totalMoneyText = new Text("Money Collected : " + totalMoneyCollected);
            totalMoneyText.setFont(Font.font("Arial", FontWeight.BOLD, 35));
            totalMoneyText.setFill(Color.WHITE);
            totalMoneyText.setLayoutX(270);
            totalMoneyText.setLayoutY(400);
            endGamePane.getChildren().addAll(gameOverText, totalMoneyText);
        }else{
            endGamePane.getChildren().add(gameOverText);
        }
        return endGamePane;
    }

    /**
     * Converts a Color object to its hexadecimal representation.
     *
     * @param color The Color object to convert.
     * @return The hexadecimal representation of the color.
     */
    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}
