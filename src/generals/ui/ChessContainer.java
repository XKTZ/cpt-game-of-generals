package generals.ui;

/**
 * @author Yidi Chen
 * @date 2022-01-14
 */
public class ChessContainer {

    private int intXFrom, intYFrom;

    private int intXTo, intYTo;

    public ChessContainer() {

    }

    /**
     * Set the from coordinate
     *
     * @param intX x axis
     * @param intY y axis
     */
    public void setFrom(int intX, int intY) {
        intXFrom = intX;
        intYFrom = intY;
    }

    /**
     * Set the to coordinate
     *
     * @param intX x axis
     * @param intY y axis
     */
    public void setTo(int intX, int intY) {
        intXTo = intX;
        intYTo = intY;
    }
}