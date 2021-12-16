package generals.network;

import java.util.function.Consumer;

/**
 * Request handler is an interface using for responding the requests
 *
 * @author Yidi Chen
 * @date 2021-12-15
 */
public interface RequestHandler {

    /**
     * Do a response for the request
     *
     * @param strData   the requesting data
     * @param responder the responding method. It should be provided by the XSocket instead of programmer
     */
    void response(String[] strData, Consumer<String[]> responder);
}
