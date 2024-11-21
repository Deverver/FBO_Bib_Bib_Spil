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

    AND yes I know it does not follow DRY, did not want to make a Variable String with DB Table names, -
    that would have to be separated and counted to print a variable amount of (?).
    I would have make something that also checks the dataType and print new prepared statements... too much work for now.
     */

    public static void uploadToScoreboards() {
        String filePath = "src/PlayersFile";
        String sql = "INSERT INTO scoreboards (playername, score, fk) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath));
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String line;
            while ((line = br.readLine()) != null) {
                // Since we are taking data from a file and not an Object, we split the String so we can use each part
                String[] values = line.split(",");
                String NAME = values[0];
                int SCORE = Integer.parseInt(values[1].trim());
                int FK = Integer.parseInt(values[2].trim());

                preparedStatement.setString(1, NAME);
                preparedStatement.setInt(2, SCORE);
                preparedStatement.setInt(3, FK);
                // Learned why we have to use a Batch, next comment
                preparedStatement.addBatch();
            }

            // Learned that you have to do a Batch instead of an Update, this is because our rowsInserted is an array -
            // this also makes it easier to get info out like how many Entries we have put into the DB
            int[] rowsInserted = preparedStatement.executeBatch();

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
                String[] values = line.split(",");
                String TITLE = values[0];
                String MAKER = values[1];

                preparedStatement.setString(1, TITLE);
                preparedStatement.setString(2, MAKER);

                preparedStatement.addBatch();
            }

            int[] rowsInserted = preparedStatement.executeBatch();

            System.out.println(rowsInserted.length + " rows inserted successfully.");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }// uploadToDB End


}
