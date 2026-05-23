package SolatTracker;

import java.time.LocalTime;
import java.util.Scanner;

public class ObligatoryPrayers {
    private Prayer[] oPrayers = new Prayer[5];
    private OPTime time = new OPTime();

    public ObligatoryPrayers() {
        for (OPEnum e : OPEnum.values()) {
            oPrayers[e.i()] = new Prayer(
                e.toString(), 
                time.getTime(e.i()),
                time.getTime(e.next().i()).minusMinutes(1)
            );
        }

    }

    public Prayer get(int index) {return oPrayers[index];}

    private int currTime = time.normaliseTime(LocalTime.now());

    public void displayCurrentPrayer() {
        for (OPEnum e : OPEnum.values()) {
            int normalStartTime = time.normaliseTimeIndex(e.i());
            int normalEndTime = time.normaliseEndTimeIndex(e.i());

            if (currTime >= normalStartTime && currTime <= normalEndTime) {
                System.out.println("\n" + this.get(e.i()).toString());
            }
        }
    }

    public void displayAllCompulsoryPrayers() {
        for (OPEnum e : OPEnum.values()) {
            System.out.println("\n" + this.get(e.i()).toString());
        }
    }

    public void displayMissedCompulsoryPrayers() {
        for (OPEnum e : OPEnum.values()) {
            if (currTime > time.normaliseEndTimeIndex(e.i()) 
                && !this.get(e.i()).getCompletionStatus()) {
                
                System.out.println("\n" + this.get(e.i()).toString());
            }
        }
    }

    public void setPrayerCompleted(Scanner input) {
        String prayer = null;
        int index = -1;
        while (true) {
            System.out.println("\nPrayer List");
            for (OPEnum e : OPEnum.values()) {
                System.out.println(e);
            }

            System.out.println("\nFrom the above list, enter which prayer you have completed:");
            try {
                prayer = input.nextLine().trim().toUpperCase();
                index = OPEnum.valueOf(prayer).i();   
                break;
            } catch (Exception e) {System.out.println("\nIncorrect prayer spelling. Try again\n");}
        }

        int normalTime = time.normaliseTimeIndex(index);
        if (normalTime > currTime) {
            System.out.println("The current time, " + currTime / 60 + ":" + currTime % 60 + " is not for prayer " + prayer);
            return;
        }

        this.get(index).setCompletionStatus(true);
    }

    public void setPrayersCompleted(User user) {
        for (int i = 0; i < oPrayers.length; i++) {oPrayers[i].setCompletionStatus(user.getCompleteStatus(i));}
    }
}
