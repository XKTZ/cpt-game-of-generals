package generals.main;

import generals.frontend.FrontendService;
import generals.frontend.GameBoardService;
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
        FrontendService frontendService = new FrontendService(1);
        GameBoardService gameBoardService = new GameBoardService(null);

        var chessBoard = new ChessBoard(1, container, frontendService, gameBoardService) {
            {
                setLocation(0, 0);
            }

        };

        frontendService.setBoard(chessBoard);

        frame.add(chessBoard);

        NotPutChessPanel notPutChessPanel = new NotPutChessPanel(5,
                chessBoard.getContainer(), frontendService, gameBoardService);
        notPutChessPanel.setLocation(900, 100);
        frame.add(notPutChessPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
