import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Ozlympic.Game;
import Ozlympic.Menus;


public class Driver {
    private ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    private ArrayList<Official> officials = new ArrayList<Official>();
    private ArrayList<Game> games = new ArrayList<Game>();
    private final int MAX_ATHLETE = 8;// maximum athlete in a game
    private final int MIN_ATHLETE = 4;// minimum athlete in a game
    private int gameIDIndex = -1;// the present game index
    private Scanner keyBoard = new Scanner(System.in);

    public void option() {
        // display menus, input option, switch to different functions -Yipeng
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
    //display the result on screen -Yanjie
    private void showResult(int index) {
        String official;
        String[] athleteinf = new String[5];
        int time = 0;
        int rank = 0;
        int countAthlete = 0;
        if (games.get(index).getResults().size() == 0)
            return;// game maybe haven't run yet
        official = getOffName(games.get(index).getOfficialID());
        countAthlete = games.get(index).getAthletes().size();
        println("==============================================");
        println("Game Number: " + games.get(index).getGameID());
        println("Official: " + official);
        println("Game: " + games.get(index).getGameID());
        print("Name", 15);
        print("Age", 5);
        print("State", 7);
        print("Athlete Type", 15);
        print("Time", 5);
        println("Rank", 5);
        for (int i = 0; i < countAthlete; i++) {
            athleteinf = getAthleteInf(games.get(index).getAthletes().get(i));
            time = games.get(index).getResults().get(i);
            rank = games.get(index).getRanks().get(i);

            print(athleteinf[0], 15);
            print(athleteinf[1], 5);
            print(athleteinf[2], 7);
            print(athleteinf[3], 15);
            print(time, 5);
            println(rank, 5);
        }
    }
    // get officer's id based on userID -Yanjie
    private String getOffName(String userID) {
        
        for (Official official : officials) {
            if (official.getUserID().equals(userID))
                return official.getName();
        }
        return null;
    }
 // Get athlete's information based on userID -Yipeng
    private String[] getAthleteInf(String userID) {
        
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
    // display all of results respectively -Yanjie
    private void showFinalResult() {
        
        int countGame = games.size();
        if (countGame == 0) {
            println("No game record!");
            return;
        }
        for (int i = 0; i < countGame; i++) {
            showResult(i);
        }
    }
 // display all of information about athlete -Yanjie
    private void showFinalPoint() {
        
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
 // generate a random time from minimum time to maximum time -Yanjie
    private int randomTime(int miniTime, int maxTime) {
        Random random = new Random();
        return random.nextInt(maxTime - miniTime + 1) + miniTime;

    }
 // select a game type from 1 to 3  -Yanjie
    private int selectGame(int gameType, int predictIndex) {
        Menus menus = new Menus();
        int newGameType = 0;   //initial the ganme type
        menus.sportMenus();
        do {
            try {
                newGameType = keyBoard.nextInt();
                if ((newGameType < 1) || (newGameType > 3)) {
                    println("Error: The number must be in 1 to 3!");
                    print("Enter an option:");
                    continue;
                }
                if (newGameType == gameType)// Type doesn't change
                    return gameType;
                // type changed, initialize game type & prediction
                gameType = newGameType;
                if (games.size() > 0) { // try to find unused one
                    for (int i = 0; i < games.size(); i++) {
                        if ((games.get(i).getResults().size() == 0)
                                && (games.get(i).getType() == gameType)) {
                            gameIDIndex = i;
                            return newGameType;
                        }
                    }
                }
                // create a new game
                newGame(gameType);
            } catch (Exception e) {
                println("Error: Input must be an integer number!");
                print("Enter an option:");
                keyBoard.next();
            }
        } while ((newGameType < 1) || (newGameType > 3));
        return gameType;
    }
     //depending on the game type return the max game id -Yanjie
    private String getMaxGameID(String gameID, int gameType) {
        String maxGameID = "null";
        int newGameID = 0;
        int stringLength = 0;
        try {
            stringLength = gameID.length();                
            gameID = gameID.substring(1, stringLength);
            newGameID = Integer.valueOf(gameID);
            newGameID++;
            maxGameID = String.valueOf(newGameID);
            switch (gameType) {
                case 1:
                    maxGameID = "S" + maxGameID;
                    break;
                case 2:
                    maxGameID = "C" + maxGameID;
                    break;
                case 3:
                    maxGameID = "R" + maxGameID;
            }
            return maxGameID;
        } catch (Exception e) {                       //error 
            println("Cannn't get maxGameID!");
            return null;
        }

    }
    //denpending on the ganme type, create a new game  -Yanjie
    private void newGame(int gameType) {
        String maxGameID = "null";
        String officialID;
        ArrayList<String> presentAthlete = new ArrayList<String>();
        gameIDIndex = games.size();
        if (games.size() > 0) {
            for (int i = games.size() - 1; i >= 0; i--) {
                if (games.get(i).getType() == gameType) {
                    maxGameID = games.get(i).getGameID();
                    break;
                }
            }
        }
        if (maxGameID.equals("null")) {
            maxGameID = "00";
            gameIDIndex = 0;
        }
        try {
            maxGameID = getMaxGameID(maxGameID, gameType);
            presentAthlete = getAthlete(gameType);
            officialID = getOfficial();
            if (presentAthlete == null) {             //every error
                println("Number of athletes is less than 4!");
                gameIDIndex = -1;
            }
            if (officialID == null) {
                println("Can not find any official!");
                gameIDIndex = -1;
            }
            if (gameIDIndex != -1) {
                games.add(new Game(maxGameID, gameType, officialID,
                        presentAthlete));
                gameIDIndex = games.size() - 1;
            }
        } catch (Exception e) {
            println("Cann't create a new game!");
        }

    }
    // get offical information -Yanjie
    private String getOfficial() {
        Random ranIndex = new Random();
        int sizeList;
        String officialID = new String();
        sizeList = officials.size();
        if (sizeList > 0) {
            sizeList--;
            officialID = officials.get(ranIndex.nextInt(sizeList)).getUserID();
            getClass();
            return officialID;   //return the offical
        } else {
            return null;
        }
    }
    //get athlete list  -Yanjie
    private ArrayList<String> getAthlete(int gameType) {
        try {
            ArrayList<String> temporaryList = new ArrayList<String>();
            // Finding athletes who are satisfied type of sport.
            for (Athlete athlete : athletes) {
                if ((athlete.getAthleteType() == gameType)
                        || (athlete.getAthleteType() == 4)) {
                    temporaryList.add(athlete.getUserID());
                }
            }
            if (temporaryList.size() <= MIN_ATHLETE) {
                // no enough athletes
                return null;
            } else {
                Random ranIndex = new Random();   //make a random number
                int sizeList;
                while (temporaryList.size() > MAX_ATHLETE) {
                    sizeList = temporaryList.size() - 1;
                    temporaryList.remove(ranIndex.nextInt(sizeList));
                }
                return temporaryList;   //return the athlete list
            }
        } catch (Exception e) {
            return null;
        }
    }
    //predict the result -Yanjie
    private int predict(int predictIndex) {
        // set prediction and check if it's legal
        int newIndex = -1;
        int athleteCount;
        if (gameIDIndex == -1) {
            println("Have to select a game first!");
            return -1;
        } else {
            showGameInf(gameIDIndex);
            athleteCount = games.get(gameIDIndex).getAthletes().size();
        }
        print("Choose an athlete:");
        do {
            try {
                newIndex = keyBoard.nextInt();
                if ((newIndex < 1) || (newIndex > athleteCount)) {
                    println("The number must be in 1 to " + athleteCount + "!");
                    print("Enter an option:");
                    continue;
                }
                predictIndex = newIndex - 1;
            } catch (Exception e) {
                println("Input must be an integer number!");
                print("Enter an option:");
                keyBoard.next();
            }
        } while (predictIndex == -1);// -1 means haven't a prediction
        return newIndex;
    }
    
    // show game information in prediction menus -Yanjie
    private void showGameInf(int index) {
        String athleteName = null;
        String state = null;
        String age = null;
        String athleteType = null;
        String point = null;
        print("Name", 15);
        print("Age", 5);
        print("State", 7);
        print("Athlete Type", 15);
        println("Point", 10);
        for (String userID : games.get(index).getAthletes()) {
            for (Athlete athlete : athletes) {
                if (athlete.getUserID().equals(userID)) {
                    athleteName = athlete.getName();
                    state = athlete.getState();
                    age = String.valueOf(athlete.getAge());
                    switch (athlete.getAthleteType()) {
                        case 1:
                            athleteType = "Swimmer";
                            break;
                        case 2:
                            athleteType = "Cyclist";
                            break;
                        case 3:
                            athleteType = "Sprinter";
                            break;
                        case 4:
                            athleteType = "Super Athlete";
                            break;
                    }
                    point = String.valueOf(athlete.getPoint());
                    break;
                }
            }
            print(athleteName, 15);
            print(age, 5);
            print(state, 7);
            print(athleteType, 15);
            println(point, 10);
        }
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
