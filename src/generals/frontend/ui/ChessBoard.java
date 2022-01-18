package generals.frontend.ui;

import generals.backend.Chess;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class ChessBoard {
    ChessPanel[][] panel = new ChessPanel[8][9];

    public ChessPanel[][] getPanel() {
        return panel;
    }
}
