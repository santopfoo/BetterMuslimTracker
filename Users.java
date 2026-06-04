package SolatTracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Users {

    private static Path filePath = Paths.get("SolatTracker", "users.csv");
    ArrayList<User> users = new ArrayList<User>();
    
    public Users() {
        users = readDataFromFile(filePath);
    }

    /**
     * 
     * @param userID
     * @param password
     * 
     * @return If user exists, registered user and tasks.
     * If not, null
     */
    public User getUserFromUsers(int userID, String password) {

        for (User user : users) {
            if (user.getUserID() == userID && user.getPassword().equals(password)) {
                System.out.println("Welcome user " + userID);
                return user;
            }
        }

        return null;
        // return new User(1, "1");
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
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}