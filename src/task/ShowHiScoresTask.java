package task;

import animation.Animation;
import animation.AnimationRunner;
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
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation ;
    }
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null; //which is a way to ensure they don't have any "real" return value.

    }
}