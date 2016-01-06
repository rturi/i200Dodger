package i200Dodger;

import jdk.nashorn.internal.ir.WhileNode;

import java.awt.*;
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

        Statement statement = null;
        int i = 0;

        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM SCORES ORDER BY SCORE DESC;";
            ResultSet results = statement.executeQuery(sql);

            do {
                highScoresTable[i][0] = results.getString("PLAYERNAME");
                highScoresTable[i][1] = results.getString("SCORE");
                highScoresTable[i][2] = results.getString("BOARDSIZE");
                highScoresTable[i][3] = results.getString("GAMEDATE");
                //System.out.println(highScoresTable[i][1]);
                i++;
            } while (results.next() && i < 10);

            statement.close();
        }catch(SQLException e){
                e.printStackTrace();
        }

        closeDBConnection(connection);

        return highScoresTable;
    }

    public static void insertHighScore(String playerName, int score, int boardHeight, int boardWidth, String gameDate) {

        Connection connection = openDBconnection();
        String boardSize = Integer.toString(boardWidth) + "x" + Integer.toString(boardHeight);

        System.out.println(score);

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

    public static void deleteTable() {

        Connection connection = openDBconnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM SCORES;";
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("table deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDBConnection(connection);
    }


    public static boolean isHighScore(int score) {

        String[][] scoreTable = getHighScores();

        if (!scoreTable[9][1].equals("-") && score < Integer.parseInt(scoreTable[9][1])) return false;


        return true;
    }

}
