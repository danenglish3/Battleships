package battleships_2.Boats;

/**
 * The Cruiser class holds information regarding a cruiser boat in the game.
 * It follows the board game standards, by using a size of of 3 spaces.
 * @author Daniel
 */
public class Cruiser {

    static final int SIZE = 3;
    private int QUANTITY = 1;
    public static final char IDENTIFIER = 'C';
    private int hitmarks = 0;

    /**
     * @return the SIZE 
     */
    public int getSize() {
        return Cruiser.SIZE;
    }
    
    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return this.QUANTITY;
    }
    
    /**
     * @param quantity the quantity to set to
     */
    public void setQuantity(int quantity){
        this.QUANTITY = quantity;
    }
    
    @Override
    public String toString(){
        return "Cruiser";
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
