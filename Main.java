package BetterMuslimTracker;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrayerTime prayerTime = new PrayerTime();
        CompulsoryPrayers compulsoryPrayers = new CompulsoryPrayers();

        LocalTime currTime = LocalTime.now();

        boolean exitChoice = false;
        while (true && !exitChoice) {
            int choice;

            System.out.print(
                "\nChoices Menu" +
                "\n1. Display Compulsory Prayers Time" +
                "\n2. Display Current Prayer" +
                "\n3. Display Missed Prayers" +
                "\n4. Exit Program" +
                "\nEnter choice:"
            );

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    displayCompulsoryPrayersTime(compulsoryPrayers, currTime);
                    break;
                case 2:
                    displayCurrentPrayer(compulsoryPrayers, currTime, prayerTime);
                    break;
                case 3:
                    displayMissedPrayers(compulsoryPrayers, currTime, prayerTime);
                    break;
                case 4:
                    exitChoice = true;
                    break;
                default:
                    break;
            }
        }







        /*
        ArrayList<LocalTime> taskArr = new ArrayList<>();
        System.out.println(
            "1. Add Task"
            + "2. Complete Task"
        );

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                
                break;
        
            default:
                break;
        }*/
    }  

    private static void displayCompulsoryPrayersTime(CompulsoryPrayers compulsoryPrayers, LocalTime currTime) {
            for (CompulsoryPrayersTimeEnum e : CompulsoryPrayersTimeEnum.values()) {
            System.out.println("\n" + compulsoryPrayers.getFromIndex(e.ordinal()).toString());
        }
    }

    private static void displayCurrentPrayer(CompulsoryPrayers compulsoryPrayers, LocalTime currTime, PrayerTime prayerTime) {
        for (CompulsoryPrayersTimeEnum e : CompulsoryPrayersTimeEnum.values()) {
            if (
                currTime.isAfter(prayerTime.getPrayerTime(e.ordinal()).minusMinutes(1)) && 
                currTime.isBefore(prayerTime.getPrayerTime((e.next().ordinal())))
            ) {
                System.out.println("\nCurrent Prayer: " + CompulsoryPrayersTimeEnum.values()[e.ordinal()]);
            
            } else if (
                compulsoryPrayers.getFromIndex(e.ordinal()).getTaskName().equals(CompulsoryPrayersTimeEnum.ISYAK.toString()) &&
                (
                    currTime.isAfter(prayerTime.getPrayerTime(e.ordinal()).minusMinutes(1)) ||
                    currTime.isBefore(prayerTime.getPrayerTime(e.next().ordinal()))
                )
            ) {
                System.out.println("\nCurrent Prayer: " + CompulsoryPrayersTimeEnum.values()[e.ordinal()]);
            }
        }
    }

    private static void displayMissedPrayers(CompulsoryPrayers compulsoryPrayers, LocalTime currTime, PrayerTime prayerTime) {
        boolean havePrayersNotDone = false;
        int[] prayerNotDoneArr = new int[5];
        for (CompulsoryPrayersTimeEnum e : CompulsoryPrayersTimeEnum.values()) {
            if (
                !compulsoryPrayers.getFromIndex(e.ordinal()).getCompletionStatus() && 
                currTime.isAfter(prayerTime.getPrayerTime(e.next().ordinal()))
            ) {
                prayerNotDoneArr[e.ordinal()] = e.ordinal();
                havePrayersNotDone = true;
            } else {
                prayerNotDoneArr[e.ordinal()] = -1;
            }
        }
        if (havePrayersNotDone) {
            System.out.println("\nMissed Prayer(s):");
            for (int i : prayerNotDoneArr) {
                if (i != -1) {
                    System.out.println(compulsoryPrayers.getFromIndex(i).toString());   
                }
            }
        }
    }
}
