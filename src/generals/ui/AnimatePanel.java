package generals.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Animating panel at the beginning of game
 *
 * @author Yidi Chen
 * @date 2022-01-11
 */
public class AnimatePanel extends JPanel {

    int intX = 0;
    int inta = 1200;

    /**
     * Animate panel
     */
    public AnimatePanel() {

    }

    @Override
    public void paint(Graphics gp) {
        super.paint(gp);
        Graphics2D g2d = (Graphics2D) gp;
        g2d.drawString("Welcome to", intX, 50);
        g2d.drawString("Games of Generals", inta, 100);
        g2d.drawString("Enjoy", intX, 150);
        try {
            Thread.sleep(200);
            intX += 20;
            inta -= 20;

            if (intX > getWidth()) {
                intX = 0;
            }
            if (inta < 0) {
                inta = 1200;
            }
            repaint();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    public void start() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
