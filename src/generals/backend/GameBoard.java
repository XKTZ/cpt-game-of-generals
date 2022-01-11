package generals.backend;

/**
 * The game board
 *
 * @author Yidi Chen
 * @date 2022-01-11
 */
public class GameBoard {

    /**
     * Game board
     * use 9 * 10 instead of 8 * 9 to make the graph start with 1
     */
    private Chess[][] board = new Chess[9][10];

    /**
     * The turn on
     */
    private int intPlayerOn = 1;

    /**
     * The winner
     */
    private int intWinner;

    public GameBoard() {

    }

    public Chess[][] getBoard() {
        return board;
    }
}
