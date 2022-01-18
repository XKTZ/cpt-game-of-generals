package generals.main;

import generals.backend.Chess;
import generals.backend.GameBoard;
import generals.backend.GameController;
import generals.backend.MessageController;
import generals.network.RequestHandler;
import generals.network.XSocket;
import generals.util.Coordinate;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.Arrays;
import java.util.function.Consumer;

import static generals.util.Util.*;
import static generals.network.Messages.*;

/**
 * Main class for server
 *
 * @author Yidi Chen
 * @date 2021-12-28
 */
public class ServerMain {

    private static final int INT_PORT = 8888;

    XSocket sock;

    public ServerMain() {
        sock = new XSocket(INT_PORT, new RequestHandler() {

            /**
             * Message controller
             */
            MessageController messageController = new MessageController(sock);

            /**
             * Game controller
             */
            GameController gameController = new GameController(sock, messageController);

            @Override
            public void response(String[] strData, Consumer<String[]> responder) {
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
                    put(Integer.parseInt(strData[1]), Integer.parseInt(strData[2]), Integer.parseInt(strData[3]), Integer.parseInt(strData[4]));
                }
                // move
                if (strData[0].equals(STR_MOVE)) {
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
                    Chess[][] boardArray = gameController.getBoard(Integer.parseInt(strData[1]));
                    String[] strResp = new String[GameBoard.INT_ROWS * GameBoard.INT_COLS];
                    int intCnt = 0;
                    for (int intRow = 0; intRow < GameBoard.INT_ROWS; intRow ++) {
                        for (int intCol = 0; intCol < GameBoard.INT_COLS; intCol ++) {
                            strResp[intCol] = boardArray[intRow][intCnt].toString();
                            intCnt ++;
                        }
                    }
                    responder.accept(strResp);
                }
                // message
                if (strData[0].equals(STR_SEND_MESSAGE)) {
                    String[] strMessage = Arrays.copyOfRange(strData, 1, strData.length);
                    messageController.sendMessage(String.join(" ", strMessage));
                    responder.accept(messageOf(1));
                }
            }

            private int connect(String strName) {
                return gameController.connect(strName);
            }

            private void put(int intPlayer, int intChessId, int intX, int intY) {
                gameController.put(intPlayer, intX, intY, intChessId);
            }

            private void ready() {
                gameController.ready();
            }

            private Coordinate[] available(int intPlayer, int intX, int intY) {
                return gameController.availablePosition(intPlayer, intX, intY);
            }

            private boolean move(int intPlayer, int intX, int intY, int intXTo, int intYTo) {
                return gameController.move(intPlayer, intX, intY, intXTo, intYTo);
            }
        });
    }

    public static void main(String[] args) {

    }
}
