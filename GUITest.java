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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.SwingUtilities.getRootPane;

class GUITest{
    JFrame frame;
    JPanel myPanel;
    JPanel oppPanel;
    JPanel myShips;
    JPanel controls;



    JButton[][] oppBoardView = new JButton[10][10];
    JLabel[][] playerBoardView = new JLabel [10][10];
    JLabel turn;
    JButton randPlace;
    public GUITest(){
        JLabel turn = new JLabel("My turn!");

        randPlace = new JButton("Random Placement");
        randPlace.setBackground(Color.GREEN);
       /* JButton randPlace = new JButton("Random Placement");
        randPlace.setBackground(Color.GREEN);
        // RandomPlaceButton randHandler = new RandomPlaceButton();
        // randPlace.addActionListener(randHandler);
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                oppBoardView[row][col] = new JButton("");
                playerBoardView[row][col] = new JButton("");
                oppBoardView[row][col].setPreferredSize(new Dimension(10,10));
                playerBoardView[row][col].setPreferredSize(new Dimension(10,10));
                playerBoardView[row][col].setName(String.valueOf(row) + String.valueOf(col));
                oppBoardView[row][col].setName(row + "" + col);
                playerBoardView[row][col].setBackground(Color.blue);
                oppBoardView[row][col].setBackground(Color.BLUE);
            }
        }



        JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel buttonPanel = new JPanel();
        JPanel imagePanel = new JPanel();
        JPanel imagePanel2 = new JPanel();
        buttonPanel.setLayout(new GridLayout(10,10));
        ImageIcon image1 = new ImageIcon("nobackgroundship1.png");
        ImageIcon image2 = new ImageIcon("nobackgroundship2.png");
        ImageIcon image3 = new ImageIcon("nobackgroundship3.png");
        ImageIcon image4 = new ImageIcon("nobackgroundship4.png");
        ImageIcon image5 = new ImageIcon("nobackgroundship5.png");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(10,10));
        imagePanel.setLayout(new GridLayout(1,3));
        imagePanel2.setLayout(new GridLayout(1,2));
	    JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
       // buttonPanel.setLayout(new GridLayout(10,10));
       ActionOnClick handler = new ActionOnClick();
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                buttonPanel.add(oppBoardView[row][col]);
                bottomPanel.add(playerBoardView[row][col]);
                oppBoardView[row][col].addActionListener(handler);
            }
        }

        imagePanel.add(new JLabel(image1));
        imagePanel.add(new JLabel(image2));
        imagePanel.add(new JLabel(image3));
        imagePanel2.add(new JLabel(image4));
        imagePanel2.add(new JLabel(image5));
        buttonPanel.setPreferredSize(new Dimension(300, 300));
        
        buttonPanel.setMaximumSize(new Dimension(500, 500));
        imagePanel.setPreferredSize(new Dimension(750, 300));
        imagePanel2.setPreferredSize(new Dimension(500, 300));
        bottomPanel.setPreferredSize(new Dimension(300, 300));
        bottomPanel.setMaximumSize(new Dimension(500, 500));
       containerPanel.add(buttonPanel, BorderLayout.NORTH);
       containerPanel.add(imagePanel);
       containerPanel.add(imagePanel2);
       containerPanel.add(turn);
        containerPanel.add(randPlace, BorderLayout.WEST);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);*/
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

        myShips.setLayout(new FlowLayout());
        myShips.setBackground(new Color(25,25,25));
        myShips.add(new JLabel(new ImageIcon("shipImages/v_five.png")));
        myShips.add(new JLabel(new ImageIcon("shipImages/v_four.png")));
        myShips.add(new JLabel(new ImageIcon("shipImages/v_three.png")));
        myShips.add(new JLabel(new ImageIcon("shipImages/v_three.png")));
        myShips.add(new JLabel(new ImageIcon("shipImages/v_two.png")));

        getRootPane(frame).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                playerBoardView[row][col] = new JLabel();
                oppBoardView[row][col] = new JButton();
                playerBoardView[row][col].setName(row + "" + col);
                oppBoardView[row][col].setName(row+""+col);
                myPanel.add(playerBoardView[row][col]);
                oppPanel.add(oppBoardView[row][col]);

                playerBoardView[row][col].setBorder(BorderFactory.createLineBorder(new Color(102,102,102), 1));
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

    public void setL(ActionListener l)
    {
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                oppBoardView[row][col].addActionListener(l);

        }}
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


}