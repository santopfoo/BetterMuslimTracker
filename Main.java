package BetterMuslimTracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CompulsoryPrayers compulsoryPrayers = new CompulsoryPrayers();

        boolean exitChoice = false;
        while (true && !exitChoice) {
            int choice;

            System.out.print(
                "\nChoices Menu" +
                "\n1. Display Compulsory Prayers Time" +
                "\n2. Display Current Prayer" +
                "\n3. Display Missed Prayers" +
                "\n4. Set A Prayer As Completed" +
                "\n5. Exit" +
                "\nEnter choice: "
            );

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    compulsoryPrayers.displayAllCompulsoryPrayers();;
                    break;
                case 2:
                    compulsoryPrayers.displayCurrentPrayer();                    
                    break;
                case 3:
                    compulsoryPrayers.displayMissedCompulsoryPrayers();;
                    break;
                case 4:
                    compulsoryPrayers.setPrayerCompleted(input);
                    break;
                case 5:
                    exitChoice = true;
                    break;
                default:
                    break;
            }
        }

        /*
        while (true) {
            System.out.println(
                "\nChoices Menu\n============" +
                "\n1. Display All Tasks" + 
                "\n2. Create New Task" +
                "\n3. Exit Program"
            );
        }*/





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

    private static void displayAllTasksMenu() {
        while (true) {
            
        }
    }
}
