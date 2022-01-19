package generals.frontend;

import generals.network.XSocket;

/**
 * @author Yidi Chen
 * @date 2022-01-18
 */
public class GameService {

    private XSocket socket;

    private int intPlayer;

    /**
     * Game Board service
     *
     * @param socket socket
     */
    public GameService(XSocket socket) {
        this.socket = socket;
    }

    /**
     * Put a chess with type at {x, y}
     * @param intX x
     * @param intY y
     * @param intType type
     */
    public boolean put(int intX, int intY, int intType) {
        System.out.println("Putting at " + intX + " , " + intY);
        return true;
    }
}
