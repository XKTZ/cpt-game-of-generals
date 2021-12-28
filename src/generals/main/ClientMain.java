package generals.main;

import generals.network.XSocket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class for client
 *
 * @author Yidi Chen
 * @date 2021-12-28
 */
public class ClientMain {

    public static String getName() {
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random());
    }

    public static void main(String[] args) {
        var sock = new XSocket(getName(), "localhost", 8888, (req, send) -> {
            send.accept(req);
        });

        sock.connect();
    }
}
