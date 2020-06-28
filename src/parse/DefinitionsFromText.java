package parse;

import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 *
 */
public class DefinitionsFromText {
    private int width;
    private int height;
    private java.awt.Color stroke;
    private java.awt.Color fillC;
    private  Image fillImg;
    //private static String symbol;


    public DefinitionsFromText(int width, int height, Color stroke, Color fillC, Image fillImg) {
        this.width = width;
        this.height = height;
        this.stroke = stroke;
        this.fillC = fillC;
        this.fillImg = fillImg;
    }

    /**
     * Receives a line and sets the default parameters of the class according to what is written.
     *
     * @param line the string we read the information from.
     * @throws Exception if color is nor identified.
     */
    public void readDefaultFromText(String line) throws Exception {
        //example default height:25 width:50 stroke:color(black)
        String[] parts = line.split(" ");
        /*for ()
        String part1 = parts[1];*/
        for (String retval : parts.split(" ")) {
            String[] parts2 = retval.split(":");
            if (parts2[0].equals("width")) {
                width = Integer.parseInt(parts2[1]);
            }
            if (parts2[0].equals("height")) {
                height = Integer.parseInt(parts2[1]);
            }
            // Matches the color according to the string
            if (parts2[0].equals("stroke")) {
                stroke = ColorsParser.colorFromString(parts2[1]);
            }
            if (parts2[0].contains("fill")) {
                int fillKey;
                if (parts2[0].contains("-")) {
                    String[] fillB = parts2[0].split("-");
                    fillKey = Integer.parseInt(fillB[1]);
                } else {
                    fillKey = 1;
                }
                if (parts2[1].contains("image")) {
                    String[] str = parts2[1].split("\\(");
                    String[] str2 = str[1].split("\\)");
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(str2[0]);
                    Image img = ImageIO.read(is);
                    fillImg.put(fillKey, img);
                } else {
                    fillC.put(fillKey, ColorsParser.colorFromString(parts2[1]));
                }
            }
        }
    }

    /**
     * Returns the levels symbol.
     *
     * @return the class's symbol.
     */
    public static String getSymbol() {
        return symbol;
    }


    /**
     * Returns the width.
     *
     * @return the class's hit width.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Returns the height.
     *
     * @return the class's height.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Returns the stroke's color.
     *
     * @return the class's stroke color.
     */
    public static java.awt.Color getStroke() {
        return stroke;
    }

    /**
     * Returns the fillC map.
     *
     * @return the class's fillC map
     */
    public static Map<Integer, java.awt.Color> getFillC() {
        return fillC;
    }

    /**
     * Returns the fillImg map.
     *
     * @return the class's fillImg map.
     */
    public static Map<Integer, Image> getFillImg() {
        return fillImg;
    }
}