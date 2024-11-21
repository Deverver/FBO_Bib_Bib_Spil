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
    }// uploadToScoreboards End

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
    }// uploadToGames End

    public static void uploadToLink() {
        // This should not be here since i opted out from using a file
        String sql = "INSERT INTO gametoscorelink (togamesfk, toscoreboardsfk) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int entry = 1; entry <= 100; entry++) {
                int TOGAMES;

                // Lesson learned: it would have been faster, scalable and future-proof if I had make a method to create a File instead
                // Edit never doing this again like this...
                switch (entry) {
                    case 1, 2, 3, 4, 5 -> {
                        TOGAMES = 1;
                    }
                    case 6, 7, 8, 9, 10, 11, 12 -> {
                        TOGAMES = 2;
                    }
                    case 13, 14, 15 -> {
                        TOGAMES = 3;
                    }
                    case 16, 17, 18, 19, 20, 21, 22 -> {
                        TOGAMES = 4;
                    }
                    case 23, 24, 25, 26 -> {
                        TOGAMES = 5;
                    }
                    case 27, 28, 29, 30, 31 -> {
                        TOGAMES = 6;
                    }
                    case 32, 33, 34 -> {
                        TOGAMES = 7;
                    }
                    case 35 -> {
                        TOGAMES = 8;
                    }
                    case 36, 37 -> {
                        TOGAMES = 9;
                    }
                    case 38, 39, 40 -> {
                        TOGAMES = 10;
                    }
                    case 41, 42, 43 -> {
                        TOGAMES = 11;
                    }
                    case 44, 45, 46, 47, 48, 49 -> {
                        TOGAMES = 12;
                    }
                    case 50, 51 -> {
                        TOGAMES = 13;
                    }
                    case 52, 53, 54 -> {
                        TOGAMES = 14;
                    }
                    case 55, 56, 57, 58, 59, 60 -> {
                        TOGAMES = 15;
                    }
                    case 61, 62, 63, 64, 65, 66, 67 -> {
                        TOGAMES = 16;
                    }
                    case 68, 69, 70, 71, 72 -> {
                        TOGAMES = 17;
                    }
                    case 73, 74, 75, 76, 77, 78 -> {
                        TOGAMES = 18;
                    }
                    case 79, 80, 81, 82 -> {
                        TOGAMES = 19;
                    }
                    case 83, 84, 85 -> {
                        TOGAMES = 20;
                    }
                    case 86 -> {
                        TOGAMES = 21;
                    }
                    case 87, 88 -> {
                        TOGAMES = 22;
                    }
                    case 89, 90 -> {
                        TOGAMES = 23;
                    }
                    case 91, 92, 93 -> {
                        TOGAMES = 24;
                    }
                    case 94, 95, 96, 97, 98 -> {
                        TOGAMES = 25;
                    }
                    case 99, 100 -> {
                        TOGAMES = 26;
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + entry);
                }// Switch End

                int TOSCORE = entry;

                preparedStatement.setInt(1, TOGAMES);
                preparedStatement.setInt(2, TOSCORE);
                preparedStatement.addBatch();
            }

            int[] rowsInserted = preparedStatement.executeBatch();

            System.out.println(rowsInserted.length + " rows inserted successfully.");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }// uploadToLink End


}
