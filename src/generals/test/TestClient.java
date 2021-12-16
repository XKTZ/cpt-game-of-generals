package generals.test;

import generals.network.XSocket;

import java.util.Arrays;

/**
 * @author Yidi Chen
 * @date 2021-12-15
 */
public class TestClient {

    public static void main(String[] args) {
        XSocket socket = new XSocket("client", "127.0.0.1", 8888, (strData, responder) -> {
            System.out.println(Arrays.toString(strData));
            responder.accept(strData);
        });
        socket.connect();
        socket.request(new String[]{"Hello", "World!"}, data -> {
            System.out.println(Arrays.toString(data));
        });
    }
}
