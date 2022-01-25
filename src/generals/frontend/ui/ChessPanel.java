package generals.frontend.ui;

import generals.frontend.GameImages;
import generals.frontend.GameService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Panel saving the chess
 *
 * @author Yidi Chen
 * @date 2022-01-12
 */
public class ChessPanel extends JPanel implements MouseListener {

    /**
     * border low light
     */
    private static final LineBorder BORDER_LOW_LIGHT = new LineBorder(Color.BLACK, 2);

    /**
     * border high light
     */
    private static final LineBorder BORDER_HIGH_LIGHT = new LineBorder(Color.YELLOW, 2);

    /**
     * X axis
     */
    protected int intX;

    /**
     * Y axis
     */
    protected int intY;

    /**
     * Type
     */
    protected int intType;

    /**
     * Chess board panel
     */
    protected ChessBoardPanel chessBoardPanel;

    /**
     * Game service
     */
    protected GameService gameService;

    /**
     * Chess panel constructor method
     *
     * @param intX        x axis
     * @param intY        y axis
     * @param board       board
     * @param gameService game service
     */
    public ChessPanel(int intX, int intY, ChessBoardPanel board, GameService gameService) {
        super();

        // set the x, y, and type
        this.intX = intX;
        this.intY = intY;
        this.intType = 0;

        // set container, chess board, and game service
        this.chessBoardPanel = board;
        this.gameService = gameService;

        // add listener
        addMouseListener(this);

        // add border
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

    /**
     * Set the type
     * @param intType type
     */
    public void setType(int intType) {
        this.intType = intType;
        this.repaint();
    }

    /**
     * Get the type
     * @return the type
     */
    public int getType() {
        return intType;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (intType == -1) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 100, 90);
        } else {
            g.drawImage(GameImages.LEVEL_IMAGES[intType], 0, 0, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Method when mouse pressed
     * @param e event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // highlight available positions
        chessBoardPanel.highlightAvailable(intX, intY);
    }

    /**
     * When mouse released, move the chess at this position to {xto, yto}
     * @param e event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // lowlight all chess
        chessBoardPanel.lowlightAll();
        // move
        gameService.move(intX, intY, chessBoardPanel.getContainer().getX(), chessBoardPanel.getContainer().getY());
    }

    /**
     * When mouse entered, set the xto and yto in container to this position
     * @param e event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        chessBoardPanel.getContainer().setTo(intX, intY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
