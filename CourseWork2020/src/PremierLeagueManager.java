import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class PremierLeagueManager implements LeagueManager {

    final static public int MAXIMUM_TEAMS = 20; //in premier league there are only 20 teams in the league
    private static List<FootballClub> footballClubList = new ArrayList<>(); //use to store football club statics
    private static List<Match> matchList = new ArrayList<>(); //use to store match details
    private int remain=MAXIMUM_TEAMS; //when adding football team checking how many slots remaining in pl table
    final static Scanner MatchDetails = new Scanner(System.in); //to take user inputs of match details
    final static Scanner USER_INPUT= new Scanner(System.in); //to take user inputs to add football club

    public  List<FootballClub> getFootballClubList() {
        return footballClubList;
    }

    public  List<Match> getMatchList() {
        return matchList;
    }

    @Override
    public void addFootballClub() {
        FootballClub footballClub;

        System.out.println("Please enter the name of the football club:");
        String clubName = USER_INPUT.next();
        if(stringCompare(clubName)){ //checking club name has only strings
            System.out.println("Please enter the location of the football club:");
            String location = USER_INPUT.next();

            if(stringCompare(location)){ //checking location has only  strings

                footballClub = new FootballClub(clubName, location);

                boolean containCheck=false;

                for(FootballClub footballClub1: footballClubList){
                    if(footballClub1.getClubName().equals(clubName)){ //checking football club already in the list or not
                        containCheck=true;
                        System.out.println("========================================================================");
                        System.out.println("================== This Football club is already in ====================");
                        System.out.println("========================================================================");
                        break;
                    }
                }

                if (!containCheck){
                    if(remain == 0){ //checking remaining slots available or not
                        System.out.println("=====================================================================");
                        System.out.println("=================== Sorry! premier league is Full ===================");
                        System.out.println("=====================================================================");
                    }

                    else {
                        footballClubList.add(footballClub); //adding team to the foot ball club list
                        remain --; //subtracting one from the remaining after added football team to the pl
                        System.out.println("=====================================================================");
                        System.out.println("===== Successfully your football team add to the premier league =====");
                        System.out.println("=====================================================================");
                    }
                }

            }else{
                System.out.println("=======================================================");
                System.out.println("======== Strings only! please Try Again ==============");
                System.out.println("=======================================================");
                System.out.println(" ");
            }
        }else {
            System.out.println("=======================================================");
            System.out.println("======== Strings only! please Try Again ==============");
            System.out.println("=======================================================");
            System.out.println(" ");
        }

    }

    @Override
    public void deleteFootballClub(String clubName) {
        if(footballClubList.isEmpty()){ //checking football club list is empty or not
            System.out.println("=====================================================================");
            System.out.println("================== Sorry! Premier league is empty ===================");
            System.out.println("=====================================================================");
        }

        else{
            boolean found=false;

            for(FootballClub footballClub : footballClubList){ //loop in foot ball club list
                if(footballClub.getClubName().equals(clubName)){ //checking user's input equals with club name in the list
                    found = true;
                    System.out.println("Are you sure to delete (Y/N):"); //warning message to delete or not
                    String optionDelete = USER_INPUT.next().toLowerCase();
                    if(optionDelete.equals("y")) { //checking user input
                        footballClubList.remove(footballClub); //remove club
                        System.out.println("=====================================================================");
                        System.out.println("============ " + clubName + " has left the premier league! ==========");
                        System.out.println("=====================================================================");
                        break;
                    }
                    else if(optionDelete.equals("n")){ //checking user input
                        System.out.println("=====================================================================");
                        System.out.println("============================Okay!! Thank You=========================");
                        System.out.println("=====================================================================");
                        break;
                    }
                    else{
                        System.out.println("=====================================================================");
                        System.out.println("========================Invalid Input Try Again======================");
                        System.out.println("=====================================================================");
                        break;
                    }
                }
            }

            if(!found){ //cant find that club in the list
                System.out.println("=====================================================================");
                System.out.println("============= Please check the name of club and re enter ============");
                System.out.println("=====================================================================");
            }
        }
    }

    @Override
    public void printFootballClubStatics(String clubName) {
        if(footballClubList.isEmpty()){//checking football club list is empty or not
            System.out.println("=====================================================================");
            System.out.println("================== Sorry! Premier league is empty ===================");
            System.out.println("=====================================================================");

        }

        else{
            boolean found=false;

            for(FootballClub footballClub : footballClubList){ //loop in foot ball club list
                if(footballClub.getClubName().equals(clubName)){//checking user's input equals with club name in the list
                    found = true;
                    System.out.println(footballClub);
                    break;
                }
            }
            
            if(!found){ //cant find that club in the list
                System.out.println("=====================================================================");
                System.out.println("============= Please check the name of club and re enter ============");
                System.out.println("=====================================================================");
            }
        }

    }

    @Override
    public void printPremierLeagueTable() {
        if(footballClubList.isEmpty()){ //checking football club list is empty or not
            System.out.println("=====================================================================");
            System.out.println("=================== Sorry! Premier league is empty ==================");
            System.out.println("=====================================================================");
        }
        else{
            Collections.sort(footballClubList); //sorting according to number of points (descending order)
            System.out.println("\n||============================================================================================================================================================||");
            System.out.println("||========================================================== PREMIER LEAGUE TABLE ============================================================================||");
            System.out.println("||============================================================================================================================================================||");
            //print format like a table
            System.out.format("%20s%20s%15s%15s%15s%15s%15s%15s%15s\n","|| CLUB NAME","|| LOCATION","|| WINS","|| DRAWS","|| DEFEATS","|| RG","|| SG","|| POINTS","|| MATCHES ");
            System.out.println("================================================================================================================================================================");
            for (FootballClub footballClub: footballClubList){
                System.out.format("%20s%20s%15s%15s%15s%15s%15s%15s%15s\n",footballClub.getClubName(),footballClub.getLocation(),footballClub.getWins(),footballClub.getDraws(),footballClub.getDefeats(),footballClub.getReceivedGoals(),footballClub.getScoredGoals(),footballClub.getNumberOfPoints(),footballClub.getNumberOfMatches());
                System.out.println("\n================================================================================================================================================================");
            }
            System.out.println("================================================================================================================================================================");
        }
    }

    @Override
    public void updateMatch() {

        if (footballClubList.isEmpty()) { //checking football club list is empty or not
            System.out.println("=====================================================================");
            System.out.println("================== Sorry! Premier league is empty ===================");
            System.out.println("=====================================================================");
        }else {
            if (footballClubList.size()==1){ //one team is not enough to play a match
                System.out.println("=====================================================================");
                System.out.println("================== One team is not enough to play ===================");
                System.out.println("=====================================================================");
            }else {
                try {
                    //checking day/month/year integer or not
                    System.out.println("Enter the day(match held):");
                    int day = MatchDetails.nextInt(); //checking day is integer or not
                    if (day > 0 && day < 32) { //validate day

                        System.out.println("Enter the month(match held):");
                        int month = MatchDetails.nextInt();
                        if (month > 0 && month < 13) { //validate month

                            System.out.println("Enter the year(match held):");
                            int year = MatchDetails.nextInt();
                            if (year > 1991 && year < 3000) { //validate year

                                System.out.println("Enter the club name of team 1: ");
                                String team1 = MatchDetails.next();
                                FootballClub footballClub1 = null;

                                boolean found = false;

                                for (FootballClub footballClub : footballClubList) { //loop in foot ball club list
                                    if (footballClub.getClubName().equals(team1)) { //checking user's input equals with club name in the list
                                        found = true;
                                        footballClub1 = footballClub;
                                        break;
                                    }
                                }

                                if (!found) {
                                    System.out.println("=====================================================================");
                                    System.out.println("============= Please check the name of club and re enter ============");
                                    System.out.println("=====================================================================");
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    updateMatch();
                                    return;
                                }

                                System.out.println("Enter the club name of team 2: ");
                                String team2 = MatchDetails.next();
                                if (team1.equals(team2)) { //cant play a match with one team so cant enter same name twice
                                    System.out.println("========================================================================");
                                    System.out.println("============= Check the name you have entered the same name ============");
                                    System.out.println("========================================================================");
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    updateMatch();
                                } else {
                                    FootballClub footballClub2 = null;

                                    boolean found1 = false;

                                    for (FootballClub footballClub : footballClubList) { //loop in foot ball club list
                                        if (footballClub.getClubName().equals(team2)) {//checking user's input equals with club name in the list
                                            found1 = true;
                                            footballClub2 = footballClub;
                                            break;
                                        }
                                    }

                                    if (!found1) {
                                        System.out.println("=====================================================================");
                                        System.out.println("============= Please check the name of club and re enter ============");
                                        System.out.println("=====================================================================");
                                        System.out.println(" ");
                                        System.out.println(" ");
                                        updateMatch();
                                        return;
                                    }

                                    try { //checking team1Scores and team2Scores integer or not
                                        System.out.println("Enter the goals of team 1:");
                                        int team1Scores = MatchDetails.nextInt();
                                        int footballClub1Goals = team1Scores;

                                        System.out.println("Enter the goals of team 2:");
                                        int team2Scores = MatchDetails.nextInt();
                                        int footballClub2Goals = team2Scores;

                                        Match match = new Match(footballClub1, footballClub2, new Date(day, month, year), team1Scores, team2Scores);
                                        matchList.add(match); // add match to  the list

                                        //updating statics of the teams

                                        footballClub1.setScoredGoals(footballClub1.getScoredGoals() + footballClub1Goals);
                                        footballClub1.setReceivedGoals(footballClub1.getReceivedGoals() + footballClub2Goals);
                                        footballClub2.setScoredGoals(footballClub2.getScoredGoals() + footballClub2Goals);
                                        footballClub2.setReceivedGoals(footballClub2.getReceivedGoals() + footballClub1Goals);
                                        footballClub1.setNumberOfMatches(footballClub1.getNumberOfMatches() + 1);
                                        footballClub2.setNumberOfMatches(footballClub2.getNumberOfMatches() + 1);

                                        System.out.println("=====================================================================");
                                        System.out.println("===== Successfully match update add to the premier league table =====");
                                        System.out.println("=====================================================================");

                                        if (footballClub1Goals > footballClub2Goals) { //checking who is the winner
                                            footballClub1.setWins(footballClub1.getWins() + 1);
                                            footballClub1.setNumberOfPoints(footballClub1.getNumberOfPoints() + 3);
                                            footballClub2.setDefeats(footballClub2.getDefeats() + 1);
                                        } else if (footballClub2Goals > footballClub1Goals) { //checking who is the winner
                                            footballClub2.setWins(footballClub2.getWins() + 1);
                                            footballClub2.setNumberOfPoints(footballClub2.getNumberOfPoints() + 3);
                                            footballClub1.setDefeats(footballClub1.getDefeats() + 1);
                                        } else {
                                            footballClub1.setDraws(footballClub1.getDraws() + 1); //if draw what should do
                                            footballClub1.setNumberOfPoints(footballClub1.getNumberOfPoints() + 1);
                                            footballClub2.setDraws(footballClub2.getDraws() + 1);
                                            footballClub2.setNumberOfPoints(footballClub2.getNumberOfPoints() + 1);
                                        }
                                    } catch (InputMismatchException ex) {
                                        System.out.println(" ");
                                        System.out.println(" ");
                                        System.out.println("======== Integers only for scores! please Try Again ========");
                                        System.out.println(" ");
                                        System.out.println(" ");
                                        MatchDetails.next(); //clear user input
                                        updateMatch();

                                    }
                                }

                            } else {
                                System.out.println(" ");
                                System.out.println(" ");
                                System.out.println("======== Invalid Year! please Try Again ========");
                                System.out.println(" ");
                                System.out.println(" ");
                                updateMatch();
                            }
                        } else {
                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println("======== Invalid Month! please Try Again ========");
                            System.out.println(" ");
                            System.out.println(" ");
                            updateMatch();
                        }
                    } else {
                        System.out.println(" ");
                        System.out.println(" ");
                        System.out.println("======== Invalid Day! please Try Again ========");
                        System.out.println(" ");
                        System.out.println(" ");
                        updateMatch();
                    }
                } catch (InputMismatchException ex1) {
                    System.out.println(" ");
                    System.out.println(" ");
                    System.out.println("======== Integers only Date! please Try Again ========");
                    System.out.println(" ");
                    System.out.println(" ");
                    MatchDetails.next(); //clear user input
                    updateMatch();
                }
            }
        }
    }

    @Override
    public void saveDetails(String fileName, String file1Name) throws IOException {
      try {
          FileOutputStream fos = new FileOutputStream(fileName); //save football list details
          ObjectOutputStream oos = new ObjectOutputStream(fos);

          for (FootballClub footballClub : footballClubList) {
              oos.writeObject(footballClub); //writing objects
          }
          oos.flush();
          oos.close();
          fos.close();

          FileOutputStream fos1 = new FileOutputStream(file1Name); //save match details
          ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

          for (Match match: matchList) {
              oos1.writeObject(match); //writing objects
          }
          oos1.flush();
          oos1.close();
          fos1.close();

      }catch (IOException e){
          e.printStackTrace();
      }
    }

    @Override
    public void loadDetails(String fileName,String file1Name) throws IOException, ClassNotFoundException {
        FileInputStream fis= new FileInputStream(fileName); //load football list details
        ObjectInputStream ois = new ObjectInputStream(fis);

        for(;;){
            try {
                FootballClub footballClub=(FootballClub) ois.readObject(); //reading objects
                footballClubList.add(footballClub);
            }catch (EOFException e){
                break;
            }
        }
        ois.close();
        fis.close();

        FileInputStream fis1= new FileInputStream(file1Name); //load match details
        ObjectInputStream ois1 = new ObjectInputStream(fis1);

        for(;;){
            try {
                Match match=(Match) ois1.readObject(); //reading objects
                matchList.add(match);
            }catch (EOFException e){
                break;
            }
        }
        ois1.close();
        fis1.close();

    }

    //regex to check string or not
    private static boolean stringCompare(String input){
        return Pattern.matches("[a-zA-Z]+",input);
    }

}
