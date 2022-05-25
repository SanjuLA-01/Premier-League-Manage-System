import java.io.Serializable;
import java.util.Objects;


public abstract class SportsClub implements Serializable {
    private String clubName; // name of the club
    private String location; // location of the club
    private String sportType; //most sports clubs not only for football

    //default constructor
    public SportsClub(){
    }

    //made this constructor for future uses
    public SportsClub(String clubName, String location, String sportType) {
       this.clubName= clubName;
       this.location = location;
       this.sportType = sportType;
    }

    //argument constructor to make obj without sports type
    public SportsClub(String clubName, String location){
        this.clubName=clubName;
        this.location=location;
    }



    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    @Override
    public String toString() {
        return
                "Club Name ==> " + clubName + '\'' +
                ", Location ==> " + location + '\'' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubName, that.clubName) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, location);
    }

}
