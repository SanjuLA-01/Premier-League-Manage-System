import java.io.IOException;

//interface
public interface LeagueManager {
    void addFootballClub ();
    void deleteFootballClub(String clubName);
    void printFootballClubStatics(String clubName);
    void printPremierLeagueTable();
    void updateMatch();
    void saveDetails(String fileName, String file1Name) throws IOException;
    void loadDetails (String fileName, String file1Name) throws IOException,ClassNotFoundException;
}
