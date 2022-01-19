package generals.frontend.ui;

import javax.swing.*;

/**
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class HelpPanel extends JPanel {

    public HelpPanel() {
        JFrame frame = new JFrame("HelpPanel");
        frame.setVisible(true);
        frame.setSize(900,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new Jlabel("Try to move the piece by dragging");
        JPanel panel = new JPanel();
    }
}
