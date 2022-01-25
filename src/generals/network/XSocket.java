package generals.network;

import util.SuperSocketMaster;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * XSocket is a wrapper for network based on SuperSocketMaster.
 * Instead of dealing with the message by using action listener,
 * XSocket supports to use different handlers to handle the request/response
 *
 * @author Yidi Chen
 * @date 2021-12-15
 */
public class XSocket implements AutoCloseable {

    /**
     * Name of server
     */
    public static final String STR_SERVER_NAME = "$SERVER$";

    /**
     * Mode for client
     */
    public static final int INT_MODE_CLIENT = 1;
    /**
     * Mode for server
     */
    public static final int INT_MODE_SERVER = 2;

    /**
     * The format for fomatting message sending
     * Format:
     * $type $id $sender $receiver $message
     */
    private static final String STR_FORMAT_SENDER_MESSAGE = "%s %d %s %s %s";

    /**
     * Splitter of message is space
     */
    private static final String STR_MESSAGE_SPLITTER = " ";

    /**
     * The position of type in array
     */
    private static final int INT_TYPE_POSITION_IN_ARRAY = 0;

    /**
     * The type of request
     */
    private static final String STR_TYPE_REQUEST = "REQUEST";

    /**
     * The type of response
     */
    private static final String STR_TYPE_RESPONSE = "RESPONSE";

    /**
     * The position of id in array
     */
    private static final int INT_ID_POSITION_IN_ARRAY = 1;

    /**
     * The position of sender in array
     */
    private static final int INT_SENDER_POSITION_IN_ARRAY = 2;

    /**
     * The position of receiver in array
     */
    private static final int INT_RECEIVER_POSITION_IN_ARRAY = 3;

    /**
     * The message shows that all people are desired receivers
     */
    public static final String STR_RECEIVER_ALL = "$ALL$";

    /**
     * Mode
     */
    private int intMode;

    /**
     * IP
     */
    private String strIP;

    /**
     * Port
     */
    private int intPort;

    /**
     * The super socket master
     */
    private SuperSocketMaster ssm;

    /**
     * The id on for requesting
     * This id should be Atomic (in the other word, thread safe).
     * Otherwise, because the user can send message in a very high speed, it might face a multithread error.
     */
    private AtomicInteger intIdOn = new AtomicInteger(0);

    /**
     * The handlers for the response from requesting
     */
    private Map<Integer, Consumer<String[]>> responseHandlers;

    /**
     * The handler for request
     */
    private RequestHandler handler;

    /**
     * Name of the server
     */
    private String strName;

    /**
     * Lock for listener
     */
    private ReentrantLock lockListener = new ReentrantLock();

    /**
     * Lock for request
     */
    private ReentrantLock lockRequest = new ReentrantLock();

    /**
     * the listener
     */
    ActionListener listener = (e) -> {
        lockListener.lock();

        // gain the data and split them
        String[] strData = ssm.readText().split(STR_MESSAGE_SPLITTER);

        // if the receiver is not itself, just ignore this message
        if (!receiverOf(strData).equals(strName) && !receiverOf(strData).equals(STR_RECEIVER_ALL)) {
            return;
        }

        // get type and id
        String strType = typeOf(strData);
        int intId = idOf(strData);
        // first thing need to ensure is that the real strData should NOT contain all types created by xsocket
        String[] strDataCut = Arrays.copyOfRange(strData, 4, strData.length);

        if (strType.equals(STR_TYPE_REQUEST)) {
            // then, use the handler to respond
            // needs to provide a consumer to send data back to the server
            handler.response(strDataCut, (strDataSend) -> {
                ssm.sendText(String.format(STR_FORMAT_SENDER_MESSAGE, STR_TYPE_RESPONSE,
                        intId, strName, senderOf(strData), String.join(
                                STR_MESSAGE_SPLITTER, strDataSend
                        )));
            });
        } else if (strType.equals(STR_TYPE_RESPONSE)) {
            // look for the response handler of the id
            Consumer<String[]> responseHandler = responseHandlers.get(intId);
            responseHandler.accept(strDataCut);
        }

        lockListener.unlock();
    };

    /**
     * Create a server by providing the port
     *
     * @param intPort the port
     * @param handler the handler
     */
    public XSocket(int intPort, RequestHandler handler) {
        this.intMode = INT_MODE_SERVER;
        this.strName = STR_SERVER_NAME;
        this.intPort = intPort;
        this.handler = handler;

        // Response handler should be thread safe
        this.responseHandlers = new ConcurrentHashMap<>();

        ssm = new SuperSocketMaster(intPort, listener);
    }

