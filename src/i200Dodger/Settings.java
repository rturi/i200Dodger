package i200Dodger;

import java.io.*;

public class Settings {

    /* Settings are stored in a text file. One row per setting. ParameterName:value
    *
    * getSetting checks if its input key matches the parameter name. If it does the corresponding values is returned.
    *
    * setSetting searches the settings file in the same way and changes the parameter value when it finds the correct row.
    * */


    private static String getSetting(String key){

        File file = new File(System.getProperty("user.dir") + "\\src\\i200Dodger\\settings\\settings.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = br.readLine();
            while (line != null) {
                String[] workerLine = line.split(":");
                if (workerLine[0].equals(key)) {
                    return workerLine[1];
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Fail";

    }


    private static void setSetting(String key, String newValue) {
        System.out.println("starting " + key + " change.. old value " + getSetting(key) + " new value = " + newValue);

        File oldSettingsFile = new File(System.getProperty("user.dir") + "\\src\\i200Dodger\\settings\\settings.txt");
        File newSettingsFile = new File(System.getProperty("user.dir") + "\\src\\i200Dodger\\settings\\settings_tmp.txt");

        FileWriter writer = null;
        try {
            writer = new FileWriter(newSettingsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(oldSettingsFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = reader.readLine();
            while (line != null) {
                String[] workerLine = line.split(":");
                if (workerLine[0].equals(key)) {
                    writer.write(key + ":" + newValue + "\r\n");
                    System.out.println("test");
                } else
                    writer.write(line + "\r\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer = new FileWriter(oldSettingsFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader(newSettingsFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            line = reader.readLine();
            while (line != null) {
                writer.write(line + "\r\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static int getBoardWidth() {
        return Integer.parseInt(getSetting("boardWidth"));
    }


    public static void setBoardWidth(int newBoardWidth) {
        setSetting("boardWidth", Integer.toString(newBoardWidth));
    }


    public static int getBoardHeight() {
        return Integer.parseInt(getSetting("boardHeight"));
    }


    public static void setBoardHeight(int newBoardHeight) {
        setSetting("boardHeight", Integer.toString(newBoardHeight));
    }


    public static String getPlayerName() {
        return getSetting("playerName");
    }


    public static void setPlayerName(String playerName) {
        setSetting("playerName", playerName);
    }

}