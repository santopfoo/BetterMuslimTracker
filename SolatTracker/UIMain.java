// javac -cp "javafx-sdk-21.0.11/lib/*" SolatTracker/*.java
    // java --module-path javafx-sdk-21.0.11/lib: --add-modules javafx.controls SolatTracker.Main
package SolatTracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UIMain extends Application {
    public double width = 900, height = 600;
    private Stage stage;
    private Scene startPage, loginPage, newUserPage;
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;

        stage.setMinWidth(this.width);
        stage.setMinHeight(this.height);
        stage.setTitle("Better Muslim Tracker");
        stage.centerOnScreen();

        startPage = UIStartPage.create(this);
        loginPage = UILoginPage.create(this);
        newUserPage = UINewUserPage.create(this);

        stage.setScene(startPage);
        stage.requestFocus();
        stage.show();
    }

    public void showStartPage() { stage.setScene(startPage); }
    public void showLoginPage() { stage.setScene(loginPage); }
    public void showNewUserPage() { stage.setScene(newUserPage); }
    public void showHomePage(User currUser) {
        stage.setScene(UIHomePage.create(this, currUser));
    }

    public static void main(String[] args) { launch(args); }
}