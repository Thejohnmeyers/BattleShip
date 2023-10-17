import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ServerMainBattle {

	public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException {
      GUITest v = new GUITest();
      BattleShipModel m = new BattleShipModel();
      BattleController2 b = new BattleController2(m, v);

      

}
}