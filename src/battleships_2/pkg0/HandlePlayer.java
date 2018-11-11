package battleships_2.pkg0;

import java.util.Random;

/**
 * HandlePlayer class hold all the information relative to a player
 * @author Daniel
 */
public class HandlePlayer {
    Grid playerGrid;
    Fleet playerFleet;
    int turnsTaken;
    
    public HandlePlayer(){
        this.turnsTaken = 0;
        this.playerGrid = new Grid();
        this.playerFleet = new Fleet();
        this.playerGrid.setEmptyGrid();
        setPlayerGrid();
    }
    
     /**
     * setPlayerGrid is the method responsible to setting up a players grid at the start of the game.
     * The method uses random numbers to set the grid randomly each time this instance is called.
     */
    private void setPlayerGrid(){
        Random rand = new Random();
        int height = rand.nextInt(10);
        int width = rand.nextInt(10);
        
        do{
            width = rand.nextInt(10);
            height= rand.nextInt(10);                  
        }while(playerGrid.checkIfOccupied(height, width));
        
        int randBoundsControl = 0;
        int direction = (rand.nextInt(4)+randBoundsControl);
        char temp = 'A'; 
        playerFleet.setFleetPiecesToPlace();
        while(!(playerFleet.getFleetPiecesToPlace() == 0)){
            //playerGrid.drawGrid();
            String boatToPlace = Character.toString(temp);
            boolean placeBoat = false;
            
            switch(boatToPlace){
                case "A":
                    // 0 up, 1 right, 2 down, 3 left
                    switch(direction){
                        case 0:
                            for(int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++){
                                if(height-i < 0)
                                {
                                    direction = (rand.nextInt(4)+(randBoundsControl+1));
                                    break;
                                }
                                else{
                                    if (this.playerGrid.checkIfOccupied(height - i, width)) {
                                        direction = (rand.nextInt(4)+(randBoundsControl+1));
                                        break;
                                    }                                    
                                }
                                if(i == (this.playerFleet.getAircraftCarrier().getSIZE()-1)){
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++) {
                                    playerGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                playerFleet.getAircraftCarrier().setQuantity(playerFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
 
                        case 1:
                            for (int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } 
                                else {
                                    if (this.playerGrid.checkIfOccupied(height, width+i)) {
                                    direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getAircraftCarrier().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++) {
                                    playerGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                playerFleet.getAircraftCarrier().setQuantity(playerFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                            
                        case 2:
                            for (int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height+i, width)) {
                                    direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getAircraftCarrier().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++) {
                                    playerGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                playerFleet.getAircraftCarrier().setQuantity(playerFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;      

                        case 3:
                            for (int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++) {
                                if (width - i < 0) {
                                    direction = 0;
                                    width = rand.nextInt(10);
                                    height = rand.nextInt(10);
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height, width - i)) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    }
                                }
                                if(i == (this.playerFleet.getAircraftCarrier().getSIZE()-1)){
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getAircraftCarrier().getSIZE(); i++) {
                                    playerGrid.setBoatPositions(height, width - i, boatToPlace);
                                }
                                playerFleet.getAircraftCarrier().setQuantity(playerFleet.getAircraftCarrier().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                    }
                    direction = (rand.nextInt(4)+randBoundsControl);
                    width = rand.nextInt(10);
                    height = rand.nextInt(10);
                    break;
                case "B":
                    // 0 up, 1 right, 2 down, 3 left
                    switch(direction){
                        case 0:
                            for(int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++){
                                if(height-i < 0)
                                {
                                    direction = (rand.nextInt(4)+(randBoundsControl+1));
                                    break;
                                }
                                else{
                                    if (this.playerGrid.checkIfOccupied(height - i, width)) {
                                    direction = (rand.nextInt(4)+(randBoundsControl+1));
                                        break;
                                    }                                    
                                }
                                if (i == (this.playerFleet.getBattleShip().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++) {
                                    playerGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                playerFleet.getBattleShip().setQuantity(playerFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
 
                        case 1:
                            for (int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height, width+i)) {
                                    direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getBattleShip().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++) {
                                    playerGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                playerFleet.getBattleShip().setQuantity(playerFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                            
                        case 2:
                            for (int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height+i, width)) {
                                    direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getBattleShip().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++) {
                                    playerGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                playerFleet.getBattleShip().setQuantity(playerFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;       

                        case 3:
                            for (int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++) {
                                if (width - i < 0) {
                                    direction = 0;
                                    width = rand.nextInt(10);
                                    height = rand.nextInt(10);
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height, width - i)) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    }
                                }
                                if(i == (this.playerFleet.getBattleShip().getSize()-1)){
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getBattleShip().getSize(); i++) {
                                    playerGrid.setBoatPositions(height, width-i, boatToPlace);
                                }
                                playerFleet.getBattleShip().setQuantity(playerFleet.getBattleShip().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                    }
                    direction = (rand.nextInt(4)+randBoundsControl);
                    width = rand.nextInt(10);
                    height = rand.nextInt(10);
                    break;
                case "C":
                    // 0 up, 1 right, 2 down, 3 left
                    switch(direction){
                        case 0:
                            for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                if (height - i < 0) {
                                    direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height - i, width)) {
                                        direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                    playerGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                playerFleet.getCruiser().setQuantity(playerFleet.getCruiser().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
 
                        case 1:
                            for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height, width+i)) {
                                    direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                    playerGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                playerFleet.getCruiser().setQuantity(playerFleet.getCruiser().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;
                            
                        case 2:
                            for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height+i, width)) {
                                    direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                    playerGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                playerFleet.getCruiser().setQuantity(playerFleet.getCruiser().getQuantity() - 1);
                                temp++;
                                placeBoat = false;
                            }
                            break;      

                        case 3:
                            for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                if (width - i < 0) {
                                    direction = 0;
                                    width = rand.nextInt(10);
                                    height = rand.nextInt(10);
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height, width - i)) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getCruiser().getSize() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getCruiser().getSize(); i++) {
                                    playerGrid.setBoatPositions(height, width - i, boatToPlace);
                                }
                                playerFleet.getCruiser().setQuantity(playerFleet.getCruiser().getQuantity() - 1);
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
                    switch(direction){
                        case 0:
                            for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                if (height - i < 0) {
                                    direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height - i, width)) {
                                        direction = (rand.nextInt(4) + (randBoundsControl + 1));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getDestroyer().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                    playerGrid.setBoatPositions(height - i, width, boatToPlace);
                                }
                                playerFleet.getDestroyer().setQuantity(playerFleet.getDestroyer().getQuantity() - 1);
                                if (playerFleet.getDestroyer().getQuantity() == 0) {
                                    temp = 'S';
                                }
                                placeBoat = false;
                            }
                            break;
 
                        case 1:
                            for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                if (width + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height, width+i)) {
                                    direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getDestroyer().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                    playerGrid.setBoatPositions(height, width + i, boatToPlace);
                                }
                                playerFleet.getDestroyer().setQuantity(playerFleet.getDestroyer().getQuantity() - 1);
                                if (playerFleet.getDestroyer().getQuantity() == 0) {
                                    temp = 'S';
                                }
                                placeBoat = false;
                            }
                            break;
                            
                        case 2:
                            for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                if (height + i > 9) {
                                    direction = (rand.nextInt(4));
                                    break;
                                } else {
                                    if (this.playerGrid.checkIfOccupied(height + i, width)) {
                                        direction = (rand.nextInt(4));
                                        break;
                                    }
                                }
                                if (i == (this.playerFleet.getDestroyer().getSIZE() - 1)) {
                                    placeBoat = true;
                                }
                            }
                            if (placeBoat) {
                                for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                    playerGrid.setBoatPositions(height + i, width, boatToPlace);
                                }
                                playerFleet.getDestroyer().setQuantity(playerFleet.getDestroyer().getQuantity() - 1);
                                if (playerFleet.getDestroyer().getQuantity() == 0) {
                                    temp = 'S';
                                }
                                placeBoat = false;
                            }
                            break;      

                            case 3:
                                for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                    if (width - i < 0) {
                                        direction = 0;
                                        width = rand.nextInt(10);
                                        height = rand.nextInt(10);
                                        break;
                                    } else {
                                        if (this.playerGrid.checkIfOccupied(height, width - i)) {
                                            direction = 0;
                                            width = rand.nextInt(10);
                                            height = rand.nextInt(10);
                                            break;
                                        }
                                    }
                                    if (i == (this.playerFleet.getDestroyer().getSIZE() - 1)) {
                                        placeBoat = true;
                                    }
                                }
                                if (placeBoat) {
                                    for (int i = 0; i < this.playerFleet.getDestroyer().getSIZE(); i++) {
                                        playerGrid.setBoatPositions(height, width - i, boatToPlace);
                                    }
                                    playerFleet.getDestroyer().setQuantity(playerFleet.getDestroyer().getQuantity() - 1);
                                    if (playerFleet.getDestroyer().getQuantity() == 0) {
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
                        if(!this.playerGrid.checkIfOccupied(height, width)){
                            playerGrid.setBoatPositions(height, width, boatToPlace);
                            playerFleet.getSubmarine().setQuantity(playerFleet.getSubmarine().getQuantity() - 1);
                        }
                            width = rand.nextInt(10);
                            height = rand.nextInt(10);
                            break;
                    
            }
            playerFleet.setFleetPiecesToPlace();
        }

    }
}
