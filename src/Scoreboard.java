public class Scoreboard {


    private int id;
    private String playerName;
    private int score;

    public Scoreboard(int id, String playerName, int score) {
        this.id = id;
        this.playerName = playerName;
        this.score = score;
    }

    //region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
