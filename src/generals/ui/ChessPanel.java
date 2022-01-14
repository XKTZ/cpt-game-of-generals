package generals.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Yidi Chen
 * @date 2022-01-12
 */
public class ChessPanel extends JPanel implements MouseListener {



    private int intX, intY;

    private ChessContainer container;


    public ChessPanel(int intX, int intY, ChessContainer container) {
        super();

        this.intX = intX;
        this.intY = intY;
        this.container = container;

        addMouseListener(this);

        setBorder(new LineBorder(Color.BLACK, 2));
    }

    /**
     * Make a circle inside
     */
    public void highlight() {
    }

    /**
     * Make circle inside disappear
     */
    public void lowlight() {

    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        container.setFrom(intX, intY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        container.setTo(intX, intY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
