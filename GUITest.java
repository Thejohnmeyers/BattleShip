import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GUITest extends JFrame{
    JButton[][] oppBoardView = new JButton[10][10];
    JButton[][] playerBoardView = new JButton [10][10];
    public GUITest(){
       
        JButton randPlace = new JButton("Random Placement");
        randPlace.setBackground(Color.GREEN);
        RandomPlaceButton randHandler = new RandomPlaceButton();
        randPlace.addActionListener(randHandler);
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                oppBoardView[row][col] = new JButton("");
                playerBoardView[row][col] = new JButton("");
                oppBoardView[row][col].setPreferredSize(new Dimension(10,10));
                playerBoardView[row][col].setPreferredSize(new Dimension(10,10));
                playerBoardView[row][col].setName("0");
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
        containerPanel.add(randPlace, BorderLayout.WEST);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);
        
	    frame.getContentPane().add(containerPanel);
	    frame.pack();
	    frame.setVisible(true);

    }
    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
	      {
	    	JButton but = (JButton)event.getSource();
            but.setBackground(Color.RED);
          }
    }
    private class RandomPlaceButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            BattleShipModel b = new BattleShipModel(playerBoardView);
            b.callRandom();
            b.setColor(playerBoardView);
        }
    }

}