package parse;

import biuoop.DrawSurface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import biuoop.GUI;

import javax.imageio.ImageIO;

/**
 * this class its job is to read a block-definitions file and returning a BlocksFromSymbolsFactory object.
 *
 * @author Eden Meidan
 * @id: 207481177
 * @since 28/06/20
 */
public class BlocksDefinitionReader {
    private GUI gui;
    private double dwidth;
    private double dheight;
    private java.awt.Color dstroke;
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructors a BlocksDefinitionReader object.
     */

    public BlocksDefinitionReader() {
        this.dheight = 0;
        this.dwidth = 0;
        this.dstroke = null;
        this.spacerWidths = new HashMap<>();
        this.blockCreators = new HashMap<>();
    }

    /**
     * this method reads the txt file and creates a BlocksFromSymbolsFactory.
     *
     * @param reader the txt BufferedReader.
     * @param absolutePath
     * @return a BlocksFromSymbolsFactory.
     * @throws Exception if
     */
    public static BlocksFromSymbolsFactory fromReader(BufferedReader reader, DrawSurface d, String absolutePath) throws Exception {
        BlocksFromSymbolsFactory blocksFSF = new BlocksFromSymbolsFactory();
        try {
            String line;
            DefinitionsFromText defText = new DefinitionsFromText();
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                } else if (line.startsWith("#")) {
                    continue;
                } else if (line.startsWith("default")) { //default only
                    defText.readDefaultFromText(line);
                } else if (line.startsWith("bdef")) {

                    int width = 0, height = 0;
                    String symbol = "", fillImg = "";
                    java.awt.Color stroke = null, fillC = null;

                    String fields = line.substring("bdef ".length()); //get rid of default
                    String[] parts = fields.split(" ");
                    for (String fieldValue : parts) {
                        String[] keyValue = fieldValue.split(":");
                        switch (keyValue[0]) {
                            case "symbol": {
                                symbol = keyValue[1];
                                break;
                            }
                            case "fill": {
                                System.out.println("full - line: " + line);
                                if (keyValue[1].startsWith("image")) {
                                    String images = keyValue[1].substring("image(".length(), keyValue[1].length() - 1);
                                    fillImg = images;
                                    System.out.println("fill imge");
                                } else {
                                    //String color = keyValue[1].substring("color(".length(), keyValue[1].length() - 1);
                                    String color = keyValue[1];
                                    System.out.println("color: " + color);
                                    fillC = ColorsParser.colorFromString(color);
                                    System.out.println("fill color " + fillC);

                                }
                                break;
                            }
                            case "height": {
                                height = Integer.parseInt(keyValue[1]);
                                break;
                            }
                            case "width": {
                                width = Integer.parseInt(keyValue[1]);
                                break;
                            }
                            case "stroke": {
                                String color = keyValue[1].substring("stroke(".length(), keyValue[1].length() - 2);
                                stroke = ColorsParser.colorFromString(color);
                                break;
                            }
                        }
                    }
                    //bdef symbol:z fill:image(block_images/zebra.jpg)
                    //bdef symbol:l fill:image(block_images/leopard.jpg)
                    if (width == 0) {
                        if (defText.getWidth() != 0) {
                            width = defText.getWidth();
                        } else {
                            throw new ParseException("no width given");
                        }
                    }
                    if (height == 0) {
                        if (defText.getHeight() != 0) {
                            height = defText.getHeight();
                        } else {
                            throw new ParseException("no height given");
                        }
                    }
                    if (symbol.equals("")) {
                        throw new ParseException("no symbol given");
                    }
                    if (fillImg.equals("") && fillC == null) {
                        throw new ParseException("no fill given");
                    }
                    if (stroke == null) {
                        System.out.println("stroke = null");
                        if (defText.getStroke() != null) {
                            stroke = defText.getStroke();

                        }
                    }
                    Image imgMy = null;
                    if (!fillImg.equals("")) {
                        File tempFile = new File(absolutePath + "/" + fillImg);
                        imgMy = ImageIO.read(tempFile);
                    }
                    BlockCreator creator = new BlockCreatorImpl(width, height, imgMy, fillC, stroke, d);
                    blocksFSF.getBlockCreator().put(symbol, creator);
                } else if (line.startsWith("sdef")) {
                    //sdef symbol:- width:30
                    int width = 0, height = 0;
                    String symbol = "";
                    String fields = line.substring("sdef ".length()); //get rid of default
                    String[] parts = fields.split(" ");
                    //getting fields from line
                    for (String fieldValue : parts) {
                        String[] keyValue = fieldValue.split(":");
                        switch (keyValue[0]) {
                            case "symbol": {
                                symbol = keyValue[1];
                                break;
                            }
                            case "height": {
                                height = Integer.parseInt(keyValue[1]);
                                break;
                            }
                            case "width": {
                                width = Integer.parseInt(keyValue[1]);
                                break;
                            }
                        }
                    }
                    //filling empty fields with default
                    if (width == 0) {
                        if (defText.getWidth() != 0) {
                            width = defText.getWidth();
                        } else {
                            throw new ParseException("no width given sdf");
                        }
                    }
                    if (height == 0) {
                        if (defText.getHeight() != 0) {
                            height = defText.getHeight();
                        }
                    }
                    if (symbol.equals("")) {
                        throw new ParseException("no symbol given sdf");
                    }
                    blocksFSF.getSpacersWidths().put(symbol, width); // todo check height if needed
                } else {
                    throw new ParseException("what kind o line is this");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading");
            throw e;
        } catch (Exception e) {
            System.out.println("other exception" + e.getMessage());
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                    throw e;
                }
            }
        }
        return blocksFSF;
    }
}