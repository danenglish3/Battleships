package battleships_2.Boats;

/**
 * The BattleShip class holds information regarding a battleship in the game.
 * It follows the board game standards, by using a size of of 4 spaces.
 * @author Daniel
 */
public class BattleShip {

    static final int SIZE = 4;
    private int QUANTITY = 1;
    public static final char IDENTIFIER = 'B';
    private int hitmarks = 0;


    /**
     * @return the SIZE 
     */
    public int getSize() {
        return BattleShip.SIZE;
    }
    
    /**
     * @return the QUANTITY
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
        return "BattleShip";
    }

    /**
     * @return the hitmarks
     */
    public int getHitmarks() {
        return hitmarks;
    }

    /**
     * @param hitmarks the Hitmarks to set
     */
    public void setHitmarks(int hitmarks) {
        this.hitmarks = hitmarks;
    }
}
