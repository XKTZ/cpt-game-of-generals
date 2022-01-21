package generals.backend;

import generals.network.XSocket;
import generals.util.Coordinate;
import generals.util.log.Loggable;

import java.util.Arrays;

import static generals.network.Messages.*;

/**
 * The controller layer
 *
 * @author Yidi Chen
 * @date 2022-01-17
 */
public class GameController implements Loggable {

    /**
     * Game start message
     */
    private static final String STR_GAME_START = "start";

    /**
     * Board update message
     */
    private static final String STR_BOARD_UPDATE = "update";

    /**
     * Winning message
     */
    private static final String STR_WINNING_MESSAGE = "Player %s has won the game!";

    /**
     * Game board
     */
    private GameBoard board;

    /**
     * The socket
     */
    private XSocket socket;

    /**
     * Message controller
     */
    private MessageController messageController;

    /**
     * Player count
     */
    private int intPlayerCount;

    /**
     * Ready count
     */
    private int intReadyCount;

    /**
     * Player names
     */
    private String[] strPlayerName = new String[3];

    public GameController(MessageController messageController) {
        this.messageController = messageController;
        intPlayerCount = 0;
        reset();
    }

    public void setSocket(XSocket socket) {
        this.socket = socket;
    }

    /**
     * Reset
     */
    private void reset() {
        board = new GameBoard();
        intReadyCount = 0;
    }

    /**
     * Connect a user
     *
     * @param strName the name
     * @return player id
     */
    public int connect(String strName) {
        intPlayerCount++;
        strPlayerName[intPlayerCount] = strName;
        return intPlayerCount;
    }

    /**
     * Say that one user has ready
     */
    public void ready() {
        intReadyCount++;
        if (intReadyCount == 2) {
            board.switchTurn();
            intReadyCount = 0;
            // start the game
            socket.request(messageOf(STR_GAME_START));
        }
    }

    /**
     * Restart the game
     */
    public void restart() {
        board = new GameBoard();
    }

    /**
     * {player} move the chess at {x, y} to {xto, yto}
     *
     * @param intPlayer player
     * @param intX      x
     * @param intY      y
     * @param intXTo    xto
     * @param intYTo    yto
     * @return success
     */
    public boolean move(int intPlayer, int intX, int intY, int intXTo, int intYTo) {
        Coordinate coor = getCoordinate(intPlayer, intX, intY);
        intX = coor.intX;
        intY = coor.intY;
        coor = getCoordinate(intPlayer, intXTo, intYTo);
        intXTo = coor.intX;
        intYTo = coor.intY;

        boolean blnRes = board.move(intPlayer, intX, intY, intXTo, intYTo);

        int intWinner;
        // if someone has won
        if ((intWinner = winner()) != 0) {
            messageController.sendMessage(String.format(STR_WINNING_MESSAGE, strPlayerName[intWinner]));
            reset();
            restartGame();
        }
        // if no one win, but update
        else if (blnRes) {
            boardUpdated();
        }
        return blnRes;
    }

    /**
     * {player} put {type} at {x, y}
     *
     * @param intPlayer player
     * @param intType   type
     * @param intX      x
     * @param intY      y
     * @return success
     */
    public boolean put(int intPlayer, int intType, int intX, int intY) {
        Coordinate coor = getCoordinate(intPlayer, intX, intY);
        intX = coor.intX;
        intY = coor.intY;

        boolean blnRes = board.put(intPlayer, intX, intY, intType);
        if (blnRes) {
            boardUpdated();
        }
        return blnRes;
    }

    /**
     * Get the winner
     *
     * @return winner
     */
    public int winner() {
        return board.checkWinner();
    }

    /**
     * Get player on
     *
     * @return player on
     */
    public int getPlayerOn() {
        return board.getPlayerOn();
    }

    /**
     * Get the board
     *
     * @return the board
     */
    public Chess[][] getBoard(int intPlayer) {
        Chess[][] ret;
        if (intPlayer == 1) {
            // for player 1, just copy
            ret = board.getBoard();
        } else {
            // for player 2, you have to reverse the board
            ret = new Chess[GameBoard.INT_ROWS + 1][GameBoard.INT_COLS + 1];
            Chess[][] boardArray = board.getBoard();
            for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow++) {
                for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol++) {
                    ret[intRow][intCol] = boardArray[GameBoard.INT_ROWS - intRow + 1][GameBoard.INT_COLS - intCol + 1];
                }
            }
        }
        return ret;
    }

    /**
     * Tell the client that board has been updated
     */
    public void boardUpdated() {
        // send that the game has updated
        socket.request(messageOf(STR_BOARD_UPDATE));
    }

    /**
     * Get available positions for {x, y} to move
     *
     * @param intX x
     * @param intY y
     * @return coordinates available
     */
    public Coordinate[] availablePosition(int intPlayer, int intX, int intY) {
        Coordinate coor = getCoordinate(intPlayer, intX, intY);
        intX = coor.intX;
        intY = coor.intY;
        return Arrays.stream(board.availablePosition(intPlayer, intX, intY))
                .map(coordinate -> getCoordinate(intPlayer, coordinate.intX, coordinate.intY))
                .toArray(Coordinate[]::new);
    }

    /**
     * Send the restart game message
     */
    public void restartGame() {
        socket.request(messageOf(STR_RESTART));
    }

    /**
     * Get the coordinate
     *
     * @param intPlayer player
     * @param intX      x
     * @param intY      y
     * @return coordinate new
     */
    private static Coordinate getCoordinate(int intPlayer, int intX, int intY) {
        if (intPlayer == 1) {
            return Coordinate.of(intX, intY);
        } else {
            // for player 2, the board should be reversed
            return Coordinate.of(GameBoard.INT_ROWS + 1 - intX, GameBoard.INT_COLS + 1 - intY);
        }
    }

}
