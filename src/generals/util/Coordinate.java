package generals.util;

/**
 * A coordinate is a two dimensional object, storing X & Y axis (int).
 *
 * @author Yidi Chen
 * @date 2021-12-14
 */
public class Coordinate {

    /**
     * The X axis of the coordinate
     */
    public int intX;

    /**
     * The Y axis of the coordinate
     */
    public int intY;

    /**
     * The coordinate constructor method. Creating the object by x & y axis
     *
     * @param intX the x axis
     * @param intY the y axis
     */
    public Coordinate(int intX, int intY) {
        this.intX = intX;
        this.intY = intY;
    }

    /**
     * A convenient method to get a coordinate by passing x & y axis
     *
     * @param intX the x axis
     * @param intY y axis
     * @return the coordinate
     */
    public static Coordinate of(int intX, int intY) {
        return new Coordinate(intX, intY);
    }
}
