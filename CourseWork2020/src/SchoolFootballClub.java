import java.util.Objects;

public class SchoolFootballClub extends FootballClub{
    private String schoolName; //name of the school

    //default constructor
    public SchoolFootballClub(){
    }

    //argument constructor
    public SchoolFootballClub(String clubName, String location, int wins, int draws, int defeats, int receivedGoals, int scoredGoals, int numberOfPoints, int numberOfMatches, String schoolName) {
        super(clubName, location, wins, draws, defeats, receivedGoals, scoredGoals, numberOfPoints, numberOfMatches);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return  schoolName;
    }

    public void setSchoolName (String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School Football Club ----> {" + super.toString()+
                ", School Name= '" + schoolName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return Objects.equals(schoolName, that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolName);
    }
}
