package generals.frontend.ui;

import generals.frontend.GameService;

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
     * @param intType   type
     * @param chessBoard chess board
     */
    public NotPutChessPanel(int intType, ChessBoard chessBoard, GameService gameService) {
        super(0, 0, chessBoard, gameService);
        setSize(100, 90);
        this.intType = intType;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // low light
        chessBoard.lowlightAll();
        // remove this
        boolean blnSuc = gameService.put(chessBoard.getContainer().getXTo(), chessBoard.getContainer().getYTo(), intType);
        var parent = this.getParent();
        parent.remove(this);
        parent.repaint();
    }
}
