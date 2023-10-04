import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
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
        JPanel bottomPanel = new JPanel();
	    JPanel containerPanel = new JPanel();
       // buttonPanel.setLayout(new GridLayout(10,10));
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                buttonPanel.add(oppBoardView[row][col]);
                bottomPanel.add(playerBoardView[row][col]);
            }
        }
       
        buttonPanel.setPreferredSize(new Dimension(400, 400));
        bottomPanel.setPreferredSize(new Dimension(400, 400));
	    frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	    //frame.getContentPane().add(containerPanel);
	    frame.pack();
	    frame.setVisible(true);

    }

}