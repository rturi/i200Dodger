package i200Dodger;

import java.sql.*;


public class HighScoresDB {


    /* Returns 2D array of strings - 10 rows of scoreboard entries (name, score, board size, time)
    *
    * */

    public static String[][] getHighScores() {

        String[][] highScoresTable = new String[10][4];


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                highScoresTable[i][j] = "-";
            }
        }

        Connection connection = openDBconnection();

        for (int i = 0; i < 10; i++) {

            Statement statement = null;
            try {
                statement = connection.createStatement();
                String sql = "SELECT * FROM SCORES ORDER BY SCORE;";
                ResultSet results = statement.executeQuery(sql);

                highScoresTable[i][0] = results.getString("PLAYERNAME");
                highScoresTable[i][1] = results.getString("SCORE");
                highScoresTable[i][2] = results.getString("BOARDSIZE");
                highScoresTable[i][3] = results.getString("GAMEDATE");
                System.out.println(highScoresTable[i][3]);
                System.out.println();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        closeDBConnection(connection);

        return highScoresTable;
    }

    //ID INT AUTO_INCREMENT, PLAYERNAME TEXT, SCORE INT, BOARDSIZE TEXT, GAMEDATE TEXT)

    public static void insertHighScore(String playerName, int score, int boardHeight, int boardWidth, String gameDate) {

        Connection connection = openDBconnection();
        String boardSize = Integer.toString(boardWidth) + "x" + Integer.toString(boardHeight);

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO SCORES (PLAYERNAME, SCORE, BOARDSIZE, GAMEDATE) VALUES ('" + playerName + "', '"+ score + "', '" + boardSize + "', '" + gameDate + "');";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDBConnection(connection);

    }

    public static Connection openDBconnection() {

        /* code from 22.11.2015 class
        * ToDO: proper referral
        * */
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:login.db");
            System.out.println("DB connection up and running");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeDBConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB connection closed");
    }

    public static void createTable() {

        Connection connection = openDBconnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE SCORES (ID INT AUTO_INCREMENT, PLAYERNAME TEXT, SCORE INT, BOARDSIZE TEXT, GAMEDATE TEXT);";
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDBConnection(connection);
    }

    public static boolean isHighScore(int score) {
        return true;
    }

    public static void countRows() {

        Connection connection = openDBconnection();

        String rowCount = "";

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT Count(*) FROM SCORES;";
            ResultSet results = statement.executeQuery(sql);
            rowCount = results.toString();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDBConnection(connection);

        System.out.println(rowCount);
    }
}
