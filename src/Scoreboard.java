public class Scoreboard {


    private String id;
    private String playerName;
    private int score;

    public Scoreboard(String id, String playerName, int score) {
        this.id = id;
        this.playerName = playerName;
        this.score = score;
    }

    //region Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    //endregion

    @Override
    public String toString() {
        return String.format("Scoreboard: [playerName=%s, score=%s]",
                playerName, score);
    }

}// Scoreboard End
