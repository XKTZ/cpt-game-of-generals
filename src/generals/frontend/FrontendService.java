package generals.frontend;

import generals.frontend.ui.ChessBoard;
import generals.frontend.ui.ChessPanel;
import generals.network.XSocket;
import generals.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static generals.util.Util.*;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class FrontendService {

    private int intPlayer;

    private ChessBoard board;

    private XSocket socket;

    public FrontendService(int intPlayer, ChessBoard board) {
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
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        List<Coordinate> listCoordinate = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (intX + dx[i] >= 1 && intX + dx[i] <= 8 && intY + dy[i] >= 1 && intY + dy[i] <= 9) {
                listCoordinate.add(Coordinate.of(intX + dx[i], intY + dy[i]));
            }
        }

        highlightAll(listCoordinate.toArray(new Coordinate[0]));
    }

    public void lowlightAll() {
        for (ChessPanel[] coordinateArrays : board.getPanel()) {
            for (ChessPanel panel : coordinateArrays) {
                if (panel != null) {
                    panel.lowlight();
                }
            }
        }
    }

    private void highlightAll(Coordinate[] coordinates) {
        for (Coordinate coordinate : coordinates) {
            board.getPanel()[coordinate.intX][coordinate.intY].highlight();
        }
    }
}
