package SolatTracker;

public class User {
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

    public int getUserID() {return userID;}
    public String getPassword() {return password;}
    public boolean getCompleteStatus(int index) {return tasks[index];}

    @Override
    public String toString() {
        String line = userID + "," + password;
        for (boolean b : tasks) {
            line += "," + Boolean.toString(b);
        }

        return line;
    }

    public static class MaxUserException extends Exception {
        public MaxUserException() {super("Max amount of users reached.\nNo new users can be created");}
    }
}