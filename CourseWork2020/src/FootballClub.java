import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub  implements Comparable<FootballClub>,Serializable{
    private int wins; //number of wins of a club
    private int draws; //number of draws of a club
    private int defeats; //number of defeats of a club
    private int receivedGoals; //number of received goals of a club
    private int scoredGoals; //number of scored goals of a club
    private int numberOfPoints; //number of points of a club
    private int numberOfMatches; //number of matches of a club that club has played

    //default constructor
    public FootballClub(){
    }

    //argument constructor
    public FootballClub(String clubName, String location){
        super(clubName,location);
    }

    public FootballClub(String clubName, String location,int wins, int draws, int defeats, int receivedGoals, int scoredGoals, int numberOfPoints, int numberOfMatches) {
        super(clubName, location); //to use sports clubs variables
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.receivedGoals = receivedGoals;
        this.scoredGoals = scoredGoals;
        this.numberOfPoints = numberOfPoints;
        this.numberOfMatches = numberOfMatches;
    }

    public int getWins( ) {
        return wins;
    }

    public void setWins(int wins ) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws ;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats (int defeats) {
        this.defeats = defeats;
    }

    public int getReceivedGoals() {
        return receivedGoals;
    }

    public void setReceivedGoals(int receivedGoals) {
        this.receivedGoals= receivedGoals;
    }

    public int getScoredGoals() {
        return scoredGoals ;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals= scoredGoals;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int  numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int  numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", Wins ==> " + wins +
                ", Draws ==> " + draws +
                ", Defeats ==> " + defeats +
                ", Received Goals ==> " + receivedGoals +
                ", Scored Goals ==> " + scoredGoals +
                ", Number Of Points ==> " + numberOfPoints +
                ", Number Of Matches ==> " + numberOfMatches ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return wins == that.wins &&
                draws == that.draws &&
                defeats == that.defeats &&
                receivedGoals == that.receivedGoals &&
                scoredGoals == that.scoredGoals &&
                numberOfPoints == that.numberOfPoints &&
                numberOfMatches == that.numberOfMatches;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wins, draws, defeats, receivedGoals, scoredGoals, numberOfPoints, numberOfMatches);
    }

    //for sorting part used this
    @Override
    public int compareTo(FootballClub o) {

        if (this.getNumberOfPoints() > o.getNumberOfPoints()) //checking what is the highest point from two teams
            return -1; //descending order

        else if(this.getNumberOfPoints() == o.getNumberOfPoints()){ //this part is when that points are equal  its checking the best goal difference
            if ((this.getScoredGoals() - this.getReceivedGoals()) > (o.getScoredGoals() - o.getReceivedGoals())) //in football best goal difference mean that different of the scored goal and received goal
                return -1;
            else
                return 1;
        }

        else
            return 1;
    }

}
