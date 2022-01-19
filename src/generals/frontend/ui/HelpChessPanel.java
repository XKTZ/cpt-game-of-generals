package generals.frontend.ui;

import generals.frontend.HelpChessContainer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class HelpChessPanel extends JPanel implements MouseListener {

    private static final LineBorder BORDER_LOW_LIGHT = new LineBorder(Color.BLACK, 2);

    private static final LineBorder BORDER_HIGH_LIGHT = new LineBorder(Color.YELLOW, 2);

    private HelpChessContainer container;

    private HelpChessPanel next;

    private boolean blnChessOn;

    public HelpChessPanel(HelpChessContainer container) {
        super(null);
        init(container);
    }

    public HelpChessPanel(HelpChessContainer container, HelpChessPanel next) {
        super(null);
        this.next = next;
        init(container);
    }

    private void init(HelpChessContainer container) {
        this.container = container;
        blnChessOn = false;
        setSize(100, 90);
        setBorder(BORDER_LOW_LIGHT);
        addMouseListener(this);
    }

    public void setNext(HelpChessPanel next) {
        this.next = next;
    }

    public void highlight() {
        setBorder(BORDER_HIGH_LIGHT);
    }

    public void lowlight() {
        setBorder(BORDER_LOW_LIGHT);
    }

    public void setChessOn(boolean blnChessOn) {
        this.blnChessOn = blnChessOn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!blnChessOn) {
            return;
        }
        next.highlight();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!blnChessOn) {
            return;
        }
        next.lowlight();
        this.blnChessOn = false;
        container.getPanel().blnChessOn = true;
        this.getParent().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        container.setPanel(this);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(); // draw the flag
    }
}
