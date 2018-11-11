package battleships_2.Boats;

/**
 * The Destroyer class holds information regarding a destroyer in the game.
 * It follows the board game standards, by using a SIZE of of 2 spaces.
 * @author Daniel
 */
public class Destroyer {
    static final int SIZE = 2;
    private int QUANTITY = 2;
    public static final char IDENTIFIER = 'D';
    private int hitmarks = 0;

    /**
     * @return the SIZE
     */
    public int getSIZE() {
        return Destroyer.SIZE;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return this.QUANTITY;
    }
    
    /**
     * @param quantity the Quantity to set to
     */
    public void setQuantity(int quantity){
        this.QUANTITY = quantity;
    }
    
    @Override
    public String toString(){
        return "Destroyer";
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
