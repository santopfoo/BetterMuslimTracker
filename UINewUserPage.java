package SolatTracker;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UINewUserPage {
    public static Scene create(UIMain app) {
        StackPane root = new StackPane();
        Button dummy = new Button();
        StackPane.setAlignment(dummy, Pos.TOP_LEFT);
        dummy.requestFocus();


        GridPane gPane = new GridPane();
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(60);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(40);
        RowConstraints rc1 = new RowConstraints();
        rc1.setValignment(VPos.CENTER);
        rc1.setPercentHeight(100);
        gPane.getColumnConstraints().addAll(cc1, cc2);
        gPane.getRowConstraints().add(rc1);


        VBox iViewBox = new VBox();
        iViewBox.setAlignment(Pos.CENTER);
        iViewBox.setStyle("-fx-background-color: #141414;");
        ImageView iView = new ImageView("SolatTracker/MatrixBlue.png");
        iView.setRotate(-30);
        iViewBox.getChildren().add(iView);


        VBox newUserBox = new VBox(20);
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
            User.saveUsersToFile(users);
        });

        
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> app.showStartPage());
        newUserBox.getChildren().addAll(promptUserPassword, passwordBox, okButton, cancelButton);
        gPane.add(newUserBox, 0, 0);
        gPane.add(iViewBox, 1, 0);

        root.getChildren().add(gPane);
        return new Scene(root, app.width, app.height);
    }
}
