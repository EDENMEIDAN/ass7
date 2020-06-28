package parse;

import sprites.Block;
import java.util.HashMap;
import java.util.Map;

/**
 * this class creates block objects from given symbols.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreator;

    /**
     * this method constructs a BlocksFromSymbolsFactory object.
     */
    public BlocksFromSymbolsFactory() {
        Map<String, Integer> sw = new HashMap<String, Integer>();
        Map<String, BlockCreator> bc = new HashMap<String, BlockCreator>();
        this.spacerWidths = sw;
        this.blockCreator = bc;
    }

    /**
     * this method creates a block according to the symbol it gets.
     * The block will be located at position (xpos, ypos)
     *
     * @param s the symbol.
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @return a new block.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreator.get(s).create(x, y);
    }

    /**
     * this method returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s the spacer.
     * @return the spacers integer value.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * This method returns the spacers widths map.
     *
     * @return this spacers width map.
     */
    public Map<String, Integer> getSpacersWidths() {
        return this.spacerWidths;
    }

    /**
     * This method returns the block creator map.
     *
     * @return this block creator map.
     */
    public Map<String, BlockCreator> getBlockCreator() {
        return this.blockCreator;
    }

    /**
     * this method returns true if 's' is a valid space symbol.
     *
     * @param s the string.
     * @return true if 's' is a valid space symbol o/w -- false.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * this method returns true if 's' is a valid block symbol.
     *
     * @param s the string.
     * @return true if 's' is a valid space symbol o/w -- false.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreator.containsKey(s);
    }
}