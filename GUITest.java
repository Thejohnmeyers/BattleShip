import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GUITest extends JFrame{
    
    public GUITest(){
        JButton[][] oppBoardView = new JButton[10][10];
        JButton[][] playerBoardView = new JButton [10][10];
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                oppBoardView[row][col] = new JButton("");
                playerBoardView[row][col] = new JButton("");
            }
        }
        JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10,10));
        ImageIcon image1 = new ImageIcon("top-warship-icon-cartoon-military-ship-vector.jpg");
        ImageIcon image2 = new ImageIcon("army-ship-icon-cartoon-military-navy-vector-44931653.jpg");
        ImageIcon image3 = new ImageIcon("ship3.png");
        ImageIcon image4 = new ImageIcon("250-ship-14-256-1.jpg");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(10,10));
	    //JPanel containerPanel = new JPanel();
       // buttonPanel.setLayout(new GridLayout(10,10));
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                buttonPanel.add(oppBoardView[row][col]);
                bottomPanel.add(playerBoardView[row][col]);
            }
        }
       
        buttonPanel.setPreferredSize(new Dimension(200, 300));
        bottomPanel.setPreferredSize(new Dimension(200, 300));
	    frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.add(new JLabel(image1), BorderLayout.WEST);
        frame.add(new JLabel(image2));
        frame.add(new JLabel(image3));
        frame.add(new JLabel(image4), BorderLayout.EAST);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	    //frame.getContentPane().add(containerPanel);
	    frame.pack();
	    frame.setVisible(true);

    }

}