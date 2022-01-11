package generals.backend;

import generals.util.Coordinate;

/**
 * A basic chess in the games of generals
 *
 * @author Yidi Chen
 * @date 2022-01-11
 */
public class Chess {

    // different type of chess

    public static final int INT_EMPTY_SPACE = 0;
    public static final int INT_FLAG = 1;
    public static final int INT_SPY = 2;
    public static final int INT_PRIVATE = 3;
    public static final int INT_SERGENT = 4;
    public static final int INT_2ND_LIEUTENANT = 5;
    public static final int INT_1ST_LIEUTENANT = 6;
    public static final int INT_CAPTAIN = 7;
    public static final int INT_MAJOR = 8;
    public static final int INT_LIEUTENANT_COLONEL = 9;
    public static final int INT_COLONEL = 10;
    public static final int INT_BRIGADIER_GENERAL = 11;
    public static final int INT_MAJOR_GENERAL = 12;
    public static final int INT_LIEUTENANT_GENERAL = 13;
    public static final int INT_GENERAL = 14;
    public static final int INT_GENERAL_OF_THE_ARMY = 15;


    /**
     * Coordinate of chess
     */
    private Coordinate coordinate;

    /**
     * Player of chess
     */
    private int intPlayer;

    /**
     * type of chess
     */
    private int intType;

    /**
     * Creating chess by providing coordinate, player, and level
     *
     * @param coordinate coordinate
     * @param intPlayer  player
     * @param intType    type
     */
    public Chess(Coordinate coordinate, int intPlayer, int intType) {
        this.coordinate = coordinate;
        this.intPlayer = intPlayer;
        this.intType = intType;
    }

    /**
     * Get the coordinate
     *
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Get the player
     *
     * @return the player
     */
    public int getPlayer() {
        return intPlayer;
    }

    /**
     * Get the type
     *
     * @return the type
     */
    public int getType() {
        return intType;
    }

    /**
     * Check if it is empty
     *
     * @return empty or not
     */
    public boolean isEmpty() {
        return INT_EMPTY_SPACE == intType;
    }

    /**
     * Compare chess a with chess b.
     *
     * @param a chess a
     * @param b chess b
     * @return chess a > chess b: 1. chess a = chess b: 0. chess a < chess b: -1. chess a is flag, chess
     */
    public static int compare(Chess a, Chess b) {
        if (a.intType == b.intType) {
            return 0;
        }

        // three conditions that a or b is empty
        if (a.isEmpty() && b.isEmpty()) {
            return 0;
        }
        if (a.isEmpty() && !b.isEmpty()) {
            return 1;
        }
        if (!a.isEmpty() && b.isEmpty()) {
            return -1;
        }

        // compare a with b

        // condition that they are spy
        // if a is spy
        if (a.intType == INT_SPY) {
            // if b is private, a lose
            if (b.intType != INT_PRIVATE) {
                return -1;
            } else { // otherwise, a win
                return 1;
            }
        }
        // if b is spy
        if (b.intType == INT_SPY) {
            // if a is private, a win
            if (a.intType == INT_PRIVATE) {
                return 1;
            } else { // a lose
                return -1;
            }
        }

        // then, compare them by int type
        if (a.intType > b.intType) {
            return 1;
        } else {
            return -1;
        }
    }
}
