package generals.frontend;

import generals.frontend.ui.ChessBoard;
import generals.network.XSocket;
import generals.util.Coordinate;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class FrontendService {

    private ChessBoard board;

    private XSocket socket;

    public FrontendService(ChessBoard board) {
        this.board = board;
    }

    /**
     * highlight all coordinates available by moving from {x, y}
     * @param intX intX
     * @param intY intY
     */
    public void highlightAvailable(int intX, int intY) {

    }

    private void highlightAll(Coordinate[] coordinates) {
        for (Coordinate coordinate: coordinates) {
            board.getPanel()[coordinate.intX][coordinate.intY].highlight();
        }
    }

    private void lowlightAll() {

    }
}
