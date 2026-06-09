package SolatTracker;

import java.util.ArrayList;
import java.time.LocalTime;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UIHomePage {
    private static ObligatoryPrayers oPrayers = new ObligatoryPrayers();

    public static Scene create(UIMain app, User currUser) {
        oPrayers.setPrayersCompleted(currUser);

        VBox root = new VBox(15);
        root.setId("home-page");

        HBox header = new HBox(15);
        header.setId("home-header");

        Text homeTitle = new Text("Better Muslim Tracker");
        homeTitle.setId("home-title");

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Text greeting = new Text("Assalamualaikum, User " + currUser.getUserID());
        greeting.setId("user-greeting");

        header.getChildren().addAll(homeTitle, headerSpacer, greeting);

        VBox prayersContainer = new VBox(10);

        OPTime oTime = new OPTime();
        for (int i = 0; i < oPrayers.length(); i++) {
            boolean isCompleted = oPrayers.get(i).getCompletionStatus();

            HBox prayerCard = new HBox(15);
            if (isCompleted) {
                prayerCard.getStyleClass().add("prayer-card-completed");
            } else {
                prayerCard.getStyleClass().add("prayer-card");
            }

            Text prayerName = new Text(formatPrayerName(OPEnum.get(i).toString()));
            prayerName.getStyleClass().add("prayer-name");

            VBox timeStatusBox = new VBox(3);
            timeStatusBox.setAlignment(Pos.CENTER);

            LocalTime startTime = oPrayers.get(i).getTaskName().contains("Subuh")
                ? oPrayers.get(i).getStartEndTime() == null ? null : null
                : null;
            String timeRange = oPrayers.get(i).getStartEndTime();
            Text timeText = new Text("Time: " + timeRange);
            timeText.getStyleClass().add("prayer-time");

            Text statusText = new Text(isCompleted ? "Completed" : "Not completed yet");
            statusText.getStyleClass().add(isCompleted ? "prayer-status-completed" : "prayer-status-incomplete");

            timeStatusBox.getChildren().addAll(timeText, statusText);

            Region cardSpacer = new Region();
            HBox.setHgrow(cardSpacer, Priority.ALWAYS);

            Text missedPrayer = new Text("Missed Prayer");
            VBox missedPrayerBox = new VBox(missedPrayer);
            missedPrayerBox.setVisible(false);
            if (oTime.normaliseTime(LocalTime.now()) > oTime.normaliseEndTimeIndex(i) 
                && !oPrayers.get(i).getCompletionStatus()) {
                missedPrayerBox.setVisible(true);
            }

            int index = i;
            Button doneButton = new Button("Done");
            doneButton.getStyleClass().add("btn-done");
            doneButton.setDisable(isCompleted);
            doneButton.setOnAction(e -> {
                if (oTime.normaliseTime(LocalTime.now()) > oTime.normaliseTimeIndex(index)
                    && oTime.normaliseTime(LocalTime.now()) < oTime.normaliseEndTimeIndex(index)) {
                    User.setCompletionStatus(index, true, currUser, oPrayers);
                    statusText.setText("Completed");
                    statusText.getStyleClass().removeAll("prayer-status-incomplete");
                    statusText.getStyleClass().add("prayer-status-completed");
                    prayerCard.getStyleClass().removeAll("prayer-card");
                    prayerCard.getStyleClass().add("prayer-card-completed");
                    doneButton.setDisable(true);

                    ArrayList<User> users = User.getUsersArray();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getUserID() == currUser.getUserID()) {
                            users.set(j, currUser);
                            break;
                        }
                    }
                    User.saveDataToFile(users);
                } else {

                }
            });

            prayerCard.getChildren().addAll(prayerName, timeStatusBox, cardSpacer, missedPrayerBox, doneButton);
            prayersContainer.getChildren().add(prayerCard);
        }

        root.getChildren().addAll(header, prayersContainer);

        Scene scene = new Scene(root, app.width, app.height);
        scene.getStylesheets().add("file:SolatTracker/styles.css");
        return scene;
    }

    private static String formatPrayerName(String name) {
        if (name == null || name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
