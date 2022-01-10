package generals.main;

import generals.network.XSocket;
import util.SuperSocketMaster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static XSocket sock;

    public static void main(String[] args) throws IOException {
        sock = new XSocket(getName(), "localhost", 8888, (a, b) -> {});

        sock.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String userMessage = reader.readLine();

        if (userMessage.equals("1")){
            sock.request(arrayOf("1"), (response) -> {
                String strResponse = response[0];
                strResponse += "world";
                System.out.println(strResponse);
            });
        } else if (userMessage.equals("2")) {
            sock.request(arrayOf("2"), (response) -> {
                String strResponse = response[0];
                strResponse += "world";
                System.out.println(strResponse);
            });
        }
    }

    public static String[] arrayOf(String ...arr) {
        return arr;
    }
}
