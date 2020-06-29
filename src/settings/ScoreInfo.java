package settings;

public class ScoreInfo {
    private String name;
    private int score;

    /**
     * this method constructs a ScoreInfo.
     * @return a ScoreInfo.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * this method gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * this method gets score.
     *
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }
}
