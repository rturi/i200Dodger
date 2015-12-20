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

        return 5;
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

        return 8;
    }

    public static void setBoardWidth(int newBoardWidth) {

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
            System.out.println(line);
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

    }

    public static void setBoardHeight(int newBoardHeight) {
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
            System.out.println(line);
            while (line != null) {
                String[] workerLine = line.split(":");
                if (workerLine[0].equals("boardWidth")) {
                    writer.write("boardWidth:" + newBoardHeight + "\r\n");
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
}
