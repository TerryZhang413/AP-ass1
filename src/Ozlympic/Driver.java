package Ozlympic;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver {
	/**
	 * @date 23.3.2017
	 * @author Yanjie
	 * @version 1.0
	 * @Description main class to drive program
	 */
    private ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    private ArrayList<Official> officials = new ArrayList<Official>();
    private ArrayList<Game> games = new ArrayList<Game>();
    private Screen screen = new Screen();	//create an object of Screen cloass
    private UserInfo userinfo;	//create an object of UserInfo cloass
    private int gameIDIndex = -1;// the present game index
    private Scanner keyBoard = new Scanner(System.in);

    // initialize data from file
    public Driver() {
        LoadData loadData = new LoadData(games, officials, athletes);
        loadData.loadData();
        userinfo = new UserInfo(this.athletes, this.officials);
    }

    public void option() {
        // display menus, input option, switch to different functions
        int optionNumber = -1; // index of menus option
        int predictIndex = -1; //index of prediction
        int gameType = -1;		//index of gameType(swimming,running,cycling)
        Menus menus = new Menus(); ////create an object of Menus cloass
        do {
            menus.mainMenus();   //call the main menu method
            try {
                optionNumber = keyBoard.nextInt();  //input the choose
                switch (optionNumber) {
                case 1:
                    // select a game type
                    gameType = selectGame(gameType, predictIndex);
                    break;
                case 2:
                    // select a prediction
                    predictIndex = predict(predictIndex);
                    break;
                case 3:
                    // star a game
                    starGame(gameType, predictIndex);
                    predictIndex = -1;
                    break;
                case 4:
                    // display final results
                    showFinalResult();
                    break;
                case 5:
                    // display points of all athletes
                    showFinalPoint();
                    break;
                case 6:
                    screen.println("See you!"); // exit
                    keyBoard.close();
                    System.exit(0);
                default:
                    // check the input number if out of range
                    screen.println("The number must be in 1 to 6!");
                    screen.print("Enter an option:");
                }
            } catch (Exception e) {
                // check error if the input is not number
                screen.print("Enter an option:");
                screen.println("Input must be a number!");
                screen.print("Enter an option:");
                keyBoard.next();
            }
        } while (optionNumber != 6);

    }

    // display the result on screen
    private void showResult(int index) {
        String official;
        String[] athleteinf = new String[5];
        int time = 0;
        int rank = 0;
        int countAthlete = 0;
        if (games.get(index).getResults().size() == 0)
            return;// game maybe haven't run yet
        official = userinfo.getOffName(games.get(index).getOfficialID());  //get one official
        countAthlete = games.get(index).getAthletes().size();			// get one game
        screen.println("==============================================");
        screen.println("Game Number: " + games.get(index).getGameID());
        screen.println("Official: " + official);
        screen.println("Game: " + games.get(index).getGameID());
        screen.print("Name", 15);
        screen.print("Age", 5);
        screen.print("State", 7);
        screen.print("Athlete Type", 15);
        screen.print("Time", 5);
        screen.println("Rank", 5);
        for (int i = 0; i < countAthlete; i++) {
            athleteinf = userinfo.getAthleteInf(games.get(index).getAthletes().get(i));
            time = games.get(index).getResults().get(i);    //show the result
            rank = games.get(index).getRanks().get(i);		//show the rank of the athlete
            //show each data of the athlete
            screen.print(athleteinf[0], 15);		
            screen.print(athleteinf[1], 5);
            screen.print(athleteinf[2], 7);
            screen.print(athleteinf[3], 15);
            screen.print(time, 5);
            screen.println(rank, 5);
        }
    }

    // display all of results respectively
    private void showFinalResult() {

        int countGame = games.size();
        if (countGame == 0) {
            screen.println("No game record!"); // if no game
            return;
        }
        for (int i = 0; i < countGame; i++) {
            showResult(i);	//show all game results
        }
    }

    // display all of information about athlete
    private void showFinalPoint() {

        String[] athleteInf = new String[5];
        int countAthlete = 0;
        screen.print("Name", 15);
        screen.print("Age", 5);
        screen.print("State", 7);
        screen.print("Athlete Type", 15);
        screen.println("Point", 5);
        countAthlete = athletes.size();  //numbers of athletes
        if (countAthlete == 0) {
            screen.println("Thers isn't any athlete's information;");
        } else {
            for (int i = 0; i < countAthlete; i++) {
            	//show each data of every athlete
                athleteInf = userinfo.getAthleteInf(athletes.get(i).getUserID());
                screen.print(athleteInf[0], 15);
                screen.print(athleteInf[1], 5);
                screen.print(athleteInf[2], 7);
                screen.print(athleteInf[3], 15);
                screen.println(athleteInf[4], 5);
            }
        }
    }

    // generate a random time from minimum time to maximum time
    private int randomTime(int miniTime, int maxTime) {
        Random random = new Random();
        return random.nextInt(maxTime - miniTime + 1) + miniTime;   

    }

    // select a game type from 1 to 3
    private int selectGame(int gameType, int predictIndex) {
        Menus menus = new Menus();
        int newGameType = 0; // initial the game type
        menus.sportMenus();
        do {
            try {
                newGameType = keyBoard.nextInt();  //input the chosen game type 
                if ((newGameType < 1) || (newGameType > 3)) {
                    screen.println("Error: The number must be in 1 to 3!");  //error check
                    screen.print("Enter an option:");
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
                screen.println("Error: Input must be an integer number!");
                // input error check
                screen.print("Enter an option:");
                keyBoard.next();
            }
        } while ((newGameType < 1) || (newGameType > 3));
        return gameType;
    }

    // depending on the game type return the max game id
    private String getMaxGameID(String gameID, int gameType) {
        String maxGameID = "null";
        int newGameID = 0;
        int stringLength = 0;
        try {
            stringLength = gameID.length();
            gameID = gameID.substring(1, stringLength); //get game ID
            newGameID = Integer.valueOf(gameID);
            newGameID++;
            maxGameID = String.valueOf(newGameID);
            switch (gameType) {
            case 1:
                maxGameID = "S" + maxGameID; // swimming
                break;
            case 2:
                maxGameID = "C" + maxGameID; // cycling
                break;
            case 3:
                maxGameID = "R" + maxGameID; // running
            }
            return maxGameID;
        } catch (Exception e) { // error
            screen.println("Cannn't get maxGameID!");
            return null;
        }
    }

    // Depending on the game type, create a new game
    private void newGame(int gameType) {
        String maxGameID = "null";
        String officialID;
        ArrayList<String> presentAthlete = new ArrayList<String>();  //make a array list to store athlete in a game
        gameIDIndex = games.size();
        if (games.size() > 0) {
            for (int i = games.size() - 1; i >= 0; i--) {
                if (games.get(i).getType() == gameType) {
                    maxGameID = games.get(i).getGameID();  //save max game id as the current game id
                    break;
                }
            }
        }
        if (maxGameID.equals("null")) {
            maxGameID = "00";    //if no previous game , make it 0
            gameIDIndex = 0;
        }
        try {
            maxGameID = getMaxGameID(maxGameID, gameType);
            presentAthlete = userinfo.getAthlete(gameType);
            officialID = userinfo.getOfficialInf();
            if (presentAthlete == null) { // error check athletes not enough
                screen.println("Number of athletes is less than 4!");
                gameIDIndex = -1;
            }
            if (officialID == null) {  //error check no official
                screen.println("Can not find any official!");
                gameIDIndex = -1;
            }
            if (gameIDIndex != -1) {  //add data into a game
                games.add(new Game(maxGameID,
                        gameType,
                        officialID,
                        presentAthlete));
                gameIDIndex = games.size() - 1;
            }
        } catch (Exception e) {
            screen.println("Cann't create a new game!");
        }

    }

    // predict the result
    private int predict(int predictIndex) {
        // set prediction and check if it's legal
        int newIndex = -1; //initialize the prediction
        int athleteCount;
        if (gameIDIndex == -1) {
            screen.println("Have to select a game first!");  //error check if not choose a game
            return -1;
        } else {
            showGameInf(gameIDIndex);
            athleteCount = games.get(gameIDIndex).getAthletes().size();  //count the number of athlete in this game
        }
        screen.print("Choose an athlete:");
        do {
            try {
                newIndex = keyBoard.nextInt();
                if ((newIndex < 1) || (newIndex > athleteCount)) {
                    screen.println("The number must be in 1 to " + athleteCount + "!");   //error check prediction number no more than athleteCount
                    screen.print("Enter an option:");		//choose again
                    continue;
                }
                predictIndex = newIndex - 1;
            } catch (Exception e) {
                screen.println("Input must be an integer number!");	//error check input not an integer
                screen.print("Enter an option:");
                keyBoard.next();
            }
        } while (predictIndex == -1);// -1 means haven't a prediction
        return newIndex;
    }

    // show game information in prediction menus
    private void showGameInf(int index) {
    	//initialize game information
        String athleteName = null;	
        String state = null;
        String age = null;
        String athleteType = null;
        String point = null;
        screen.print("Name", 15);
        screen.print("Age", 5);
        screen.print("State", 7);
        screen.print("Athlete Type", 15);
        screen.println("Point", 10);
        for (String userID : games.get(index).getAthletes()) {
            for (Athlete athlete : athletes) {
                if (athlete.getUserID().equals(userID)) {
                    athleteName = athlete.getName();
                    state = athlete.getState();
                    age = String.valueOf(athlete.getAge());
                    switch (athlete.getAthleteType()) { //athlete type
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
            //print game information
            screen.print(athleteName, 15);
            screen.print(age, 5);
            screen.print(state, 7);
            screen.print(athleteType, 15);
            screen.println(point, 10);
        }
    }

    // start the game , implement the function
    private void starGame(int gameType, int predictIndex) {
        int maxTime = 0, miniTime = 0;  //initialize game data
        int resultCount;
        ArrayList<Integer> results = new ArrayList<Integer>();
        ArrayList<Integer> ranks = new ArrayList<Integer>();
        if (gameType == -1) {
            screen.println("Error: Have to choose a type of game first!");  //error check not choose type before
            // start game
            return;
        }
        try {
            switch (gameType) { //set range of time
            case 1:
                miniTime = 10;  
                maxTime = 20;
                break;
            case 2:
                miniTime = 100;
                maxTime = 200;
                break;
            case 3:
                miniTime = 500;
                maxTime = 800;
                break;
            }
            resultCount = games.get(gameIDIndex).getAthletes().size();
            for (int i = 0; i < resultCount; i++) {   //give result of the game
                results.add(randomTime(miniTime, maxTime));
            }
            games.get(gameIDIndex).setResults(results);  //set result 
            ranks = rank(gameIDIndex);	//make a rank
            games.get(gameIDIndex).setRanks(ranks);	//set the rank
            refreshPoint();		//add points to first three athlete
            showResult(gameIDIndex);	//show result of the game
            showPredict(ranks, predictIndex); //compare the prediction with the real rank
            newGame(gameType);// prepare next game;
        } catch (Exception e) {
            screen.println("method 'starGame' is error!"); // error
        }
    }

    // if the prediction is correct, congratulation is shown on screen
    private void showPredict(ArrayList<Integer> ranks, int predictIndex) {
        if (predictIndex != -1) {
            if (ranks.get(predictIndex - 1) == 1) { // predict player is 1st
                screen.println("************************************************");
                screen.println("*Congratulations! Your prediction is right!!!!!*");
                screen.println("************************************************");
            }
        }
    }

    // find top 3 and add point into their documents
    private void refreshPoint() {
        int resultCount;
        resultCount = games.get(gameIDIndex).getAthletes().size();
        for (int i = 0; i < resultCount; i++) {
            switch (games.get(gameIDIndex).getRanks().get(i)) {
            case 1:
                userinfo.addPoint(games.get(gameIDIndex).getAthletes().get(i), 5);
                // give first place 5 points
                break;
            case 2:
                userinfo.addPoint(games.get(gameIDIndex).getAthletes().get(i), 2);
                // give first place 2 points
                break;
            case 3:
                userinfo.addPoint(games.get(gameIDIndex).getAthletes().get(i), 1);
                // give first place 1 points
                break;
            }
        }
    }

    // make a rank depending on the points of every player
    private ArrayList<Integer> rank(int index) {
        ArrayList<Integer> ranks = new ArrayList<Integer>();
        ArrayList<Integer> results = games.get(index).getResults();
        int sizeResult = results.size();
        boolean existence;// weather this rank is created
        ranks.add(1);// fist one is the least at first
        for (int i = 1; i < sizeResult; i++) {
            existence = false;
            for (int n = 0; n < i; n++) {
                if (results.get(n) > results.get(i)) {
                    if (existence) {
                        if (ranks.get(i) > ranks.get(n)) {
                            // get the smaller one's rank
                            ranks.set(i, ranks.get(n));
                        }
                        // add 1 on smaller one
                        ranks.set(n, ranks.get(n) + 1);

                    } else {
                        // get the smaller one's rank
                        ranks.add(ranks.get(n));
                        // add 1 on smaller one
                        ranks.set(n, ranks.get(n) + 1);
                        existence = true;
                    }
                } else if (results.get(n) == results.get(i)) {
                    if (existence) {
                        // get this one's rank
                        ranks.set(i, ranks.get(n));
                    } else {
                        // get this one's rank
                        ranks.add(ranks.get(n));
                        existence = true;
                    }
                }
            }
            if (!existence) {
                // cann't find any one smaller than this one
                ranks.add(i + 1);
            }
        }
        return ranks;
    }
}
