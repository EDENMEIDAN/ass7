import animation.AnimationRunner;
import biuoop.GUI;
import levels.GameFlow;
import levels.Level1DirectHit;
import levels.Level2WideEasy;
import levels.Level3Green3;
import levels.Level4FinalFour;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;
import task.ExitTask;
import task.ShowHiScoresTask;
import task.StartGameTask;
import task.Task;
import settings.Const;
import menu.HighScore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is the program's main class.
 *
 * @author Eden Meidan
 * @id: 207481177
 * @since 25/06/2020
 */
public class Ass7Game {
    /**
     * this method is the Main method that initializes and runs the whole game!
     *
     * @param args this array stores the user's input. at the moment is empty.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", Const.getScreenWidth(), Const.getScreenHight());
        AnimationRunner runner = new AnimationRunner(gui, 60 / 6);

        File highScoresFile = new File("highscores.txt");
        HighScore table =  new HighScore(highScoresFile);
        table.loadFromFile();

        List<LevelInformation> levelsToPlay = new ArrayList<>();
        LevelInformation level1 = new Level1DirectHit();
        LevelInformation level2 = new Level2WideEasy();
        LevelInformation level3 = new Level3Green3();
        LevelInformation level4 = new Level4FinalFour();

        for (int i = 0; i < args.length; i++) {
            String cur = args[i];
            System.out.println("arg is: " + cur);
            if (cur.equals("1")) {
                levelsToPlay.add(level1);
            } else if (cur.equals("2")) {
                levelsToPlay.add(level2);
            } else if (cur.equals("3")) {
                levelsToPlay.add(level3);
            } else if (cur.equals("4")) {
                levelsToPlay.add(level4);
            }
        }
        if (levelsToPlay.isEmpty()) {
            levelsToPlay.add(level1);
            levelsToPlay.add(level2);
            levelsToPlay.add(level3);
            levelsToPlay.add(level4);
        }
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor(), table);

        int gameScore = gameFlow.runLevels(levelsToPlay);
        table.save(gameScore); //save new file with updated score

        runner.getGui().close();

        // menu definition --- using generics
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Menu Title", gui.getKeyboardSensor(), runner);

        // Press "s" to start a new game.
        menu.addSelection("s", "Start game",
                new StartGameTask(gui, runner, table, levelsToPlay, highScoresFile));
        // Press "h" to see the highest score

        menu.addSelection("h", "Hi Score", new ShowHiScoresTask(runner, table));
        //AnimationRunner runner, Animation highScoresAnimation

        // Press "q" to quit.
        menu.addSelection("q", "Quit game", new ExitTask(gui));



        while (true) { //menu loop
            runner.run(menu); // wait for user selection
            //status = menu.getStatus();

            Task<Void> task = menu.getStatus();
            task.run();
            //menu.reset();
        }
    }
}