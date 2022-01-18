package generals.frontend.ui;

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
    int intA = 1200;

    int intCnt = 0;


    Timer timer;

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
        g2d.drawString("Games of Generals", intA, 100);
        g2d.drawString("Enjoy", intX, 150);
        intCnt++;
        if (intCnt >= 100) {
            timer.stop();
            return;
        }
        intX += 20;
        intA -= 20;

        if (intX > getWidth()) {
            intX = 0;
        }
        if (intA < 0) {
            intA = 1200;
        }
    }

    public void start() {
        timer = new Timer(50, (e) -> {
            this.repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
