package task;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import settings.Const;
import settings.ScoreInfo;

import java.awt.Color;
import java.util.List;

import static java.lang.String.valueOf;

public class HighScoreAnimation implements Animation {
    private List<ScoreInfo> highScoreList;
    private KeyboardSensor keyboard;


    /**
     * this method constructsna new High scores animation.
     *
     * @param highScoreList the high score list.
     * @param keyboard the KeyboardSensor.
     */
    public void HighScoresAnimation(List<ScoreInfo> highScoreList, KeyboardSensor keyboard) {
        this.highScoreList = highScoreList;
        this.keyboard = keyboard;
    }

    /**
     * this method is the frame-management code.
     *
     * @param d is the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0xF500030E, true));
        d.fillRectangle(0, 0, Const.getScreenWidth(), Const.getScreenHight());

        d.setColor(new Color(0xFF5A4F));
        d.drawText((Const.getScreenWidth() / 2) - 130, (Const.getScreenHight() / 2) - 200, "High Scores", 50);

        d.setColor(new Color(0x0EFF26));
        d.drawText((Const.getScreenWidth() / 2) - 250, (Const.getScreenHight() / 2) - 100, "Player Name", 40);
        d.drawText((Const.getScreenWidth() / 2) + 150, (Const.getScreenHight() / 2) - 100, "Score", 40);

        d.setColor(new Color(0xFFFD64));
        d.drawText((Const.getScreenWidth() / 2) - 250, (Const.getScreenHight() / 2) - 90, "__________________", 50);

        d.setColor(new Color(0x118FFF));
        int xPlayer = (Const.getScreenWidth() / 2) - 220;
        int y = (Const.getScreenHight() / 2) - 40;
        int xScore = (Const.getScreenWidth() / 2) + 170;
        for (ScoreInfo i : this.highScoreList) {
            d.drawText(xPlayer, y, i.getName(), 35);
            d.drawText(xScore, y, valueOf(i.getScore()), 35);
            y += 60;
        }

        d.setColor(Color.black);
        d.drawText((Const.getScreenWidth() / 2) - 140, d.getHeight() - 40, "press space to exit", 32);
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
