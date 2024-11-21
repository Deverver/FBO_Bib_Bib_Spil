import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creates an instance of our repository, we use it for CRUD operations
        GamesRepository repository = new GamesRepository();

        // Makes the Program run continuously, while refreshing the menu
        while (true) {
            System.out.println("\n* * * Retro Games Manger * * *");
            System.out.println("[1] Create Game");
            System.out.println("[2] Update Game information");
            System.out.println("[3] Delete game");
            System.out.println("[4] Show all games");
            System.out.println("[5] Exit Program");
            System.out.println("[6] Upload to Scoreboards");
            System.out.println("[7] Upload to Games");
            System.out.print("Please enter your choice: ");

            // Stores the menu choice and clears scanner
            int selectedAction = scanner.nextInt();
            scanner.nextLine();

            switch (selectedAction) {
                case 1 -> {
                    System.out.println("Enter game Title: ");
                    String gameTitle = scanner.nextLine();
                    System.out.println("Enter game Maker: ");
                    String gameMaker = scanner.nextLine();

                    // Should crate game object and add it to database
                    // Hopefully AutoIncrement links id and SB-FK to the same value (Update: It did not..)
                    Game game = new Game(0, gameTitle, gameMaker);
                    repository.createGame(game);
                }
                case 2 -> {
                    System.out.println("Enter game ID to Update: ");
                    int id  = scanner.nextInt();
                    scanner.nextLine();// Clears scanner before next set of inputs
                    System.out.println("Enter new Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.println("Enter new Maker: ");
                    String newMaker = scanner.nextLine();

                    Game game = new Game(id, newTitle, newMaker);
                    repository.updateGame(game);
                }
                case 3 -> {
                    System.out.println("Enter game ID to Delete: ");
                    int id  = scanner.nextInt();
                    repository.deleteGame(id);
                }
                case 4 -> {
                    // Crates a list of Game objects from DB, and prints them out
                    List<Game> games = repository.readGames();
                    games.forEach(System.out::println);
                }
                case 5 -> {
                    // We close the scanner and exit the WLoop
                    System.out.println("System shutting down...");
                    scanner.close();
                    return;
                }
                case 6 -> {
                    uploadFromFile.uploadToScoreboards();
                }
                case 7 -> {
                    uploadFromFile.uploadToGames();
                }
                default -> System.out.println("Invalid choice, please try again");
            }// Switch End
        }// WLoop End

        // need to make it so the FK in games links to fk in scoreboards


    }// main End
}// Main End