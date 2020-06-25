package menu;

import settings.ScoreInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a high score table object in the game.
 */

public class HighScoreTable {
    private List<ScoreInfo> highScores;
    private File filename;
    private int size;

    /**
     * construct a HighScoresTable object with the given size.
     */
    public HighScoreTable(int Size) {
        this.highScores = new ArrayList<ScoreInfo>();
        this.size = size;
    }

    /**
     * this methods reads a table from file and return it.
     *
     * @param filename the certain file name.
     * @return a new score table with the updated values according to the data in the file.
     * if the file does not exist, or there is a proble reading it --- an empty table is returned.
     */
    public static HighScoreTable loadFromFile(File filename) { //todo
        HighScoreTable emptyTable = new HighScoreTable();
        try {
            if (!filename.exists()) {
                return emptyTable;
            }
            emptyTable.load(filename);
        } catch (IOException e) {
            System.err.println("Failed closing file: " + filename.getName());
            return new HighScoresTable(5);
        }
        return emptyTable;
    }

    /**
     * this method loads the score data from file.
     *
     * @param filename the given filename.
     * @throws IOException if there was an error with reading the file.
     */
    private void load(File filename) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename.getName()));
            highScoresTable = (HighScoreTable) objectInputStream.readObject();
            this.highScores = highScoresTable.highScores;
            this.size = highScoresTable.size;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
            HighScoresTable emptyTable = new HighScoresTable(5);
            emptyTable.save(filename);
            this.highScores = emptyTable.highScores;
            this.size = emptyTable.size;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: " + filename.getName());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    public void save(File highScoresFile) {

    }
}