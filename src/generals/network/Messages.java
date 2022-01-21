package generals.network;

import java.util.Arrays;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public interface Messages {

    int INT_FAILED = 0;

    int INT_SUCCESS = 1;

    String STR_CONNECT = "connect";

    String STR_PUT = "put";

    String STR_READY = "ready";

    String STR_AVAILABLE = "available";

    String STR_MOVE = "move";

    String STR_BOARD = "board";

    String STR_UPDATE = "update";

    String STR_SEND_MESSAGE = "sendMessage";

    String STR_MESSAGE = "message";

    String STR_RESTART = "restart";

    /**
     * Turn variadic args to array
     *
     * @return array
     */
    static String[] messageOf(Object... str) {
        return Arrays.stream(str).map(Object::toString).toArray(String[]::new);
    }
}
