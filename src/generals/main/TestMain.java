package generals.main;

import generals.ui.AnimatePanel;

import javax.swing.*;

/**
 * @author Yidi Chen
 * @date 2022-01-10
 */
public class TestMain {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(new AnimatePanel());
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
