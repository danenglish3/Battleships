package battleships_2.pkg0;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * FleetTest hold unit tests responsible for testing the Fleet class
 * @author Daniel
 */
public class FleetTest {
    
    /**
     * Test of getPiecesRemaining method, of class Fleet.
     * Through-out the program the computer or user will destroy each others pieces one by one,
     * this method will mock a hit, remove 1 from pieces remaining and expect 18 - 1 which is 17.
     * 
     */
    @Test
    public void testGetPiecesRemaining() {
        System.out.println("getPiecesRemaining");
        Fleet instance = new Fleet();
        int expResult = 17;
        instance.piecesRemainingLessOne();
        int result = instance.getPiecesRemaining();
        assertEquals(expResult, result);
    }
}
