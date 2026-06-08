package SolatTracker;

import java.time.LocalTime;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UIHomePage {
    private static ObligatoryPrayers oPrayers = new ObligatoryPrayers();
    public static Scene create(UIMain app, User currUser) {
        oPrayers.setPrayersCompleted(currUser);
        GridPane root = new GridPane();
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(30);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(70);
        root.getColumnConstraints().addAll(cc1, cc2);
        root.setGridLinesVisible(true);

        ArrayList<User> users = User.getUsersArray(); 
        OPTime oTime = new OPTime();
        for (int i = 0; i < oPrayers.length(); i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(19);

            Text prayerName = new Text(OPEnum.get(i).toString());
            prayerName.setFont(Font.font("FreeSans", FontWeight.BOLD, 48));
            VBox.setMargin(prayerName, new Insets(0,0,0,20));            
            VBox prayerNameBox = new VBox(prayerName);
            prayerNameBox.setAlignment(Pos.CENTER_LEFT);

            Text prayerTime = new Text("Prayer Time: " + oPrayers.get(i).getStartEndTime());
            Text prayerCompletion = new Text("Completion: " + (oPrayers.get(i).getCompletionStatus() ? "Yes" : "No"));
            VBox prayerTimeCompletionBox = new VBox(20, prayerTime, prayerCompletion);
            VBox.setMargin(prayerTime, new Insets(0,0,0,50));
            VBox.setMargin(prayerCompletion, new Insets(0,0,0,50));
            prayerTimeCompletionBox.setAlignment(Pos.CENTER);

            Button setCompleteButton = new Button("Done Prayer");
            Text notAvailablText = new Text("It is not currently\n" + prayerName.getText() + " time slot");
            notAvailablText.setVisible(false);
            VBox completeBox = new VBox(20, setCompleteButton, notAvailablText);
            completeBox.setPadding(new Insets(0,0,0,80));
            completeBox.setAlignment(Pos.CENTER);

            Text missedPrayerText = new Text("Missed Prayer " + prayerName.getText());
            missedPrayerText.setVisible(false);
            if (oTime.normaliseTime(LocalTime.now()) > oTime.normaliseEndTimeIndex(i) && !oPrayers.get(i).getCompletionStatus()) {
                missedPrayerText.setVisible(true);
            }
            VBox missedPrayerBox = new VBox(missedPrayerText);

            int index = i;
            setCompleteButton.setOnAction(e -> {
                if (oTime.normaliseTime(LocalTime.now()) > oTime.normaliseTimeIndex(index) 
                    && oTime.normaliseTime(LocalTime.now()) < oTime.normaliseEndTimeIndex(index)) {
                    prayerCompletion.setText("Completion: Yes");
                    User.setCompletionStatus(index, true, currUser, oPrayers);
                    replaceUserInUsers(currUser);
                    User.saveDataToFile(users);
                } else {
                    notAvailablText.setVisible(true);
                }
            });
            HBox prayerDetailsBox = new HBox(10, prayerTimeCompletionBox, completeBox, missedPrayerBox);
            prayerDetailsBox.setMinWidth(630);
        
            root.getRowConstraints().add(rc);
            root.add(prayerNameBox, 0, i);
            root.add(prayerDetailsBox, 1, i);
        }
        return new Scene(root, app.width, app.height);
    }

    private static ArrayList<User> replaceUserInUsers(User currUser) {
        ArrayList<User> users = User.getUsersArray();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == currUser.getUserID()) {
                users.set(i, currUser);
            }            
        }
        for (User user : users) {
        }

        return users;
    }
}
