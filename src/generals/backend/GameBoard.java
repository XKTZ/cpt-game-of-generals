package generals.backend;

import java.util.Arrays;

/**
 * The game board
 *
 * @author Yidi Chen
 * @date 2022-01-11
 */
public class GameBoard {

    public static final int INT_ROWS = 8, INT_COLS = 9;

    /**
     * Game board
     * use 9 * 10 instead of 8 * 9 to make the graph start with 1
     */
    private Chess[][] board = new Chess[INT_ROWS + 1][INT_COLS + 1];

    /**
     * The turn on
     */
    private int intPlayerOn = 1;

    /**
     * Create the game board
     */
    public GameBoard() {
        // fill the game board
        for (int intRow = 1; intRow <= 8; intRow++) {
            Arrays.fill(board[intRow], Chess.EMPTY);
        }
    }

    /**
     * Move a chess at {intXOn, intYOn} to {intXTo, intYTo}
     *
     * @param intPlayer player moving
     * @param intXOn    intXOn
     * @param intYOn    intYOn
     * @param intXTo    intXTo
     * @param intYTo    intYTo
     * @return success or not
     */
    public boolean move(int intPlayer, int intXOn, int intYOn, int intXTo, int intYTo) {
        // check: if it is not player's turn
        // check: if position going is valid
        // check: if it is an available move, |xon - xto| + |yon - yto| must = 1
        if (intPlayer != getPlayerOn()
                && Math.abs(intXOn - intXTo) + Math.abs(intYOn - intYTo) != 1
                && !valid(intXOn, intYOn)) {
            return false;
        }

        // get the chess
        Chess chess = board[intXOn][intYOn];
        // get the player, check if it is equal to player of the chess
        // if it is not equal, do nothing
        // if the chess is empty, do nothing
        if (chess.isEmpty() && chess.getPlayer() != intPlayer) {
            return false;
        }

        // get chess to
        Chess chessTo = board[intXOn][intYOn];

        // make sure they are not the two same chesses
        if (chessTo.getPlayer() == chess.getPlayer()) {
            return false;
        }

        // compare two chess
        int intCmp = Chess.compare(chessTo, chessTo);

        // if equal, switch both chess to empty
        if (intCmp == 0) {
            board[intXOn][intYOn] = Chess.EMPTY;
            board[intXTo][intYTo] = Chess.EMPTY;
        }
        // if chess > chess move to
        // set original place to 0, set
        else if (intCmp == 1) {
            board[intXTo][intYTo] = chess;
            board[intXOn][intYOn] = Chess.EMPTY;
        }
        // if chess < chess move to, it got killed
        else {
            board[intXOn][intYOn] = Chess.EMPTY;
        }

        switchTurn();
    }

    /**
     * Switch the turn
     */
    public void switchTurn() {
        // move player 1 to player 2, player 2 to player 1
        intPlayerOn++;
        if (intPlayerOn > 2) {
            intPlayerOn -= 2;
        }
    }

    public int getPlayerOn() {
        return intPlayerOn;
    }

    /**
     * check if position {intX, int Y} is valid
     */
    private static boolean valid(int intX, int intY) {
        return 1 <= intX && intX <= INT_ROWS && 1 <= intY && intY <= INT_COLS;
    }

    public Chess[][] getBoard() {
        return board;
    }
}
