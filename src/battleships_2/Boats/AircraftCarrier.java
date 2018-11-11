package battleships_2.Boats;

/**
 * The AircraftCarrier class holds information regarding an aircraft carrier in the game.
 * It follows the board game standards, by using a size of of 5 spaces.
 * @author Daniel 
 */
public class AircraftCarrier {
    private final int SIZE = 5;
    private int Quantity = 1;
    public static final char IDENTIFIER = 'A';
    private int hitmarks = 0;

    /**
     * @return the SIZE
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return Quantity;
    }   
    public void setQuantity(int quantity){
        this.Quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Aircraft Carrier";
    }

    /**
     * @return the hitmarks
     */
    public int getHitmarks() {
        return hitmarks;
    }

    /**
     * @param hitmarks the hitmarks to set
     */
    public void setHitmarks(int hitmarks) {
        this.hitmarks = hitmarks;
    }
}
