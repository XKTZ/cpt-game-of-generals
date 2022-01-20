package generals.frontend;

/**
 * @author Yidi Chen
 * @date 2022-01-14
 */
public class ChessContainer {

    private int intX, intY;

    public ChessContainer() {

    }

    public int getX() {
        return intX;
    }

    public int getY() {
        return intY;
    }

    /**
     * Set the to coordinate
     *
     * @param intX x axis
     * @param intY y axis
     */
    public void setTo(int intX, int intY) {
        this.intX = intX;
        this.intY = intY;
    }
}
