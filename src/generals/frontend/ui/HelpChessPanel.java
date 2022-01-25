package generals.frontend.ui;

import generals.frontend.GameImages;
import generals.frontend.HelpChessContainer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Chess panel on help panel
 *
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class HelpChessPanel extends JPanel implements MouseListener {

    /**
     * border lowlight
     */
    private static final LineBorder BORDER_LOW_LIGHT = new LineBorder(Color.BLACK, 2);

    /**
     * border highlight
     */
    private static final LineBorder BORDER_HIGH_LIGHT = new LineBorder(Color.YELLOW, 2);

    /**
     * Container of chess
     */
    private HelpChessContainer container;

    /**
     * Next chess panel
     */
    private HelpChessPanel next;

    /**
     * Is chess on this panel
     */
    private boolean blnChessOn;

    /**
     * Constructor of chess panel
     *
     * @param container chess container
     */
    public HelpChessPanel(HelpChessContainer container) {
        super(null);
        init(container);
    }

    /**
     * Constructor of chess panel
     *
     * @param container container
     * @param next      next chess
     */
    public HelpChessPanel(HelpChessContainer container, HelpChessPanel next) {
        super(null);
        this.next = next;
        init(container);
    }

    /**
     * Initialize
     *
     * @param container container
     */
    private void init(HelpChessContainer container) {
        // initialize
        this.container = container;
        blnChessOn = false;
        // set swing properties
        setSize(100, 90);
        setBorder(BORDER_LOW_LIGHT);
        addMouseListener(this);
    }

    /**
     * Set next chess panel
     *
     * @param next next chess panel
     */
    public void setNext(HelpChessPanel next) {
        this.next = next;
    }

    /**
     * Highlight
     */
    public void highlight() {
        setBorder(BORDER_HIGH_LIGHT);
    }

    /**
     * Lowlight
     */
    public void lowlight() {
        setBorder(BORDER_LOW_LIGHT);
    }

    /**
     * Set chess on
     *
     * @param blnChessOn boolean going to set
     */
    public void setChessOn(boolean blnChessOn) {
        this.blnChessOn = blnChessOn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * If mouse pressed, set highlight next if chess is on it
     *
     * @param e event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (!blnChessOn) {
            return;
        }
        next.highlight();
    }

    /**
     * If mouse released and chess is on it, let chess go to  next
     *
     * @param e event
     */
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

    /**
     * If mouse entered, set container panel to this
     *
     * @param e event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        container.setPanel(this);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Paint component
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // if chess is on it, paint a flag
        if (blnChessOn) {
            g.drawImage(GameImages.IMAGE_FLAG, 0, 0, null);
        }
    }
}
