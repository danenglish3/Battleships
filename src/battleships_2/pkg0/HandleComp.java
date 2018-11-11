package battleships_2.pkg0;

import static battleships_2.pkg0.BattleShips.comp;
import static battleships_2.pkg0.BattleShips.player;
import java.awt.Point;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Daniel
 */
public class HandleComp {

    Grid compGrid;
    Fleet compFleet;
    Grid compAttackReferenceGrid;

    private int totalGuesses;
    private int correctGuesses;

    protected int repeatIterations = 0;
    /**
     * Used to dictate which case to use in the
     * handleFollowOnAttack method Case 1 = Move up the grid Case 2 = Move right
     * Case 3 = Move down Case 4 = Move Left
     */
    protected int switchCase = 1;
    protected int previousHeight = -1;
    protected int previousWidth = -1;
    protected int originalHitHeight = -1;
    protected int originalHitWidth = -1;
    protected int followOnAttackFinalHeight = -1;
    protected int followOnAttackFinalWidth = -1;
    
    /**
     * Constructor for HandleComp, it will set up each grid and fleet
     */
    public HandleComp() {
        this.compGrid = new Grid();
        this.compAttackReferenceGrid = new Grid();
        this.compAttackReferenceGrid.setEmptyGrid();
        this.compFleet = new Fleet();
        this.compGrid.setEmptyGrid();
        setComputerGrid();
    }
    
    /**
     * setComputerGrid is the method responsible to setting up a computers grid at the start of the game.
     * The method uses random numbers to set the grid randomly each time this instance is called.
     */
    private void setComputerGrid() {
        Random rand = new Random();
        int height = rand.nextInt(10);
        int width = rand.nextInt(10);

        do {
            width = rand.nextInt(10);
            height = rand.nextInt(10);
        } while (compGrid.checkIfOccupied(height, width));

        int randBoundsControl = 0;
        int direction = (rand.nextInt(4) + randBoundsControl);
        char temp = 'A';
        compFleet.setFleetPiecesToPlace();
        while (!(compFleet.getFleetPiecesToPlace() == 0)) {
            //compGrid.drawGrid();
            String boatToPlace = Character.toString(temp);
            boolean placeBoat = false;

            switch (boatToPlace) {
                case "A":
                    // 0 up, 1 right, 2 down, 3 left
                    switch (direction) {
                        case 0:
                            for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                if (height - i < 0) {
                                    direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height - i, width)) {
                                        direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getAircraftCarrier().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                compFleet.getAircraftCarrier().setQuantity(compFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 1:
                            for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width + i)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getAircraftCarrier().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                compFleet.getAircraftCarrier().setQuantity(compFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 2:
                            for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height + i, width)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getAircraftCarrier().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                compFleet.getAircraftCarrier().setQuantity(compFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 3:
                            for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                if (width - i < 0) {
                                    direction = 0;
                                    width = rand.nextInt(10);
                                    height = rand.nextInt(10);
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width - i)) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getAircraftCarrier().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getAircraftCarrier().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height, width - i, boatToPlace);
                                }
                                compFleet.getAircraftCarrier().setQuantity(compFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                    }
                    direction = (rand.nextInt(4) + randBoundsControl);
                    width = rand.nextInt(10);
                    height = rand.nextInt(10);
                    break;
                case "B":
                    // 0 up, 1 right, 2 down, 3 left
                    switch (direction) {
                        case 0:
                            for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                if (height - i < 0) {
                                    direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height - i, width)) {
                                        direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getBattleShip().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                    compGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                compFleet.getBattleShip().setQuantity(compFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 1:
                            for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width + i)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getBattleShip().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                    compGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                compFleet.getBattleShip().setQuantity(compFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 2:
                            for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height + i, width)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getBattleShip().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                    compGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                compFleet.getBattleShip().setQuantity(compFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 3:
                            for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                if (width - i < 0) {
                                    direction = 0;
                                    width = rand.nextInt(10);
                                    height = rand.nextInt(10);
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width - i)) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getBattleShip().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getBattleShip().getSize(); i++) {
                                    compGrid.setBoatPositions(height, width - i, boatToPlace);
                                }
                                compFleet.getBattleShip().setQuantity(compFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                    }
                    direction = (rand.nextInt(4) + randBoundsControl);
                    width = rand.nextInt(10);
                    height = rand.nextInt(10);
                    break;
                case "C":
                    // 0 up, 1 right, 2 down, 3 left
                    switch (direction) {
                        case 0:
                            for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                if (height - i < 0) {
                                    direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height - i, width)) {
                                        direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                    compGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                compFleet.getCruiser().setQuantity(compFleet.getCruiser().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 1:
                            for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width + i)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                    compGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                compFleet.getCruiser().setQuantity(compFleet.getCruiser().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 2:
                            for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height + i, width)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                    compGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                compFleet.getCruiser().setQuantity(compFleet.getCruiser().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;

                        case 3:
                            for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                if (width - i < 0) {
                                    direction = 0;
                                    width = rand.nextInt(10);
                                    height = rand.nextInt(10);
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width - i)) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getCruiser().getSize(); i++) {
                                    compGrid.setBoatPositions(height, width - i, boatToPlace);
                                }
                                compFleet.getCruiser().setQuantity(compFleet.getCruiser().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                    }
                    direction = (rand.nextInt(4) + randBoundsControl);
                    width = rand.nextInt(10);
                    height = rand.nextInt(10);
                    break;

                case "D":
                    // 0 up, 1 right, 2 down, 3 left
                    switch (direction) {
                        case 0:
                            for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                if (height - i < 0) {
                                    direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height - i, width)) {
                                        direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getDestroyer().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                compFleet.getDestroyer().setQuantity(compFleet.getDestroyer().getQuantity() - 1);
                                if (compFleet.getDestroyer().getQuantity() == 0) {
                                    temp = 'S';
                                }
                                placeBoat = false;
                            }
                            break;

                        case 1:
                            for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width + i)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getDestroyer().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                compFleet.getDestroyer().setQuantity(compFleet.getDestroyer().getQuantity() - 1);
                                if (compFleet.getDestroyer().getQuantity() == 0) {
                                    temp = 'S';
                                }
                                placeBoat = false;
                            }
                            break;