    /**
     * Create a server by providing the port
     *
     * @param strIP   the ip of the server
     * @param strName the name of the server
     * @param intPort the port
     * @param handler the handler
     */
    public XSocket(String strName, String strIP, int intPort, RequestHandler handler) {
        this.intMode = INT_MODE_SERVER;
        this.strIP = strIP;
        this.intPort = intPort;
        this.handler = handler;
        this.strName = strName;

        // Response handler should be thread safe
        this.responseHandlers = new ConcurrentHashMap<>();

        ssm = new SuperSocketMaster(strIP, intPort, listener);
    }

    /**
     * Method for only requesting, not response
     *
     * @param strMsgs strMessage
     */
    public synchronized void request(String[] strMsgs) {
        lockRequest.lock();
        // check if it is server or client
        if (isServer()) {
            // if server, send all
            ssm.sendText(String.format(STR_FORMAT_SENDER_MESSAGE,
                    STR_TYPE_REQUEST, intIdOn.get(), strName, STR_RECEIVER_ALL,
                    String.join(STR_MESSAGE_SPLITTER, strMsgs)));
        } else {
            // if client, send server
            ssm.sendText(String.format(STR_FORMAT_SENDER_MESSAGE,
                    STR_TYPE_REQUEST, intIdOn.get(), strName, STR_SERVER_NAME,
                    String.join(STR_MESSAGE_SPLITTER, strMsgs)));
        }
        intIdOn.incrementAndGet();
        lockRequest.unlock();
    }

    /**
     * Method for requesting to the server
     *
     * @param strMsgs the message needs to send
     * @param then    the thing to do after gaining the response
     */
    public synchronized void request(String[] strMsgs, Consumer<String[]> then) {
        lockRequest.lock();
        // put into handler
        responseHandlers.put(intIdOn.get(), then);
        // check if it is server or client
        if (isServer()) {
            // if server, send to all
            ssm.sendText(String.format(STR_FORMAT_SENDER_MESSAGE,
                    STR_TYPE_REQUEST, intIdOn.get(), strName, STR_RECEIVER_ALL,
                    String.join(STR_MESSAGE_SPLITTER, strMsgs)));
        } else {
            // if client, send to server
            ssm.sendText(String.format(STR_FORMAT_SENDER_MESSAGE,
                    STR_TYPE_REQUEST, intIdOn.get(), strName, STR_SERVER_NAME,
                    String.join(STR_MESSAGE_SPLITTER, strMsgs)));
        }
        // increase
        intIdOn.incrementAndGet();
        lockRequest.unlock();
    }

    /**
     * Method for requesting a specified destination
     *
     * @param strDest the destination of the client sending
     * @param strMsgs the message needs to send
     * @param then    the thing to do after gaining the response
     */
    public synchronized void request(String strDest, String[] strMsgs, Consumer<String[]> then) {
        lockRequest.lock();
        // put the handler
        responseHandlers.put(intIdOn.get(), then);
        // send request
        ssm.sendText(String.format(STR_FORMAT_SENDER_MESSAGE,
                STR_TYPE_REQUEST, intIdOn.get(), strName, strDest,
                String.join(STR_MESSAGE_SPLITTER, strMsgs)));
        // increase int id
        intIdOn.incrementAndGet();
        lockRequest.unlock();
    }

    /**
     * Connect the socket
     */
    public void connect() {
        ssm.connect();
    }

    /**
     * Check if the socket is server or not
     *
     * @return is server or not
     */
    public boolean isServer() {
        return strName.equals(STR_SERVER_NAME);
    }

    /**
     * Get the id of the raw message
     *
     * @param strRawMessage the raw message
     * @return the id
     */
    public static int idOf(String[] strRawMessage) {
        return Integer.parseInt(strRawMessage[INT_ID_POSITION_IN_ARRAY]);
    }

    /**
     * Get the type of the message (is request or response)
     *
     * @param strRawMessage the message
     * @return the type
     */
    public static String typeOf(String[] strRawMessage) {
        return strRawMessage[INT_TYPE_POSITION_IN_ARRAY];
    }

    /**
     * Get the name of the desired receiver of the message
     *
     * @param strRawMessage the message
     * @return the desired receiver
     */
    public static String receiverOf(String[] strRawMessage) {
        return strRawMessage[INT_RECEIVER_POSITION_IN_ARRAY];
    }

    /**
     * Get the name of the sender of the message
     *
     * @param strRawMessage the message
     * @return the sender
     */
    public static String senderOf(String[] strRawMessage) {
        return strRawMessage[INT_SENDER_POSITION_IN_ARRAY];
    }

    @Override
    public void close() throws Exception {
        ssm.disconnect();
    }
}
