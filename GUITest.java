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
        JPanel imagePanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10,10));
        ImageIcon image1 = new ImageIcon("nobackgroundship1.png");
        ImageIcon image2 = new ImageIcon("nobackgroundship2.png");
        ImageIcon image3 = new ImageIcon("nobackgroundship3.png");
        ImageIcon image4 = new ImageIcon("nobackgroundship4.png");
        ImageIcon image5 = new ImageIcon("nobackgroundship5.png");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(10,10));
        imagePanel.setLayout(new GridLayout(1,5));
	    //JPanel containerPanel = new JPanel();
       // buttonPanel.setLayout(new GridLayout(10,10));
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                buttonPanel.add(oppBoardView[row][col]);
                bottomPanel.add(playerBoardView[row][col]);
            }
        }
        imagePanel.add(new JLabel(image1));
        imagePanel.add(new JLabel(image2));
        imagePanel.add(new JLabel(image3));
        imagePanel.add(new JLabel(image4));
        imagePanel.add(new JLabel(image5));
        buttonPanel.setPreferredSize(new Dimension(200, 300));
        imagePanel.setPreferredSize(new Dimension(750, 300));
        bottomPanel.setPreferredSize(new Dimension(200, 300));
	    frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(imagePanel);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	    //frame.getContentPane().add(containerPanel);
	    frame.pack();
	    frame.setVisible(true);

    }

}