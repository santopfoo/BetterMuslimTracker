package SolatTracker;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UILoginPage {
    public static Scene create(UIMain app) {
        VBox root = new VBox();

        HBox userIDBox = new HBox(10);
        Label userIDLabel = new Label("User ID:");
        TextField userIDField = new TextField(); userIDField.setPromptText("100");
        userIDField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                userIDField.setText(newValue.replaceAll("[^\\d]", ""));                
            }
        });
        userIDBox.getChildren().addAll(userIDLabel, userIDField);

        HBox passwordBox = new HBox(10);
        Label passwordLabel = new Label("Password:  ");
        PasswordField passwordField = new PasswordField(); passwordField.setPromptText("password123");
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            User currUser;
            // todo: try catch parseInt
            int userID = Integer.parseInt(userIDField.getText());
            String password = passwordField.getText().trim();

            if (userID < 100 || password.isEmpty()) currUser = null;
             else {
                ArrayList<User> users = User.getUsersArray();
                currUser = getUserFromUsers(users, userID, password);
            }
            
            if (currUser == null) {
                userIDField.setText("");
                userIDField.setPromptText("User doesn't exist or incorrect password");
            } else {
                app.showHomePage(currUser);
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> app.showStartPage());

        root.getChildren().addAll(userIDBox, passwordBox, okButton, cancelButton);
        root.requestFocus();
        return new Scene(root, app.width, app.height);
    }

    /**
     * 
     * @param userID
     * @param password
     * 
     * @return If user exists, registered user and tasks.
     * If not, null
     */
    private static User getUserFromUsers(ArrayList<User> users, int userID, String password) {

        for (User user : users) {
            if (user.getUserID() == userID && user.getPassword().equals(password)) {
                System.out.println("Welcome user " + userID);
                return user;
            }
        }

        return null;
        // return new User(1, "1");
    }
}
