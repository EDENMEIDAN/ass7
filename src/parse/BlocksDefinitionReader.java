package parse;

import java.io.IOException;
import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * this class its job is to read a block-definitions file and returning a BlocksFromSymbolsFactory object.
 */
public class BlocksDefinitionReader {
    private static double dwidth;
    private static double dheight;
    private static int dhitPoints;
    private Map<Integer, Color> dfillC;
    private Map<Integer, Image> dfillImg;
    private java.awt.Color dstroke;
    private Map<String, Integer> spacerWidths; //todo
    private Map<String, BlockCreator> blockCreators; //todo

    /**
     * Constructor.
     */
    public BlocksDefinitionReader() {
        Map<Integer, java.awt.Color> cf = new HashMap<Integer, java.awt.Color>();
        Map<Integer, Image> fi = new HashMap<Integer, Image>();
        this.dfillC = cf;
        this.dfillImg = fi;
        this.dheight = 0;
        this.dhitPoints = 0;
        this.dwidth = 0;
        this.dstroke = null;
    }

    public BlocksFromSymbolsFactory fromReader(java.io.BufferedReader reader)
        throws Exception {
            try {
                Map<Integer, java.awt.Color> fillC = new HashMap<>();
                Map<Integer, Image> fillImg = new HashMap<>();
                BlocksFromSymbolsFactory blocksFSF = new BlocksFromSymbolsFactory();
                String line;
                double height = 0, width = 0;
                int hitPoints = 0;
                String symbol = null;
                java.awt.Color stroke = null;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("")) {
                        continue;
                    }
                    else if (line.startsWith("#")) {
                        continue;
                    }
                    else if (line.startsWith("default")) { //default only
                        DefinitionsFromText.readDefaultFromText(line);
                        this.dwidth = DefinitionsFromText.getWidth();
                        this.dheight = DefinitionsFromText.getHeight();
                        this.dstroke = DefinitionsFromText.getStroke();
                        this.dfillC = DefinitionsFromText.getFillC();
                        this.dfillImg = DefinitionsFromText.getFillImg();
                    }
                    else if (line.startsWith("bdef symbol")) {
                        width = DefinitionsFromText.getWidth();
                        height = DefinitionsFromText.getHeight();
                        stroke = DefinitionsFromText.getStroke();
                        fillC = DefinitionsFromText.getFillC();
                        fillImg = DefinitionsFromText.getFillImg();
                        symbol = DefinitionsFromText.getSymbol();
                        if (width == 0) {
                            width = dwidth;
                        }
                        if (height == 0) {
                            height = dheight;
                        }
                        if (stroke == null) {
                            stroke = dstroke;
                        }
                        if (fillC.isEmpty()) {
                            fillC = this.dfillC;
                        }
                        if (fillImg.isEmpty()) {
                            fillImg = this.dfillImg;
                        }
                        if (fillImg == null && fillC == null) {
                            throw new Exception("No fill was included");
                        }

                        if (!fillC.isEmpty()) {
                            for (Map.Entry<Integer, Color> entry : fillC.entrySet()) {
                                entry.getValue(i);
                            }
                            java.awt.Color col = fillC.get(1);

                                if (!fillC.containsKey(i)) {

                                    fillC.put(i, col);
                                }
                            }
                        }
                        assert fillImg != null;
                        if (!fillImg.isEmpty()) {
                            Image im = fillImg.get(1);
                            for (int i = 2; i <= hitPoints; i++) {
                                if (!fillImg.containsKey(i)) {
                                    fillImg.put(i, im);
                                }
                            }
                        }
                        if (width != 0 && height != 0 && hitPoints != 0) {
                            BlockCreatorImpl blockReader;
                            if (stroke != null) {
                                blockReader = new BlockCreatorImpl(width, height, hitPoints, fillImg, fillC, stroke);
                            } else {
                                blockReader = new BlockCreatorImpl(width, height, hitPoints, fillImg, fillC, null);
                            }
                            blocksFSF.getBlockCreator().put(symbol, blockReader);
                            height = 0;
                            width = 0;
                            //hitPoints = 0;
                            symbol = null;
                            stroke = null;
                            fillC = new HashMap<Integer, java.awt.Color>();
                            fillImg = new HashMap<Integer, Image>();
                        } else {
                            throw new Exception("Block wasn't defined well");
                        }
                    }
                    if (line.startsWith("sdef symbol:")) {
                        String[] parts3 = line.split("sdef symbol:");
                        String part4 = parts3[1];
                        String[] parts4 = part4.split(" ");
                        symbol = parts4[0];
                        String[] parts5 = parts4[1].split(":");
                        if (parts5[0].equals("width")) {
                            blocksFSF.getSpacersWidths().put(symbol, Integer.parseInt(parts5[1]));
                        } else {
                            throw new Exception("Wrong spacer");
                        }
                    }
                }
                return blocksFSF;
            } catch (IOException e) {
                System.out.println("Something went wrong while reading");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        System.out.println("Failed closing the file!");
                    }
                }
            }
            return null;
        }
    }
}