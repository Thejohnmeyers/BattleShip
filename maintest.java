import javax.swing.JFrame;

public class maintest {

	public static void main(String[] args) {
      GUITest v = new GUITest();
      BattleShipModel m = new BattleShipModel();
      BattleController b = new BattleController(m, v);
		// while(true){
		// 	b.waitForServer();
		// 	if(m.isWin()){
		// 		break;
		// 	}
		// }
      

}
}