                        case 2:
                            for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height + i, width)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getDestroyer().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                compFleet.getDestroyer().setQuantity(compFleet.getDestroyer().getQuantity() - 1);
                                if (compFleet.getDestroyer().getQuantity() == 0) {
                                    temp = 'S';
                                }
                                placeBoat = false;
                            }
                            break;

                        case 3:
                            for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                if (width - i < 0) {
                                    direction = 0;
                                    width = rand.nextInt(10);
                                    height = rand.nextInt(10);
                                    break;
                                } else {
                                    if (this.compGrid.checkIfOccupied(height, width - i)) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    }
                                }
                                if (i == (this.compFleet.getDestroyer().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.compFleet.getDestroyer().getSIZE(); i++) {
                                    compGrid.setBoatPositions(height, width - i, boatToPlace);
                                }
                                compFleet.getDestroyer().setQuantity(compFleet.getDestroyer().getQuantity() - 1);
                                if (compFleet.getDestroyer().getQuantity() == 0) {
                                    temp = 'S';
                                }
                                placeBoat = false;
                            }
                            break;
                    }
                    direction = (rand.nextInt(4) + randBoundsControl);
                    width = rand.nextInt(10);
                    height = rand.nextInt(10);
                    break;
                case "S":
                    if (!this.compGrid.checkIfOccupied(height, width)) {
                        compGrid.setBoatPositions(height, width, boatToPlace);
                        compFleet.getSubmarine().setQuantity(compFleet.getSubmarine().getQuantity() - 1);
                    }
                    width = rand.nextInt(10);
                    height = rand.nextInt(10);
                    break;

            }
            compFleet.setFleetPiecesToPlace();
        }

    }

    /**
     * compAttack is the controlling method of the HandleComp class.
     * This contains the randomized integers for the computers turn, and 
     * uses the rest of the class methods to complete the computers turn.
     * @return a point value pointing to which button to update
     */
    Point compAttack() throws IOException {

        Random rand = new Random();
        int height = rand.nextInt(10);
        int width = rand.nextInt(10);

        do {
            width = rand.nextInt(10);
            height = rand.nextInt(10);
        } while (comp.compAttackReferenceGrid.checkIfOccupied(height, width));

        boolean compTurn = true;

        if (repeatIterations == 0) {
            checkIfBoatHit(height, width);
            compTurn = false;
            previousHeight = height;
            previousWidth = width;
            originalHitHeight = height;
            originalHitWidth = width;
            //printOutComputerGuess(height,width);
        } else if (repeatIterations > 0 && repeatIterations < 6 && comp.getCorrectGuesses() > 0 && compTurn) {
            handleFollowOnAttack();
            height = followOnAttackFinalHeight;
            width = followOnAttackFinalWidth;
            compTurn = false;
            //printOutComputerGuess(height,width);            
        }
        return new Point(width, height);
    }
    
    /**
     * checkIfBoatHit method uses the 2 randomized numbers to check if the corresponding value in the
     * array grid is occupied.
     * @param height y value to check for in the array
     * @param width x value to check for in the array
     * @return boolean value on whether a boat was hit or not
     */
    boolean checkIfBoatHit(int height, int width) throws IOException {
        //if the computer missed
        if (player.playerGrid.checkIfOccupied(height, width) == false) {
            comp.compAttackReferenceGrid.setBoatPositions(height, width, "/");
            comp.setTotalGuesses(comp.getTotalGuesses() + 1);
            return false;
        } 
        //else the computer has hit
        else {
            //update reference grid
            comp.compAttackReferenceGrid.setBoatPositions(height, width, "X");
            //set players grid to hit
            player.playerGrid.setIsHit(height, width);
            //update correct guesses
            comp.setCorrectGuesses(comp.getCorrectGuesses() + 1);
            //Minus 1 from players fleet
            player.playerFleet.piecesRemainingLessOne();
            //update boat hitpoints
            updateBoatHit(height, width);
            //add 1 to total guesses
            comp.setTotalGuesses(comp.getTotalGuesses() + 1);
            //If this is the first case of a boat hitting, update iteration by 1.
            //This will mean followonattack will be called nect turn
            if (repeatIterations == 0) {
                repeatIterations++;
            }
            trackBoatsHit(player.playerGrid.grid[height][width].boatIdentifier);
            return true;
        }
    }

    /**
     * This method is called upon a successful hit made by the computer. It will
     * update the hit marks of the appropriate boat hit corresponding to the
     * passed grid position values.
     *
     * @param height y value of the grid array to check
     * @param width x value of the grid array to check
     */
    void updateBoatHit(int height, int width) {
        switch (player.playerGrid.grid[height][width].boatIdentifier) {
            case "A":
                player.playerFleet.getAircraftCarrier().setHitmarks(player.playerFleet.getAircraftCarrier().getHitmarks() + 1);
                break;
            case "B":
                player.playerFleet.getBattleShip().setHitmarks(player.playerFleet.getBattleShip().getHitmarks() + 1);
                break;
            case "C":
                player.playerFleet.getCruiser().setHitmarks(player.playerFleet.getCruiser().getHitmarks() + 1);
                break;
            case "D":
                player.playerFleet.getDestroyer().setHitmarks(player.playerFleet.getDestroyer().getHitmarks() + 1);
                break;
            case "S":
                player.playerFleet.getSubmarine().setHitmarks(player.playerFleet.getSubmarine().getHitmarks() + 1);
                break;
            default:
                break;
        }
    }
    
    /**
     * trackBoatsHit method is used to track how many times a certain boat has been hit by the computer.
     * Once the HitMarks variable has reached the size of the boat, a message will be displayed
     * saying the computer has destroyed one of the players boats. 
     * @param boatIdentifier Used to update the players boat hit marks
     */
    void trackBoatsHit(String boatIdentifier) {
        switch (boatIdentifier) {
            case "A":
                if (player.playerFleet.getAircraftCarrier().getHitmarks() == player.playerFleet.getAircraftCarrier().getSIZE()) {
                    //BattleShips.getMessgeBox().setText("The computer has destroyed your AircraftCarrier!");                    
                   // System.out.println("The computer has destroyed your AircraftCarrier!");
                    repeatIterations = 0;
                    switchCase = 1;
                    break;
                } else {
                    break;
                }
            case "B":
                if (player.playerFleet.getBattleShip().getHitmarks() == player.playerFleet.getBattleShip().getSize()) {
                    //BattleShips.getMessgeBox().setText("The computer has destroyed your BattleShip!");
                    //System.out.println("The computer has destroyed your BattleShip!");
                    repeatIterations = 0;
                    switchCase = 1;
                    break;
                } else {
                    break;
                }
            case "C":
                if (player.playerFleet.getCruiser().getHitmarks() == player.playerFleet.getCruiser().getSize()) {
                    //BattleShips.getMessgeBox().setText("The computer has destroyed your Cruiser!");                   
                    //System.out.println("The computer has destroyed your Cruiser!");
                    repeatIterations = 0;
                    switchCase = 1;
                    break;
                } else {
                    break;
                }
            case "D":
                if (player.playerFleet.getDestroyer().getHitmarks() == player.playerFleet.getDestroyer().getSIZE()
                        || player.playerFleet.getDestroyer().getHitmarks() == (player.playerFleet.getDestroyer().getSIZE() * 2)) {
                    //BattleShips.getMessgeBox().setText("The computer has destroyed a Destroyer!");                   
                    //System.out.println("The computer has destroyed a Destroyer!");
                    repeatIterations = 0;
                    switchCase = 1;
                    break;
                } else {
                    break;
                }
            case "S":
                if (player.playerFleet.getSubmarine().getHitmarks() == player.playerFleet.getSubmarine().getSIZE()
                        || player.playerFleet.getDestroyer().getHitmarks() == (player.playerFleet.getDestroyer().getSIZE() * 2)) {
                    //BattleShips.getMessgeBox().setText("The computer has destroyed a Submarine!");                   
                    //System.out.println("The computer has destroyed a Submarine!");
                    repeatIterations = 0;
                    break;
                } else {
                    break;
                }
        }

    }

    /**
     * HandleFollowOnAttack is called on the following turn once the computer has made a
     * successful attack. It uses the previous hit values to move around that
     * position and try to find another hit. This is done by using the
     * switchCase variable.
     */
    void handleFollowOnAttack() throws IOException {

        int height = previousHeight;
        int width = previousWidth;

        /**
         * NOTE case fall through. IF on the first iteration through, (where 1
         * is subtracted from the height). If this goes out of the bound of the
         * grid array, past 0, then case 0 will fall through to case 1, and so
         * on.
         */
        switch (switchCase) {
            //Check to the right of the previous grid position
            case 1:
                --height;
                //Check that the new value is still within the bounds of the array
                if (height > -1) {
                    //Check if the new position has made a successfull attack
                    if (checkIfBoatHit(height, width)) {
                        //Check that the trackBoatHit method called within the CheckIfBoatHit method hasnt changed
                        //repeatIterations to 0.
                        if (repeatIterations != 0) {
                            //Update repeatIterations if the computer needs to do another pass(right) on the next turn
                            repeatIterations++;
                        }
                        //Upon successfull hit, set the previous height to the new height that was hit
                        previousHeight = height;
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;

                    } else {
                        //If the attack missed, then change the switchcase variable to the next case and set the variables
                        //back to the original values
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;
                        switchCase = 2;
                        previousHeight = originalHitHeight;
                        previousWidth = originalHitWidth;
                        height = originalHitHeight;
                    }
                    break;
                } //If the new value wasn't within the bounds of the array
                else {
                    //set the switchcase to the next value, and DONT break from the switch statement
                    //NOTE: swtich fallthrough is happenining here
                    followOnAttackFinalHeight = height;
                    followOnAttackFinalWidth = width;
                    switchCase = 2;
                    previousHeight = originalHitHeight;
                    previousWidth = originalHitWidth;
                    height = originalHitHeight;
                }
            //Check to the right of the original hit
            case 2:
                ++width;
                if (width < 10) {
                    if (checkIfBoatHit(height, width)) {
                        if (repeatIterations != 0) {
                            repeatIterations++;
                        }
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;
                        previousWidth = width;
                    } else {
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;
                        width = originalHitWidth;
                        switchCase = 3;
                        previousHeight = originalHitHeight;
                        previousWidth = originalHitWidth;
                    }
                    break;
                } else {
                    followOnAttackFinalHeight = height;
                    followOnAttackFinalWidth = width;
                    width = originalHitWidth;
                    switchCase = 3;
                    previousHeight = originalHitHeight;
                    previousWidth = originalHitWidth;
                }
            //Check underneath the original hit position    
            case 3:
                ++height;
                if (height < 10) {
                    if (checkIfBoatHit(height, width)) {
                        if (repeatIterations != 0) {
                            repeatIterations++;
                        }
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;
                        previousHeight = height;
                    } else {
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;
                        height = originalHitHeight;
                        switchCase = 4;
                        previousHeight = originalHitHeight;
                        previousWidth = originalHitWidth;
                    }
                    break;
                } else {
                    followOnAttackFinalHeight = height;
                    followOnAttackFinalWidth = width;
                    height = originalHitHeight;
                    switchCase = 4;
                    previousHeight = originalHitHeight;
                    previousWidth = originalHitWidth;
                }
            //Check to the left of the original hit position    
            case 4:
                --width;
                if (width > -1) {
                    if (checkIfBoatHit(height, width)) {
                        if (repeatIterations != 0) {
                            repeatIterations++;
                        }
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;
                        previousWidth = width;
                    } else {
                        followOnAttackFinalHeight = height;
                        followOnAttackFinalWidth = width;
                        width = originalHitWidth;
                        switchCase = 0;
                    }
                    break;
                } else {
                    followOnAttackFinalHeight = height;
                    followOnAttackFinalWidth = width;
                    width = originalHitWidth;
                    switchCase = 0;
                }
            //If the computer can go no further, the repeatIterations is set to 0, and handleFollowOnAttack()
            //wont be called on the next pass.
            default:
                repeatIterations = 0;
                break;
        }
    }

    /**
     * @return the totalGuesses
     */
    public int getTotalGuesses() {
        return totalGuesses;
    }

    /**
     * @param totalGuesses the totalGuesses to set
     */
    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    /**
     * @return the correctGuesses
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }

    /**
     * @param correctGuesses the correctGuesses to set
     */
    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

}
