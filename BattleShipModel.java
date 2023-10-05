
public class BattleShipModel {
	private Board playerBoard;
	private Board oppBoard;
	Ship[] ship = new Ship[5];
	private int shipcoordsX;
	private int shipcoordsY;
	
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
			System.out.println();
		System.out.println();
		System.out.println();
		}
		playerBoard.displayBoard();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	public void placeRandomPosition(Ship ship) {
		
		
		int horoz = (int)(Math.random() * 2);
		collisionChecker(ship.size, horoz);
		int randomX = shipcoordsX;
		int randomY = shipcoordsY;
		System.out.println(randomX +" , " + randomY);
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
	
		public void collisionChecker(int size, int horoz){
			boolean returnable = false;
			
			int holder = 0;
			if(horoz == 1){
				
			while (returnable == false){
				shipcoordsX = (int)(Math.random() * (10));
				shipcoordsY = (int)(Math.random() * (10-size));
				System.out.println(shipcoordsX +" bruh, " + shipcoordsY);
				if((size + shipcoordsY) > 10){
					holder = 0;
				for(int i = 0; i < size; i++){
					holder+=playerBoard.gameBoard[shipcoordsX][shipcoordsY];
					shipcoordsY-=1;
				}
			}
			else{
				System.out.println(shipcoordsX +" bruh, " + shipcoordsY);
				holder = 0;
				for(int i = 0; i < size; i++){
					holder+=playerBoard.gameBoard[shipcoordsX][shipcoordsY];
					shipcoordsY+=1;
				}
			}
			if(holder == 0){
				returnable = true;
				break;
			}
			else{
				System.out.println("collision!!");
			}

			}
			
		}
		else{
			
			while (returnable == false){
				
				System.out.println(shipcoordsX +" bruh,bruh " + shipcoordsY);
				shipcoordsX = (int)(Math.random() * (10-size));
				shipcoordsY = (int)(Math.random() * (10));
				if((size + shipcoordsX) > 10){
					holder = 0;
				for(int i = 0; i < size; i++){
					holder+=playerBoard.gameBoard[shipcoordsX][shipcoordsY];
					shipcoordsX-=1;
				}
			}
			else{
				for(int i = 0; i < size; i++){
					holder =0;
					holder+=playerBoard.gameBoard[shipcoordsX][shipcoordsY];
					shipcoordsX+=1;
				}
			}
			if(holder == 0){
				returnable = true;
				break;
			}
			else{
				System.out.println("collision!!");
			}


			}
		}
		}
	
}

