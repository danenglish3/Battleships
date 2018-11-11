package battleships_2.pkg0;
/**
 *This class holds statistics about the player. It gets this information originally
 * from a text file "PlayerStats.txt".
 * @author Daniel
 */
public class PlayerStats {
    
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
        
    public PlayerStats(){}
    
    public PlayerStats(String name){
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
    } 

    /**
     * @return the gamesPlayed
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * @param gamesPlayed the gamesPlayed to set
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * @return the gamesWon
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * @param gamesWon the gamesWon to set
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * @return the gamesLost
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * @param gamesLost the gamesLost to set
     */
    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
}
