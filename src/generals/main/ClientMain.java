package generals.main;

import generals.backend.Chess;
import generals.backend.GameBoard;
import generals.frontend.GameService;
import generals.frontend.ui.ChessBoardPanel;
import generals.frontend.ChessContainer;
import generals.frontend.ui.ChessPanel;
import generals.frontend.ui.NotPutChessPanel;
import generals.frontend.ui.PiecePickPanel;
import generals.network.XSocket;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    private ChessBoardPanel chessBoard;

    public ClientMain() {
        super("Games of Generals");
        setPreferredSize(new Dimension(1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        this.sock = new XSocket(createName(), "localhost", 8888, (strData, ignored) -> {
            System.out.println(Arrays.toString(strData));
            if (strData[0].equals(STR_UPDATE)) {
                gameService.getBoard((board) -> {
                    int intCnt = 0;
                    for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow ++) {
                        for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol ++) {
                            var panelOn = chessBoard.getPanels()[intRow][intCol];
                            // get the data
                            int[] intData = Arrays.stream(board[intCnt].split("-")).mapToInt(Integer::parseInt).toArray();
                            // set the panel on
                            if (intData[1] == Chess.INT_EMPTY_SPACE) {
                                panelOn.setType(Chess.INT_EMPTY_SPACE);
                            } else if (intData[0] != gameService.getPlayer()) {
                                panelOn.setType(Chess.INT_OCCUPIED_BY_OTHERS);
                            } else {
                                panelOn.setType(intData[1]);
                            }
                            // repaint
                            panelOn.repaint();

                            // add cnt
                            intCnt ++;
                        }
                    }
                });
            }

        });
    }

    @Override
    public void run() {
        sock.connect();

        gameService = new GameService(sock);

        gameService.connect("yidi");

        ChessContainer container = new ChessContainer();

        chessBoard = new ChessBoardPanel(1, container, gameService);
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
