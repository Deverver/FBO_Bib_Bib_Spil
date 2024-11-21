public class Game {

    private int id;
    private String title;
    private String maker;

    // Constructor
    public Game(int id, String title, String maker) {
        this.id = id;
        this.title = title;
        this.maker = maker;
    }

    //region Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    //endregion

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Maker: %s",
                id, title, maker);
    }

}// Game End

