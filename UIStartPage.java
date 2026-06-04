package SolatTracker;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class UIStartPage {
    public static Scene create(UIMain app) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #141414;");
        ImageView iView = new ImageView("SolatTracker/MatrixCropped.jpg");
        iView.setFitWidth(app.width);
        iView.setPreserveRatio(true);
        iView.fitWidthProperty().bind(root.widthProperty());

        GridPane gPane = new GridPane(0, 0);
        for (int i = 0; i < 4; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            RowConstraints rc = new RowConstraints();
            
            cc.setPercentWidth(25);
            rc.setPercentHeight(25);
            if (i==1 || i==2) { cc.setHalignment(HPos.CENTER); }
            if (i==2) { rc.setValignment(VPos.CENTER); }

            gPane.getColumnConstraints().add(cc);
            gPane.getRowConstraints().add(rc);
        }
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> app.showLoginPage());
        loginButton.setTranslateX(-35);
        loginButton.setTranslateY(5);

        Button newUserButton = new Button("New User");
        newUserButton.setOnAction(e -> app.showNewUserPage());
        newUserButton.setTranslateX(35);
        newUserButton.setTranslateY(5);

        gPane.add(loginButton, 1, 2);
        gPane.add(newUserButton, 2, 2);
        root.getChildren().addAll(iView, gPane);
        root.requestFocus();
        
        // Timeline flicker = new Timeline(
        //     new KeyFrame(Duration.ZERO, new KeyValue(iView.opacityProperty(), 0.0)),
        //     new KeyFrame(Duration.millis(80), new KeyValue(iView.opacityProperty(), 1.0)),
        //     new KeyFrame(Duration.millis(160), new KeyValue(iView.opacityProperty(), 0.3)),
        //     new KeyFrame(Duration.millis(260), new KeyValue(iView.opacityProperty(), 0.9)),
        //     new KeyFrame(Duration.millis(340), new KeyValue(iView.opacityProperty(), 0.2)),
        //     new KeyFrame(Duration.millis(420), new KeyValue(iView.opacityProperty(), 0.5)),
        //     new KeyFrame(Duration.millis(700), new KeyValue(iView.opacityProperty(), 1.0))
        // );
        // flicker.setCycleCount(1);
        // flicker.setAutoReverse(false);
        // flicker.play();
        return new Scene(root, app.width, app.height);
    }
}
