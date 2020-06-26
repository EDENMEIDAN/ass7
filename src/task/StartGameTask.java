/*
package task;

import animation.AnimationRunner;
import biuoop.GUI;
import levels.GameFlow;
import levels.LevelInformation;
import menu.HighScore;

import java.io.File;
import java.util.List;

*/
/**
 * this class represents a StartGameTask object.
 *//*

public class StartGameTask implements Task {
    private GUI gui;
    private AnimationRunner animationRunner;
    private HighScore table;
    private List<LevelInformation> levels;
    private File highScoresFile;

    */
/**
     * construct a StartGameTask from a GUI, an animationRunner, a highScoresTable, a levelInformation list,
     * a number of lives and a highScoresFile.
     *
     * @param gui the given GUI.
     * @param animationRunner the given animationRunner.
     * @param table the given highScoresTable.
     * @param levels the given levelInformation list.
     * @param highScoresFile the given highScores file.
     *//*

    public StartGameTask(GUI gui, AnimationRunner animationRunner, HighScore table, List<LevelInformation> levels,
                         File highScoresFile) {
        this.gui = gui;
        this.animationRunner = animationRunner;
        this.table = table;
        this.levels = levels;
        this.highScoresFile = highScoresFile;
    }

    */
/**
     * this method runs this task.
     *
     * @return unimplemented option.
     *//*

    public Void run() {
        //GameFlow game = new GameFlow(this.animationRunner, this.gui.getKeyboardSensor(), this.table, );
        game.runLevels(this.levels);
        //table.save(this.highScoresFile, si);
        return null;
    }
}
*/
