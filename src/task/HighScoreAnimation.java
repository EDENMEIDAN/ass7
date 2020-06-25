package task;

import animation.Animation;
import biuoop.DrawSurface;

public class HighScoreAnimation implements Animation {
    /**
     * this method is the frame-management code.
     *
     * @param d is the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        
    }

    /**
     * this method in charge of the game-specific logic and stopping conditions are handled.
     *
     * @return true when the current game frame should stop. false, when  shouldn't stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
