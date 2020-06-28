package parse;

import sprites.Block;


/**
 * an interface of a factory-object that is used for creating blocks:
 */

public interface BlockCreator {
    /**
     * this method creates a block at the specified location.
     *
     * @param xpos the x position.
     * @param ypos the y position.
     * @return a new block according to the positions.
     */
    Block create(int xpos, int ypos);
}