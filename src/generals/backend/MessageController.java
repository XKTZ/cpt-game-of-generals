package generals.backend;

import static generals.util.Util.*;

import generals.network.XSocket;

/**
 * Message Controller
 *
 * @author Yidi Chen
 * @date 2022-01-17
 */
public class MessageController {

    /**
     * String identifying sending message
     */
    private static final String STR_MESSAGE = "message";

    /**
     * socket
     */
    private XSocket socket;

    /**
     * Create the message controller by giving socket
     *
     * @param socket socket
     */
    public MessageController(XSocket socket) {
        this.socket = socket;
    }

    /**
     * Send message
     */
    public void sendMessage(String msg) {
        socket.request(XSocket.STR_RECEIVER_ALL,
                messageOf(STR_MESSAGE, msg),
                (ignored) -> {
                });
    }
}
