package parse;

import biuoop.DrawSurface;
import geometry.Point;
import sprites.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * this class implements BlockCreator.
 *
 * @author Eden Meidan
 * @id: 207481177
 * @since 28/06/20
 */
public class BlockCreatorImpl implements BlockCreator {
    private Color fillC;
    private Image fillImg;
    private double width;
    private double height;
    private java.awt.Color stroke;
    private DrawSurface d;

    /**
     * constructs a BlockCreatorImpl object.
     *
     * @param width The Block's width.
     * @param height The Block's height.
     * @param fillImg the image that fills.
     * @param fillC the color that fills.
     * @param stroke the color that fills.
     */
    public BlockCreatorImpl(double width, double height, Image fillImg, java.awt.Color fillC, java.awt.Color stroke,
                            DrawSurface d) {
        this.width = width;
        this.height = height;
        this.stroke = stroke;
        this.fillC = fillC;
        this.fillImg = fillImg;
        this.d = d;
    }

    /**
     * this method creates a block object.
     *
     * @param xpos the x position.
     * @param ypos the y position.
     * @return
     */
    public Block create(int xpos, int ypos) {
        assert (fillC != null || fillImg != null);
        Block b = new Block(new Point(xpos, ypos), this.width, this.height, fillC);
        if (fillImg != null) {
            b.setImg(d, fillImg, xpos, ypos);
        }
        b.setStroke(this.stroke);
        return b;
    }
}