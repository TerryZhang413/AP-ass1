import java.util.Scanner;

public class Driver {
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
