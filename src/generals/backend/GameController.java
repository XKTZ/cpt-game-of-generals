package generals.backend;

import generals.network.XSocket;

import static generals.util.Util.*;

/**
 * The controller layer
 *
 * @author Yidi Chen
 * @date 2022-01-17
 */
public class GameController {

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

    public GameController(XSocket socket, MessageController messageController) {
        this.socket = socket;
        this.messageController = messageController;
        intPlayerCount = 0;
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
            socket.request(XSocket.STR_RECEIVER_ALL, messageOf(STR_GAME_START), (ignored) -> {
            });
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
        intX = getX(intPlayer, intX);
        boolean blnRes = board.move(intPlayer, intX, intY, intXTo, intYTo);

        int intWinner;
        // if someone has won
        if ((intWinner = winner()) != 0) {
            messageController.sendMessage(String.format(STR_WINNING_MESSAGE, strPlayerName[intWinner]));
            reset();
            boardUpdated();
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
     * @param intX      x
     * @param intY      y
     * @param intType   type
     * @return success
     */
    public boolean put(int intPlayer, int intX, int intY, int intType) {
        intX = getX(intPlayer, intX);
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
        Chess[][] ret = new Chess[GameBoard.INT_ROWS + 1][];
        if (intPlayer == 1) {
            // for player 1, just copy
            ret = board.getBoard();
        } else {
            int intOn = GameBoard.INT_ROWS;
            // for player 2, you have to reverse the board
            for (var row : board.getBoard()) {
                ret[intOn] = row;
                intOn--;
            }
        }
        return ret;
    }

    /**
     * Tell the client that board has been updated
     */
    public void boardUpdated() {
        // send that the game has updated
        socket.request(XSocket.STR_RECEIVER_ALL, messageOf(STR_BOARD_UPDATE), (ignored) -> {
        });
    }

    /**
     * Get the x-axis
     *
     * @param intPlayer player
     * @param intX      x
     * @return x
     */
    private static int getX(int intPlayer, int intX) {
        if (intPlayer == 1) {
            return intX;
        } else {
            // for player 2, the board should be reversed
            return GameBoard.INT_ROWS + 1 - intX;
        }
    }
}
