package Ozlympic;

public class Menus {
    /**
     * @date 17.3.2017
     * @author Yipeng.Zhang
     * @version 1.0
     * @Description including all of menus
     */
    public void sportMenus() {						//menu after select 1 in main menu
        System.out.println("Select a sport from 1 to 3");
        System.out.println("1. Swimming");
        System.out.println("2. Cycling");
        System.out.println("3. Running");
        System.out.println("");
        System.out.print("Enter an opwtion:");
    }

    public void mainMenus() {						//main menu,including 6 option to be chossen
        System.out.println("");
        System.out.println("Ozlympic Game");
        System.out.println("===================================");
        System.out.println("1. Select a game to run");
        System.out.println("2. Predict the winner of the game");
        System.out.println("3. Start the game");
        System.out.println("4. Display the final results of all games");
        System.out.println("5. Display the points of all athletes");
        System.out.println("6. Exit");
        System.out.println("");
        System.out.print("Enter an option:");
    }
}
