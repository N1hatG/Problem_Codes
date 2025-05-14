import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a game element.
 */
abstract class GameElement {
    /**
     * Abstract method to display the game element.
     *
     * @return String representation of the game element.
     */
    public abstract String display();

    /**
     * Method to determine the winner among the players.
     *
     * @param players List of players in the game.
     * @return Player object representing the winner.
     */
    public Player winner(List<Player> players) {
        Player winner = null;
        for (Player player : players) {
            if (player.getInGame()) {
                winner = player;
                break;
            }
        }
        return winner;
    }
}

/**
 * Class representing a dice in the game.
 */
class Dice extends GameElement {
    private int firstDice;
    private int secondDice;

    /**
     * Constructor for Dice class.
     *
     * @param firstDice  Value of the first dice.
     * @param secondDice Value of the second dice.
     */
    public Dice(int firstDice, int secondDice) {
        this.firstDice = firstDice;
        this.secondDice = secondDice;
    }

    /**
     * Get the value of the first dice.
     *
     * @return Value of the first dice.
     */
    public Integer getFirstDice() {
        return firstDice;
    }

    /**
     * Get the value of the second dice.
     *
     * @return Value of the second dice.
     */
    public Integer getSecondDice() {
        return secondDice;
    }

    /**
     * Display the dice values.
     *
     * @return String representation of the dice values.
     */
    @Override
    public String display() {
        return firstDice + "-" + secondDice;
    }
}

/**
 * Class representing a player in the game.
 */
class Player extends GameElement {
    private int score;
    private String name;
    private boolean inGame;

    /**
     * Constructor for Player class.
     *
     * @param name   Name of the player.
     * @param score  Score of the player.
     * @param inGame Flag indicating whether the player is in the game or not.
     */
    public Player(String name, int score, boolean inGame) {
        this.score = score;
        this.name = name;
        this.inGame = inGame;
    }

    /**
     * Get the score of the player.
     *
     * @return Score of the player.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Get the name of the player.
     *
     * @return Name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Check if the player is in the game.
     *
     * @return True if the player is in the game, false otherwise.
     */
    public boolean getInGame() {
        return inGame;
    }

    /**
     * Set the in-game status of the player.
     *
     * @param inGame In-game status of the player.
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Set the score of the player.
     *
     * @param score Score of the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set the name of the player.
     *
     * @param name Name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Display the player's name and score.
     *
     * @return String representation of the player's name and score.
     */
    @Override
    public String display() {
        return name + " (" + score + ")";
    }

    /**
     * Method to determine the winner among the players.
     *
     * @param players List of players in the game.
     * @return Player object representing the winner.
     */
    @Override
    public Player winner(List<Player> players) {
        return super.winner(players);
    }
}

/**
 * Class representing the dice game.
 */
class DiceGame {

    /**
     * Read contents of a file into a list of strings.
     *
     * @param path              Path to the file that is going to be read.
     * @param discardEmptyLines If true, discards empty lines; else, it takes all the lines from the file.
     * @param trim              Trim status; if true, trims each line; else, it leaves each line as-is.
     * @return Contents of the file as a list of strings, returns null if there is no such a file or this program does not have sufficient permissions to read that file.
     */
    public static List<String> readFile(String path, boolean discardEmptyLines, boolean trim) {
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

    /**
     * Write content to a file at a given path.
     *
     * @param path    Path for the file content is going to be written.
     * @param content Content that is going to be written to file.
     * @param append  Append status, true if wanted to append to file if it exists, false if wanted to create file from zero.
     * @param newLine True if wanted to append a new line after content, false if vice versa.
     */
    public static void writeToFile(String path, List<String> content, boolean append, boolean newLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, append))) {
            for (int i = 0; i < content.size(); i++) {
                String line = content.get(i);
                writer.write(line);
                if (newLine && i < content.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class to parse input data.
     */
    public static class ParseInput {

        private String players_input;
        private List<String> dices_input;

        /**
         * Parse inputs from a list of strings.
         *
         * @param lines List of strings containing the input data.
         */
        public void parseInputs(List<String> lines) {
            players_input = lines.get(1);
            dices_input = new ArrayList<>(lines.subList(2, lines.size()));
        }

        /**
         * Parse dice data.
         *
         * @return List of Dice objects parsed from the input.
         */
        public List<Dice> parseDices() {
            List<Dice> dices = new ArrayList<>();
            for (String diceString : dices_input) {
                String[] values = diceString.split("-");
                int first_dice = Integer.parseInt(values[0]);
                int second_dice = Integer.parseInt(values[1]);
                Dice dice = new Dice(first_dice, second_dice);
                dices.add(dice);
            }
            return dices;
        }

        /**
         * Parse player data.
         *
         * @return List of Player objects parsed from the input.
         */
        public List<Player> parsePlayers() {
            List<Player> players = new ArrayList<>();
            String[] playerData = players_input.split(",");

            for (String data : playerData) {
                Player player = new Player(data, 0, true);
                players.add(player);
            }
            return players;
        }

    }

    /**
     * Check if the game is over.
     *
     * @param players List of players in the game.
     * @return True if the game is over, false otherwise.
     */
    public static boolean GameOver(List<Player> players) {
        int count = 0;
        for (Player player : players) {
            if (player.getInGame()) {
                count++;
            }
        }
        return count > 1;
    }

    /**
     * Main method to run the dice game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        List<String> Input = readFile(inputFile, true, true);

        ParseInput parser = new ParseInput();
        parser.parseInputs(Input);
        List<Player> players = parser.parsePlayers();
        List<Dice> dices = parser.parseDices();

        List<String> outputLines = new ArrayList<>();

        while (GameOver(players)) {
            int pointer = 0;
            int player_pointer;
            Player currentPlayer;
            int first_dice;
            int second_dice;
            
            for (Dice dice : dices) {
                player_pointer = pointer % players.size();
                currentPlayer = players.get(player_pointer);
                while (!currentPlayer.getInGame()) {
                    pointer++;
                    player_pointer = pointer % players.size();
                    currentPlayer = players.get(player_pointer);
                }

                first_dice = dice.getFirstDice();
                second_dice = dice.getSecondDice();
                if (first_dice == 0 && second_dice == 0) {
                    outputLines.add(currentPlayer.getName() + " skipped the turn and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".");
                } else if ((first_dice == 1 && second_dice != 1) || (first_dice != 1 && second_dice == 1)) {
                    outputLines.add(currentPlayer.getName() + " threw " + dice.display() + " and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".");
                } else if (first_dice == 1 && second_dice == 1) {
                    outputLines.add(currentPlayer.getName() + " threw " + dice.display() + ". Game over " + currentPlayer.getName() + "!");
                    currentPlayer.setInGame(false);
                } else {
                    int score = currentPlayer.getScore() + first_dice + second_dice;
                    currentPlayer.setScore(score);
                    outputLines.add(currentPlayer.getName() + " threw " + dice.display() + " and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".");
                }
                pointer++;
            }
        }

        Player winner = players.get(0).winner(players); // Call the winner method on an instance of Player
        outputLines.add(winner.getName() + " is the winner of the game with the score of " + winner.getScore() + ". Congratulations " + winner.getName() + "!");

        writeToFile(outputFile, outputLines, false, true);
    }
}