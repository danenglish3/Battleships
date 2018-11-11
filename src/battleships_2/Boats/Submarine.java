package battleships_2.Boats;

/**
 * The Submarine class holds information regarding a submarine in the game.
 * It follows the board game standards, by using a SIZE of of 1 space.
 * @author Daniel
 */
public class Submarine {
    private final int SIZE = 1;
    private int QUANTITY = 2;
    public static final char IDENTIFIER = 'S';
    private int hitmarks = 0;

    /**
     * @return the SIZE 
     */
    public int getSIZE()
    {
        return this.SIZE;
    }
    
    /**
     * @return the Quantity 
     */
    public int getQuantity()
    {
        return this.QUANTITY;
    }
    
    /**
     * @param quantity the quantity to be set to
     */
    public void setQuantity(int quantity){
        this.QUANTITY = quantity;
    }
    
    @Override
    public String toString(){
        return "Submarine";
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
