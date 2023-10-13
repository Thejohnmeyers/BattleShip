package JLabelDnD;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class JLabelDnD {
	
	ImageIcon water = new ImageIcon("ocean.jfif");
	ImageIcon miss = new ImageIcon("whiteMiss.png");
	ImageIcon hit = new ImageIcon("redHit.png");
	
	int[][] shipBoard = new int[10][10];	//2d integer array holds 0 if no ship, 1 if ship
	
    public static void main(String[] args) {
        new JLabelDnD();
    }

    // Constructor 
    public JLabelDnD() {
    	//initialize shipBoard with all 0's
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			this.shipBoard[i][j] = 0;
    		}
    	}
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
            }
        });
    }	// end JLabelDnD

    //create JPanel of JLabels grid 10x10
    public class TestPane extends JPanel {

        public TestPane() {
        	
            setLayout(new GridLayout(10,10));	
            for(int col = 0; col < 10; col++) {
            	for(int row = 0; row < 10; row++) {
	            	JLabel jLab = new JLabel();    	
	            	jLab.setIcon(water);
	            	jLab.setPreferredSize(new Dimension(40, 40));
	                jLab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	            	jLab.setVerticalTextPosition(JButton.BOTTOM);
	            	jLab.setHorizontalTextPosition(JButton.CENTER);
	            	jLab.setTransferHandler(new ImageTransferHandler());
	            	jLab.setName(String.valueOf(row) + String.valueOf(col));
	            	add(jLab);
            	}
            }	
        }	//end TestPane constructor

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

    }

    public class ImageTransferHandler extends TransferHandler {

        public final DataFlavor[] SUPPORTED_DATA_FLAVORS = new DataFlavor[]{
            DataFlavor.javaFileListFlavor,
            DataFlavor.imageFlavor
        };

        // This function checks to see if an image can be Dropped onto a JLabel.
        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            boolean canImport = false;
            for (DataFlavor flavor : SUPPORTED_DATA_FLAVORS) {
                if (support.isDataFlavorSupported(flavor)) {
                    canImport = true;
                    break;
                }
            }
            return canImport;
        }

        // This is the actual 'Drop' function. 
        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Component component = support.getComponent();
                    if (component instanceof JLabel) {
                        Image image = null;
                        if (support.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                            image = (Image) t.getTransferData(DataFlavor.imageFlavor);
                        } else if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                            List files = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
                            if (files.size() > 0) {
                                image = ImageIO.read((File) files.get(0));
                            }
                        }
                        ImageIcon icon = null;
                        if (image != null) {
                            icon = new ImageIcon(image);	//this is where we change/ default set for pieces of ship
                        }
                        ((JLabel) component).setIcon(icon);	//set icon of image being dropped on
                        System.out.println("Image dropped at " + component.getName());
                        shipBoard = placeShip(component.getName(), shipBoard);
                        printBoard(shipBoard);
                        accept = true;
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }	//Image Transfer Handler end
        
        
    }	
    
    /* FUNCTION: get coords from ImageTransferHandler of dropped ship, (length and direction as well)
     * check int ship board to see if ship can be placed
     * if YES: update JLabel Icons at said coordinates and shipBoard int values to 1, return true
     * if NO: return false, don't change any boards
     */
    int[][] placeShip(String coords, int[][] shipBoard) {
    	char x = coords.charAt(0);
    	char y = coords.charAt(1);
    	
    	shipBoard[x][y] = 1;
    	
    	return shipBoard;
    }
    
    void printBoard(int[][] board) {
    	for(int i = 0; i < 10; i++) {
        	System.out.println();
        	for(int j = 0; j < 10; j++) {
        		System.out.print("| " + board[i][j]);
        	}
        }
    }
    
    

    
}	//end BattleShipBoardDnD class