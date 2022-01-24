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
public class ClientMain extends JFrame {

    /**
     * Create a name for the client
     *
     * @return the client name
     */
    public static String createName() {
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random());
    }

    /**
     * Socket
     */
    private XSocket sock;

    /**
     * Container of the moving chess
     */
    private ChessContainer container;

    /**
     * Game service
     */
    private GameService gameService;

    /**
     * Chess baord panel
     */
    private ChessBoardPanel chessBoard;

    /**
     * Help panel
     */
    private HelpPanel helpPanel;

    /**
     * Piece picking panel
     */
    private PiecePickPanel piecePickPanel;

    /**
     * Button switching between help and board
     */
    private JButton buttonHelpGameBoard;

    /**
     * Chat area
     */
    private ChatArea chatArea;

    /**
     * Connecting panel
     */
    private ConnectionPanel connectionPanel;

    /**
     * Animate panel
     */
    private AnimatePanel animatePanel;

    public ClientMain() {
        super("Games of Generals");

        // set the window
        setPreferredSize(new Dimension(1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        // animate panel, set location {0, 0}, start it
        animatePanel = new AnimatePanel(this::init);
        animatePanel.setLocation(0, 0);
        add(animatePanel);
        animatePanel.start();

        pack();
    }

    /**
     * Init method after created main
     */
    private void init() {
        // remove the animate panel
        remove(animatePanel);
        // initialize help panel
        initHelpPanel();
        add(helpPanel);
        // initialize connect panel
        initConnectPanel();
        add(connectionPanel);
        // initialize container
        initContainer();
        // repaint
        pack();
        repaint();
    }

    /**
     * Initialize the help panel
     */
    private void initHelpPanel() {
        // help panel locate at 0, 0
        helpPanel = new HelpPanel();
        helpPanel.setLocation(0, 0);
    }

    /**
     * Initialize the button switching help screen and board
     */
    private void initButtonHelpGameBoard() {
        // create the button
        buttonHelpGameBoard = new JButton("Help");
        // set button size
        buttonHelpGameBoard.setSize(380, 40);
        // set location
        buttonHelpGameBoard.setLocation(900, 300);
        // action listener
        buttonHelpGameBoard.addActionListener((e) -> {
            // if it is on help panel mode
            if (Arrays.asList(this.getContentPane().getComponents()).contains(helpPanel)) {
                // set it to chess board
                this.remove(helpPanel);
                this.add(chessBoard);
                buttonHelpGameBoard.setText("Help");
            }
            // if it is on chess board
            else if (Arrays.asList(this.getContentPane().getComponents()).contains(chessBoard)) {
                // set it to content pane
                this.remove(chessBoard);
                this.add(helpPanel);
                buttonHelpGameBoard.setText("Chess Board");
            }
            // repaint
            repaint();
        });
    }

    /**
     * Init chat area
     */
    private void initChatArea() {
        chatArea = new ChatArea(gameService);
        chatArea.setLocation(900, 0);
    }

    /**
     * Init container
     */
    private void initContainer() {
        container = new ChessContainer();
    }

    /**
     * Init connect panel
     */
    private void initConnectPanel() {
        // create connection panel
        connectionPanel = new ConnectionPanel();
        connectionPanel.setLocation(900, 160);
        // set what do after connection panel button pressed
        connectionPanel.setThen((strIP, strName) -> {
            initNetwork(strIP, strName);
            return null;
        });
    }

    /**
     * Init the network
     *
     * @param strIP   ip
     * @param strName name of user
     */
    private void initNetwork(String strIP, String strName) {
        // create socket
        this.sock = new XSocket(createName(), strIP, 8888, (strData, ignored) -> {
            // what do when update the screen
            if (strData[0].equals(STR_UPDATE)) {
                refresh();
            }
            // what do when restart the game
            if (strData[0].equals(STR_RESTART)) {
                // remove the old chess board & piece pick panel
                this.remove(chessBoard);
                this.remove(piecePickPanel);
                // initialize the chess board and piece pick panel again
                initChessBoard();
                initPiecePickPanel();
                // add
                this.add(chessBoard);
                this.add(piecePickPanel);
                // refresh
                refresh();
            }
            // if a message is sent
            if (strData[0].equals(STR_MESSAGE)) {
                // send in chat area
                this.chatArea.println(String.join(" ", Arrays.copyOfRange(strData, 1, strData.length)));
            }
        });
        // connect
        sock.connect();
        // create game service
        gameService = new GameService(sock);
        // connect
        gameService.connect(strName);

        // remove the connection panel
        remove(connectionPanel);

        // create & add button
        initButtonHelpGameBoard();
        add(buttonHelpGameBoard);

        // create & add chat area
        initChatArea();
        add(chatArea);

        // create & add chess board, remove help panel
        initChessBoard();
        remove(helpPanel);
        add(chessBoard);

        // set button text to help
        buttonHelpGameBoard.setText("Help");

        // create & add the piece pick panel
        initPiecePickPanel();
        add(piecePickPanel);

        // repaint
        repaint();
        pack();

        // refresh
        refresh();
    }

    /**
     * Init the chess board
     */
    private void initChessBoard() {
        chessBoard = new ChessBoardPanel(container, gameService) {{
            // locate at {0, 0}
            setLocation(0, 0);
        }};
    }

    /**
     * Init the piece pick panel
     */
    private void initPiecePickPanel() {
        piecePickPanel = new PiecePickPanel(chessBoard, gameService) {{
            // locate at {900, 340}
            setLocation(900, 340);
        }};
    }

    /**
     * Refresh the game board
     */
    private void refresh() {
        // refresh from callback in game service
        gameService.getBoard((board) -> {
            // count on
            int intCnt = 0;
            // go through the rows & cols
            for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow++) {
                for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol++) {
                    // get the panel on
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
            // repaint
            this.repaint();
            this.pack();
        });
    }

    /**
     * Main method
     *
     * @param args arguments
     * @throws Exception ignored exception
     */
    public static void main(String[] args) throws Exception {
        // set UIManager
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // client main
        new ClientMain() {{
            // pack
            pack();
            // set visible as true
            setVisible(true);
        }};
    }
}
