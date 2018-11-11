package battleships_2.pkg0;

/**
 * This class holds information on each grid placement in the grid array.
 * It holds whether the grid position is currently occupied, and also what it
 * is occupied with (if it is occupied).
 * @author Daniel
 */
public class GridPositionInformation {
    
    public boolean occupied;
    public String boatIdentifier;
    public boolean isHit;
    /**
     * GridPositionInformation constructor. No method overriding needs to occur as this is the
     * only instance of the object that should be created.
     */
    public GridPositionInformation(){
        this.occupied = false;
        this.boatIdentifier = " ";
        this.isHit = false;
    }
    
}
