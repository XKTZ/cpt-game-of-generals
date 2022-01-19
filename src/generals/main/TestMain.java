package generals.main;

import generals.frontend.GameService;
import generals.frontend.HelpChessContainer;
import generals.frontend.ui.ChessBoardPanel;
import generals.frontend.ChessContainer;
import generals.frontend.ui.HelpChessPanel;
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

        frame.setLayout(new GridLayout(1, 2));

        frame.pack();
        frame.setVisible(true);
    }
}
