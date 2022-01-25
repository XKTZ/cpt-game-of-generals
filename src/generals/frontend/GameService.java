package generals.frontend;

import generals.frontend.ui.NotPutChessPanel;
import generals.network.XSocket;
import generals.util.Coordinate;

import javax.swing.*;
import java.util.function.Consumer;

import static generals.network.Messages.*;

/**
 * Service of game (communication between client and server)
 *
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class GameService {

    /**
     * Socket
     */
    private XSocket socket;

    /**
     * Player
     */
    private int intPlayer;

    /**
     * Player name
     */
    private String strPlayerName;

    /**
     * Game Board service
     *
     * @param socket socket
     */
    public GameService(XSocket socket) {
        this.socket = socket;
    }

    /**
     * Connect to server
     *
     * @param strName name
     */
    public void connect(String strName) {
        // connect by providing name
        this.strPlayerName = strName;
        // request connect
        socket.request(messageOf(STR_CONNECT, strName), (resp) -> {
            // set player
            intPlayer = Integer.parseInt(resp[0]);
        });
    }

    /**
     * Connect to server
     */
    public void ready() {
        // request ready
        socket.request(messageOf(STR_READY));
    }

    /**
     * Put a chess with type at {x, y}
     *
     * @param intX    x
     * @param intY    y
     * @param intType type
     */
    public void put(int intX, int intY, int intType, NotPutChessPanel notPutChessPanel) {
        // request to put
        socket.request(messageOf(STR_PUT, intPlayer, intType, intX, intY), (resp) -> {
            // if put success
            if (Integer.parseInt(resp[0]) == INT_SUCCESS) {
                // remove the chess from the piece picking panel
                // get parent
                var parent = notPutChessPanel.getParent();
                // remove it
                parent.remove(notPutChessPanel);
                // refresh scroll pane
                JScrollPane scrollPane = (JScrollPane) parent.getParent().getParent();
                int valueOld = scrollPane.getVerticalScrollBar().getValue();
                // scroll it a bit
                scrollPane.getVerticalScrollBar().setValue(1);
                scrollPane.getVerticalScrollBar().setValue(valueOld);

                // if the scroll pane's viewpoint is empty
                if (((JPanel) scrollPane.getViewport().getView()).getComponents().length == 0) {
                    // change it by button, pressing ready
                    scrollPane.getViewport().remove(scrollPane.getViewport().getView());
                    scrollPane.getViewport().add(new JButton("Ready") {{
                        addActionListener((e) -> {
                            socket.request(messageOf(STR_READY));
                            this.setEnabled(false);
                        });
                    }});
                }
            }
        });
    }

    /**
     * Get the board, then do something
     *
     * @param then then
     */
    public void getBoard(Consumer<String[]> then) {
        socket.request(messageOf(STR_BOARD, intPlayer), then);
    }

    /**
     * Get the available position, then do then
     *
     * @param intX x
     * @param intY y
     * @param then then
     */
    public void available(int intX, int intY, Consumer<Coordinate[]> then) {
        socket.request(messageOf(STR_AVAILABLE, intPlayer, intX, intY), (resp) -> {
            int intN = Integer.parseInt(resp[0]);
            Coordinate[] availablePositions = new Coordinate[intN];
            int intOn = 1;
            for (int intCnt = 0; intCnt < intN; intCnt++) {
                availablePositions[intCnt] = Coordinate.of(
                        Integer.parseInt(resp[intOn]),
                        Integer.parseInt(resp[intOn + 1])
                );
                intOn += 2;
            }
            then.accept(availablePositions);
        });
    }

    /**
     * Move {x, y} to {xto, yto}
     *
     * @param intX   x
     * @param intY   y
     * @param intXTo xto
     * @param intYTo yto
     */
    public void move(int intX, int intY, int intXTo, int intYTo) {
        // request move
        socket.request(messageOf(STR_MOVE, intPlayer, intX, intY, intXTo, intYTo), (ignored) -> {
        });
    }

    /**
     * Send a message
     *
     * @param strMsg the message
     */
    public void message(String strMsg) {
        // request send message
        socket.request(messageOf(STR_SEND_MESSAGE, intPlayer, strMsg), (ignored) -> {
        });
    }

    /**
     * Get player
     *
     * @return player
     */
    public int getPlayer() {
        return intPlayer;
    }
}
