public class ServerMainBattle {

	public static void main(String[] args) {
      GUITest v = new GUITest();
      BattleShipModel m = new BattleShipModel();
      BattleController2 b = new BattleController2(m, v);
    //   while(true){
    //     b.waitForClient();
    //     if(m.isWin()){
    //         break;
    //     }
    //   }

      

}
}