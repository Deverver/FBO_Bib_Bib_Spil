import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class uploadFromFile {
    /*
    This was made, so I could test the program using multiple computers without having to upload small test to my DB
    Only used to quickly add stuff to tables
    AND yes I know it does not follow DRY
     */

    public static void uploadToScoreboards() {
        String filePath = "src/PlayersFile";
        String sql = "INSERT INTO scoreboards (playername, score, fk) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath));
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split the line into columns
                String NAME = values[0];
                int SCORE = Integer.parseInt(values[1].trim());
                int FK = Integer.parseInt(values[2].trim());

                preparedStatement.setString(1, NAME);
                preparedStatement.setInt(2, SCORE);
                preparedStatement.setInt(3, FK);
                preparedStatement.addBatch(); // Add to batch
            }

            // Learned that you have to do a Batch instead of an Update, this is because our rowsInserted is an array
            int[] rowsInserted = preparedStatement.executeBatch(); // Execute all inserts at once

            System.out.println(rowsInserted.length + " rows inserted successfully.");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }// uploadToDB End

    public static void uploadToGames() {
        String filePath = "src/GamesFile";
        String sql = "INSERT INTO games (title, maker) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath));
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split the line into columns
                String TITLE = values[0];
                String MAKER = values[1];

                preparedStatement.setString(1, TITLE);
                preparedStatement.setString(2, MAKER);

                preparedStatement.addBatch(); // Add to batch
            }

            int[] rowsInserted = preparedStatement.executeBatch(); // Execute all inserts at once

            System.out.println(rowsInserted.length + " rows inserted successfully.");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }// uploadToDB End


}
