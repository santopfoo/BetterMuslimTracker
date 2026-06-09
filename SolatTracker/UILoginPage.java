package SolatTracker;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UILoginPage {
    public static Scene create(UIMain app) {
        StackPane root = new StackPane();
        root.setId("auth-page");

        Button dummy = new Button();
        StackPane.setAlignment(dummy, Pos.TOP_LEFT);
        dummy.requestFocus();

        VBox card = new VBox(20);
        card.getStyleClass().add("card");
        card.setMaxWidth(400);

        Text title = new Text("Welcome Back");
        title.getStyleClass().add("form-title");

        VBox userIDBox = new VBox(6);
        userIDBox.setAlignment(Pos.CENTER_LEFT);
        Label userIDLabel = new Label("User ID");
        userIDLabel.getStyleClass().add("form-label");
        TextField userIDField = new TextField();
        userIDField.setPromptText("Enter your user ID");
        userIDField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                userIDField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        userIDBox.getChildren().addAll(userIDLabel, userIDField);

        VBox passwordBox = new VBox(6);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        Label passwordLabel = new Label("Password");
        passwordLabel.getStyleClass().add("form-label");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        Text errorText = new Text("User doesn't exist or incorrect password");
        errorText.getStyleClass().add("error-text");
        errorText.setVisible(false);
        errorText.setManaged(false);

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("btn-primary");
        loginButton.setOnAction(e -> {
            User currUser;
            int userID = Integer.parseInt(userIDField.getText());
            String password = passwordField.getText().trim();

            if (userID < 100 || password.isEmpty())
                currUser = null;
            else {
                ArrayList<User> users = User.getUsersArray();
                currUser = getUserFromUsers(users, userID, password);
            }

            if (currUser == null) {
                userIDField.setText("");
                passwordField.setText("");
                errorText.setManaged(true);
                errorText.setVisible(true);
            } else {
                app.showHomePage(currUser);
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("btn-secondary");
        cancelButton.setOnAction(e -> app.showStartPage());

        buttonBox.getChildren().addAll(loginButton, cancelButton);

        card.getChildren().addAll(title, userIDBox, passwordBox, errorText, buttonBox);
        root.getChildren().addAll(dummy, card);

        Scene scene = new Scene(root, app.width, app.height);
        scene.getStylesheets().add("file:SolatTracker/styles.css");
        return scene;
    }

    private static User getUserFromUsers(ArrayList<User> users, int userID, String password) {
        for (User user : users) {
            if (user.getUserID() == userID && user.getPassword().equals(password)) {
                System.out.println("Welcome user " + userID);
                return user;
            }
        }
        return null;
    }
}
