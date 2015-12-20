package i200Dodger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Roland on 20/12/2015.
 */
public class HighScores {


    /* Returns 2D array of strings - 10 rows of scoreboard entries (name, score, board size, time)
    *
    * */

    //To be run only once
    public static void createDBTable() {
        /* DB fields:
        * id - auto increment
        * name - string
        * score - int
        * board size - string
        * time - timestamp/string?
        * */
    }

    public static void testDBConnecion() {
        closeDBConnection(openDBconnection());
    }

    public String[][] getHighScores () {

        String[][] highScoresTable = new String[10][];


        return highScoresTable;
    }

    private static Connection openDBconnection() {

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

    private static void closeDBConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB connection closed");
    }

}
