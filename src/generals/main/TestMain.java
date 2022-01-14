package generals.main;

import generals.ui.AnimatePanel;
import generals.ui.ChatArea;

import javax.swing.*;

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
        var area = new ChatArea();
        jf.add(area);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
