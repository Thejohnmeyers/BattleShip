
public class BattleShipModel {
	private Board playerBoard;
	private Board oppBoard;
	Ship[] ship = new Ship[5];
	
	public BattleShipModel(){
		playerBoard = new Board();
		
		oppBoard = new Board();
		ship[0] = new Ship(5);
		ship[1] = new Ship(4);
		ship[2] = new Ship(3);
		ship[3] = new Ship(3);
		ship[4] = new Ship(2);
	}
	public void callRandom() {
		for(int i = 0; i < ship.length; i++) {
			placeRandomPosition(ship[i]);
		}
		playerBoard.displayBoard();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	public void placeRandomPosition(Ship ship) {
		int randomX = (int)(Math.random() * (10));
		int randomY = (int)(Math.random() * (10));
		
		int horoz = (int)(Math.random() * 2);
		
		if(horoz == 1) {
			
			if((ship.size + randomY) > 10) {
				
				for(int i = 0; i < ship.size; i++) {
					playerBoard.gameBoard[randomX][randomY] = 1;
					randomY-=1;
				}
				
			}
			else {
				
				for(int i = 0; i < ship.size; i++) {
					playerBoard.gameBoard[randomX][randomY] = 1;
					randomY+=1;
				}
				
			}
			
		}
		else {
			
			if((ship.size + randomX) > 10) {
				for(int i = 0; i < ship.size; i++) {
					playerBoard.gameBoard[randomX][randomY] = 1;
					randomX-=1;
					
				}
				
			}
			else {
				for(int i = 0; i < ship.size; i++) {
					playerBoard.gameBoard[randomX][randomY] = 1;
					randomX+=1;
				}
				
			}
		}
		
	}
	
		
	
}

