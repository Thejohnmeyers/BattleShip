import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket.Listener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;


import static javax.swing.SwingUtilities.getRootPane;

class GUITest{
    JFrame frame;
    JPanel myPanel;
    JPanel oppPanel;
    JPanel myShips;
    JPanel controls;


    JLabel carrier = new JLabel();
    JLabel battleship = new JLabel();       
    JLabel cruiser = new JLabel();
    JLabel submarine = new JLabel();
    JLabel destroyer = new JLabel();
    JButton[][] oppBoardView = new JButton[10][10];
    JLabel[][] playerBoardView = new JLabel [10][10];
    JLabel turn;
    JButton randPlace;
    JButton lockPlace;
    public GUITest(){
        JLabel turn = new JLabel("My turn!");

        randPlace = new JButton("Random Placement");
        randPlace.setBackground(Color.GREEN);
        lockPlace = new JButton("Lock In Place");
        lockPlace.setBackground(Color.RED);

        // MouseListener listener = new MouseAdapter() {
		// 		public void mousePressed(MouseEvent e)
		// 		{
		// 			JComponent c = (JComponent) e.getSource();
		// 			TransferHandler handler = c.getTransferHandler();
		// 			handler.exportAsDrag(c, e, TransferHandler.COPY); // export copy of clicked component: Can we add a ship class object to the components?
				
        //         }
        //         /*
        //          * public void mouseReleased(MouseEvent x) {
        //          *  findShipOnGrid(){
        //          *      
        //          *  }
        //          *  
        //          * }
        //          * 
        //          * 
        //          */
		// 	};
            
        
        frame = new JFrame();
        myPanel = new JPanel();
        oppPanel = new JPanel();
        controls = new JPanel();
        myShips = new JPanel();

        frame.setLayout(new GridLayout(2,2));
        frame.setBackground(new Color(0,0,0));

        myPanel.setLayout(new GridLayout(10,10));
        myPanel.setBackground(new Color(51,204,255));
        myPanel.setBorder(BorderFactory.createLineBorder(new Color(102,102,102), 15));

        oppPanel.setLayout(new GridLayout(10,10));
        oppPanel.setBackground(new Color(51,204,255));
        oppPanel.setBorder(BorderFactory.createLineBorder(new Color(102,102,102), 15));

        controls.setLayout(new FlowLayout());
        controls.setBackground(new Color(25,25,25));
        controls.add(randPlace);
        controls.add(turn);
        controls.add(lockPlace);

        myShips.setLayout(new FlowLayout());
        myShips.setBackground(new Color(25,25,25));

        
        
        carrier.setIcon(new ImageIcon("shipImages/v_five.png"));
        myShips.add(carrier);
        // carrier.addMouseListener(listener);
		// carrier.setTransferHandler(new TransferHandler("icon"));
        // System.out.println(carrier.getIcon());


        battleship.setIcon(new ImageIcon("shipImages/v_four.png"));
        myShips.add(battleship);
        // battleship.addMouseListener(listener);
		// battleship.setTransferHandler(new TransferHandler("icon"));

        cruiser.setIcon(new ImageIcon("shipImages/v_threetwo.png"));
        myShips.add(cruiser);
        // cruiser.addMouseListener(listener);
		// cruiser.setTransferHandler(new TransferHandler("icon"));

        submarine.setIcon(new ImageIcon("shipImages/v_three.png"));
        myShips.add(submarine);
        // submarine.addMouseListener(listener);
		// submarine.setTransferHandler(new TransferHandler("icon"));

        destroyer.setIcon(new ImageIcon("shipImages/v_two.png"));
        myShips.add(destroyer);
        // destroyer.addMouseListener(listener);
		// destroyer.setTransferHandler(new TransferHandler("icon"));


        getRootPane(frame).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        
        JLabel boardLabel = new JLabel();

        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                boardLabel = new JLabel();
		        boardLabel.setTransferHandler(new TransferHandler("icon"));
                playerBoardView[row][col] =boardLabel;
                oppBoardView[row][col] = new JButton();
                playerBoardView[row][col].setName(row + "" + col);
                oppBoardView[row][col].setName(row+""+col);
                myPanel.add(playerBoardView[row][col]);
                oppPanel.add(oppBoardView[row][col]);

                playerBoardView[row][col].setBorder(BorderFactory.createLineBorder(new Color(102,102,102), 1));
                playerBoardView[row][col].setIcon((new ImageIcon(new ImageIcon("replaceImages/trans.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH))));
                oppBoardView[row][col].setBorder(BorderFactory.createLineBorder(new Color(102,102,102), 1));
                oppBoardView[row][col].setBackground(new Color(51,204,255));

            }
        }

        frame.add(oppPanel);
        frame.add(controls);
        frame.add(myPanel);
        frame.add(myShips);

        frame.setMinimumSize(new Dimension(800, 800));
        frame.setMaximumSize(new Dimension(800, 800));
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);

    }
    
    public JLabel[][] getMyGrid()
    {
        return playerBoardView;
    }

    public void setMyGrid(JLabel[][] g)
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerBoardView[i][j] = g[i][j];
            }
        }
    }
    public void removeCarrier(){
        carrier.setIcon(null);
        //carrier.setBackground(Color.BLUE);
        System.out.println(carrier.getIcon());
        carrier.setTransferHandler(null);
        myShips.remove(carrier);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeDestroyer(){
        destroyer.setIcon(null);
        destroyer.setTransferHandler(null);
        myShips.remove(destroyer);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeSubmarine(){
        submarine.setIcon(null);
        submarine.setTransferHandler(null);
        myShips.remove(submarine);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeBattleship(){
        battleship.setIcon(null);
        battleship.setTransferHandler(null);
        myShips.remove(battleship);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeCruiser(){
        cruiser.setIcon(null);
        cruiser.setTransferHandler(null);
        myShips.remove(cruiser);
        myShips.revalidate();
        myShips.repaint();
    }

    public void setMouseListener(MouseListener l){
        carrier.addMouseListener(l);
        //carrier.addMouseMotionListener((MouseMotionListener)l);
		carrier.setTransferHandler(new TransferHandler("icon"));

        battleship.addMouseListener(l);
       // battleship.addMouseMotionListener((MouseMotionListener)l);
		battleship.setTransferHandler(new TransferHandler("icon"));

        cruiser.addMouseListener(l);
      //  cruiser.addMouseMotionListener((MouseMotionListener)l);
		cruiser.setTransferHandler(new TransferHandler("icon"));

        submarine.addMouseListener(l);
      //  submarine.addMouseMotionListener((MouseMotionListener)l);
		submarine.setTransferHandler(new TransferHandler("icon"));

        destroyer.addMouseListener(l);
      //  destroyer.addMouseMotionListener((MouseMotionListener)l);
		destroyer.setTransferHandler(new TransferHandler("icon"));
    }
    public void setL(ActionListener l)
    {
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                oppBoardView[row][col].addActionListener(l);

        }}
    }
    // public void setMouseBoard(MouseListener l){
    //     for(int row = 0; row < 10; row++){
    //         for(int col = 0; col < 10; col++){
    //             playerBoardView[row][col].addMouseListener(l);

    //     }}
    // }
    public void setLock(ActionListener l){
        lockPlace.addActionListener(l);
    }
    
    public void setRandomListen(ActionListener l){

        randPlace.addActionListener(l);
    }
    public void setTurn(String x)
    {
        turn.setText(x);
    }
    public void displayWin(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    public void changeHit(int x, int y) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        playerBoardView[x][y].setIcon(new ImageIcon(new ImageIcon("replaceImages/Explosion_0.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        AudioInputStream audioInputStream;
        Clip clip;
        audioInputStream =  AudioSystem.getAudioInputStream(new File("sounds/mixkit-arcade-game-explosion-1699.wav").getAbsoluteFile());
        clip = AudioSystem.getClip(); 
        clip.open(audioInputStream); 
        clip.start();
    }
    public void changeMiss(int x, int y) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        playerBoardView[x][y].setIcon(new ImageIcon(new ImageIcon("replaceImages/Solid_white.svg.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        AudioInputStream audioInputStream;
        Clip clip;
        audioInputStream =  AudioSystem.getAudioInputStream(new File("sounds/mixkit-water-splash-1311.wav").getAbsoluteFile());
        clip = AudioSystem.getClip(); 
        clip.open(audioInputStream); 
        clip.start();
    }

    public JPanel getShipsPanel()
    {
        return myShips;
    }

    public void setShipsPanel(JPanel p)
    {
        myShips = p;
    }

    
}