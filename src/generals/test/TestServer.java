package generals.test;

import generals.network.XSocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yidi Chen
 * @date 2021-12-15
 */
public class TestServer {

    XSocket socket;

    public void run() {
        socket = new XSocket(8888, (strData, responder) -> {
            responder.accept(strData);
            this.socket.request(new ArrayList<>(List.of(strData)){{
                add("aaa");
            }}.toArray(new String[]{}), (strings) -> {
                System.out.println(Arrays.toString(strings));
            });
        });
        socket.connect();
    }

    public static void main(String[] args) {
        new TestServer().run();
    }
}
