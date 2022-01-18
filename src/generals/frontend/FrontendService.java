package generals.frontend;

import generals.frontend.ui.ChessBoard;
import generals.frontend.ui.ChessPanel;
import generals.network.XSocket;
import generals.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class FrontendService {

    private int intPlayer;

    private ChessBoard board;

    private XSocket socket;

    public FrontendService(int intPlayer) {
    }

    /**
     * Set the board
     *
     * @param board the board
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
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
                    if (board.getPanels()[i][j].getType() == 0) {
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
        for (ChessPanel[] coordinateArrays : board.getPanels()) {
            for (ChessPanel panel : coordinateArrays) {
                if (panel != null) {
                    panel.lowlight();
                }
            }
        }
    }

    private void highlightCoordinates(Coordinate[] coordinates) {
        for (Coordinate coordinate : coordinates) {
            board.getPanels()[coordinate.intX][coordinate.intY].highlight();
        }
    }
}
