
public class BattleShipModel {
	private Board playerBoard;
	private Board oppBoard;
	Ship ship;
	public BattleShipModel(){
		playerBoard = new Board();
		oppBoard = new Board();
		ship = new Ship(5);
		
	}
	public void placeRandomPosition(Ship ship) {
		int randomX = (int)(Math.random() * (10));
		int randomY = (int)(Math.random() * (10));
		int borderCheck = 1;
		if((randomX+ship.size) > 10) {
			borderCheck = -1;
			for(int i = 0; i < ship.size; i++) {
				playerBoard.gameBoard[randomX][randomY] = 1;
				randomX+=borderCheck;
			}
		}
		else {
			for(int i = 0; i < ship.size; i++) {
				playerBoard.gameBoard[randomX][randomY] = 1;
				randomX+=borderCheck;
			}
		}
		playerBoard.displayBoard();
	}
	
		
	
}

