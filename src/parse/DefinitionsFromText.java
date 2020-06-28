package parse;

import java.awt.Image;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 *
 */
public class DefinitionsFromText {
    private static int width;
    private static int height;
    private static int hitPoints;
    private static java.awt.Color stroke;
    private static Map<Integer, java.awt.Color> fillC;
    private static Map<Integer, Image> fillImg;
    private static String symbol;

    /**
     * Receives a line and sets the default parameters of the class according
     * to what is written.
     *
     * @param line the string we read the information from.
     * @throws Exception if color is nor identified.
     */
    public static void readFromTextd(String line) throws Exception {
        width = 0;
        height = 0;
        hitPoints = 0;
        stroke = null;
        fillC = new HashMap<Integer, java.awt.Color>();
        fillImg = new HashMap<Integer, Image>();
        String[] parts = line.split(" ", 2);
        String part1 = parts[1];
        for (String retval : part1.split(" ")) {
            String[] parts2 = retval.split(":");
            if (parts2[0].equals("width")) {
                width = Integer.parseInt(parts2[1]);
            }
            if (parts2[0].equals("height")) {
                height = Integer.parseInt(parts2[1]);
            }
            if (parts2[0].equals("hit_points")) {
                hitPoints = Integer.parseInt(
                        parts2[1]);
            }
            // Matches the color according to the string
            if (parts2[0].equals("stroke")) {
                stroke = ColorsParser.colorFromString(
                        parts2[1]);
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
                    InputStream is = ClassLoader.
                            getSystemClassLoader().
                            getResourceAsStream(str2[0]);
                    Image img = ImageIO.read(is);
                    fillImg.put(fillKey, img);
                } else {
                    fillC.put(fillKey,
                            ColorsParser.colorFromString(parts2[1]));
                }
            }
        }
    }

    /**
     * Receives a line and sets the default parameters of the class according
     * to what is written.
     *
     * @param line the string we read the information from.
     * @throws Exception if color is nor identified.
     */
    public static void readFromTextsd(String line) throws Exception {
        width = 0;
        height = 0;
        hitPoints = 0;
        stroke = null;
        fillC = new HashMap<Integer, java.awt.Color>();
        fillImg = new HashMap<Integer, Image>();
        symbol = new String();
        String[] parts = line.split("bdef symbol:");
        // part1 will be what comes after "bdef symbol:"
        String part1 = parts[1];
        String[] parts1 = part1.split(" ", 2);
        symbol = parts1[0];
        for (String retval : parts1[1].split(" ")) {
            String[] parts2 = retval.split(":");
            if (parts2[0].equals("width")) {
                width = Integer.parseInt(parts2[1]);
            }
            if (parts2[0].equals("height")) {
                height = Integer.parseInt(parts2[1]);
            }
            if (parts2[0].equals("hit_points")) {
                hitPoints = Integer.parseInt(parts2[1]);
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
                    InputStream is = ClassLoader.
                            getSystemClassLoader().
                            getResourceAsStream(str2[0]);
                    Image img = ImageIO.read(is);
                    fillImg.put(fillKey, img);
                } else {
                    fillC.put(fillKey,
                            ColorsParser.colorFromString(parts2[1]));
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
     * Returns the hit points.
     *
     * @return the classes hit points.
     */
    public static int getHitPoints() {
        return hitPoints;
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