package generals.backend;

import generals.util.Coordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The game board
 *
 * @author Yidi Chen
 * @date 2022-01-11
 */
public class GameBoard {

    public static final int INT_ROWS = 8, INT_COLS = 9;

    /**
     * Displacement x
     */
    private static final int[] intDx = new int[]{1, 0, -1, 0};
    /**
     * Displacement y
     */
    private static final int[] intDy = new int[]{0, 1, 0, -1};

    /**
     * Game board
     * use 9 * 10 instead of 8 * 9 to make the graph start with 1
     */
    private Chess[][] board = new Chess[INT_ROWS + 1][INT_COLS + 1];

    /**
     * The turn on
     */
    private int intPlayerOn = 0;

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
        // if the player on is 0, meaning game not started yet, check by another way
        if (getPlayerOn() == 0) {
            return moveBeforeStart(intPlayer, intXOn, intYOn, intXTo, intYTo);
        }

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
            board[intXTo][intYTo] = chess;
        }

        switchTurn();

        return true;
    }

    /**
     * before the turn, move {intX, intY} to {intXTo, intYTo}
     *
     * @param intX   intX
     * @param intY   intY
     * @param intXTo intXTo
     * @param intYTo intYTo
     * @return
     */
    public boolean moveBeforeStart(int intPlayer, int intX, int intY, int intXTo, int intYTo) {
        // if the player is 1, allow to move intX <= 3
        if (intPlayer == 1) {
            if (!(inRange(intX, 1, 3) && inRange(intXTo, 1, 3)
                    && inRange(intY, 1, INT_COLS) && inRange(intYTo, 1, INT_COLS))) {
                return false;
            }
        }
        // if player is 2, allow to move intX >= 6
        else {
            if (!(inRange(intX, 6, INT_ROWS) && inRange(intXTo, 6, INT_ROWS)
                    && inRange(intY, 1, INT_COLS) && inRange(intYTo, 1, INT_COLS))) {
                return false;
            }
        }
        // check if there is chess at the position moving to and if there is a chess at position moving from
        if (board[intX][intY].getType() == Chess.INT_EMPTY_SPACE
                || board[intXTo][intYTo].getType() != Chess.INT_EMPTY_SPACE
                || board[intX][intY].getPlayer() != intPlayer) {
            return false;
        }
        // move the chess
        board[intXTo][intYTo] = board[intX][intY];
        board[intX][intY] = Chess.EMPTY;
        return true;
    }

    /**
     * Get the available position to move for {x, y}
     * @param intPlayer player
     * @param intX x
     * @param intY y
     * @return the available position
     */
    public Coordinate[] availablePosition(int intPlayer, int intX, int intY) {
        // if game not started yet, use another available position method
        if (getPlayerOn() == 0) {

        }
        List<Coordinate> coordinates = new ArrayList<>();
        for (int intCnt = 0, intXNew, intYNew; intCnt < 4; intCnt ++) {
            intXNew = intX + intDx[intCnt];
            intYNew = intY + intDy[intCnt];
            if (valid(intXNew, intYNew) && board[intXNew][intYNew].getPlayer() != intPlayer ) {
                coordinates.add(new Coordinate(intXNew, intYNew));
            }
        }
        return coordinates.toArray(new Coordinate[]{});
    }

    /**
     * Get the available position to move for {x, y} before game start
     * @param intPlayer player
     * @param intX x
     * @param intY y
     * @return coordinate can move
     */
    public Coordinate[] availablePositionBeforeStart(int intPlayer, int intX, int intY) {
        // player 1: from row 1 to row 3
        if (intPlayer == 1) {

        }
        // player 2: from row 6 to row 8
        return null;
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

    /**
     * Get the player on turn
     *
     * @return the player on
     */
    public int getPlayerOn() {
        return intPlayerOn;
    }

    /**
     * Check the winner
     *
     * @return the winner
     */
    public int checkWinner() {
        // if game not started yet, return 0
        if (intPlayerOn == 0) {
            return 0;
        }
        // check if player 1's flag reaches bottom or player 2's flag reaches top
        for (int intCol = 0; intCol < INT_COLS; intCol++) {
            if (board[INT_ROWS][intCol].getPlayer() == 1 && board[INT_ROWS][intCol].getType() == Chess.INT_FLAG) {
                return 1;
            }
            if (board[1][intCol].getPlayer() == 2 && board[1][intCol].getType() == Chess.INT_FLAG) {
                return 2;
            }
        }

        // check existence of flag
        boolean blnExistPlayer1 = false;
        boolean blnExistPlayer2 = false;

        for (int intRow = 0; intRow < INT_ROWS; intRow++) {
            for (int intCol = 0; intCol < INT_COLS; intCol++) {
                if (board[intRow][intCol].getPlayer() == 1 && board[intRow][intCol].getType() == Chess.INT_FLAG) {
                    blnExistPlayer1 = true;
                }
                if (board[intRow][intCol].getPlayer() == 2 && board[intRow][intCol].getType() == Chess.INT_FLAG) {
                    blnExistPlayer2 = true;
                }
            }
        }

        // check the move
        if (blnExistPlayer1 && blnExistPlayer2) {
            return 0;
        } else if (blnExistPlayer1) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * put a chess at {x, y}
     *
     * @param intPlayer player
     * @param intX      x
     * @param intY      y
     * @param intType   type of chess
     * @return success or not
     */
    public boolean put(int intPlayer, int intX, int intY, int intType) {
        // check the player (1 <= x <= 3 if player is 1, 6 <= x <= 8 if player is 2)
        if (intPlayer == 1) {
            if (!inRange(intX, 1, 3)) {
                return false;
            }
        } else {
            if (!inRange(intX, 6, INT_ROWS)) {
                return false;
            }
        }
        // if the position is not empty, return false
        if (board[intX][intY].getType() != Chess.INT_EMPTY_SPACE) {
            return false;
        }

        board[intX][intY] = new Chess(intPlayer, intType);
        return true;
    }

    /**
     * check if position {intX, intY} is valid
     *
     * @return is or not
     */
    private static boolean valid(int intX, int intY) {
        return 1 <= intX && intX <= INT_ROWS && 1 <= intY && intY <= INT_COLS;
    }

    /**
     * Check if it is in a range
     *
     * @return is or not
     */
    private static boolean inRange(int intV, int intFrom, int intTo) {
        return intFrom <= intV && intV <= intTo;
    }

    public Chess[][] getBoard() {
        return board;
    }
}
