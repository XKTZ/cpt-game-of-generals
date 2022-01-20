package generals.frontend.ui;

import generals.frontend.GameImages;
import generals.frontend.GameService;

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

    protected int intX, intY;

    protected int intType;

    protected ChessBoardPanel chessBoardPanel;

    protected GameService gameService;

    public ChessPanel(int intX, int intY, ChessBoardPanel board, GameService gameService) {
        super();

        this.intX = intX;
        this.intY = intY;
        this.intType = 0;

        // set container, chess board, and game service
        this.chessBoardPanel = board;
        this.gameService = gameService;

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

    public void setType(int intType) {
        this.intType = intType;
        this.repaint();
    }

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

    @Override
    public void mousePressed(MouseEvent e) {
        chessBoardPanel.highlightAvailable(intX, intY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        chessBoardPanel.lowlightAll();
        gameService.move(intX, intY, chessBoardPanel.getContainer().getX(), chessBoardPanel.getContainer().getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        chessBoardPanel.getContainer().setTo(intX, intY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
