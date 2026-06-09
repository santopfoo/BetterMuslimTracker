package SolatTracker;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class UIStartPage {
    public static Scene create(UIMain app) {
        VBox root = new VBox(15);
        root.setId("start-page");
        root.setAlignment(Pos.CENTER);

        Text bismillah = new Text("\u0650\u0628\u0650\u0633\u0652\u0645\u0650 \u0671\u0644\u0644\u064e\u0651\u0647\u0650 \u0671\u0644\u0631\u064e\u0651\u062d\u0652\u0645\u064e\u0670\u0646\u0650 \u0671\u0644\u0631\u064e\u0651\u062d\u0650\u064a\u0645\u0650");
        bismillah.setId("bismillah");

        Text title = new Text("Better Muslim Tracker");
        title.setId("app-title");

        Text subtitle = new Text("Your daily prayer companion");
        subtitle.setId("app-subtitle");

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("btn-primary");
        loginButton.setOnAction(e -> app.showLoginPage());

        Button newUserButton = new Button("New User");
        newUserButton.getStyleClass().add("btn-secondary");
        newUserButton.setOnAction(e -> app.showNewUserPage());

        HBox buttonBox = new HBox(20, loginButton, newUserButton);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(bismillah, title, subtitle, buttonBox);
        VBox.setMargin(buttonBox, new Insets(25, 0, 0, 0));

        Scene scene = new Scene(root, app.width, app.height);
        scene.getStylesheets().add("file:SolatTracker/styles.css");
        return scene;
    }
}
