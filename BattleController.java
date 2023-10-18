import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.MouseEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class BattleController{
    private BattleShipModel model;
    private GUITest view;
    Client application;
    String message = "";
    int myShips = 17;
    public BattleController(BattleShipModel m , GUITest v) throws UnsupportedAudioFileException, LineUnavailableException
    {
        model = m;
        view = v;
        v.setL(new ActionOnClick());
        v.setRandomListen(new RandomOnClick());
        v.setLock(new LockOnClick());
        application = new Client("127.0.0.1");
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runClient(); // run client application
        while(true){
            
            try {
               
                application.processConnection();
                
                if(application.recieveMessage() == "Closing Connection Client Win!!"){
                    view.displayWin(application.recieveMessage());
                    application.closeConnection();
                    break;
                }
                
                waitForServer();
                

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        

            
          

        }
        //application.closeConnection();
        
    }
    
    public void waitForServer() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        System.out.println("bruh1" + model.getTurn());
        if(model.getTurn() == "server"){
        String[] split2 = application.recieveMessage().split("");
        int x = Integer.parseInt(split2[0]);
        int y = Integer.parseInt(split2[1]);
        System.out.println(split2[0] + " " + split2[1]);
            System.out.println(x + " " + y);
        boolean hit = model.checkHit(x, y);
            System.out.println(hit);
        if(hit)
        {
            application.sendData("1");
            myShips--;
            view.changeHit(x, y);
            if(isWin()){
                application.sendData("Closing Connection Server Win!!");
                view.displayWin("Closing Connection Server Win!!");
                
            }
        }
        else
        {
            application.sendData("0");
            view.changeMiss(x, y);
        }
        model.setTurn("client");
      //  view.setTurn("My turn!");
    }

    }
    public boolean isWin(){
		if(myShips == 0){
			return true;
		}
		return false;
	}
    MouseListener LFrame = new MouseAdapter() {
        public void mouseReleased(MouseEvent e){
            JLabel c = (JLabel) e.getSource();
            c.getIcon();
            lookThrough();
        }
    };
    private class LockOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
	      {
            System.out.println("ttttt");
            lookThrough();
          }
        }
    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
	      {
            JButton but = (JButton)event.getSource();
	    	if(model.getTurn() == "client"){
                application.sendData(but.getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // application.processConnection();
                    // message = application.recieveMessage();
                    // System.out.println("forst: " +message);
                if(model.recieveHit(application.recieveMessage())){
                    but.setBackground(Color.RED);
                 }
                else{
                    but.setBackground(Color.white);
                } 
                
                model.setTurn("server");
              //  view.setTurn("Opponent's turn!");

            }
          }
    }


    private class RandomOnClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("HELPPPPPP");
            //add code here to iterate through ships and add to map based on call random
            JLabel g[][] = view.getMyGrid();
            int play[][] = model.getBoard();
            Ship[] s = model.getShips();
            view.randPlace.setEnabled(false);
            for(int i = 0; i <5; i++){
                placeRandomShip(s[i], g);
            }
           
            view.setMyGrid(g);
            
        }
    }
     public void placeRandomShip(Ship ship, JLabel[][] g) {
        boolean collides = true;
        int horiz = (int) (Math.random() * 2);
        int boardRow, boardCol;
        boolean horizontal = (horiz == 1) ? true : false;
      //  String name = ship.getName();
        do {
            if (horizontal) {
                boardCol = (int) (Math.random() * (9 - ship.getSize() + 1));
                boardRow = (int) (Math.random() * (9 + 1));
            } else {
                boardCol = (int) (Math.random() * (9 + 1));
                boardRow = (int) (Math.random() * (9 - ship.getSize() + 1));
            }
            collides = placeShip(boardRow, boardCol, horizontal, ship);
        }
        while (!collides);
        if (horizontal) {
            g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/h_left.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
            for (int i = 0; i < ship.getSize()-2; i++) {
                try {
                    g[boardRow][boardCol+i+1].setIcon(new ImageIcon(new ImageIcon("shipImages/h_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    model.setGridPos(boardRow,boardCol, 1);

                } catch (Exception err) {
                    System.out.println("Couldn't set icon: " + err);
                }
            }
            g[boardRow][boardCol+ship.getSize()-1].setIcon(new ImageIcon(new ImageIcon("shipImages/h_right.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

        } else {
            g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

            for (int i = 0; i < ship.getSize()-2; i++) {
                try {
                        g[boardRow+i+1][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
                        model.setGridPos(boardRow, boardCol, 1);

                } catch (Exception err) {
                    System.out.println("Couldn't set icon: " + err);
                }

            }
            g[boardRow+ship.getSize()-1][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_bottom.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

MouseListener LFrame = new MouseAdapter() {
            public void mouseReleased(MouseEvent e){

            }
        };
        }
    }

    /* 
    mouseReleased for water Board which parses through board[][]
    getIcon -> string fdsfds/v_five
    v or h = boolean
    row col 
    placeShip() 
    
    */
   

    boolean placeShip(int row, int col, boolean horizontal, Ship ship)
    {
        int length = ship.getSize();
        int iter = horizontal ? col : row;

        // check if the ship will collide with any ships.
        for (int i = iter; i < iter+length; i++) {
            if(horizontal) {
                if(model.getPos(row, i) == 1) return false;}
            else {
                if(model.getPos(i, col) == 1) return false; }
        }

        //place the ship
        for (int i = iter; i < iter+length; i++) {
            if(horizontal) model.setGridPos(row, i, 1);
            else model.setGridPos(i, col, 1);
            model.incrementCount();
        }
        return true;
    }

    public void lookThrough(){
        System.out.println("BRUUHHHHHHHHH");
        JLabel g[][] = view.getMyGrid();
        Ship[] s = model.getShips();
        
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                System.out.println(row + "," + col);
                if((g[row][col].getIcon()).toString() == "shipImages/v_five.png"){
                    draggedShip(row, col, s[0], g);
                    // placeShip(row,col, false, s[0]);
                }
                if((g[row][col].getIcon()).toString() == "shipImages/v_four.png"){
                    draggedShip(row, col, s[1], g);
                    // placeShip(row,col, false, s[0]);
                }
                if((g[row][col].getIcon()).toString() == "shipImages/v_three.png"){
                    draggedShip(row, col, s[2], g);
                    // placeShip(row,col, false, s[0]);
                }
                if((g[row][col].getIcon()).toString() == "shipImages/v_two.png"){
                    draggedShip(row, col, s[4], g);
                    // placeShip(row,col, false, s[0]);
                }
                
            }
        }
        view.setMyGrid(g);
    }
    public void draggedShip(int boardRow, int boardCol, Ship ship, JLabel[][] g){
        g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

            for (int i = 0; i < ship.getSize()-2; i++) {
                try {
                        g[boardRow+i+1][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
                        model.setGridPos(boardRow, boardCol, 1);

                } catch (Exception err) {
                    System.out.println("Couldn't set icon: " + err);
                }

            }
            g[boardRow+ship.getSize()-1][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_bottom.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));


    }
} 