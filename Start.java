package SolatTracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class UserLogin {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Path filePath = Paths.get("SolatTracker", "users.csv");
        createFileIfNotExist(filePath);

        ArrayList<User> users = new ArrayList<User>();
        users = readDataFromFile(filePath);

        User currUser;
        boolean exitChoice = false;
        int userID, choice = 0;

        while (!exitChoice) {
            // for (User user : users) {System.out.println(user.toString());}
            // System.out.println(users.size());
            saveDataToFile(filePath, users);
            
            try {
                System.out.print("Command: ");
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Command entered is not an integer\n");
                continue;
            } catch (Exception e) {System.out.println(e.getMessage());}

            switch (choice) {
                case 1:
                    currUser = loginUser(input, users);
                    OPManager.manageUserPrayers(input, currUser);
                    break;

                case 2:
                    try {
                       checkMaxUsers(users); 
                       users.add(createNewUser(input, users));
                    } catch (User.MaxUserException e) {System.out.println(e.getMessage());}
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid command\n");
                    break;
            }
        }

    }

    private static void createFileIfNotExist(Path filePath) {
        try {
            Files.newOutputStream(filePath, StandardOpenOption.CREATE).close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static ArrayList<User> readDataFromFile(Path filePath) {
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    private static void saveDataToFile(Path filePath, ArrayList<User> users) {
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
    private static User loginUser(Scanner input, ArrayList<User> users) {
        int userID;
        String password;

        System.out.print("UserID: ");
        userID = input.nextInt(); input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();

        for (User user : users) {
            if (user.getUserID() == userID && user.getPassword().equals(password)) {
                System.out.println("Welcome user " + userID);
                return user;
            }
        }

        return null;
    }
    private static User createNewUser(Scanner input, ArrayList<User> users) throws User.MaxUserException {
        boolean unusedID = false;
        int userID = -1;
        
        while (!unusedID) {
            int min = 100, max = 200;
            userID = min + (int)(Math.random() * (max - min +1));
            unusedID = true;

            for (User user : users) {
                if (user.getUserID() == userID) {
                    System.out.println("UserID " + userID);
                    unusedID = false;
                }
            }
        }
        System.out.println("UserID: " + userID);
        System.out.print("Enter a password: ");
        String password = input.nextLine();

        return (new User(userID, password));
    }
    private static void checkMaxUsers(ArrayList<User> users) throws User.MaxUserException {
        if (users.size() >= 100) {throw new User.MaxUserException();}}
}