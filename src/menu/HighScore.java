package menu;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * this class represents a high score table object in the game.
 */

public class HighScore {
    private File filename;
    private int size;

    /**
     * construct a HighScoresTable object with the given size.
     */
    public HighScore(File filename) {
        this.filename = filename;
        this.size = 0;
    }

    /**
     * this methods reads a table from file and return it.
     *
     * @return a new score table with the updated values according to the data in the file.
     * if the file does not exist, or there is a proble reading it --- an empty table is returned.
     */
    public void loadFromFile() {
        try {
            if (filename.exists()) {
                Path filePath = Paths.get(filename.getAbsolutePath());
                Charset charset = StandardCharsets.ISO_8859_1;
                List<String> lines = Files.readAllLines(filePath, charset); //list of lines in file
                String line0 = lines.get(0); //our first line
                String[] part0 = line0.split(" "); //spilt at " "
                String stringSize = part0[part0.length - 1]; //last split
                this.size = Integer.parseInt(stringSize);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("IOException" + e.getMessage());
        } finally {
            System.out.println("loadFromFile size is - " + this.size);
        }
    }

    public void save(int newSize) {
        if (newSize > this.size) {
            this.size = newSize;
            try {
                FileWriter myWriter = new FileWriter(filename.getName());
                myWriter.write("The highest score so far is: " + newSize);
                myWriter.close();
                System.out.println("Successfully wrote to the file."); //test
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.out.println("save - hieghst score updated");
        } else {
            System.out.println("save - score not changed");
        }
    }
}