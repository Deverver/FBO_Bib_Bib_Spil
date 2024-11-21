import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GamesRepository {
    // This is where the horrors begin

    public void createGame(Game game) {
        // In order to transfer data to the DB we have to make our object readable to MySQL
        // We do this by choosing where our object data goes in our SQL Query
        String sql = "INSERT INTO games (title, maker) VALUES(?, ?)";

        // In order to do anything in our DB we have to make a connection
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Selects where the data goes into our query
            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, game.getMaker());

            // Should "do" execute the query, and report if something went wrong
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Game " + game.getTitle() + " has been created in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// createGame End

    public void updateGame(Game game) {
        // In order to update in the DB, we have to provide and SET new data, and choose WHERE it goes
        String sql = "UPDATE games SET title = ?, maker = ? WHERE id = ?";

        // We have to make a new connection to our DB
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, game.getMaker());
            preparedStatement.setInt(3, game.getId());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Game " + game.getTitle() + "ID: " + game.getId() + " has been updated in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// updateGame End

    // Same as updating we have to choose which game to delete via ID
    public void deleteGame(int gameId) {
        String sql = "DELETE FROM games WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, gameId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Game from ID: " + gameId + " has been deleted in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// deleteGame End

    public List<Game> readGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games";

        /*
        Here is something vital, Idk why but we need to use Statement this time to intake the DB data.
        Using both the Statement and the ResultSet, we read and store the info separately before -
        creating Game objects from the ResultSet and storing them in an array.
         */

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Loops through DB table, and creates the Game objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String maker = resultSet.getString("maker");

                games.add(new Game(id, title, maker));
            }// WLoop End
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }// readGames End

}// GamesRepository End
