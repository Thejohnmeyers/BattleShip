import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BattleController2{
    private BattleShipModel model;
    private GUITest view;
    Server application;
    public BattleController2(BattleShipModel m , GUITest v)
    {
        model = m;
        view = v;
        v.setL(new ActionOnClick());
        v.setRandomListen(new RandomOnClick());
        application = new Server();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runServer(); // run client application
        while(true){
            
           
            
            try {
               

                System.out.println(" LLLLL befor process    bruhhhhhhhh");

                application.processConnection();
                
                // message = application.recieveMessage();
                System.out.println(" LLLLL afte process    bruhhhhhhhh");
                waitForClient();
                System.out.println(" after client bruhhhhhhh");


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            
           
        }
            
           

        }
        //application.closeConnection();
        
    }

    public void waitForClient() throws IOException
    {
        System.out.println("bruh1" + model.getTurn());
        if(model.getTurn() == "client"){
        String[] split2 = application.recieveMessage().split("");
        int x = Integer.parseInt(split2[0]);
        int y = Integer.parseInt(split2[1]);
        System.out.println(split2[0] + " " + split2[1]);
        System.out.println(x + " " + y);
        boolean hit = model.checkHit(x, y);

        if(hit)
        {
            application.sendData("1");
        }
        else
        {
            application.sendData("0");
        }
        // model.setTurn("server");
        // view.setTurn("My turn!");
    }
    model.setTurn("server");
     //   view.setTurn("My turn!");

    }

    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
	      {
            JButton but = (JButton)event.getSource();
            System.out.println(model.getTurn());
	        if(model.getTurn() == "server"){
                 System.out.println(but.getName());
                application.sendData(but.getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //application.processConnection();
                // message = application.recieveMessage();
                // System.out.println("forstserve: " +message);
                System.out.println("bruhhhhh" +application.recieveMessage());
                 if(model.recieveHit(application.recieveMessage())){
                     but.setBackground(Color.RED);
                }
                else{
                    but.setBackground(Color.white);
                }
                // try {
                //     application.processConnection();
                //     message = application.recieveMessage();
                //     System.out.println(message);
                //     waitForClient();
                //     System.out.println("afer wait client");
                // } catch (IOException e) {
                //     // TODO Auto-generated catch block
                //     e.printStackTrace();
                // }
               
                model.setTurn("client");
               // view.setTurn("Opponent's turn!");
            }

            //}
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


        }

    }

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



    


} 