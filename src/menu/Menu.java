package menu;

import animation.Animation;

/**
 * this interface represents a menu object
 * @param <T> the menu type.
 */
public interface Menu<T> extends Animation {
    /**
     * this method adds a selection to the menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param returnVal the selection value according to the menu type.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * this method returns the selected value after the selection event, according to the menu type.
     * @return the selected value.
     */
    T getStatus();

    /**
     * this method resets the menu.
     */
    void reset();
}
