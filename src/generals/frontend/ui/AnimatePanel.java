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

    /**
     * Font
     */
    private static final Font FONT = new Font("Times New Roman", Font.PLAIN, 30);

    /**
     * Left side X
     */
    private int intX = 0;
    /**
     * Right side X
     */
    int intA = 1200;

    /**
     * Number of frames go
     */
    private int intCnt = 0;

    /**
     * What should do after finish
     */
    private Runnable next;

    /**
     * The timer
     */
    private Timer timer;

    /**
     * Animate panel
     * @param next what should do after finish
     */
    public AnimatePanel(Runnable next) {
        // set size
        setSize(1280, 720);
        // set layout
        setLayout(null);
        // set next
        this.next = next;
    }

    @Override
    public void paint(Graphics gp) {
        super.paint(gp);

        // draw the string
        Graphics2D g2d = (Graphics2D) gp;
        g2d.setColor(Color.BLACK);
        g2d.setFont(FONT);
        g2d.drawString("Welcome to", intX, 50);
        g2d.drawString("Games of Generals", intA, 100);
        g2d.drawString("Enjoy", intX, 150);

        // frame count up
        intCnt++;

        // if frame count is enough
        if (intCnt >= 150) {
            // stop timer & run next
            timer.stop();
            next.run();
            return;
        }

        // add x
        intX += 10;
        // add a
        intA -= 10;

        // if X or A exceeds, reset
        if (intX > getWidth()) {
            intX = 0;
        }
        if (intA < 0) {
            intA = 1280;
        }
    }

    /**
     * Start the animate panel
     */
    public void start() {
        // create & start the timer
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
