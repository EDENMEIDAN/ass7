package parse;

import java.awt.Color;
import java.awt.Image;

/**
 * this class represents the DefinitionsFromText object.
 */
public class DefinitionsFromText {
    private int width;
    private int height;
    private java.awt.Color stroke;
   /* private java.awt.Color fillC;
    private Image fillImg;*/

    public DefinitionsFromText() { //Color fillC, Image fillImg
        this.width = 0;
        this.height = 0;
        this.stroke = null;
    }

    /**
     * Receives a line and sets the default parameters of the class according to what is written.
     *
     * @param line the string we read the information from.
     * @throws Exception if color is nor identified.
     */
    public void readDefaultFromText(String line) throws Exception {
        //example default height:25 width:50 stroke:color(black) //TODO START HERE
        line = line.substring("default ".length()); //get rid of default
        String[] parts = line.split(" ");

        for (String fieldValue : parts) {
            String[] keyValue = fieldValue.split(":");
            switch (keyValue[0]) {
                case "width": {
                    width = Integer.parseInt(keyValue[1]);
                    break;
                }
                case "height": {
                    height = Integer.parseInt(keyValue[1]);
                    break;
                }
                case "stroke": {
                    stroke = ColorsParser.colorFromString(keyValue[1]);
                    break;
                }
               /* case "fill" : {  //todo check about fill in default
                    if (keyValue[1].startsWith("image")) {
                        String[] str = keyValue[1].split("\\(");
                        String[] str2 = str[1].split("\\)");
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(str2[0]);
                        Image img = ImageIO.read(is);
                        fillImg.put(fillKey, img);
                    } else {
                        fillC.put(fillKey, ColorsParser.colorFromString(keyValue[1]));
                    }
                }*/
            }
        }
    }

    /**
     * Returns the width.
     *
     * @return the class's hit width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height.
     *
     * @return the class's height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the stroke's color.
     *
     * @return the class's stroke color.
     */
    public java.awt.Color getStroke() {
        return stroke;
    }
}