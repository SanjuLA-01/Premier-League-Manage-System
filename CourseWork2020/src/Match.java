import java.io.Serializable;
import java.util.Objects;

public class Match implements Serializable {

    private FootballClub team1; //use to two teams because two teams play one match at one time
    private FootballClub team2;
    private Date date;
    private int team1Goals; //scored goals of team 1
    private int team2Goals; //scored goals of team 2

    //default constructor
    public Match(){
    }

    //argument constructor
    public Match(FootballClub team1, FootballClub team2, Date date, int team1Goals, int team2Goals) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.team1Goals = team1Goals;
        this.team2Goals = team2Goals;
    }

    public String getTeam1() {
        return team1.getClubName();
    }

    public void setTeam1(FootballClub team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2.getClubName();
    }

    public void setTeam2(FootballClub team2) {
        this.team2 = team2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTeam1Goals() {
        return team1Goals;
    }

    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public int getTeam2Goals() {
        return team2Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return team1Goals == match.team1Goals &&
                team2Goals == match.team2Goals &&
                Objects.equals(team1, match.team1) &&
                Objects.equals(team2, match.team2) &&
                Objects.equals(date, match.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team1, team2, date, team1Goals, team2Goals);
    }

    @Override
    public String toString() {
        return "Team 1 ==> " + team1.getClubName() +
                ", Team 2 ==> " + team2.getClubName() +
                ", Date ==> " + date +
                ", Team 1 Scored Goals ==> " + team1Goals +
                ", Team 2 Scored Goals ==> " + team2Goals ;
    }
}
