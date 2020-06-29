package screens;

import biuoop.DrawSurface;
import interfaces.Sprite;
import settings.Const;

import java.awt.Color;
import java.awt.Image;

public class ImagesBackground implements Sprite {
    private Image img;
    private Color color;

    public ImagesBackground(Image img, Color color) {
        this.img = img;
        this.color = color;
    }

    /**
     * this method draws the sprite on to the screen.
     *
     * @param d the draw surface on the screen to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (color != null) {
            d.setColor(color);
            d.fillRectangle(0, 0, Const.getScreenWidth(), Const.getScreenHight());
        } else {
            d.drawImage(0, 0, img);
        }
    }

    /**
     * this method notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
