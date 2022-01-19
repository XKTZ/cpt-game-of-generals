package generals.backend;

import static generals.network.Messages.*;

import generals.network.XSocket;

/**
 * Message Controller
 *
 * @author Yidi Chen
 * @date 2022-01-17
 */
public class MessageController {

    /**
     * socket
     */
    private XSocket socket;

    /**
     * Create the message controller by giving socket
     */
    public MessageController() {
    }

    public void setSocket(XSocket socket) {
        this.socket = socket;
    }

    /**
     * Send message
     */
    public void sendMessage(String strMsg) {
        socket.request(messageOf(STR_MESSAGE, strMsg));
    }
}
