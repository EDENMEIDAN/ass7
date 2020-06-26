package parse;

import java.util.Map;

/**
 * this class
 * its job is to read a block-definitions file and returning a BlocksFromSymbolsFactory object.
 */
public class BlocksDefinitionReader{
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        // ...
    }
}
