package generals.main;

import generals.backend.GameBoard;
import generals.frontend.GameService;
import generals.frontend.ui.ChessBoardPanel;
import generals.frontend.ChessContainer;
import generals.frontend.ui.NotPutChessPanel;
import generals.network.XSocket;

import javax.swing.*;
import java.awt.*;

import static generals.network.Messages.*;

/**
 * Main class for client
 *
 * @author Yidi Chen
 * @date 2021-12-28
 */
public class ClientMain implements Runnable {

    public static String getName() {
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random());
    }

    private XSocket sock;

    private GameService gameService;

    public ClientMain() {
        this.sock = new XSocket(getName(), "localhost", 8888, (strData, ignored) -> {

            if (strData[0].equals(STR_UPDATE)) {
                gameService.getBoard((board) -> {
                    int intCnt = 0;
                    for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow ++) {
                        for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol ++) {
                            System.out.print(board[intCnt] + " ");
                            intCnt  ++;
                        }
                        System.out.println();
                    }
                    System.out.println("===============================");
                });
            }

        });
    }

    @Override
    public void run() {
        sock.connect();

        gameService = new GameService(sock);

        gameService.connect("yidi");

        gameService.ready();

        JFrame frame = new JFrame("test");
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(null);

        ChessContainer container = new ChessContainer();

        var chessBoard = new ChessBoardPanel(1, container, gameService);
        chessBoard.setLocation(0, 0);

        frame.add(chessBoard);

        NotPutChessPanel notPutChessPanel = new NotPutChessPanel(5,
                chessBoard, gameService);
        notPutChessPanel.setLocation(900, 100);

        frame.add(notPutChessPanel);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new ClientMain().run();
    }
}
