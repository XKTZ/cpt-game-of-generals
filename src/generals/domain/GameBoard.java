package generals.domain;

import java.util.List;

/**
 * This is the interface for a basic game board. It provides the method a game board should have.
 *
 * @param <E> the chess on the game board should be a chess extends from the basic chess
 * @author Yidi Chen
 * @date 2021-12-14
 */
public interface GameBoard<E extends BasicChess> {

    /**
     * Try to move the chess at {X, Y} to {XNew, YNew}
     *
     * @param intX    X
     * @param intY    Y
     * @param intXNew XNew
     * @param intYNew YNew
     */
    void move(int intX, int intY, int intXNew, int intYNew);

    /**
     * Get the whole board with a two dimensional array with chesses
     *
     * @return the board with chesses
     */
    E[][] getBoard();

    /**
     * Get the chess at position {X, Y}
     *
     * @param intX X
     * @param intY Y
     * @return the chess at {X, Y}
     */
    E chessAt(int intX, int intY);

    /**
     * Get the died chess from the player
     *
     * @param intPlayer the player
     * @return all died chess
     */
    List<E> diedChessFrom(int intPlayer);

    /**
     * Get the width of the game board
     *
     * @return the width of the game board
     */
    int getWidth();

    /**
     * Get the height of the game board
     *
     * @return the height of the game board
     */
    int getHeight();
}
