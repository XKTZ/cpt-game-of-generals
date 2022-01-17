package generals.backend;

/**
 * The controller layer
 * @author Yidi Chen
 * @date 2022-01-17
 */
public class Controller {

    private GameBoard board;

    public Controller() {
        board = new GameBoard();
    }

    /**
     * Restart the game
     */
    public void restart() {
        board = new GameBoard();
    }

    /**
     * Get the board
     * @return the board
     */
    public GameBoard getBoard() {
        return board;
    }
}
