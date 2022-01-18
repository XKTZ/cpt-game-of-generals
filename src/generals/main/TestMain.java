package generals.main;

import generals.frontend.ui.ChessBoard;

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

        frame.add(new ChessBoard(1) {{
            setLocation(0, 0);
        }});
        frame.pack();
        frame.setVisible(true);
    }
}
