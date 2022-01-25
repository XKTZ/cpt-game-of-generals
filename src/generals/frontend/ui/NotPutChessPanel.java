package generals.frontend.ui;

import generals.frontend.GameService;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Chess panel not put yet
 *
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class NotPutChessPanel extends ChessPanel {

    /**
     * Create not put chess panel by provide type
     *
     * @param intType         type
     * @param chessBoardPanel chess board
     */
    public NotPutChessPanel(int intType, ChessBoardPanel chessBoardPanel, GameService gameService) {
        super(0, 0, chessBoardPanel, gameService);
        // set preferred size to 100x90
        setPreferredSize(new Dimension(100, 90));

        // set type
        this.intType = intType;
    }

    /**
     * Paint component
     *
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Mouser released, low light all on chess panel, put the chess
     *
     * @param e event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // low light
        chessBoardPanel.lowlightAll();
        // remove this
        gameService.put(chessBoardPanel.getContainer().getX(), chessBoardPanel.getContainer().getY(), intType, this);
    }
}
