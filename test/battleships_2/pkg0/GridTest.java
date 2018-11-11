package battleships_2.pkg0;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * GridTest holds unit tests responsible for testing the Grid class
 * @author Daniel
 */
public class GridTest {

    /**
     * Test of setBoatPositions method, of class Grid.
     * Create a new grid object, set a random position to a submarine ("S").
     * check if position has been changed.
     */
    @Test
    public void testSetBoatPositions(){
        System.out.println("setBoatPositions");
        Grid instance = new Grid();
        instance.setEmptyGrid();
        
        instance.setBoatPositions(6, 7, "S");
        
        boolean result = instance.grid[6][7].boatIdentifier.equals("S");
 
        assertEquals(result, true);
    }
    
    /**
     * Test of checkIfOccupied method, of class Grid.
     * Create a new grid instance, set it to an empty grid.
     * Assign height and width an integer value between 0 - 9.
     * Set that space to occupied. 
     * Test to see if it comes back occupied.
     */
    @Test
    public void testCheckIfOccupied() {
        System.out.println("checkIfOccupied");
        int height = 3;
        int width = 4;
        Grid instance = new Grid();
        instance.setEmptyGrid();
        instance.grid[height][width].occupied = true;
        instance.grid[height][width].boatIdentifier = "A";
        boolean expResult = true;
        boolean result = instance.checkIfOccupied(height, width);
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmptyGrid method, of class Grid.
     * Ensure the method sets each element to unoccupied
     */
    @Test
    public void testSetEmptyGrid() {
        System.out.println("setEmptyGrid");
        Grid instance = new Grid();
        instance.setEmptyGrid();
        
        boolean temp = instance.grid[5][5].occupied;
        assertEquals(!temp,true);
    }

}
