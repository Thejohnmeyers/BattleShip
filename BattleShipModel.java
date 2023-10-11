import java.awt.Color;

import javax.swing.JButton;

public class BattleShipModel {
	
	private int[][] playerBoard = new int[10][10];
	private int[][] oppBoard = new int[10][10];
	Ship[] ship = new Ship[5];
	private int shipcoordsX;
	private int shipcoordsY;
	private int totalhits = 0;
	public BattleShipModel(JButton[][] buttonArr, JButton[][] buttonArr2){
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				playerBoard[row][col] = Integer.parseInt(buttonArr[row][col].getName());
				System.out.println(playerBoard[row][col]);
			}
		}
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				oppBoard[row][col] = Integer.parseInt(buttonArr2[row][col].getName());
				System.out.println(playerBoard[row][col]);
			}
		}
		displayBoard(playerBoard);
		
		ship[0] = new Ship(5);
		ship[1] = new Ship(4);
		ship[2] = new Ship(3);
		ship[3] = new Ship(3);
		ship[4] = new Ship(2);
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


	public void shoot(int x, int y, Model opp)
    {
        if(opp.checkHit(x, y))
        {
            oppBoard[x][y] = 1;
            totalhits++;
            System.out.println("HIT");
        }
        else
        {
            oppBoard[x][x] = 0;
            System.out.println("MISS");
        }
    }

    public boolean checkHit(int x, int y)
    {
        if(playerBoard[x][y] == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkWin()
    {
        if(totalhits == 17)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkLose()
    {
        if(totalShips == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
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
	
}

