import java.util.Objects;

public class UniversityFootballClub extends FootballClub{
    private String universityName; //name of the university

    //default constructor
    public UniversityFootballClub(){
    }

    //argument constructor
    public UniversityFootballClub(String clubName, String location, int wins, int draws, int defeats, int receivedGoals, int scoredGoals, int numberOfPoints, int numberOfMatches, String universityName) {
        super(clubName, location, wins, draws, defeats, receivedGoals, scoredGoals, numberOfPoints, numberOfMatches);
        this.universityName = universityName;
    }


    public String getUniversityName() {
        return  universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName =universityName;
    }

    @Override
    public String toString() {
        return "University Football Club ----> {" +super.toString()+
                ", University Name= '" + universityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return Objects.equals(universityName, that.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), universityName);
    }
}
