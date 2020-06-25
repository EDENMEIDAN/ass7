package task;

import animation.Animation;
import animation.AnimationRunner;
import menu.HighScore;
import task.Task;

/**
 * this class represents ShowHiScoresTask object.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation ;

    /**
     * this method constructs the ShowHiScoresTask object.
     * @param runner the animation runner.
     * @param highScoresAnimation
     */
    public ShowHiScoresTask(AnimationRunner runner, HighScore highScoresAnimation) {
        this.runner = runner;
        //this.highScoresAnimation = highScoresAnimation ; //todo
    }
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null; //which is a way to ensure they don't have any "real" return value.

    }
}