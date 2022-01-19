package generals.main;

import generals.frontend.GameService;
import generals.frontend.ui.ChessBoard;
import generals.frontend.ui.ChessContainer;
import generals.frontend.ui.NotPutChessPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yidi Chen
 * @date 2022-01-10
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("test");
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(null);

        ChessContainer container = new ChessContainer();
        GameService gameService = new GameService(null);

        var chessBoard = new ChessBoard(1, container, gameService) {
            {
                setLocation(0, 0);
            }

        };

        frame.add(chessBoard);

        NotPutChessPanel notPutChessPanel = new NotPutChessPanel(5,
                chessBoard, gameService);
        notPutChessPanel.setLocation(900, 100);
        frame.add(notPutChessPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
