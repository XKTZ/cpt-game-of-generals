package generals.frontend.ui;

import javax.swing.*;
import java.awt.*;

import generals.backend.GameBoard;
import generals.frontend.FrontendService;
import generals.frontend.GameBoardService;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class ChessBoard extends JPanel {

    private ChessPanel[][] panel = new ChessPanel[GameBoard.INT_ROWS + 1][GameBoard.INT_COLS + 1];

    private ChessContainer container;

    private FrontendService frontendService;

    private GameBoardService gameBoardService;

    public ChessBoard(int intPlayer,
                      ChessContainer container, FrontendService frontendService, GameBoardService gameBoardService) {
        super(new GridLayout(GameBoard.INT_ROWS, GameBoard.INT_COLS));

        setSize(900, 720);

        this.container = container;
        this.frontendService = frontendService;
        this.gameBoardService = gameBoardService;

        for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow++) {
            for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol++) {
                panel[intRow][intCol] = new ChessPanel(intRow, intCol, container, frontendService, gameBoardService);
                add(panel[intRow][intCol], GameBoard.INT_ROWS - intRow, intCol - 1);
            }
        }
    }

    public ChessPanel[][] getPanels() {
        return panel;
    }

    public ChessContainer getContainer() {
        return container;
    }
}
