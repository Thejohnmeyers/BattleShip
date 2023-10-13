import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BattleController2{
    private BattleShipModel model;
    private GUITest view;
    Server application;
    String message = "";

    public BattleController2(BattleShipModel m , GUITest v)
    {
        model = m;
        view = v;
        application = new Server();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runServer(); // run client application
        v.setL(new ActionOnClick());
    }

    public void waitForClient()
    {
        try {
            message = application.processConnection();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        String[] split2 = message.toString().split("");
        int x = Integer.parseInt(split2[0]);
        int y = Integer.parseInt(split2[1]);

        boolean hit = model.checkHit(x, y);

        if(hit)
        {
            application.sendData("1");
        }
        else
        {
            application.sendData("0");
        }
        model.setTurn(1);
        view.setTurn("My turn!");

    }

    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
	      {
            JButton but = (JButton)event.getSource();
	    	if(model.getTurn() == 1){
                application.sendData(but.getName() + 1);
                try {
                    message = application.processConnection();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }
                if(model.recieveHit(message)){
                    but.setBackground(Color.RED);
                }
                else{
                    but.setBackground(Color.white);
                }
                model.setTurn(0);
                view.setTurn("Opponent's turn!");

            }
          }
    }



    


} 