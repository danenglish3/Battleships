package battleships_2.pkg0;

/**
 * The grid class describes how a grid is laid out.  For the purpose of this assignment,
 * the grid size has been determined to be 10 x 10.
 * @author Daniel
 */
public class Grid {
    private static final int GRIDHEIGHT = 10;
    private static final int GRIDWIDTH = 10;

    GridPositionInformation grid[][] = new GridPositionInformation[GRIDHEIGHT][GRIDWIDTH];
    
    /**
     * setBoatPositions will set a position in the grid to the specified boat identifier.
     * This identifier is unique to each boat in a fleet and can be found in each corresponding class.
     * @param height height in grid to set boat 
     * @param width width in grid to set boat
     * @param boatIdentifier which boat to set. e.g 'A' for aircraft carrier
     */
    public void setBoatPositions(int height, int width, String boatIdentifier) {
        grid[height][width].boatIdentifier = boatIdentifier;
        grid[height][width].occupied = true;
    }
    
    public String getId(int height,int width){
        return grid[height][width].boatIdentifier;
    }
    public boolean getIsHit(int height, int width){
        return grid[height][width].isHit;
    }
    public void setIsHit(int height, int width){
        grid[height][width].isHit = true;
    }
    
    /**
     * checkIfOcupied will check if a particular grid space is occupied 
     * @param height height position to check
     * @param width width position to check
     * @return true if position is occupied, false if not
     */
    public boolean checkIfOccupied(int height, int width) {
        if (this.grid[height][width].occupied == true && !this.grid[height][width].boatIdentifier.equals(" ")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * setEmptyGrid is called once each new grid has been defined. This will set each
     * position to a default position, which is a default constructor from the
     * GridPositionInformaion class
     */
    public void setEmptyGrid() {
        for (int k = 0; k < GRIDHEIGHT; k++) {
            for (int j = 0; j < GRIDWIDTH; j++) {
                grid[k][j] = new GridPositionInformation();
            }
        }
    }
    
    /**
     * This method is used for testing purposes, and will print out a visual representation
     * of the grid called to the output
     */
    public void drawGrid() {
        for (char c = 'A'; c < 'K'; c++) {
            if (c == 'A') {
                System.out.print("    ");
            }
            System.out.print(c + "  ");
        }
        System.out.print("\n");
        for (int i = 0; i < GRIDHEIGHT; i++) {
            if (i != GRIDHEIGHT - 1) {
                System.out.print((i + 1) + "  ");
            } else {
                System.out.print((i + 1) + " ");
            }
            for (int j = 0; j < GRIDWIDTH; j++) {
                System.out.print("[" + grid[i][j].boatIdentifier + "]");
            }
            System.out.print("\n");
        }
    }
}
