package SolatTracker;

import java.time.LocalTime;
import java.util.Scanner;

public class ObligatoryPrayers {
    private Prayer[] oPrayers = new Prayer[5];
    private OPTime time = new OPTime();

    public ObligatoryPrayers() {
        for (OPEnum e: OPEnum.values()) {
            oPrayers[e.i()] = new Prayer(
                e.toString(), 
                time.getTime(e.i()),
                time.getTime(e.next().i()).minusMinutes(1)
            );
        }

    }

    public Prayer get(int index) { return oPrayers[index]; }
    public int length() { return oPrayers.length; }
    public void setPrayersCompleted(User user) {
        for (int i = 0; i < oPrayers.length; i++) {
            oPrayers[i].setCompletionStatus(user.getCompletionStatus(i));

        }
    }
}
