package generals.main;

import generals.backend.Chess;
import generals.backend.GameBoard;
import generals.backend.GameController;
import generals.backend.MessageController;
import generals.network.RequestHandler;
import generals.network.XSocket;
import generals.util.Coordinate;
import generals.util.log.Loggable;

import java.util.Arrays;
import java.util.function.Consumer;

import static generals.network.Messages.*;

/**
 * Main class for server
 *
 * @author Yidi Chen
 * @date 2021-12-28
 */
public class ServerMain implements Runnable, Loggable {

    /**
     * Port
     */
    private static final int INT_PORT = 8888;

    /**
     * Socket
     */
    private XSocket sock;

    /**
     * Message controller
     */
    MessageController messageController = new MessageController();

    /**
     * Game controller
     */
    GameController gameController = new GameController(messageController);

    /**
     * Constructor of server
     */
    public ServerMain() {
        // create socket
        sock = new XSocket(INT_PORT, new RequestHandler() {
            /**
             * Responding
             * @param strData   the requesting data
             * @param responder the responding method. It should be provided by the XSocket instead of programmer
             */
            @Override
            public void response(String[] strData, Consumer<String[]> responder) {
                // log the data
                info(Arrays.toString(strData));
                // connect
                if (strData[0].equals(STR_CONNECT)) {
                    responder.accept(messageOf(connect(strData[1])));
                }
                // ready
                if (strData[0].equals(STR_READY)) {
                    ready();
                }
                // available positions
                if (strData[0].equals(STR_AVAILABLE)) {
                    // get available positions
                    Coordinate[] availablePositions = available(
                            Integer.parseInt(strData[1]),
                            Integer.parseInt(strData[2]),
                            Integer.parseInt(strData[3])
                    );
                    // create response
                    String[] strResponse = new String[2 * availablePositions.length + 1];
                    // set the length
                    strResponse[0] = String.valueOf(availablePositions.length);
                    // put coordinate into response
                    int intCnt = 1;
                    for (Coordinate coordinate : availablePositions) {
                        strResponse[intCnt] = String.valueOf(coordinate.intX);
                        strResponse[intCnt + 1] = String.valueOf(coordinate.intY);
                        intCnt += 2;
                    }
                    responder.accept(strResponse);
                }
                // put
                if (strData[0].equals(STR_PUT)) {
                    // put it
                    if (put(Integer.parseInt(strData[1]), Integer.parseInt(strData[2]),
                            Integer.parseInt(strData[3]), Integer.parseInt(strData[4]))) {
                        responder.accept(messageOf(1));
                    } else {
                        responder.accept(messageOf(0));
                    }
                }
                // move
                if (strData[0].equals(STR_MOVE)) {
                    // move it
                    boolean blnSuc = move(
                            Integer.parseInt(strData[1]),
                            Integer.parseInt(strData[2]),
                            Integer.parseInt(strData[3]),
                            Integer.parseInt(strData[4]),
                            Integer.parseInt(strData[5])
                    );
                    if (blnSuc) {
                        responder.accept(messageOf(1));
                    } else {
                        responder.accept(messageOf(0));
                    }
                }
                // board
                if (strData[0].equals(STR_BOARD)) {
                    // game board
                    Chess[][] boardArray = gameController.getBoard(Integer.parseInt(strData[1]));
                    // response
                    String[] strResp = new String[GameBoard.INT_ROWS * GameBoard.INT_COLS];
                    // put it in array
                    int intCnt = 0;
                    for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow++) {
                        for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol++) {
                            strResp[intCnt] = boardArray[intRow][intCol].toString();
                            intCnt++;
                        }
                    }
                    responder.accept(strResp);
                }
                // message
                if (strData[0].equals(STR_SEND_MESSAGE)) {
                    // send a message by message controller
                    String strName = gameController.nameOf(Integer.parseInt(strData[1]));
                    String[] strMessage = Arrays.copyOfRange(strData, 2, strData.length);
                    messageController.sendMessage(strName + ": " + String.join(" ", strMessage));
                    responder.accept(messageOf(1));
                }
            }

            /**
             * Connect a player
             * @param strName name
             * @return player id
             */
            private int connect(String strName) {
                return gameController.connect(strName);
            }

            /**
             * Put a chess
             * @param intPlayer player
             * @param intChessId chess type
             * @param intX x
             * @param intY y
             * @return succeed
             */
            private boolean put(int intPlayer, int intChessId, int intX, int intY) {
                return gameController.put(intPlayer, intChessId, intX, intY);
            }

            /**
             * A player ready
             */
            private void ready() {
                gameController.ready();
            }

            /**
             * Get available positions
             * @param intPlayer player
             * @param intX x
             * @param intY y
             * @return all available positions
             */
            private Coordinate[] available(int intPlayer, int intX, int intY) {
                return gameController.availablePosition(intPlayer, intX, intY);
            }

            /**
             * Move a chess from {x, y} to {xto, yto}
             * @param intPlayer player
             * @param intX x
             * @param intY y
             * @param intXTo xto
             * @param intYTo yto
             * @return succeed
             */
            private boolean move(int intPlayer, int intX, int intY, int intXTo, int intYTo) {
                return gameController.move(intPlayer, intX, intY, intXTo, intYTo);
            }
        });

        // set the socket in controller after created socket
        messageController.setSocket(sock);
        gameController.setSocket(sock);
    }

    /**
     * Run the server
     */
    @Override
    public void run() {
        sock.connect();
    }


    public static void main(String[] args) {
        new ServerMain().run();
    }
}
