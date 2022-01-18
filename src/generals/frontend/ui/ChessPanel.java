package generals.frontend.ui;

import generals.frontend.FrontendService;

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

    private static final LineBorder BORDER_LOW_LIGHT = new LineBorder(Color.BLACK, 2);

    private static final LineBorder BORDER_HIGH_LIGHT = new LineBorder(Color.YELLOW, 2);

    private int intX, intY;

    private ChessContainer container;

    private FrontendService frontendService;


    public ChessPanel(int intX, int intY, ChessContainer container, FrontendService service) {
        super();

        this.intX = intX;
        this.intY = intY;
        this.container = container;
        this.frontendService = service;

        addMouseListener(this);

        setBorder(BORDER_LOW_LIGHT);
    }

    /**
     * Set border yellow
     */
    public void highlight() {
        setBorder(BORDER_HIGH_LIGHT);
    }

    /**
     * Set border black
     */
    public void lowlight() {
        setBorder(BORDER_LOW_LIGHT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        frontendService.highlightAvailable(intX, intY);
        container.setFrom(intX, intY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        frontendService.lowlightAll();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        container.setTo(intX, intY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
