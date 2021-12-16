package generals.domain;

import generals.util.Coordinate;

import java.util.List;

/**
 * This is the interface for a basic chess on the board. It provides method this chess should have.
 *
 * @author Yidi Chen
 * @date 2021-12-14
 */
public interface BasicChess {

    /**
     * Get the player owns the chess
     *
     * @return the player owns the chess
     */
    int player();

    /**
     * Get the position of the chess
     *
     * @return the position of the chess
     */
    Coordinate position();

    /**
     * Move the chess to a new position
     *
     * @param intX the x axis
     * @param intY the y axis
     */
    void move(int intX, int intY);

    /**
     * Get all available positions the chess can move
     *
     * @return the available positions of the chess
     */
    List<Coordinate> availableMovingPositions();
}
