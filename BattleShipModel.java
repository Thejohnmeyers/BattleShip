import java.awt.Color;

import javax.swing.JButton;

public class BattleShipModel {
	
	private int[][] playerBoard = new int[10][10];
	private int[][] oppBoard = new int[10][10];
	Ship[] ship = new Ship[5];
	private int shipcoordsX;
	private int shipcoordsY;
	private int myShips = 17;
	private int hitNum = 0;
	private String turn = "client";
	private int count = 0;
	
	
	public BattleShipModel(){
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				oppBoard[row][col] = 0;
				playerBoard[row][col] = 0;
			}
		}
		displayBoard(playerBoard);
		ship[0] = new Ship(5);
		ship[1] = new Ship(4);
		ship[2] = new Ship(3);
		ship[3] = new Ship(3);
		ship[4] = new Ship(2);
		
	}
	public boolean checkHit(int x, int y){
		
		boolean hit = false;
		if(playerBoard[x][y] == 1){
			hit = true;
			myShips--;
		}
		return hit;
	}
	public boolean isWin(){
		if(myShips == 0){
			return true;
		}
		return false;
	}
	public boolean recieveHit(String check){
		int hit = Integer.parseInt(check);
		if(hit == 0){
			return false;
		}
		else if(hit == 1){
			return true;
		}
		else{
			return false;
		}
		
	}
	public void callRandom() {
		for(int i = 0; i < ship.length; i++) {
			placeRandom(ship[i]);
		}
		displayBoard(playerBoard);
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
        displayBoard(playerBoard);
		return true;
		
    }





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
		public void displayPlayerBoard() {
			for(int row = 0; row < 10; row++) {
				for(int col = 0; col < 10; col++) {
					System.out.print(playerBoard[row][col]);
				}
				System.out.println();
			}
		}
		public void clearBoard(){
			for(int row = 0; row < 10; row++) {
				for(int col = 0; col < 10; col++) {
					playerBoard[row][col] = 0;
				}
				
			}
		}
		public int[][] getBoard(){
			return playerBoard;
		}
		public Ship[] getShips(){
			return ship;
		}
		public void setGridPos(int x, int y, int val)
		{ 
			playerBoard[x][y] = val;
		}
		public int getPos(int x, int y){
			return playerBoard[x][y];
		}
		public String getTurn()
		{
			return turn;
		}
		public void setTurn(String t)
		{
			turn = t;
		}
		public void incrementCount(){
			count++;
		}
		public void decrementCount(){
			count--;
		}
		
}

