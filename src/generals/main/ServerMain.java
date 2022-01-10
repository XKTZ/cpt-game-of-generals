package generals.main;

import generals.network.XSocket;

/**
 * Main class for server
 *
 * @author Yidi Chen
 * @date 2021-12-28
 */
public class ServerMain {

    static XSocket sock;

    public static void main(String[] args) {
        sock = new XSocket(8888, (req, sender) -> {
            if (req[0].equals("1")) {
                sender.accept(arrayOf("hello")); // send back to client
            }
            if (req[0].equals("2")) {
                sender.accept(arrayOf("bye"));
            }
        });
        sock.connect();
    }

    public static String[] arrayOf(String ...arr) {
        return arr;
    }
}
