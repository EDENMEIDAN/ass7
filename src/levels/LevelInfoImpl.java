package levels;

import interfaces.Sprite;
import settings.Velocity;
import sprites.Block;
import java.util.ArrayList;
import java.util.List;

public class LevelInfoImpl implements LevelInformation {
    private String levelName;
    private List<Velocity> initialBallVelocities;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private String blocksDefs; //block_definitions:blocks1.txt
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numBlocks;
    private List<String> blocksRowFormat;
    private List<Block> blocks;

    public LevelInfoImpl() {
        this.levelName = " ";
        this.initialBallVelocities = new ArrayList<>();
        this.background = null;
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.blocksDefs = null; //block_definitions:blocks1.txt
        this.blocksStartX = 0;
        this.blocksStartY = 0;
        this.rowHeight = 0;
        this.numBlocks = 0;
        this.blocksRowFormat = new ArrayList<>();
        this.blocks = new ArrayList<>();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public List<Velocity> getInitialBallVelocities() {
        return initialBallVelocities;
    }

    public void setInitialBallVelocities(List<Velocity> initialBallVelocities) {
        this.initialBallVelocities = initialBallVelocities;
    }

    public void setBackground(Sprite background) {
        this.background = background;
    }

    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    public int getPaddleWidth() {
        return paddleWidth;
    }

    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    public String getBlocksDefs() {
        return blocksDefs;
    }

    public void setBlocksDefs(String blocksDefs) {
        this.blocksDefs = blocksDefs;
    }

    public int getBlocksStartX() {
        return blocksStartX;
    }

    public void setBlocksStartX(int blocksStartX) {
        this.blocksStartX = blocksStartX;
    }

    public int getBlocksStartY() {
        return blocksStartY;
    }

    public void setBlocksStartY(int blocksStartY) {
        this.blocksStartY = blocksStartY;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public int getNumBlocks() {
        return numBlocks;
    }

    public void setNumBlocks(int numBlocks) {
        this.numBlocks = numBlocks;
    }

    public List<String> getBlocksRowFormat() {
        return blocksRowFormat;
    }

    public void setBlocksRowFormat(List<String> blocksRowFormat) {
        this.blocksRowFormat = blocksRowFormat;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }


    /**
     * this method returns number of balls of a certain level.
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities.size();
    }

    /**
     * this method returns a list of the different balls velocities.
     *
     * @return list list of balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * this method returns the paddle's speed.
     *
     * @return paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * this method returns the paddle's width.
     *
     * @return paddle's width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * this method returns the level's name as a string. the level name will be displayed at the top of the screen.
     *
     * @return the level's name as a string.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * this method returns a sprite with the level's background.
     *
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * this method returns a list of level's blocks.
     *
     * @return a list of the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * this method returns the number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return number of blocks left on the screen.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    public void addRowToBlocksString(String row) {
        blocksRowFormat.add(row);
    }
}
