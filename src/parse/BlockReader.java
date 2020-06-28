package parse;

import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BlockReader {
    private Map<Integer, Color> fillC;
    private Map<Integer, Image> fillImg;
    private double width;
    private double height;
    private int hitPoints;
    private java.awt.Color stroke;

    public BlockReader(double width, double height, int hitPoints, Map<Integer, Image> fillImg,
                       Map<Integer, java.awt.Color> fillC, java.awt.Color stroke) {
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.stroke = stroke;
        this.fillC = new TreeMap<Integer, Color>();
        this.fillImg = new TreeMap<Integer, Image>();
        this.fillC.putAll(fillC);
        this.fillImg.putAll(fillImg);
    }

    public BlockReader(double width, double height, int hitPoints,
                       Map<Integer, Image> fillImg, Map<Integer, java.awt.Color> fillC) {
        this.width = width;
        this.height = height;
        this.fillC = new TreeMap<Integer, Color>();
        this.fillImg = new TreeMap<Integer, Image>();
        this.fillC.putAll(fillC);
        this.fillImg.putAll(fillImg);
        this.stroke = null;
        this.hitPoints = hitPoints;
    }

    public Block create(int xpos, int ypos) {
        //public Block(Point upperLeft, double width, double height, Color color) { ////constroctor
        Block b = new Block(new Point(xpos, ypos), this.width, this.height, null);
        List<Color> color = new ArrayList<Color>();
        List<Image> image = new ArrayList<Image>();
        for (int i = 1; i <= hitPoints; i++) {
            if (i <= this.fillC.size()) {
                color.add(this.fillC.get(i));
            }
            if (i <= this.fillImg.size()) {
                image.add(this.fillImg.get(i));
            }
        }
        b.setColor(color);
        b.setImg(image);
        b.setColor(this.stroke);
        return b;
    }

}