package generals.frontend.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import generals.backend.GameBoard;
import generals.frontend.ChessContainer;
import generals.frontend.GameService;
import generals.util.Coordinate;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class ChessBoardPanel extends JPanel {

    private ChessPanel[][] panels = new ChessPanel[GameBoard.INT_ROWS + 1][GameBoard.INT_COLS + 1];

    private ChessContainer container;

    private GameService gameService;

    public ChessBoardPanel(ChessContainer container, GameService gameService) {
        super(new GridLayout(GameBoard.INT_ROWS, GameBoard.INT_COLS));

        setSize(900, 720);

        this.container = container;
        this.gameService = gameService;

        for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow++) {
            for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol++) {
                panels[intRow][intCol] = new ChessPanel(intRow, intCol, this, gameService);
                add(panels[intRow][intCol], GameBoard.INT_ROWS - intRow, intCol - 1);
            }
        }
    }

    /**
     * highlight all coordinates available by moving from {x, y}
     *
     * @param intX intX
     * @param intY intY
     */
    public void highlightAvailable(int intX, int intY) {
        gameService.available(intX, intY, this::highlightCoordinates);
    }

    public void lowlightAll() {
        for (ChessPanel[] coordinateArrays : panels) {
            for (ChessPanel panel : coordinateArrays) {
                if (panel != null) {
                    panel.lowlight();
                }
            }
        }
    }

    /**
     * Hightlight the coordinates
     * @param coordinates coordinates
     */
    private void highlightCoordinates(Coordinate[] coordinates) {
        for (Coordinate coordinate : coordinates) {
            panels[coordinate.intX][coordinate.intY].highlight();
        }
    }

    /**
     * Get the panels
     * @return the panel
     */
    public ChessPanel[][] getPanels() {
        return panels;
    }

    public ChessContainer getContainer() {
        return container;
    }
}
