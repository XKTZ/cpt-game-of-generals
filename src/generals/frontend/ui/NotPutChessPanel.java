package generals.frontend.ui;

import generals.frontend.FrontendService;
import generals.frontend.GameBoardService;

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
     * @param container container
     * @param frontendService   frontendService
     */
    public NotPutChessPanel(int intType, ChessContainer container, FrontendService frontendService, GameBoardService gameBoardService) {
        super(0, 0, container, frontendService, gameBoardService);
        setSize(100, 90);
        this.intType = intType;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        frontendService.highlightAvailable(intX, intY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        frontendService.lowlightAll();
        gameBoardService.put(container.getXTo(), container.getYTo(), intType);
        var parent = this.getParent();
        parent.remove(this);
        parent.repaint();
    }
}
