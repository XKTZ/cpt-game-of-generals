package generals.main;

import generals.network.XSocket;

/**
 * Main class for server
 *
 * @author Yidi Chen
 * @date 2021-12-28
 */
public class ServerMain {

    public static void main(String[] args) {
        var sock = new XSocket(8888, (req, sender) -> {
            System.out.println(req[0]);
            sender.accept(req);
        });
        sock.connect();
    }
}
