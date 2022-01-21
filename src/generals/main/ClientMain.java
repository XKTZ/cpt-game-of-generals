package generals.main;

import generals.backend.Chess;
import generals.backend.GameBoard;
import generals.frontend.GameService;
import generals.frontend.ui.*;
import generals.frontend.ChessContainer;
import generals.network.XSocket;
import generals.util.log.Loggable;
import jdk.swing.interop.SwingInterOpUtils;

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

    private ChessContainer container;

    private GameService gameService;

    private ChessBoardPanel chessBoard;

    private HelpPanel helpPanel;

    private PiecePickPanel piecePickPanel;

    private JButton buttonHelpGameBoard;

    public ClientMain() {
        super("Games of Generals");

        helpPanel = new HelpPanel();
        helpPanel.setLocation(0, 0);

        buttonHelpGameBoard = new JButton("Help");
        buttonHelpGameBoard.setSize(380, 40);
        buttonHelpGameBoard.setLocation(900, 300);
        buttonHelpGameBoard.addActionListener((e) -> {
            // if it is on help panel mode
            if (Arrays.asList(this.getContentPane().getComponents()).contains(helpPanel)) {
                System.out.println("Case A");
                this.remove(helpPanel);
                this.add(chessBoard);
                buttonHelpGameBoard.setText("Help");
            } else if (Arrays.asList(this.getContentPane().getComponents()).contains(chessBoard)) {
                System.out.println("Case B");
                this.remove(chessBoard);
                this.add(helpPanel);
                buttonHelpGameBoard.setText("Chess Board");
            }
            this.getContentPane().repaint();
        });
        add(buttonHelpGameBoard);

        setPreferredSize(new Dimension(1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        this.sock = new XSocket(createName(), "localhost", 8888, (strData, ignored) -> {
            if (strData[0].equals(STR_UPDATE)) {
                refresh();
            }
            if (strData[0].equals(STR_RESTART)) {
                this.remove(chessBoard);
                this.remove(piecePickPanel);
                chessBoard = getChessBoard();
                piecePickPanel = getPiecePickPanel();
                this.add(chessBoard);
                this.add(piecePickPanel);
                refresh();
            }
        });
    }

    private void refresh() {
        gameService.getBoard((board) -> {
            int intCnt = 0;
            for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow++) {
                for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol++) {
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
                    intCnt++;
                }
            }
            this.repaint();
            this.pack();
        });
    }

    @Override
    public void run() {
        sock.connect();

        gameService = new GameService(sock);

        gameService.connect("yidi");

        container = new ChessContainer();

        chessBoard = getChessBoard();

        piecePickPanel = getPiecePickPanel();

        add(chessBoard);

        add(piecePickPanel);


        pack();
        setVisible(true);
    }

    private ChessBoardPanel getChessBoard() {
        return new ChessBoardPanel(container, gameService) {{
            setLocation(0, 0);
        }};
    }

    private PiecePickPanel getPiecePickPanel() {
        return new PiecePickPanel() {{
            init(chessBoard, gameService);
            setLocation(900, 340);
        }};
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new ClientMain().run();
    }
}
