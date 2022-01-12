package generals.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Animating panel at the beginning of game
 *
 * @author Yidi Chen
 * @date 2022-01-11
 */
public class AnimatePanel extends JPanel  {

    int x=0;
    int y=100;
    int a =400;
    int b =200;
    /**
     * Animate panel
     */
    public AnimatePanel() {

    }

    public void paint(Graphics gp) {
        super.paint(gp);
        Graphics2D g2d= (Graphics2D) gp;
        g2d.drawString("Welcome to",x,y);
        g2d.drawString("Games of Generals",a,b);
        g2d.drawString("Enjoy",x,300);
            try{
                Thread.sleep(200);
                x+=20;
                a-=20;

                if(x>getWidth()){
                    x=0;
                }
                if(a<0){
                    a=500;
                }
                repaint();
            } catch (InterruptedException ex){
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
