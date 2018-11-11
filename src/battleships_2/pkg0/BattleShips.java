package battleships_2.pkg0;

//Import statements
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 * This is the main class of the program which holds the overall frame of the GUI
 * @author Daniel
 */
public class BattleShips extends JFrame{
    
    public BattleShips() throws IOException{
        super("BattleShips");
        //Set background of frame from image provided
        JLabel bg = new JLabel(new ImageIcon("background.jpg"));
        bg.setSize(new Dimension (900,460));
        bg.setPreferredSize(new Dimension(900,460));
        //Sets the background of the frame to the given image
        setContentPane(bg);
        initComponents();
    }
    
    /**
     * main method of the program
     * @param args 
     * @throws java.sql.SQLException 
     * @throws java.lang.ClassNotFoundException 
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       /**
         *This section is focused around javadb. It will create a connection with a database that is 
         * located locally, and will populate a player list from its contents.
         **/
        try {
            //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection("jdbc:derby:Person; create=true");
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PERSON");
            while (rs.next()) {
                PlayerStats tempPlayer = new PlayerStats();
                String ab1 = rs.getString(1); // name
                int ab2 = rs.getInt(2); // games won
                //System.out.println(ab1);
                tempPlayer.setName(ab1);
                tempPlayer.setGamesWon(ab2);
                tempList.add(tempPlayer);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        //Run the frame
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new BattleShips().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(BattleShips.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    /**
     * reset is called when the play wishes to reset their board, either at the start of the game or
     * once the game has finished.
     */
    public static void reset(){
        player = new HandlePlayer();
        comp = new HandleComp();    
        System.gc();
        getPlayersGrid().resetComponents(player.playerGrid, 1);
        getComputersGrid().resetComponents(player.playerGrid, 0);
        
        BattleShips.getMessgeBox().setText(defaultMessage);
    }

    /**
     * Populate scores is used to populate the table on the Jframe holding the players names and relevant scores.
     * It will insert a new row into the database if the username entered by the user is not currently in there
     * @param user User entered username
     * @return String array to be used by JTable
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @SuppressWarnings("empty-statement")
    private String[] populateScores(String user) throws FileNotFoundException, IOException {
        //PlayerStats tempPlayer = new PlayerStats();

        //Check if the user entered name is currently in the list / database.
        //If it is update elementofplayerinlist
        for (int i = 0; i < tempList.size(); i++) {
            if (user.equals(tempList.get(i).getName())) {
                elementOfPlayerInList = tempList.indexOf(tempList.get(i));
            }
        }
        //If the user isnt in the list, add it into the database as a new row
        if (elementOfPlayerInList == -1) {
            tempList.add(new PlayerStats(user));
            elementOfPlayerInList = tempList.size() - 1;
            try {
                String insertSQL = "INSERT INTO Person (name, gamesWon) VALUES (?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(insertSQL)) {
                    pst.setString(1, user);
                    pst.setInt(2, 0);
                    pst.executeUpdate();
                    //statement = conn.createStatement();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BattleShips.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Add player information from list into a string array to be returned
        String[] toReturn = new String[tempList.size()];
        for(int j = 0; j < tempList.size(); j++){
            toReturn[j] = "";
        }

        for(int i = 0; i < tempList.size(); i++){
            toReturn[i] += tempList.get(i).getName();
            toReturn[i] += " ";
            toReturn[i] += tempList.get(i).getGamesWon();
        }
        return toReturn;
    }

    /**
     * initComponents is the method used to set up the frame. it holds all the components
     * @throws IOException 
     */
    private void initComponents() throws IOException {
        player = new HandlePlayer();
        comp = new HandleComp();
        setPlayersGrid(new GridPanel(player.playerGrid,1));
        setComputersGrid(new GridPanel(comp.compGrid,0));
        jScrollPane1 = new JScrollPane();
        scores = new JList<>();
        setMessgeBox(new JTextArea());
        playersGridLabel = new JLabel();
        compGridLabel = new JLabel();
        scoresLabel = new JLabel();
        setResizable(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
        addWindowListener(new WindowAdapter() {
            @Override
            /**
             * Window closing action listener is need to update the player information in the database
             * if they won the game
             */
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    try (PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Person SET gamesWon = ? WHERE name = ?")) {

                        stmt.setInt(1, tempList.get(elementOfPlayerInList).getGamesWon());
                        stmt.setString(2, tempList.get(elementOfPlayerInList).getName());

                        stmt.executeUpdate();
                    }                   
                    //Close connections to database
                    conn.close();
                    statement.close();
                    //Dispose of the frame when the user wants to exit
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(BattleShips.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //Draw both grids to the IDE output window
        player.playerGrid.drawGrid();
        comp.compGrid.drawGrid();
        
        //Username input dialog
        String userName = (String) JOptionPane.showInputDialog(
                null,
                "Please Enter your username:\n",
                "UserName",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        
        scores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = populateScores(userName);

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }

        });
        
        //Table section used to hold the players names and games won
        DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model);  
        model.addColumn("UserName");
        model.addColumn("Games Won");
        for(int i = 0; i < tempList.size(); i++){
            model.addRow(new Object[]{(String) tempList.get(i).getName(), (int) tempList.get(i).getGamesWon()});          
        }
        jScrollPane1.setPreferredSize(new Dimension(162, 70));
        jScrollPane1.setMinimumSize(new Dimension(162, 70));
        jScrollPane1.setViewportView(table);

        getMessgeBox().setText(defaultMessage);

        playersGridLabel.setText("Players Grid");

        compGridLabel.setText("Attacking Grid");

        scoresLabel.setText("User Results");
                
        //Layout component of the frame. This was originally designed in the NetBeans GUI developer, and exported
        //to a new project. The layout used is GroupLayout 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, true)
                    .addComponent(getMessgeBox())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playersGridLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(getPlayersGrid(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getComputersGrid(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(compGridLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, Short.MAX_VALUE))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scoresLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, Short.MAX_VALUE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, true)
                    .addComponent(playersGridLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(compGridLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoresLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, true)
                    .addComponent(getPlayersGrid(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getComputersGrid(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(getMessgeBox(), javax.swing.GroupLayout.PREFERRED_SIZE, 85, Short.MAX_VALUE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * @return the computersGrid
     */
    public static GridPanel getComputersGrid() {
        return computersGrid;
    }

    /**
     * @param acomputersGrid
     * the computersGrid to set
     */
    public static void setComputersGrid(GridPanel acomputersGrid) {
        computersGrid = acomputersGrid;
    }

    /**
     * @return the playersGrid
     */
    public static GridPanel getPlayersGrid() {
        return playersGrid;
    }

    /**
     * @param aPlayersGrid the playersGrid to set
     */
    public static void setPlayersGrid(GridPanel aPlayersGrid) {
        playersGrid = aPlayersGrid;
    }

    /**
     * @return the messgeBox
     */
    public static javax.swing.JTextArea getMessgeBox() {
        return messgeBox;
    }

    /**
     * @param aMessgeBox the messgeBox to set
     */
    public static void setMessgeBox(javax.swing.JTextArea aMessgeBox) {
        messgeBox = aMessgeBox;
    }

    //Component Declaration
    private javax.swing.JLabel compGridLabel;
    public static GridPanel computersGrid;
    public static javax.swing.JTextArea messgeBox;
    public static GridPanel playersGrid;
    private javax.swing.JLabel playersGridLabel;
    private javax.swing.JList<String> scores;
    private javax.swing.JLabel scoresLabel;
    private javax.swing.JScrollPane jScrollPane1;
    
    public static Connection conn;
    public static Statement statement;
    public static HandlePlayer player;// = new HandlePlayer();
    public static HandleComp comp;// = new HandleComp();
    public static ArrayList<PlayerStats> tempList = new ArrayList();
    public static int elementOfPlayerInList = -1;
    public static String defaultMessage = "Welcome to BattleShips!"+"\n"+"The Board on the left is your board which the computer will try and destroy! "
            + "The Green buttons are spaces which are occupied by a ship.\n" + "This has already been randomly set up for you. To re-shuffle this left click anywhere in "
            + "the players grid, before your first turn.\n"
            + "To play, click on the attacking grid to the right, and if the button turns red, then you have hit a ship! Black indicates a missed shot!\n"
            + "Each player has 1x Aircraft Carrier (5 spaces), 1x Battleship (4 spaces), 1x Cruiser(3 spaces), 2x Destroyers(2 spaces) and 2x Submaries(1 space)";
}
