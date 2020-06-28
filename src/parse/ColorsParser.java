package parse;

/**
 * this class is a ColorsParser object.
 *
 * @author Eden Meidan
 * @id: 207481177
 * @since 28/06/20
 */
public class ColorsParser {
    /**
     * this method parse color definition and returns the specified color.
     *
     * @param s symbol.
     * @return the specified color.
     */
    public static java.awt.Color colorFromString(String s) throws Exception {
        java.awt.Color returnColor = null;
        //color(RGB(x,y,z))
        if (s.startsWith("color(RGB")) {
            int r, g, b;
            String[] parts = s.split("\\(");
            String xyz = parts[2].substring(0, parts[2].length() - 3); //get xyz

            String[] parts2 = xyz.split(",");
            if (parts2.length == 3) {
                r = Integer.parseInt(parts2[0]);
                g = Integer.parseInt(parts2[1]);
                b = Integer.parseInt(parts2[2]);
            } else {
                throw new Exception("Color was written wrong");
            }
            float[] defs = java.awt.Color.RGBtoHSB(r, g, b, null);
            returnColor = java.awt.Color.getHSBColor(defs[0], defs[1], defs[2]);
        } else {
            // Matches the color according to the string
            if (s.startsWith("color")) {
                switch (s) {
                    case "color(blue)": {
                        returnColor = java.awt.Color.blue;
                        break;
                    }
                    case "color(cyan)": {
                        returnColor = java.awt.Color.cyan;
                        break;
                    }
                    case "color(white)": {
                        returnColor = java.awt.Color.white;
                        break;
                    }
                    case "color(black)": {
                        returnColor = java.awt.Color.black;
                        break;
                    }
                    case "color(orange)": {
                        returnColor = java.awt.Color.orange;
                        break;
                    }
                    case "color(green)": {
                        returnColor = java.awt.Color.green;
                        break;
                    }
                    case "color(darkGray)": {
                        returnColor = java.awt.Color.darkGray;
                        break;
                    }
                    case "color(gray)": {
                        returnColor = java.awt.Color.gray;
                        break;
                    }
                    case "color(lightGray)": {
                        returnColor = java.awt.Color.lightGray;
                        break;
                    }
                    case "color(magenta)": {
                        returnColor = java.awt.Color.magenta;
                        break;
                    }
                    case "color(pink)": {
                        returnColor = java.awt.Color.pink;
                        break;
                    }
                    case "color(yellow)": {
                        returnColor = java.awt.Color.yellow;
                        break;
                    }
                    case "color(red)": {
                        returnColor = java.awt.Color.red;
                        break;
                    }
                    default:
                        returnColor = java.awt.Color.black;
                }
            }
        }
        return returnColor;
    }
}