package SolatTracker;

import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UIHomePage {
    public static Scene create(UIMain app, User currUser) {
        ObligatoryPrayers oPrayers = new ObligatoryPrayers();
        GridPane root = new GridPane();
        ColumnConstraints cc1 = new ColumnConstraints();    cc1.setPercentWidth(30);
        ColumnConstraints cc2 = new ColumnConstraints();
        RowConstraints rc1 = new RowConstraints();

        root.getColumnConstraints().addAll(cc1, cc2);
        root.getRowConstraints().add(rc1);

        for (OPEnum e : OPEnum.values()) {
            GridPane inner = new GridPane();
            RowConstraints rcInner1 = new RowConstraints();     rcInner1.setVgrow(Priority.ALWAYS);     rcInner1.setPercentHeight(50);
            RowConstraints rcInner2 = new RowConstraints();     rcInner2.setVgrow(Priority.ALWAYS);     rcInner2.setPercentHeight(50);
            inner.getRowConstraints().addAll(rcInner1, rcInner2);

            Text prayerName = new Text(e.toString());   prayerName.setFont(Font.font("FreeSans", FontWeight.BOLD, 48));
            Text prayerTime = new Text("Prayer Time: " + oPrayers.get(e.i()).getStartEndTime());
            Text prayerCompletion = new Text("Completion: " + (currUser.getCompletionStatus(e.i()) ? "Yes" : "No"));

            inner.add(prayerTime, 0, 0);
            inner.add(prayerCompletion, 0, 1);
            root.add(prayerName, 0, e.i());
            root.add(inner, 1, e.i());
        }
        return new Scene(root, app.width, app.height);
    }
}
