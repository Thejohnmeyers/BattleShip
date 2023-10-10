import java.awt.Color;

import javax.swing.JButton;

public class BattleShipModel {
	
	private int[][] playerBoard = new int[10][10];
	private Board oppBoard;
	Ship[] ship = new Ship[5];
	private int shipcoordsX;
	private int shipcoordsY;
	Server s;
	Client application = new Client( "10.249.43.73" );
	
	public BattleShipModel(JButton[][] buttonArr){
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				playerBoard[row][col] = Integer.parseInt(buttonArr[row][col].getName());
				System.out.println(playerBoard[row][col]);
			}
		}
		displayBoard(playerBoard);
		oppBoard = new Board();
		ship[0] = new Ship(5);
		ship[1] = new Ship(4);
		ship[2] = new Ship(3);
		ship[3] = new Ship(3);
		ship[4] = new Ship(2);
		s = new Server();
	}
	public void callRandom() {
		for(int i = 0; i < ship.length; i++) {
			placeRandom(ship[i]);
			System.out.println();
		System.out.println();
		System.out.println();
		}
		displayBoard(playerBoard);
		System.out.println();
		System.out.println();
		System.out.println();
	}


	public void placeRandom(Ship ship)
    {
        
            boolean collides = true;
            int horiz = (int)(Math.random() * 2);
            int shipRow, shipCol;
            boolean horizontal = (horiz == 1) ? true : false;
            do{
                if(horizontal){
                    shipCol = (int)(Math.random() * (9 - ship.getSize()+1 + 1));
                    shipRow = (int)(Math.random() * (9 + 1));
                }
                else{
                    shipCol = (int)(Math.random() * (9 + 1));
                    shipRow = (int)(Math.random() * (9-ship.getSize()+1 + 1));
                }
              collides = placeShip(shipRow, shipCol, horizontal, ship);
            }
            while(!collides);
        
    }
    public boolean placeShip( int row, int col, boolean horizontal, Ship ship)
    {
        int length = ship.getSize();
        int iter = horizontal ? col : row;

        System.out.println(horizontal);

        for (int i = iter; i < iter+length; i++) {
            if(horizontal) {
                if(playerBoard[row][i] == 1) return false;}
            else {
                if(playerBoard[i][col] == 1) return false; }
        }

        for(int i = iter; i < iter + length; i++)
        {
            if(horizontal)
            {
                playerBoard[row][i] = 1;
            }
            else
            {
                playerBoard[i][col] = 1;
            }
        }
        return true;
    }






/*

	public void placeRandomPosition(Ship ship) {
		
		
		int horoz = (int)(Math.random() * 2);
		collisionChecker(ship.getSize(), horoz);
		int randomX = shipcoordsX;
		int randomY = shipcoordsY;
		System.out.println(randomX +" , " + randomY);
		if(horoz == 1) {
			
			if((ship.getSize() + randomY) > 10) {
				
				for(int i = 0; i < ship.getSize(); i++) {
					playerBoard[randomX][randomY] = 1;
					randomY-=1;
				}
				
			}
			else {
				
				for(int i = 0; i < ship.getSize(); i++) {
					playerBoard[randomX][randomY] = 1;
					randomY+=1;
				}
				
			}
			
		}
		else {
			
			if((ship.getSize() + randomX) > 10) {
				for(int i = 0; i < ship.getSize(); i++) {
					playerBoard[randomX][randomY] = 1;
					randomX-=1;
					
				}
				
			}
			else {
				for(int i = 0; i < ship.getSize(); i++) {
					playerBoard[randomX][randomY] = 1;
					randomX+=1;
				}
				
			}
		}
		System.out.println();
		displayBoard(playerBoard);
		System.out.println();
		System.out.println();
		
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
						holder+=playerBoard[shipcoordsX][shipcoordsY];
						shipcoordsY-=1;
						System.out.println("Holder: " + holder);
					}
				}
				else{
					System.out.println(shipcoordsX +" bruh, " + shipcoordsY);
					holder = 0;
					for(int i = 0; i < size; i++){
						holder+=playerBoard[shipcoordsX][shipcoordsY];
						shipcoordsY+=1;
						System.out.println("Holder: " + holder);
					}
				}
				if(holder == 0){
					returnable = true;
					System.out.println("Final coords: " + shipcoordsX + " , " + shipcoordsY);
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
					holder+=playerBoard[shipcoordsX][shipcoordsY];
					shipcoordsX-=1;
					System.out.println("Holder: " + holder);
				}
			}
			else{
				holder =0;
				for(int i = 0; i < size; i++){
					
					holder+=playerBoard[shipcoordsX][shipcoordsY];
					shipcoordsX+=1;
					System.out.println("Holder: " + holder);
				}
			}
			if(holder == 0){
				returnable = true;
				System.out.println("Final coords: " + shipcoordsX + " , " + shipcoordsY);
				break;
			}
			else{
				System.out.println("collision!!");
			}


			}
		}
		}
		*/
		public void displayBoard(int gameBoard[][]) {
			for(int row = 0; row < 10; row++) {
				for(int col = 0; col < 10; col++) {
					System.out.print(gameBoard[row][col]);
				}
				System.out.println();
			}
		}
		public void setColor( JButton[][] Buttonarr){
			for(int row = 0; row < 10; row++){
				for(int col = 0; col < 10; col++){
					if(playerBoard[row][col] == 1){
						Buttonarr[row][col].setBackground(Color.GREEN);
					}
				}
			}
		}
		public void sendHit(String message){
			application.sendData(message);
		}
}

