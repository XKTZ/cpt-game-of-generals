package generals.main;

import generals.network.XSocket;
import generals.frontend.ui.MainFrame;

import java.io.IOException;

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

    static XSocket sock;

    public static void main(String[] args) throws IOException {
        new MainFrame();
    }

    public static String[] arrayOf(String ...arr) {
        return arr;
    }
}
