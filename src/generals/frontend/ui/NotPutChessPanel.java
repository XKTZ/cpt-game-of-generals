package generals.frontend.ui;

import generals.frontend.GameService;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class NotPutChessPanel extends ChessPanel {

    /**
     * Type of chess
     */
    private int intType;

    /**
     * Create not putten chess panel by provide type
     *
     * @param intType         type
     * @param chessBoardPanel chess board
     */
    public NotPutChessPanel(int intType, ChessBoardPanel chessBoardPanel, GameService gameService) {
        super(0, 0, chessBoardPanel, gameService);
        setPreferredSize(new Dimension(100, 90));
        this.intType = intType;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // low light
        chessBoardPanel.lowlightAll();
        // remove this
        gameService.put(chessBoardPanel.getContainer().getXTo(), chessBoardPanel.getContainer().getYTo(), intType, this);
    }
}
