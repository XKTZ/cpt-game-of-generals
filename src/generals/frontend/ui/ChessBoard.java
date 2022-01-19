package generals.frontend.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import generals.backend.GameBoard;
import generals.frontend.GameService;
import generals.util.Coordinate;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class ChessBoard extends JPanel {

    private ChessPanel[][] panels = new ChessPanel[GameBoard.INT_ROWS + 1][GameBoard.INT_COLS + 1];

    private ChessContainer container;

    private GameService gameService;

    public ChessBoard(int intPlayer,
                      ChessContainer container, GameService gameService) {
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
        /*
        socket.request(messageOf("available", String.valueOf(intPlayer), String.valueOf(intX), String.valueOf(intY)),
                (resp) -> {
                    // ...
                });
         */

        List<Coordinate> listCoordinate = new ArrayList<>();

        if (intX == 0 && intY == 0) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 9; j++) {
                    if (panels[i][j].getType() == 0) {
                        listCoordinate.add(Coordinate.of(i, j));
                    }
                }
            }
        } else {
            int[] dx = new int[]{1, 0, -1, 0};
            int[] dy = new int[]{0, 1, 0, -1};

            for (int i = 0; i < 4; i++) {
                if (intX + dx[i] >= 1 && intX + dx[i] <= 8 && intY + dy[i] >= 1 && intY + dy[i] <= 9) {
                    listCoordinate.add(Coordinate.of(intX + dx[i], intY + dy[i]));
                }
            }
        }
        highlightCoordinates(listCoordinate.toArray(new Coordinate[0]));
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
