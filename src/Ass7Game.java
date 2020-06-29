import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Sprite;
import levels.GameFlow;
import levels.Level1DirectHit;
import levels.Level2WideEasy;
import levels.Level3Green3;
import levels.Level4FinalFour;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;
import parse.LevelSpecificationReader;
import screens.KeyPressStoppableAnimation;
import settings.Velocity;
import sprites.Block;
import task.HighScoreAnimation;
import task.Task;
import settings.Const;
import menu.HighScore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI("Arkanoid", Const.getScreenWidth(), Const.getScreenHight());
        AnimationRunner runner = new AnimationRunner(gui, 60 / 1);

        File highScoresFile = new File("highscores.txt");
        HighScore table = new HighScore(highScoresFile);
        table.loadFromFile();

        //File setFile = new File("blocks1.txt");
        File levelDefFile = new File("resources/level_definition.txt");
        FileReader fr = new FileReader(levelDefFile);

        BufferedReader br = new BufferedReader(fr);
        LevelSpecificationReader lsr = new LevelSpecificationReader();
        List<LevelInformation> listLevelInfo = null;
        try {
            listLevelInfo = lsr.fromReader(br, gui.getDrawSurface(), levelDefFile.getAbsoluteFile().getParent());
        } catch (Exception e) {
            System.out.println("parse sys error: " + e.getMessage());
            System.exit(-1); //wrong break program
        }
        System.out.println("finished parsing file!!!yayy");
        for (LevelInformation info : listLevelInfo) {
            System.out.println("levelName: " + info.levelName());
            System.out.println("initialBallVelocities: " + info.initialBallVelocities());
            System.out.println("getBackground: " + info.getBackground());
            System.out.println("paddleSpeed: " + info.paddleSpeed());
            System.out.println("paddleWidth: " + info.paddleWidth());
            System.out.println("numBlocks: " + info.numberOfBlocksToRemove());
            System.out.println("blocksList: " + info.blocks());
            //private String blocksDefs; //block_definitions:blocks1.txt
//            System.out.println("blocksDefs: " + info.b); //todo
//            System.out.println("blocksStartX: "); //todo
//            System.out.println("blocksStartY: "); //todo
            //private int rowHeight;
//            System.out.println("rowHeight: "); //todo
            //private List<String> blocksRowFormat;
//            System.out.println("blocksRowFormat: "); //todo
        }
/*        for (int i = 0; i < args.length; i++) {
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
        }*/
        Menu < Task < Void >> menu = new MenuAnimation<Task<Void>>("Menu Title", gui.getKeyboardSensor(), runner);
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor(), table, menu);
        /* menu definition --- using generics
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Menu Title", gui.getKeyboardSensor(), runner);
        menu.addSelection("s", "Start game", new StartGameTask(gui, runner, table, levelsToPlay, highScoresFile));
        menu.addSelection("h", "Hi Score", new ShowHiScoresTask(runner, table));
        menu.addSelection("q", "Quit game", new ExitTask(gui));*/
        List<LevelInformation> finalListLevelInfo = listLevelInfo;
        Task<Void> startGameTask = new Task<Void>() {
           @Override
            public Void run() {
                int gameScore = gameFlow.runLevels(finalListLevelInfo);
                table.save(gameScore); //save new file with updated score
                return null;
            }
        };
        Task<Void> ShowHiScoresTask = new Task<Void>() {
            private HighScoreAnimation highScoreAnimation = new HighScoreAnimation();
            @Override
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, highScoreAnimation));
                return null;
            }
        };
        Task<Void> ExitTask = new Task<Void>() {
            @Override
            public Void run() {
                gui.close();
                return null;
            }
        };
        menu.addSelection("s", "Start", startGameTask);
        menu.addSelection("h", "High Scores", ShowHiScoresTask); //(runner, score)
        menu.addSelection("q", "Quit", ExitTask);

        while (true) {
            menu.doOneFrame(runner.getGui().getDrawSurface());
            runner.run(menu); // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            menu.reset();
        }
    }
}