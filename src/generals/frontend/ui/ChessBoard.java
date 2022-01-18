package generals.frontend.ui;

import generals.backend.Chess;

import javax.swing.*;
import java.awt.*;

import generals.backend.GameBoard;
import generals.frontend.FrontendService;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class ChessBoard extends JPanel {

    private ChessPanel[][] panel = new ChessPanel[GameBoard.INT_ROWS + 1][GameBoard.INT_COLS + 1];

    private ChessContainer container = new ChessContainer();

    private FrontendService service;

    public ChessBoard(int intPlayer) {
        super(new GridLayout(GameBoard.INT_ROWS, GameBoard.INT_COLS));

        service = new FrontendService(intPlayer, this);

        setSize(900, 720);

        for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow ++) {
            for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol ++) {
                panel[intRow][intCol] = new ChessPanel(intRow, intCol, container, service);
                add(panel[intRow][intCol], GameBoard.INT_ROWS - intRow, intCol - 1);
            }
        }
    }

    public ChessPanel[][] getPanel() {
        return panel;
    }
}
