package battleships_2.pkg0;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GridPanel describes a new component which is used in the program. It is a JPanel which 
 * hold a 10 x 10 grid of buttons
 * @author Daniel
 */
public class GridPanel extends JPanel{
    public static Point h;

    //2D grid array of button
    public  JButton[][] buttonGrid;
    //Holds a reference to the other players grid
    public static Grid playerGrid;
    public static Grid compGrid;
        
    public GridPanel(Grid grid, int id){
       buttonGrid = new JButton[10][10];
        if(id == 1){
            GridPanel.playerGrid = grid;
            GridPanel.compGrid = null;
            initComponents();
        }
        else
        {
            GridPanel.compGrid = grid;
            GridPanel.playerGrid = null;
            initCompComponents();
        }
    }
    
    /**
     * reset Components is called when the user wants to play again, or wants to change their grid
     * @param grid the new grid to be changed to
     * @param id id tag for if computer or player grid
     */
    public void resetComponents(Grid grid, int id){
            if(id == 1){
            GridPanel.playerGrid = grid;
            resetPlayerComponents();            
        }
        else
        {
            GridPanel.compGrid = grid;
            resetCompComponents();            
        }    
    }
    
    /**
     * resetPlayer components will set the player grid panel to the newly created grid design
     */
    public void resetPlayerComponents(){
        JButton defaultColorButtton = new JButton();
            for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j++){
                if ((playerGrid.checkIfOccupied(i, j))) {
                    buttonGrid[i][j].setBackground(Color.green);
                    buttonGrid[i][j].setContentAreaFilled(false);
                    buttonGrid[i][j].setOpaque(true);
                    if(playerGrid.getIsHit(i, j)){
                    buttonGrid[i][j].setBackground(Color.red);
                    buttonGrid[i][j].setContentAreaFilled(false);
                    buttonGrid[i][j].setOpaque(true);             
                    }
                }
                else {
                    buttonGrid[i][j].setBackground(defaultColorButtton.getBackground());
                    buttonGrid[i][j].setContentAreaFilled(true);
                    buttonGrid[i][j].setOpaque(false);
                }
                buttonGrid[i][j].setActionCommand("player");                
                buttonGrid[i][j].addActionListener(actionListener);
            }
        }    
    }
    
    /**
     * resetCompComponents change the computers button panel back to default
     */
    public void resetCompComponents(){
        JButton defaultColorButtton = new JButton();
        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j++){
                buttonGrid[i][j].setBackground(defaultColorButtton.getBackground());
                buttonGrid[i][j].setOpaque(false);
                buttonGrid[i][j].setContentAreaFilled(true);
                buttonGrid[i][j].setActionCommand("comp");    
            }
        }    
    }
    
    /**
     * initComponents is what is called at the start of creating a new grid panel, and will set each
     * button to for its intended purpose.
     * It also holds the actionListener which is added to each button
     */
    private void initComponents() {
        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j++){
                buttonGrid[i][j] = new JButton();
                if ((playerGrid.checkIfOccupied(i, j))) {
                    buttonGrid[i][j].setBackground(Color.green);
                    buttonGrid[i][j].setContentAreaFilled(false);
                    buttonGrid[i][j].setOpaque(true);
                    if(playerGrid.getIsHit(i, j)){
                    buttonGrid[i][j].setBackground(Color.red);
                    buttonGrid[i][j].setContentAreaFilled(false);
                    buttonGrid[i][j].setOpaque(true);             
                    }
                }
                buttonGrid[i][j].setActionCommand("player");                
                buttonGrid[i][j].addActionListener(actionListener);
                add(buttonGrid[i][j]);
            }
        }
        setMinimumSize(new Dimension(250, 250));
        setPreferredSize(new Dimension(250, 250));
        setLayout(new GridLayout(10, 10, 0, 0));
    }
    
        ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //If the player wants to reset their grid, on the first turn
            if (actionEvent.getActionCommand().equals("player")) {
                if(BattleShips.player.turnsTaken == 0){
                    BattleShips.reset();
                }
            } 
            //If the user has clicked on the attacking grid
            else if (actionEvent.getActionCommand().equals("comp")) {
                BattleShips.player.turnsTaken++;
                //get the source button
                JButton temp = (JButton) actionEvent.getSource();
                //get the location of the button and use it to reference the grid array
                Point di = temp.getLocation();
                temp.setActionCommand("guessed");
                //If the attack hit
                if (BattleShips.comp.compGrid.checkIfOccupied(di.y / 25, di.x / 25)) {
                    BattleShips.comp.compFleet.piecesRemainingLessOne();
                    temp.setBackground(Color.red);
                    temp.setContentAreaFilled(false);
                    temp.setOpaque(true);
                } 
                //else the attack missed
                else {
                    temp.setBackground(Color.black);
                    temp.setContentAreaFilled(false);
                    temp.setOpaque(true);
                }
                try {
                    h = BattleShips.comp.compAttack();
                    simulateClick();
                } catch (IOException ex) {
                    Logger.getLogger(BattleShips.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           /**
            *This portion only happens then the game has finished, i.e either the play or the computer has won
            * It will open up a new prompt and ask the user if they want to continue
            **/
            Object[] options = {"Reset", "Close"};
            //If the computer has won
            if(BattleShips.player.playerFleet.getPiecesRemaining() == 0){
                int result = JOptionPane.showOptionDialog(null, "THE COMPUTER HAS DESTROYED ALL YOUR PIECES!\n","Prompt",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);
                if(result == JOptionPane.YES_OPTION){
                    BattleShips.reset();
                }
                BattleShips.tempList.get(BattleShips.elementOfPlayerInList).setGamesLost(BattleShips.tempList.get(BattleShips.elementOfPlayerInList).getGamesLost()+1);
            }
            //If the player has won
            else if(BattleShips.comp.compFleet.getPiecesRemaining() == 0){
                int result = JOptionPane.showOptionDialog(null, "YOU HAVE DESTROYED ALL THE COMPUTERS PIECES!\n","Prompt",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);                
                //JOptionPane.showMessageDialog(null, "YOU HAVE DESTROYED ALL THE COMPUTERS PIECES!\n");       
                    if(result == JOptionPane.YES_OPTION){
                    BattleShips.reset();
                }
                BattleShips.tempList.get(BattleShips.elementOfPlayerInList).setGamesWon(BattleShips.tempList.get(BattleShips.elementOfPlayerInList).getGamesWon()+1);
            }
        }
    };
    
    /**
     * simulateClick is called once the user has made a move. It will simulate the computers attack, and will
     * update the button grid as appropriate
     */
    public void simulateClick(){
        JButton t = BattleShips.getPlayersGrid().buttonGrid[h.y][h.x];
        if (BattleShips.player.playerGrid.checkIfOccupied(h.y, h.x)) {
            t.setBackground(Color.red);
            t.setContentAreaFilled(false);
            t.setOpaque(true);
        }
        else{
            t.setBackground(Color.black);
            t.setContentAreaFilled(false);
            t.setOpaque(true);            
        }
    }
    
    /**
     * initcompComponents is called at the start and will set up the computers button grid array.
     */
    private void initCompComponents() {
        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j++){
                buttonGrid[i][j] = new JButton();
                buttonGrid[i][j].addActionListener(actionListener);
                buttonGrid[i][j].setActionCommand("comp");    
                add(buttonGrid[i][j]);
            }
        }
        setMinimumSize(new Dimension(250, 250));
        setPreferredSize(new Dimension(250, 250));
        setLayout(new GridLayout(10, 10, 0, 0));    }

}
