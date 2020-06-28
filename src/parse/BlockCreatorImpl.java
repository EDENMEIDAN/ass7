package parse;

import geometry.Point;
import sprites.Block;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BlockCreatorImpl implements BlockCreator {
    private Map<Integer, Color> fillC;
    private Map<Integer, Image> fillImg;
    private double width;
    private double height;
    private int hitPoints;
    private java.awt.Color stroke;

    public BlockCreatorImpl(double width, double height, int hitPoints, Map<Integer, Image> fillImg,
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

    public Block create(int xpos, int ypos) {
        Block b = new Block(new Point(xpos, ypos), this.width, this.height, null);
        if(fillC != null) {
            b.setColor(fillC);
        }
                fill

        b.setColor(color);
        b.setImg(image);
        b.setColor(this.stroke);
        return b;
    }

}