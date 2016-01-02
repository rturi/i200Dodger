package i200Dodger;

import java.io.*;

/**
 * Created by Roland on 19/12/2015.
 */
public class Settings {

    public static int getBoardWidth(){

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
                if (workerLine[0].equals("boardWidth")) {
                    return Integer.parseInt(workerLine[1]);
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

        return 5; // returns a sensible value, if reading from file fails
    }

    public static int getBoardHeight(){
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
                if (workerLine[0].equals("boardHeight")) {
                    return Integer.parseInt(workerLine[1]);
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

        return 8; // returns a sensible value, if reading from file fails
    }

    public static void setBoardWidth(int newBoardWidth) {

        System.out.println("starting width change.. old value " + getBoardWidth());

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
                if (workerLine[0].equals("boardWidth")) {
                    writer.write("boardWidth:" + newBoardWidth + "\r\n");
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
        System.out.println("new width " + getBoardWidth());
    }

    public static void setBoardHeight(int newBoardHeight) {

        System.out.println("starting height change.. old value " + getBoardHeight());

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
                if (workerLine[0].equals("boardHeight")) {
                    writer.write("boardHeight:" + newBoardHeight + "\r\n");
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

        System.out.println("new value " + getBoardHeight());
    }

    public static String getPlayerName() {
        return getSetting("playerName");
    }

    private static String getSetting(String key){
        String result = "";

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

        return "8"; // returns a sensible value, if reading from file fails

    }

    private static void setSetting(String key, String newValue) {
        System.out.println("starting " + key + " change.. old value " + getSetting(key));

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

        System.out.println("new value " + getSetting(key));
    }

    public static void setPlayerName(String playerName) {
        setSetting("playerName", playerName);
    }
}
