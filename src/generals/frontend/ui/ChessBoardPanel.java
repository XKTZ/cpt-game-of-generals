package generals.frontend.ui;

import generals.backend.GameBoard;
import generals.frontend.ChessContainer;
import generals.frontend.GameService;
import generals.util.Coordinate;

import javax.swing.*;
import java.awt.*;

/**
 * Chess board panel, saving the chess panels
 *
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class ChessBoardPanel extends JPanel {

    /**
     * the panel 2D array
     */
    private ChessPanel[][] panels = new ChessPanel[GameBoard.INT_ROWS + 1][GameBoard.INT_COLS + 1];

    /**
     * Container
     */
    private ChessContainer container;

    /**
     * Game service
     */
    private GameService gameService;

    /**
     * Create the chess board panel
     *
     * @param container   container
     * @param gameService game service
     */
    public ChessBoardPanel(ChessContainer container, GameService gameService) {
        // chess board should be grid layout
        super(new GridLayout(GameBoard.INT_ROWS, GameBoard.INT_COLS));

        // set size
        setSize(900, 720);

        // set container & game service
        this.container = container;
        this.gameService = gameService;

        // iterate through the panels
        for (int intRow = 1; intRow <= GameBoard.INT_ROWS; intRow++) {
            for (int intCol = 1; intCol <= GameBoard.INT_COLS; intCol++) {
                // create chess panel
                panels[intRow][intCol] = new ChessPanel(intRow, intCol, this, gameService);
                // add into chess board
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

    /**
     * Low light all panels
     */
    public void lowlightAll() {
        // iterate two dimensionally low light
        for (ChessPanel[] coordinateArrays : panels) {
            for (ChessPanel panel : coordinateArrays) {
                if (panel != null) {
                    panel.lowlight();
                }
            }
        }
    }

    /**
     * Highlight the coordinates
     *
     * @param coordinates coordinates
     */
    private void highlightCoordinates(Coordinate[] coordinates) {
        for (Coordinate coordinate : coordinates) {
            panels[coordinate.intX][coordinate.intY].highlight();
        }
    }

    /**
     * Get the panels
     *
     * @return the panel
     */
    public ChessPanel[][] getPanels() {
        return panels;
    }

    /**
     * Get container
     *
     * @return container
     */
    public ChessContainer getContainer() {
        return container;
    }
}
