package menu;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * this class represents a menu animation object in the game.
 *
 * @param <T> the menu type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private List<String> keys;
    private List<String> messages;
    private List<T> options;
    private List<Menu<T>> subMenus;
    private List<Boolean> isOption;
    private T status;
    private boolean close;

    /**
     * this method constructs a menu animation object.
     *
     * @param menuTitle the given menuTitle.
     * @param keyboardSensor the given keyboardSensor.
     * @param animationRunner the given animationRunner.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.menuTitle = menuTitle;
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.options = new ArrayList<T>();
        this.subMenus = new ArrayList<Menu<T>>();
        this.isOption = new ArrayList<Boolean>();
        this.close = false;
    }

    /**
     * this method adds a selection to the menu.
     *
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param returnVal the selection value according to the menu type.
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.options.add(returnVal);
        this.subMenus.add(null);
        this.isOption.add(true);
    }

    /**
     * this method returns the selected value after the selection event, according to the menu type.
     *
     * @return the selected value.
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * this method resets the menu.
     */
    @Override
    public void reset() {
        this.status = null;
        this.close = false;
    }

    /**
     * this method is the frame-management code.
     *
     * @param d is the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) { //todo
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.decode("#1e7f00"));
        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 90);

        d.setColor(Color.GREEN);
        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 3);
        d.drawText(180, d.getHeight() / 2 - 90, this.menuTitle, 70);

        d.fillRectangle(0, d.getHeight() / 2 - 70, d.getWidth(), 3);

        for (int i = 0; i < this.keys.size(); i++) {
            d.drawText(260, 200 + 40 * (i + 2), "(" + this.keys.get(i) + ") " + this.messages.get(i), 30);
        }
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keyboardSensor.isPressed(this.keys.get(i))) {
                if (this.isOption.get(i)) {
                    this.status = this.options.get(i);
                    this.close = true;
                } else {
                    this.animationRunner.run(this.subMenus.get(i));
                    this.status = this.subMenus.get(i).getStatus();
                    this.subMenus.get(i).reset();
                    this.close = true;
                    break;
                }
            }
        }
    }

    /**
     * this method in charge of the game-specific logic and stopping conditions are handled.
     *
     * @return true when the current game frame should stop. false, when  shouldn't stop.
     */
    @Override
    public boolean shouldStop() {
        return this.close;
    }
}