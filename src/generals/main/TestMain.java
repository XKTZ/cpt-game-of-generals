package generals.main;

import generals.frontend.GameService;
import generals.frontend.HelpChessContainer;
import generals.frontend.ui.ChessBoardPanel;
import generals.frontend.ChessContainer;
import generals.frontend.ui.HelpChessPanel;
import generals.frontend.ui.HelpPanel;
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
        HelpPanel helpPanel = new HelpPanel();
        helpPanel.setLocation(0, 0);
        frame.add(helpPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
