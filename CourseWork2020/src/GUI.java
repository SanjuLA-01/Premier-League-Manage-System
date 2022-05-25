import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class GUI {
    static PremierLeagueManager pl= new PremierLeagueManager(); //premier league
    static List<FootballClub> footballClubList1=pl.getFootballClubList();//call to football club list
    static List<Match> matchList1=pl.getMatchList(); //call to match list

    public void startGUI(){
        Stage stage = new Stage(); //stage for first main GUI
        stage.setTitle("Premier League");
        Group group= new Group();

        ImageView imageView=new ImageView("Premier-League.png"); //image for main GUI
        imageView.fitWidthProperty().bind(stage.widthProperty()); //fit to the layout

        Button btn1= new Button("PL LIST(SORT)"); //button to display premier league table and to sort methods
        btn1.setStyle("-fx-background-color: \n" + //styles for button
                "        #090a0c,\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-padding: 10 20 10 20;");
        btn1.setLayoutX(50);  //button set on layout in exact place
        btn1.setLayoutY(20);
        btn1.setOnAction(e->leagueTablePoints()); //calling to the premier league table

        Button btn2= new Button("PLAY MATCHES"); //button to play a random match with two random generate teams
        btn2.setStyle("-fx-background-color: \n" + //styles for button
                "        #090a0c,\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-padding: 10 20 10 20;");
        btn2.setLayoutX(900); //button set on layout in exact place
        btn2.setLayoutY(20);
        btn2.setOnAction(e-> playMatch()); //calling to the play match

        Button btn3= new Button("MATCH (DATE)"); //button to display match list table according to ascending order of dates
        btn3.setStyle("-fx-background-color: \n" + //styles for button
                "        #090a0c,\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-padding: 10 20 10 20;");
        btn3.setLayoutX(50); //button set on layout in exact place
        btn3.setLayoutY(560);
        btn3.setOnAction(e-> matchTableDate()); //calling to the match table

        TextField textBox=new TextField(); //text field to get date by user input
        textBox.setPromptText("DD-MM-YY"); //displaying prompt messages
        textBox.setLayoutX(900); //text field set on layout in exact place
        textBox.setLayoutY(520);

        Button btn4= new Button("FIND MATCHES"); //button to display matches on specific date
        btn4.setStyle("-fx-background-color: \n" + //styles for button
                "        #090a0c,\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-padding: 10 20 10 20;");
        btn4.setLayoutX(900); //button set on layout in exact place
        btn4.setLayoutY(560);
        btn4.setOnAction(new EventHandler<ActionEvent>() { //event handler to find match according to the data
            @Override
            public void handle(ActionEvent event) {
                if(textBox.getText().isEmpty()){ //checking text field is empty or not
                    System.out.println("=====================================================================");
                    System.out.println("================== Sorry! First, Enter the date =====================");
                    System.out.println("=====================================================================");
                }else {
                    List<Match> findList= new ArrayList<>(); //new list to store filtered matches
                    for(Match match1: matchList1){
                        if(findList.contains(match1)){  //checking team in the list
                            continue;
                        }
                        //checking user input equals with match list details
                        if ((match1.getDate().getDay() + "-" + match1.getDate().getMonth() + "-" + match1.getDate().getYear()).equals(textBox.getText())) {
                            findList.add(match1);
                        }
                    }
                    ObservableList<Match> data= FXCollections.observableList(findList);

                    Stage stage = new Stage();
                    stage.setTitle("Match Table (According to Date)");
                    VBox vBox= new VBox(20);

                    TableView<Match> tableView=new TableView<>(); //table to match list

                    TableColumn<Match,FootballClub> club1NameCol= new TableColumn<>("CLUB 1 NAME"); //table column to display club 1 name
                    club1NameCol.setCellValueFactory(new PropertyValueFactory<>("Team1"));
                    club1NameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
                    club1NameCol.setResizable(false);

                    TableColumn<Match,FootballClub> club2NameCol= new TableColumn<>("CLUB 2 NAME"); //table column to display club 2 name
                    club2NameCol.setCellValueFactory(new PropertyValueFactory<>("Team2"));
                    club2NameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
                    club2NameCol.setResizable(false);

                    TableColumn<Match, Date> dayCol= new TableColumn<>("DATE"); //table column to display date
                    dayCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
                    dayCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
                    dayCol.setResizable(false);

                    TableColumn<Match, Integer> team1Score = new TableColumn<>("TEAM 1 SCORE"); //table column to display team 1 score
                    team1Score.setCellValueFactory(new PropertyValueFactory<>("Team1Goals"));
                    team1Score.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
                    team1Score.setResizable(false);

                    TableColumn<Match, Integer> team2Score= new TableColumn<>("TEAM 2 SCORE"); //table column to display team 2 score
                    team2Score.setCellValueFactory(new PropertyValueFactory<>("Team2Goals"));
                    team2Score.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));  //fit to table
                    team2Score.setResizable(false);

                    tableView.setItems(data);
                    tableView.getColumns().setAll(club1NameCol,club2NameCol,dayCol,team1Score,team2Score);

                    vBox.prefWidthProperty().bind(stage.widthProperty());  //fit to stage
                    vBox.prefWidthProperty().bind(stage.heightProperty());  //fit to stage
                    vBox.setPadding(new Insets(25,25,25,25));
                    vBox.getChildren().add(tableView);
                    Scene scene = new Scene(vBox,1000,450);
                    stage.setScene(scene);
                    stage.show();

                    textBox.clear(); //clearing texting in the text field
                }
            }
        });
        Button btn5= new Button("Close Stage"); //button to close the stage
        btn5.setStyle("-fx-background-color: \n" + //styles for button
                "        red;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-padding: 10 20 10 20;");
        btn5.setLayoutX(490); //button set on layout in exact place
        btn5.setLayoutY(560);
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        group.getChildren().addAll(imageView,btn1,btn2,btn3,btn4,btn5,textBox);
        Scene scene = new Scene(group,1056,680);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void leagueTablePoints(){

        Stage stage = new Stage(); //stage for premier league table
        stage.setTitle("Premier League Table");

        AnchorPane anchorPane = new AnchorPane(); //premier league (Points)
        Scene scene = new Scene(anchorPane,1000,800);

        AnchorPane anchorPane1 = new AnchorPane(); //premier league (Goals)
        Scene scene1 = new Scene(anchorPane1,1000,800);

        AnchorPane anchorPane2 = new AnchorPane(); //premier league (Wins)
        Scene scene2 = new Scene(anchorPane2,1000,800);

        Collections.sort(footballClubList1); //sorting premier league table  descending order according number of points of the club

        ObservableList<FootballClub> data = FXCollections.observableList(footballClubList1);

        TableView<FootballClub> tableView=new TableView<>(); //table view to display premier league table according to the number of the points of the club
        tableView.setPrefSize(1000,750);

        TableColumn<FootballClub,String> clubNameCol= new TableColumn<>("CLUB NAME"); //table column to display club name
        clubNameCol.setCellValueFactory(new PropertyValueFactory<>("ClubName"));
        clubNameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        clubNameCol.setResizable(false);

        TableColumn<FootballClub,String> locationCol= new TableColumn<>("LOCATION"); //table column to display location
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        locationCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        locationCol.setResizable(false);

        TableColumn<FootballClub, Integer> winsCol= new TableColumn<>("WINS"); //table column to display number of wins
        winsCol.setCellValueFactory(new PropertyValueFactory<>("Wins"));
        winsCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        winsCol.setResizable(false);

        TableColumn<FootballClub, Integer> drawsCol= new TableColumn<>("DRAWS");//table column to display number of draws
        drawsCol.setCellValueFactory(new PropertyValueFactory<>("Draws"));
        drawsCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        drawsCol.setResizable(false);

        TableColumn<FootballClub, Integer> defeatsCol= new TableColumn<>("DEFEATS"); //table column to display number of defeats
        defeatsCol.setCellValueFactory(new PropertyValueFactory<>("Defeats"));
        defeatsCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        defeatsCol.setResizable(false);

        TableColumn<FootballClub, Integer> sgCol= new TableColumn<>("SG"); //table column to display number of scored goals
        sgCol.setCellValueFactory(new PropertyValueFactory<>("ScoredGoals"));
        sgCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        sgCol.setResizable(false);

        TableColumn<FootballClub, Integer> rgCol= new TableColumn<>("RG"); //table column to display number of received goals
        rgCol.setCellValueFactory(new PropertyValueFactory<>("ReceivedGoals"));
        rgCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        rgCol.setResizable(false);

        TableColumn<FootballClub, Integer> pointsCol= new TableColumn<>("POINTS"); //table column to display number of points
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("NumberOfPoints"));
        pointsCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        pointsCol.setResizable(false);

        TableColumn<FootballClub, Integer> matchCol= new TableColumn<>("MATCHES"); //table column to display number of matches
        matchCol.setCellValueFactory(new PropertyValueFactory<>("NumberOfMatches"));
        matchCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.11));  //fit to table
        matchCol.setResizable(false);

        tableView.setItems(data);
        tableView.getColumns().setAll(clubNameCol,locationCol,winsCol,drawsCol,defeatsCol,sgCol,rgCol,pointsCol,matchCol);

        Button btn1= new Button("GOALS"); //button to display premier league table according to descending order of scored goals
        btn1.setStyle("-fx-background-color: \n" + //styles for button
                "        #090a0c,\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-padding: 10 20 10 20;");
        btn1.setLayoutX(10);  //button set on layout in exact place
        btn1.setLayoutY(750);
        btn1.setOnAction(event -> {

            List<FootballClub> ftGoals=new ArrayList<>(); //list to store sorted data (scored goals)
            for (FootballClub footballClub:footballClubList1){
                ftGoals.add(footballClub);
            }
            Comparator <FootballClub> scoreCom= new Comparator<FootballClub>() { //sorting premier league table  descending order according number of scored goals of the club
                @Override
                public int compare(FootballClub f1,FootballClub f2) {

                    int sc1=f1.getScoredGoals();
                    int sc2=f2.getScoredGoals();
                    return sc2-sc1; //descending order
                }
            };

            Collections.sort(ftGoals,scoreCom);

            ObservableList<FootballClub> data1 = FXCollections.observableList(ftGoals);

            TableView<FootballClub> tableView1=new TableView<>(); //table view to display premier league table according to the scored goals of the club
            tableView1.setPrefSize(1000,750);

            TableColumn<FootballClub,String> clubNameCol1= new TableColumn<>("CLUB NAME"); //table column to display club name
            clubNameCol1.setCellValueFactory(new PropertyValueFactory<>("ClubName"));
            clubNameCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            clubNameCol1.setResizable(false);

            TableColumn<FootballClub,String> locationCol1= new TableColumn<>("LOCATION"); //table column to display location
            locationCol1.setCellValueFactory(new PropertyValueFactory<>("Location"));
            locationCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            locationCol1.setResizable(false);

            TableColumn<FootballClub, Integer> winsCol1= new TableColumn<>("WINS"); //table column to display number of wins
            winsCol1.setCellValueFactory(new PropertyValueFactory<>("Wins"));
            winsCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            winsCol1.setResizable(false);

            TableColumn<FootballClub, Integer> drawsCol1= new TableColumn<>("DRAWS");//table column to display number of draws
            drawsCol1.setCellValueFactory(new PropertyValueFactory<>("Draws"));
            drawsCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            drawsCol1.setResizable(false);

            TableColumn<FootballClub, Integer> defeatsCol1= new TableColumn<>("DEFEATS"); //table column to display number of defeats
            defeatsCol1.setCellValueFactory(new PropertyValueFactory<>("Defeats"));
            defeatsCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            defeatsCol1.setResizable(false);

            TableColumn<FootballClub, Integer> sgCol1= new TableColumn<>("SG"); //table column to display number of scored goals
            sgCol1.setCellValueFactory(new PropertyValueFactory<>("ScoredGoals"));
            sgCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            sgCol1.setResizable(false);

            TableColumn<FootballClub, Integer> rgCol1= new TableColumn<>("RG"); //table column to display number of received goals
            rgCol1.setCellValueFactory(new PropertyValueFactory<>("ReceivedGoals"));
            rgCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            rgCol1.setResizable(false);

            TableColumn<FootballClub, Integer> pointsCol1= new TableColumn<>("POINTS"); //table column to display number of points
            pointsCol1.setCellValueFactory(new PropertyValueFactory<>("NumberOfPoints"));
            pointsCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            pointsCol1.setResizable(false);

            TableColumn<FootballClub, Integer> matchCol1= new TableColumn<>("MATCHES"); //table column to display number of matches
            matchCol1.setCellValueFactory(new PropertyValueFactory<>("NumberOfMatches"));
            matchCol1.prefWidthProperty().bind(tableView1.widthProperty().multiply(0.11));  //fit to table
            matchCol1.setResizable(false);

            tableView1.setItems(data1);
            tableView1.getColumns().setAll(clubNameCol1,locationCol1,winsCol1,drawsCol1,defeatsCol1,sgCol1,rgCol1,pointsCol1,matchCol1);

            Button btn3= new Button("BACK"); //button to go back
            btn3.setStyle("-fx-background-color: \n" + //styles for button
                    "        #090a0c,\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-padding: 10 20 10 20;");
            btn3.setLayoutX(450);  //button set on layout in exact place
            btn3.setLayoutY(750);
            btn3.setOnAction(event1 -> {
                stage.setScene(scene);
            });
            anchorPane1.getChildren().addAll(tableView1,btn3);
            stage.setScene(scene1);
        });

        Button btn2= new Button("WINS"); //button to display premier league table according to descending order number of wins of the club
        btn2.setStyle("-fx-background-color: \n" + //styles for button
                "        #090a0c,\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-padding: 10 20 10 20;");
        btn2.setLayoutX(910);  //button set on layout in exact place
        btn2.setLayoutY(750);
        btn2.setOnAction(event -> {

            List<FootballClub> ftWins=new ArrayList<>(); //list to store sorted data (number of wins)
            for (FootballClub footballClub:footballClubList1){
                ftWins.add(footballClub);
            }
            Comparator<FootballClub> winsCom= new Comparator<FootballClub>() { //sorting premier league table  descending order according number of wins of the club
                @Override
                public int compare(FootballClub f1, FootballClub f2) {
                    int win1=f1.getWins();
                    int win2=f2.getWins();
                    return win2-win1; //descending order
                }
            };
            Collections.sort(ftWins,winsCom);

            ObservableList<FootballClub> data2 = FXCollections.observableList(ftWins);

            TableView<FootballClub> tableView2=new TableView<>(); //table view to display premier league table according to the number of the wins of the club
            tableView2.setPrefSize(1000,750);

            TableColumn<FootballClub,String> clubNameCol2= new TableColumn<>("CLUB NAME"); //table column to display club name
            clubNameCol2.setCellValueFactory(new PropertyValueFactory<>("ClubName"));
            clubNameCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            clubNameCol2.setResizable(false);

            TableColumn<FootballClub,String> locationCol2= new TableColumn<>("LOCATION"); //table column to display location
            locationCol2.setCellValueFactory(new PropertyValueFactory<>("Location"));
            locationCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            locationCol2.setResizable(false);

            TableColumn<FootballClub, Integer> winsCol2= new TableColumn<>("WINS"); //table column to display number of wins
            winsCol2.setCellValueFactory(new PropertyValueFactory<>("Wins"));
            winsCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            winsCol2.setResizable(false);

            TableColumn<FootballClub, Integer> drawsCol2= new TableColumn<>("DRAWS");//table column to display number of draws
            drawsCol2.setCellValueFactory(new PropertyValueFactory<>("Draws"));
            drawsCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            drawsCol2.setResizable(false);

            TableColumn<FootballClub, Integer> defeatsCol2= new TableColumn<>("DEFEATS"); //table column to display number of defeats
            defeatsCol2.setCellValueFactory(new PropertyValueFactory<>("Defeats"));
            defeatsCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            defeatsCol2.setResizable(false);

            TableColumn<FootballClub, Integer> sgCol2= new TableColumn<>("SG"); //table column to display number of scored goals
            sgCol2.setCellValueFactory(new PropertyValueFactory<>("ScoredGoals"));
            sgCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            sgCol2.setResizable(false);

            TableColumn<FootballClub, Integer> rgCol2= new TableColumn<>("RG"); //table column to display number of received goals
            rgCol2.setCellValueFactory(new PropertyValueFactory<>("ReceivedGoals"));
            rgCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            rgCol2.setResizable(false);

            TableColumn<FootballClub, Integer> pointsCol2= new TableColumn<>("POINTS"); //table column to display number of points
            pointsCol2.setCellValueFactory(new PropertyValueFactory<>("NumberOfPoints"));
            pointsCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            pointsCol2.setResizable(false);

            TableColumn<FootballClub, Integer> matchCol2= new TableColumn<>("MATCHES"); //table column to display number of matches
            matchCol2.setCellValueFactory(new PropertyValueFactory<>("NumberOfMatches"));
            matchCol2.prefWidthProperty().bind(tableView2.widthProperty().multiply(0.11));  //fit to table
            matchCol2.setResizable(false);

            tableView2.setItems(data2);
            tableView2.getColumns().setAll(clubNameCol2,locationCol2,winsCol2,drawsCol2,defeatsCol2,sgCol2,rgCol2,pointsCol2,matchCol2);

            Button btn3= new Button("BACK"); //button to go back
            btn3.setStyle("-fx-background-color: \n" + //styles for button
                    "        #090a0c,\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                    "    -fx-background-radius: 5,4,3,5;\n" +
                    "    -fx-text-fill: white;\n" +
                    "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                    "    -fx-padding: 10 20 10 20;");
            btn3.setLayoutX(450);  //button set on layout in exact place
            btn3.setLayoutY(750);
            btn3.setOnAction(event1 -> {
                stage.setScene(scene);
            });

            anchorPane2.getChildren().addAll(tableView2,btn3);
            stage.setScene(scene2);
        });

        Label label =new Label("<--- Select Any Sorting Option --->");
        label.setStyle("-fx-background-color: yellow; \n"+
                "-fx-font-size: 20;\n"+
                "-fx-text-fill: black;");
        label.setLayoutX(370);
        label.setLayoutY(750);

        anchorPane.getChildren().addAll(tableView,btn1,btn2,label);
        stage.setScene(scene);
        stage.showAndWait();

    }

    private void playMatch(){

        if(footballClubList1.isEmpty()){ //checking football club list is empty or not
            System.out.println("=====================================================================");
            System.out.println("================== Sorry! Premier league is empty ===================");
            System.out.println("=====================================================================");
        } else {

            if(footballClubList1.size()==1){ //checking is there two teams or less than that
                System.out.println("=====================================================================");
                System.out.println("================== One team is not enough to play ===================");
                System.out.println("=====================================================================");
            }else {

                Random random = new Random(); //randomly selecting

                //randomly selecting day and month
                int mDay = random.nextInt(31) + 1;
                int mMonth = random.nextInt(12) + 1;
                int mYear = 2021;

                int num1 = random.nextInt(footballClubList1.size()); //selecting team 1 number
                FootballClub f1 = footballClubList1.get(num1); //football team 1
                String name1 = f1.getClubName();

                int num2 = random.nextInt(footballClubList1.size()); //selecting team 2 number

                if (num1 == num2) { //checking first team is equal to second team
                    playMatch();

                } else {
                    FootballClub f2 = footballClubList1.get(num2);  //football team 2
                    String name2 = f2.getClubName();

                    //randomly selecting scores for teams
                    int scoreTeam1 = random.nextInt(25); //maximum goal can be 24
                    int scoreTeam2 = random.nextInt(25); //maximum goal can be 24

                    f1.setScoredGoals(f1.getScoredGoals() + scoreTeam1); //updating that two teams statics
                    f2.setScoredGoals(f2.getScoredGoals() + scoreTeam2);
                    f1.setReceivedGoals(f1.getReceivedGoals() + scoreTeam2);
                    f2.setReceivedGoals(f2.getReceivedGoals() + scoreTeam1);
                    f1.setNumberOfMatches(f1.getNumberOfMatches() + 1);
                    f2.setNumberOfMatches(f2.getNumberOfMatches() + 1);

                    if (scoreTeam1 > scoreTeam2) { //checking who is the winner and updating stats
                        f1.setWins(f1.getWins() + 1);
                        f1.setNumberOfPoints(f1.getNumberOfPoints() + 3);
                        f2.setDefeats(f2.getDefeats() + 1);
                    } else if (scoreTeam2 > scoreTeam1) { //checking who is the winner and updating stats
                        f2.setWins(f2.getWins() + 1);
                        f2.setNumberOfPoints(f2.getNumberOfPoints() + 3);
                        f1.setDefeats(f1.getDefeats() + 1);
                    } else {
                        f1.setDraws(f1.getDraws() + 1); //if draw what should happen
                        f1.setNumberOfPoints(f1.getNumberOfPoints() + 1);
                        f2.setDraws(f2.getDraws() + 1);
                        f2.setNumberOfPoints(f2.getNumberOfPoints() + 1);
                    }

                    Stage stage = new Stage();
                    stage.setTitle("PLAY MATCH");
                    Group group = new Group();

                    ImageView imageView = new ImageView("match.jpg"); //image for random match GUI
                    imageView.fitWidthProperty().bind(stage.widthProperty()); //fit to the layout

                    Button btn1 = new Button("Team 1 Name --> " + name1); //button to display details ( work as a label)
                    btn1.setStyle("-fx-background-color: \n" + //styles for button
                            "        #090a0c,\n" +
                            "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                            "    -fx-background-radius: 5,4,3,5;\n" +
                            "    -fx-text-fill: white;\n" +
                            "    -fx-font-size: 22;\n" +
                            "    -fx-padding: 10 20 10 20;");
                    btn1.setLayoutX(25); //set on exact place
                    btn1.setLayoutY(25);

                    Button btn2 = new Button("Team 2 Name --> " + name2); //button to display details ( work as a label)
                    btn2.setStyle("-fx-background-color: \n" + //styles for button
                            "        #090a0c,\n" +
                            "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                            "    -fx-background-radius: 5,4,3,5;\n" +
                            "    -fx-text-fill: white;\n" +
                            "    -fx-font-size: 22;\n" +
                            "    -fx-padding: 10 20 10 20;");
                    btn2.setLayoutX(750); //set on exact place
                    btn2.setLayoutY(25);

                    Button btn3 = new Button("Team 1 Score --> " + scoreTeam1); //button to display details ( work as a label)
                    btn3.setStyle("-fx-background-color: \n" + //styles for button
                            "        #090a0c,\n" +
                            "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                            "    -fx-background-radius: 5,4,3,5;\n" +
                            "    -fx-text-fill: white;\n" +
                            "    -fx-font-size: 22;\n" +
                            "    -fx-padding: 10 20 10 20;");
                    btn3.setLayoutX(25); //set on exact place
                    btn3.setLayoutY(100);

                    Button btn4 = new Button("Team 2 Score --> " + scoreTeam2); //button to display details ( work as a label)
                    btn4.setStyle("-fx-background-color: \n" + //styles for button
                            "        #090a0c,\n" +
                            "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                            "    -fx-background-radius: 5,4,3,5;\n" +
                            "    -fx-text-fill: white;\n" +
                            "    -fx-font-size: 22;\n" +
                            "    -fx-padding: 10 20 10 20;");
                    btn4.setLayoutX(750); //set on exact place
                    btn4.setLayoutY(100);

                    Button btn5 = new Button(); //button to display details ( work as a label)
                    btn5.setStyle("-fx-background-color: \n" + //styles for button
                            "        #090a0c,\n" +
                            "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                            "    -fx-background-radius: 5,4,3,5;\n" +
                            "    -fx-text-fill: yellow;\n" +
                            "    -fx-font-size: 28;\n" +
                            "    -fx-padding: 10 20 10 20;");
                    if (scoreTeam1 > scoreTeam2) { //checking who is the winner and display winner according to the scores
                        btn5.setText("WINNER IS --> " + name1);
                    } else if (scoreTeam2 > scoreTeam1) {
                        btn5.setText("WINNER IS --> " + name2);
                    } else {
                        btn5.setText("NO WINNER");
                    }
                    btn5.setLayoutX(380); //set on exact place
                    btn5.setLayoutY(380);

                    Button btn6 = new Button("EXIT"); //button to close the stage
                    btn6.setStyle("-fx-background-color: \n" + //styles for button
                            "        red;\n" +
                            "    -fx-background-radius: 5,4,3,5;\n" +
                            "    -fx-text-fill: black;\n" +
                            "    -fx-font-size: 22;\n" +
                            "    -fx-padding: 10 20 10 20;");
                    btn6.setLayoutX(950); //button set on layout in exact place
                    btn6.setLayoutY(600);
                    btn6.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stage.close();
                        }
                    });

                    group.getChildren().addAll(imageView, btn1, btn2, btn3, btn4, btn5, btn6);
                    Scene scene1 = new Scene(group, 1056, 680); //match stage
                    stage.setScene(scene1);
                    stage.show();

                    Match match = new Match(f1, f2, new Date(mDay, mMonth, mYear), scoreTeam1, scoreTeam2); //add match to the match list
                    matchList1.add(match); // add match to the list
                }
            }
        }
    }

    private void matchTableDate(){
        Comparator<Match> dateCom= new Comparator<Match>() { //sorting match list table  ascending order of dates
            @Override
            public int compare(Match m1, Match m2) {
                int day1=m1.getDate().getDay();
                int day2=m2.getDate().getDay();
                int month1=m1.getDate().getMonth();
                int month2=m2.getDate().getMonth();
                int year1=m1.getDate().getYear();
                int year2=m2.getDate().getYear();
                int totalDays1=(year1*356)+(month1*30)+(day1); //converting years and months to days
                int totalDays2=(year2*356)+(month2*30)+(day2); //converting years and months to days
                return totalDays1-totalDays2;
            }
        };
        Collections.sort(matchList1,dateCom);
        ObservableList<Match> data= FXCollections.observableList(matchList1);

        Stage stage = new Stage();
        stage.setTitle("Match Table");
        VBox vBox= new VBox(20);

        TableView<Match> tableView=new TableView<>(); //table to display match list

        TableColumn<Match,FootballClub> club1NameCol= new TableColumn<>("TEAM 1 NAME"); //table column to display club 1 name
        club1NameCol.setCellValueFactory(new PropertyValueFactory<>("Team1"));
        club1NameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
        club1NameCol.setResizable(false);

        TableColumn<Match,FootballClub> club2NameCol= new TableColumn<>("TEAM 2 NAME"); //table column to display club 2 name
        club2NameCol.setCellValueFactory(new PropertyValueFactory<>("Team2"));
        club2NameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
        club2NameCol.setResizable(false);

        TableColumn<Match, Integer> team1Score= new TableColumn<>("TEAM 1 SCORE"); //table column to display team 1 score
        team1Score.setCellValueFactory(new PropertyValueFactory<>("Team1Goals"));
        team1Score.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
        team1Score.setResizable(false);

        TableColumn<Match, Integer> team2Score= new TableColumn<>("TEAM 2 SCORE"); //table column to display team 2 score
        team2Score.setCellValueFactory(new PropertyValueFactory<>("Team2Goals"));
        team2Score.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));  //fit to table
        team2Score.setResizable(false);

        TableColumn<Match, Date> dayCol= new TableColumn<>("DATE"); //table column to display date
        dayCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        dayCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2)); //fit to table
        dayCol.setResizable(false);

        tableView.setItems(data);
        tableView.getColumns().setAll(club1NameCol,club2NameCol,team1Score,team2Score,dayCol);

        vBox.prefWidthProperty().bind(stage.widthProperty());  //fit to stage
        vBox.prefWidthProperty().bind(stage.heightProperty());  //fit to table
        vBox.setPadding(new Insets(25,25,25,25));
        vBox.getChildren().add(tableView);
        Scene scene = new Scene(vBox,1000,450);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
