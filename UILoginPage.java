package SolatTracker;

import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UILoginPage {
    public static Scene create(UIMain app) {
        StackPane root = new StackPane();
        Button dummy = new Button();
        StackPane.setAlignment(dummy, Pos.TOP_LEFT);
        dummy.requestFocus();
        root.getChildren().add(dummy);


        GridPane gPane = new GridPane();
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(40);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(60);
        RowConstraints rc1 = new RowConstraints();
        rc1.setValignment(VPos.CENTER);
        rc1.setPercentHeight(100);
        gPane.getColumnConstraints().addAll(cc1, cc2);
        gPane.getRowConstraints().add(rc1);


        VBox iViewBox = new VBox();
        iViewBox.setAlignment(Pos.CENTER);
        iViewBox.setStyle("-fx-background-color: #141414;");
        ImageView iView = new ImageView("SolatTracker/MatrixRed.png");
        iView.setRotate(30);
        iViewBox.getChildren().add(iView);


        VBox loginBox = new VBox(20);
        loginBox.setAlignment(Pos.CENTER);
        HBox userIDBox = new HBox(10);
        userIDBox.setAlignment(Pos.CENTER);
        Label userIDLabel = new Label("  User ID:");
        TextField userIDField = new TextField();
        userIDField.setPromptText("100");
        userIDField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                userIDField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        userIDBox.getChildren().addAll(userIDLabel, userIDField);

        HBox passwordBox = new HBox(10);
        passwordBox.setAlignment(Pos.CENTER);
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password123");
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        Text incorrectText = new Text("User doesn't exist\n or incorrect password");
        incorrectText.setTextAlignment(TextAlignment.CENTER);
        incorrectText.setVisible(false);
        incorrectText.setManaged(false);

        HBox buttonBox = new HBox(30);
        buttonBox.setAlignment(Pos.CENTER);
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            User currUser;
            // todo: try catch parseInt
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
                incorrectText.setManaged(true);
                incorrectText.setVisible(true);
            } else {
                app.showHomePage(currUser);
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> app.showStartPage());
        buttonBox.getChildren().addAll(okButton, cancelButton);
        loginBox.getChildren().addAll(userIDBox, passwordBox, incorrectText, buttonBox);

        gPane.add(iViewBox, 0, 0);
        gPane.add(loginBox, 1, 0);


        root.getChildren().add(gPane);
        return new Scene(root, app.width, app.height);
    }

    /**
     * 
     * @param userID
     * @param password
     * 
     * @return If user exists, registered user and tasks.
     *         If not, null
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
