import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;


import static javax.swing.SwingUtilities.getRootPane;

class GUITest{
    JFrame frame;
    JPanel myPanel;
    JPanel oppPanel;
    JPanel myShips;
    JPanel controls;

    Font buttonFont;
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
        turn = new JLabel();
        turn.setForeground(Color.white);
        turn.setLocation(50, 50);

        randPlace = new JButton("Random Placement");
        randPlace.setBackground(Color.GREEN);
        
        lockPlace = new JButton("Clear Board!");
        lockPlace.setBackground(Color.RED);

        buttonFont = new Font("verdana", Font.PLAIN, 40);
        randPlace.setFont(buttonFont);
        lockPlace.setFont(buttonFont);
        turn.setFont(buttonFont);
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

        controls.setLayout(new GridLayout(3,1));
        controls.setBackground(new Color(25,25,25));
       
        controls.add(turn);
        controls.add(randPlace);
        controls.add(lockPlace);

        myShips.setLayout(new GridLayout(2,3));
        myShips.setBackground(new Color(25,25,25));

        
        
        carrier.setIcon(new ImageIcon("shipImages/v_five.png"));
        myShips.add(carrier);
     
        battleship.setIcon(new ImageIcon("shipImages/v_four.png"));
        myShips.add(battleship);


        cruiser.setIcon(new ImageIcon("shipImages/v_threetwo.png"));
        myShips.add(cruiser);

        submarine.setIcon(new ImageIcon("shipImages/v_three.png"));
        myShips.add(submarine);


        destroyer.setIcon(new ImageIcon("shipImages/v_two.png"));
        myShips.add(destroyer);


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
    public JButton[][] getOppGrid(){
        return oppBoardView;
    }
    public void setOppGrid(JButton[][] g){
        oppBoardView = g;
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
        myShips.remove(carrier);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeDestroyer(){
        myShips.remove(destroyer);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeSubmarine(){
        myShips.remove(submarine);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeBattleship(){
        myShips.remove(battleship);
        myShips.revalidate();
        myShips.repaint();
    }
    public void removeCruiser(){
        myShips.remove(cruiser);
        myShips.revalidate();
        myShips.repaint();
    }

    public void setMouseListener(MouseListener l){
        carrier.addMouseListener(l);
		carrier.setTransferHandler(new TransferHandler("icon"));

        battleship.addMouseListener(l);
		battleship.setTransferHandler(new TransferHandler("icon"));

        cruiser.addMouseListener(l);
		cruiser.setTransferHandler(new TransferHandler("icon"));

        submarine.addMouseListener(l);
		submarine.setTransferHandler(new TransferHandler("icon"));

        destroyer.addMouseListener(l);
		destroyer.setTransferHandler(new TransferHandler("icon"));
    }
    public void setL(ActionListener l)
    {
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                oppBoardView[row][col].addActionListener(l);

        }}
    }
    
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
    public void clearViewBoard(){
        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                playerBoardView[row][col].setIcon(new ImageIcon("/replaceImages/trans.png"));
            }
        }
        removeBattleship();
        removeCarrier();
        removeCruiser();
        removeDestroyer();
        removeSubmarine();
        myShips.revalidate();
        myShips.repaint();
        myPanel.revalidate();
        myPanel.repaint();
    
    }
     public void clearViewBoardAdd(){
        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                playerBoardView[row][col].setIcon(new ImageIcon("/replaceImages/trans.png"));
            }
        }
        
        
        myShips.add(submarine);
        myShips.add(battleship);
        myShips.add(carrier);
        myShips.add(cruiser);
        myShips.add(destroyer);
        myShips.revalidate();
        myShips.repaint();
    
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