package generals.main;

import generals.backend.GameBoard;
import generals.frontend.GameService;
import generals.frontend.ui.ChessBoardPanel;
import generals.frontend.ChessContainer;
import generals.frontend.ui.NotPutChessPanel;
import generals.frontend.ui.PiecePickPanel;
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
public class ClientMain extends JFrame implements Runnable {

    public static String createName() {
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random());
    }

    private XSocket sock;

    private GameService gameService;

    public ClientMain() {
        super("Games of Generals");
        setPreferredSize(new Dimension(1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        this.sock = new XSocket(createName(), "localhost", 8888, (strData, ignored) -> {

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



        ChessContainer container = new ChessContainer();

        var chessBoard = new ChessBoardPanel(1, container, gameService);
        chessBoard.setLocation(0, 0);

        add(chessBoard);

        PiecePickPanel piecePickPanel = new PiecePickPanel();
        piecePickPanel.init(chessBoard, gameService);
        piecePickPanel.setLocation(900, 340);
        add(piecePickPanel);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new ClientMain().run();
    }
}
