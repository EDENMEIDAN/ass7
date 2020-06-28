package parse;

/**
 * this class
 */
public class ColorsParser {
    /**
     * this method parse color definition and returns the specified color.
     * @param s symbl
     * @return the specified color.
     * @throws Exception
     */
    public static java.awt.Color colorFromString(String s) throws Exception {
        java.awt.Color c = null;
        if (s.contains("RGB")) {
            int r, g, b;
            String[] parts1 = s.split("RGB");
            String s1 = parts1[1].substring(1, parts1[1].length() - 2);
            String[] parts = s1.split(",");
            if (parts.length == 3) {
                r = Integer.parseInt(parts[0]);
                g = Integer.parseInt(parts[1]);
                b = Integer.parseInt(parts[2]);
            } else {
                throw new Exception("Color was written wrong");
            }
            float[] defs = java.awt.Color.RGBtoHSB(r, g, b, null);
            c = java.awt.Color.getHSBColor(defs[0], defs[1], defs[2]);
        } else {
            // Matches the color according to the string
            if (s.equals("color(blue)")) {
                c = java.awt.Color.blue;
            } else {
                if (s.equals("color(cyan)")) {
                    c = java.awt.Color.cyan;
                } else {
                    if (s.equals("color(white)")) {
                        c = java.awt.Color.white;
                    } else {
                        if (s.equals("color(black)")) {
                            c = java.awt.Color.black;
                        } else {
                            if (s.equals("color(orange)")) {
                                c = java.awt.Color.orange;
                            } else {
                                if (s.equals("color(green)")) {
                                    c = java.awt.Color.green;
                                } else {
                                    if (s.equals("color(darkGray)")) {
                                        c = java.awt.Color.darkGray;
                                    } else {
                                        if (s.equals("color(gray)")) {
                                            c = java.awt.Color.gray;
                                        } else {
                                            if (s.equals("color(lightGray)")) {
                                                c = java.awt.Color.lightGray;
                                            } else {
                                                if (s.equals("color(magenta)")) {
                                                    c = java.awt.Color.magenta;
                                                } else {
                                                    if (s.equals("color(pink)")) {
                                                        c = java.awt.Color.pink;
                                                    } else {
                                                        if (s.equals("color(yellow)")) {
                                                            c = java.awt.Color.yellow;
                                                        } else {
                                                            if (s.equals("color(red)")) {
                                                                c = java.awt.Color.red;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return c;
        }
        return c;
    }
}