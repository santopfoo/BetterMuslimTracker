package SolatTracker;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UINewUserPage {
    public static Scene create(UIMain app) {
        ArrayList<User> users = User.getUsersArray();

        // if (users.size() >= 100) { throw new User.MaxUserException();}
        boolean unusedID = false;
        int userID = -1;
        while (!unusedID) {
            int min = 100, max = 200;
            userID = min + (int)(Math.random() * (max - min + 1));
            unusedID = true;

            for (User user : users) {
                if (user.getUserID() == userID) {
                    unusedID = false;
                }
            }
        }

        TextArea promptUserPassword = new TextArea("Enter a password for your account"); promptUserPassword.setEditable(false);

        HBox passwordBox = new HBox(10); 
        Label passwordLabel = new Label("Password:  ");
        TextField passwordField = new TextField(); passwordField.setPromptText("password123");
        passwordBox.getChildren().addAll(passwordLabel, passwordField);
        
        Button okButton = new Button("Confirm\npassword");
        int id = userID;
        okButton.setOnAction(e-> {
            String password = passwordField.getText().trim();
            promptUserPassword.setText(
                "Congrats your userID is\n" +
                id + "\n" +
                "Please go back to login"
            );
            users.add(new User(id, password));
            User.saveDataToFile(users);
        });

        
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> app.showStartPage());

        VBox root = new VBox(promptUserPassword, passwordBox, okButton, cancelButton);
        root.requestFocus();
        return new Scene(root, app.width, app.height);
    }
}
