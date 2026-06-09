package SolatTracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class User {
    private static Path filePath = Paths.get("SolatTracker", "users.csv");
    private int userID;
    private String password;
    private boolean[] tasks = new boolean[5];

    public User(int userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public User(int userID, String password, boolean[] tasks) {
        this.userID = userID;
        this.password = password;
        this.tasks = tasks;
    }

    public int getUserID() { return userID; }
    public String getPassword() { return password; }
    public boolean getCompletionStatus(int index) { return tasks[index]; }

    public static ObligatoryPrayers setCompletionStatus(int index, boolean status, User currUser, ObligatoryPrayers oPrayers) { 
        currUser.tasks[index] = status; 
        oPrayers.setPrayersCompleted(currUser);
        return oPrayers;
    }

    /**
     * @return id,password,tasksCompleteStatus[0:5]
     */
    public String toCSV() {
        String line = userID + "," + password;
        for (boolean b : tasks) {
            line += "," + Boolean.toString(b);
        }

        return line;
    }

    public static ArrayList<User> getUsersArray() {
        return readDataFromFile(filePath);
    }

    private static void createFileIfNotExist(Path filePath) {
        try {
            Files.newOutputStream(filePath, StandardOpenOption.CREATE).close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<User> readDataFromFile(Path filePath) {
        createFileIfNotExist(filePath);
        ArrayList<User> users = new ArrayList<User>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            users.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");

                int userID = Integer.parseInt(split[0]);
                String password = split[1];
                boolean[] tasks = new boolean[5];
                for (int i = 0; i < tasks.length; i++) {
                    tasks[i] = Boolean.parseBoolean(split[i + 2]);
                }

                users.add(new User(userID, password, tasks));
            }

        } catch (Exception e) { System.out.println(e.getMessage()); }
        return users;
    }

    public static void saveDataToFile(ArrayList<User> users) {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (User user : users) {
                bw.write(user.toCSV());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static class MaxUserException extends Exception {
        public MaxUserException() {super("Max amount of users reached.\nNo new users can be created");}
    }
}