package generals.frontend;

/**
 * @author Yidi Chen
 * @date 2022-01-14
 */
public class ChessContainer {

    /**
     * X axis of chess container
     */
    private int intX;

    /**
     * Y axis of chess container
     */
    private int intY;

    /**
     * Create empty chess container
     */
    public ChessContainer() {
    }

    /**
     * Get x axis
     *
     * @return x axis
     */
    public int getX() {
        return intX;
    }

    /**
     * Get y axis
     *
     * @return y axis
     */
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
