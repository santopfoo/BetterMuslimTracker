package SolatTracker;

import java.util.Scanner;

public class OPManager {
    public static void manageUserPrayers(Scanner input, User user) {
        ObligatoryPrayers oPrayers = new ObligatoryPrayers();

        boolean exitChoice = false;
        while (!exitChoice) {
            int choice = -1;

            oPrayers.setPrayersCompleted(user);
            // Choices Menu

            try {choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {System.out.println("Command not a number");
                continue;
            }

            switch (choice) {
                case 1:
                    oPrayers.displayAllCompulsoryPrayers();;
                    break;
                case 2:
                    oPrayers.displayCurrentPrayer();                    
                    break;
                case 3:
                    oPrayers.displayMissedCompulsoryPrayers();;
                    break;
                case 4:
                    oPrayers.setPrayerCompleted(input);
                    break;
                case 5:
                    exitChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }    
}
