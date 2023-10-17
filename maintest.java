import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class maintest {

	public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException {
      GUITest v = new GUITest();
      BattleShipModel m = new BattleShipModel();
      BattleController b = new BattleController(m, v);
		
      

}
}
