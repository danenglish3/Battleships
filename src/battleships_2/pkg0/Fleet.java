package battleships_2.pkg0;

import battleships_2.Boats.Destroyer;
import battleships_2.Boats.AircraftCarrier;
import battleships_2.Boats.Submarine;
import battleships_2.Boats.BattleShip;
import battleships_2.Boats.Cruiser;

/**
 * This Fleet class holds all the information regarding a player or computers fleet.
 * It uses classes held in the Boats folder to gain information on each.
 * @author Daniel
 */
public class Fleet {

    /**
     * @return the currentHitMarks
     */
    public int getCurrentHitMarks() {
        return currentHitMarks;
    }

    /**
     * @param currentHitMarks the currentHitMarks to set
     */
    public void setCurrentHitMarks(int currentHitMarks) {
        this.currentHitMarks = this.aircraftCarrier.getHitmarks() + this.battleShip.getHitmarks() + this.cruiser.getHitmarks() +
                this.destroyer.getHitmarks() + this.submarine.getHitmarks();
    }

    private AircraftCarrier aircraftCarrier = new AircraftCarrier();
    private BattleShip battleShip = new BattleShip();
    private Cruiser cruiser = new Cruiser();
    private Destroyer destroyer = new Destroyer();
    private Submarine submarine = new Submarine();
    private final int totalHitMarks = (this.aircraftCarrier.getSIZE()) + 
            (this.battleShip.getSize()) + (this.cruiser.getSize()) + 
            (this.destroyer.getSIZE() * this.destroyer.getQuantity()) + (this.submarine.getSIZE() + this.submarine.getQuantity());
    private int currentHitMarks;
    
    /**
     * pieces remaining is used to track how many pieces are left, once destroyed
     */
    private int piecesRemaining = (this.aircraftCarrier.getSIZE()*this.getAircraftCarrier().getQuantity()) + 
            (this.battleShip.getSize()*this.battleShip.getQuantity()) + (this.cruiser.getSize()*this.cruiser.getQuantity()) + 
            (this.destroyer.getSIZE()*this.destroyer.getQuantity()) + (this.submarine.getSIZE()*this.submarine.getQuantity());
    private int fleetPiecesToPlace;

    /**
     * @return the piecesRemaining
     */
    public int getPiecesRemaining() {
        return piecesRemaining;
    }

    /**
     * Removes 1 from the piecesRemaining variable
     */
    public void piecesRemainingLessOne() {
        this.piecesRemaining--;
    }

     /**
     * @return the fleetPiecesToPlace
     */
    public int getFleetPiecesToPlace() {
        return fleetPiecesToPlace;
    }

    /**
     * sets the fleetpiecesremaining variable
     */
    public void setFleetPiecesToPlace() {
        this.fleetPiecesToPlace = this.aircraftCarrier.getQuantity() + this.battleShip.getQuantity()+
            this.cruiser.getQuantity() + this.destroyer.getQuantity() + this.submarine.getQuantity();
    }
    
    public void overrideFleetPiecesToPlace(int value){
        this.fleetPiecesToPlace = value;
    }
    /**
     * @return the battleShip
     */
    
    public BattleShip getBattleShip() {
        return battleShip;
    }

    /**
     * @param battleShip the battleShip to set
     */
    public void setBattleShip(BattleShip battleShip) {
        this.battleShip = battleShip;
    }

    /**
     * @return the cruiser
     */
    public Cruiser getCruiser() {
        return cruiser;
    }

    /**
     * @param cruiser the cruiser to set
     */
    public void setCruiser(Cruiser cruiser) {
        this.cruiser = cruiser;
    }

    /**
     * @return the destroyer
     */
    public Destroyer getDestroyer() {
        return destroyer;
    }

    /**
     * @param destroyer the destroyer to set
     */
    public void setDestroyer(Destroyer destroyer) {
        this.destroyer = destroyer;
    }

    /**
     * @return the submarine
     */
    public Submarine getSubmarine() {
        return submarine;
    }

    /**
     * @param submarine the submarine to set
     */
    public void setSubmarine(Submarine submarine) {
        this.submarine = submarine;
    }

    /**
     * @param aircraftCarrier the aircraftCarrier to set
     */
    public void setAircraftCarrier(AircraftCarrier aircraftCarrier) {
        this.aircraftCarrier = aircraftCarrier;
    }
    
    /**
     * @return the aircraftCarrier
     */
    public AircraftCarrier getAircraftCarrier() {
        return aircraftCarrier;
    }

    /**
     * @return the totalHitMarks
     */
    public int getTotalHitMarks() {
        return totalHitMarks;
    }
}
