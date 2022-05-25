import javafx.application.Application;
import javafx.stage.Stage;
import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

public class MenuConsole extends Application {

    static LeagueManager pl= new PremierLeagueManager(); //premier league
    final static Scanner USER_INPUT= new Scanner(System.in); //to take user inputs

    @Override
    public void start(Stage primaryStage) throws Exception,IOException,ClassNotFoundException{
        GUI gui= new GUI();

        try {
            pl.loadDetails("footballDetails.txt", "matchDetails"); //loading details from text files
        }catch (EOFException e){
            System.out.println(" ============================================================ ");
            System.out.println(" =========== No data in File ! please Try Again ============= ");
            System.out.println(" ============================================================ ");
            System.out.println(" ");
            System.out.println(" ");
        }
        System.out.println(" ============================================================ ");
        System.out.println(" ==========================WELCOME=========================== ");
        System.out.println(" =============================TO============================= ");
        System.out.println(" ===================LEAGUE MANAGER SYSTEM==================== ");
        System.out.println(" ============================================================ ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ================ Details successfully loaded =============== ");

        menuLoop: //looping options to user
        while (true){

            System.out.println(" ");
            System.out.println(" ");
            System.out.println("=====================================================================");
            System.out.println(" Enter 1 -----> to add a football club to premier league");
            System.out.println(" Enter 2 -----> to delete club from premier league");
            System.out.println(" Enter 3 -----> to display the various static of a selected club");
            System.out.println(" Enter 4 -----> to display premier league table");
            System.out.println(" Enter 5 -----> to add a match and update details");
            System.out.println(" Enter 6 -----> to works with GUI");
            System.out.println(" Enter 7 -----> to save files");
            System.out.println(" Enter 8 -----> to quit");
            System.out.println("=====================================================================");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Dear user, what you want to do:");
            String choice = USER_INPUT.next();

            switch (choice){
                case "1":
                    pl.addFootballClub();     //add football club to premier league

                    break;

                case "2":
                    deleteFootballClub(); //calling to delete method
                    break;

                case "3":
                    printFootballClubStatics(); //print statics for selected club
                    break;

                case "4":
                    pl.printPremierLeagueTable(); //print premier league table
                    break;

                case "5":
                    pl.updateMatch(); //add a match and updating details of the premier league table and statics
                    break;

                case "6":
                    gui.startGUI();
                    break ;

                case "7":
                    pl.saveDetails("footballDetails.txt","matchDetails"); //automatically save details in text files
                    System.out.println("=======================================================");
                    System.out.println("============== Files Successfully Saved ===============");
                    System.out.println("=======================================================");
                    break ;

                case "8":
                    System.out.println("=======================================================");
                    System.out.println("====================== GOOD BYE =======================");
                    System.out.println("=======================================================");
                    break menuLoop;

                default:
                    System.out.println("======== Invalid Input! please Try Again ========");
            }
        }
    }

    private static void deleteFootballClub(){ //delete football club from premier league
        System.out.println("Please enter the club name that you want to delete:");
        String clubName= USER_INPUT.next();

        pl.deleteFootballClub(clubName);
    }

    private static void printFootballClubStatics(){ //print statics for selected football team
        System.out.println("Please enter the club name that you want to print static:");
        String clubName= USER_INPUT.next();

        pl.printFootballClubStatics(clubName);
    }

    public static void main(String[]args){
        launch(args);
    }

}
