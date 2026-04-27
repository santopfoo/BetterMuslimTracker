package BetterMuslimTracker;

import java.time.LocalTime;
import java.util.Scanner;

public class CompulsoryPrayers {
    private Prayer[] compulsoryPrayers = new Prayer[5];
    private CompulsoryPrayersTime prayersTime = new CompulsoryPrayersTime();

    public CompulsoryPrayers() {
        for (CompulsoryPrayersTime.Enum e : CompulsoryPrayersTime.Enum.values()) {
            compulsoryPrayers[e.ordinal()] = new Prayer(
                e.toString(), 
                prayersTime.getTime(e.ordinal()),
                prayersTime.getTime(e.next().ordinal()).minusMinutes(1)
            );
        }

    }

    public Prayer get(int index) {
        return compulsoryPrayers[index];
    }

    private int currTime = prayersTime.normaliseTime(LocalTime.now());

    public void displayCurrentPrayer() {
        for (CompulsoryPrayersTime.Enum e : CompulsoryPrayersTime.Enum.values()) {
            int normalisedStartTime = prayersTime.normaliseTimeIndex(e.getInt());
            int normalisedEndTime = prayersTime.normaliseEndTimeIndex(e.getInt());

            if (currTime >= normalisedStartTime && currTime <= normalisedEndTime) {
                System.out.println("\n" + this.get(e.getInt()).toString());
            }
        }
    }

    public void displayAllCompulsoryPrayers() {
        for (CompulsoryPrayersTime.Enum e : CompulsoryPrayersTime.Enum.values()) {
            System.out.println("\n" + this.get(e.getInt()).toString());
        }
    }

    public void displayMissedCompulsoryPrayers() {
        for (CompulsoryPrayersTime.Enum e : CompulsoryPrayersTime.Enum.values()) {
            if (currTime > prayersTime.normaliseEndTimeIndex(e.getInt()) && !this.get(e.getInt()).getCompletionStatus()) {
                System.out.println("\n" + this.get(e.getInt()).toString());
                
            }
        }
    }

    public void setPrayerCompleted(Scanner input) {
        String prayer = null;
        int index = -1;
        while (true) {
            System.out.println("\nPrayer List");
            for (CompulsoryPrayersTime.Enum e : CompulsoryPrayersTime.Enum.values()) {
                System.out.println(e);
            }

            System.out.println("\nFrom the above list, enter which prayer you have completed:");
            try {
                prayer = input.nextLine().trim().toUpperCase();
                index = CompulsoryPrayersTime.Enum.valueOf(prayer).getInt();   
                break;
            } catch (Exception e) {
                System.out.println("\nIncorrect prayer spelling. Try again\n");
            }
        }

        int normalisedIndexedPrayer = prayersTime.normaliseTimeIndex(index);

        if (normalisedIndexedPrayer > currTime) {
            System.out.println("The current time, " + currTime / 60 + ":" + currTime % 60 + " is not for prayer " + prayer);
            return;
        }

        this.get(index).setCompletionStatus(true);
    }
}
