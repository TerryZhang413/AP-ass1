package Ozlympic;

import java.util.ArrayList;
import java.util.Random;

public class UserInfo {
	/**
	 * @date 31.3.2017
	 * @author Yipeng
	 * @version 1.0
	 * @Description call user information
	 */
    private ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    private ArrayList<Official> officials = new ArrayList<Official>();
    private final int MAX_ATHLETE = 8;// maximum athlete in a game
    private final int MIN_ATHLETE = 4;// minimum athlete in a game

    public UserInfo(ArrayList<Athlete> athletes,
            ArrayList<Official> officials) {
        this.athletes = athletes;
        this.officials = officials;
    }

    // Get athlete's information based on userID
    public String[] getAthleteInf(String userID) {
        String[] athleteinf = new String[5];
        for (Athlete athlete : athletes) {
            if (athlete.getUserID().equals(userID)) {
                athleteinf[0] = athlete.getName(); // name
                athleteinf[1] = String.valueOf(athlete.getAge()); // age
                athleteinf[2] = athlete.getState(); // state
                switch (athlete.getAthleteType()) { // type
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

    // get offical information
    public String getOfficialInf() {
        Random ranIndex = new Random();
        int sizeList;
        String officialID = new String();
        sizeList = officials.size();
        if (sizeList > 0) {
            sizeList--;
            officialID = officials.get(ranIndex.nextInt(sizeList)).getUserID();
            getClass();
            return officialID; // return the offical
        } else {
            return null;
        }
    }

    // get officer's id based on userID
    public String getOffName(String userID) {
        for (Official official : officials) {
            if (official.getUserID().equals(userID))
                return official.getName();
        }
        return null;
    }

    // get athlete list
    public ArrayList<String> getAthlete(int gameType) {
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
                Random ranIndex = new Random(); // make a random number
                int sizeList;
                while (temporaryList.size() > MAX_ATHLETE) {
                    sizeList = temporaryList.size() - 1;
                    temporaryList.remove(ranIndex.nextInt(sizeList));
                }
                return temporaryList; // return the athlete list
            }
        } catch (Exception e) {
            return null;
        }
    }

    // add point based on users' ID and point
    public void addPoint(String athleteID, int addPoint) {
        int athleteCount;
        int point = 0;
        athleteCount = athletes.size();
        for (int i = 0; i < athleteCount; i++) { // add points
            if (athleteID == athletes.get(i).getUserID()) {
                point = athletes.get(i).getPoint() + addPoint;
                athletes.get(i).setPoint(point);
                return;
            }
        }
    }
}
