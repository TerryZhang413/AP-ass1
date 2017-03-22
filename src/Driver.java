import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    private ArrayList<Official> officials = new ArrayList<Official>();
    private ArrayList<Game> games = new ArrayList<Game>();
    private final int MAX_ATHLETE = 8;// maximum athlete in a game
    private final int MIN_ATHLETE = 4;// minimum athlete in a game
    private int gameIDIndex = -1;// the present game index
    private Scanner keyBoard = new Scanner(System.in);

    public void option() {
        // display menus, input option, switch to different functions
        int optionNumber = -1; // index of menus option
        Menus menus = new Menus();
        do {
            menus.mainMenus();
            try {
                optionNumber = keyBoard.nextInt();
                switch (optionNumber) {
                case 1:
                    // select a game type
                    break;
                case 2:
                    // select a prediction
                    break;
                case 3:
                    // star a game
                    break;
                case 4:
                    // display final results;
                    break;
                case 5:
                    // display points of all athletes
                    showFinalPoint();
                    break;
                case 6:
                    System.out.println("See you!");
                    keyBoard.close();
                    System.exit(0);
                default:
                    println("The number must be in 1 to 6!");
                    print("Enter an option:");
                }
            } catch (Exception e) {
                println("Input must be a number!");
                print("Enter an option:");
                keyBoard.next();
            }
        } while (optionNumber != 6);

    }

    private void showFinalPoint() {
        // display all of information about athlete
        String[] athleteInf = new String[5];
        int countAthlete = 0;
        print("Name", 15);
        print("Age", 5);
        print("State", 7);
        print("Athlete Type", 15);
        println("Point", 5);
        countAthlete = athletes.size();
        if (countAthlete == 0) {
            println("Thers isn't any athlete's information;");
        } else {
            for (int i = 0; i < countAthlete; i++) {
                athleteInf = getAthleteInf(athletes.get(i).getUserID());
                print(athleteInf[0], 15);
                print(athleteInf[1], 5);
                print(athleteInf[2], 7);
                print(athleteInf[3], 15);
                println(athleteInf[4], 5);
            }
        }
    }

    private String[] getAthleteInf(String userID) {
        // Get athlete's information based on userID
        String[] athleteinf = new String[5];
        for (Athlete athlete : athletes) {
            if (athlete.getUserID().equals(userID)) {
                athleteinf[0] = athlete.getName();
                athleteinf[1] = String.valueOf(athlete.getAge());
                athleteinf[2] = athlete.getState();
                switch (athlete.getAthleteType()) {
                case 1:
                    athleteinf[3] = "Swimmer";
                    break;
                case 2:
                    athleteinf[3] = "Cyclist";
                    break;
                case 3:
                    athleteinf[3] = "Sprinter";
                    break;
                case 4:
                    athleteinf[3] = "Super Athlete";
                    break;
                }
                athleteinf[4] = String.valueOf(athlete.getPoint());
            }
        }
        return athleteinf;
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void print(String message, int length) {
        message = String.format("%1$-" + length + "s", message);
        System.out.print(message);
    }

    private void print(int messageInt, int length) {
        String message = String.format("%1$-" + length + "s", messageInt);
        System.out.print(message);
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void println(String message, int length) {
        message = String.format("%1$-" + length + "s", message);
        System.out.println(message);
    }

    private void println(int messageInt, int length) {
        String message = String.format("%1$-" + length + "s", messageInt);
        System.out.println(message);
    }
}
