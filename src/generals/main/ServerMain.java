package generals.main;

import generals.backend.GameController;
import generals.backend.MessageController;
import generals.network.RequestHandler;
import generals.network.XSocket;
import generals.util.Coordinate;

import javax.xml.transform.sax.SAXTransformerFactory;
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
                if (strData[0].equals(STR_CONNECT)) {
                    responder.accept(messageOf(String.valueOf(connect(strData[1]))));
                }
                if (strData[0].equals(STR_PUT)) {
                    put(Integer.parseInt(strData[1]), Integer.parseInt(strData[2]), Integer.parseInt(strData[3]), Integer.parseInt(strData[4]));
                }
                if (strData[0].equals(STR_READY)) {
                    ready();
                }
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
            }

            private int connect(String strName) {
                return gameController.connect(strName);
            }

            private void put(int intPlayer, int intChessId, int intX, int intY) {
                gameController.put(intPlayer, intX, intY, intChessId)
            }

            private void ready() {
                gameController.ready();
            }

            private Coordinate[] available(int intPlayer, int intX, int intY) {
                return gameController.availablePosition(intPlayer, intX, intY);
            }
        });
    }

    public static void main(String[] args) {

    }
}
