package SolatTracker;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UINewUserPage {
    public static Scene create(UIMain app) {
        StackPane root = new StackPane();
        root.setId("auth-page");

        Button dummy = new Button();
        StackPane.setAlignment(dummy, Pos.TOP_LEFT);
        dummy.requestFocus();

        VBox card = new VBox(20);
        card.getStyleClass().add("card");
        card.setMaxWidth(400);

        Text title = new Text("Create Account");
        title.getStyleClass().add("form-title");

        ArrayList<User> users = User.getUsersArray();
        int userID = -1;
        while (true) {
            int min = 100, max = 200;
            userID = min + (int)(Math.random() * (max - min + 1));
            boolean unusedID = true;
            for (User user : users) {
                if (user.getUserID() == userID) {
                    unusedID = false;
                }
            }
            if (unusedID) break;
        }

        Text assignedID = new Text("Your assigned User ID: " + userID);
        assignedID.setStyle("-fx-fill: #8899AA; -fx-font-size: 14px;");

        VBox passwordBox = new VBox(6);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        Label passwordLabel = new Label("Create a Password");
        passwordLabel.getStyleClass().add("form-label");
        TextField passwordField = new TextField();
        passwordField.setPromptText("Enter a password for your account");
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        Text successText = new Text();
        successText.getStyleClass().add("error-text");
        successText.setVisible(false);
        successText.setManaged(false);

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        int id = userID;
        Button confirmButton = new Button("Create Account");
        confirmButton.getStyleClass().add("btn-primary");
        confirmButton.setOnAction(e -> {
            String password = passwordField.getText().trim();
            if (password.isEmpty()) {
                successText.setStyle("-fx-fill: #E57373; -fx-font-size: 13px;");
                successText.setText("Password cannot be empty");
                successText.setManaged(true);
                successText.setVisible(true);
                return;
            }
            successText.setStyle("-fx-fill: #4CAF50; -fx-font-size: 13px;");
            successText.setText("Account created! Your ID: " + id + "\nPlease go back to login");
            successText.setManaged(true);
            successText.setVisible(true);
            users.add(new User(id, password));
            User.saveDataToFile(users);
            confirmButton.setDisable(true);
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("btn-secondary");
        cancelButton.setOnAction(e -> app.showStartPage());

        buttonBox.getChildren().addAll(confirmButton, cancelButton);

        card.getChildren().addAll(title, assignedID, passwordBox, successText, buttonBox);
        root.getChildren().addAll(dummy, card);

        Scene scene = new Scene(root, app.width, app.height);
        scene.getStylesheets().add("file:SolatTracker/styles.css");
        return scene;
    }
}
