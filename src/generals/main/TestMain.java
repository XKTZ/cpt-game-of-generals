package generals.main;

import generals.ui.AnimatePanel;
import generals.ui.ChatArea;
import generals.ui.ChessContainer;
import generals.ui.ChessPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yidi Chen
 * @date 2022-01-10
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame jf = new JFrame();
        jf.setSize(1280, 720);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        ChessContainer container = new ChessContainer();

        panel.add(new ChessPanel(1, 1, container));

        panel.add(new ChessPanel(2, 1, container));

        jf.setContentPane(panel);

        jf.setVisible(true);
    }
}
