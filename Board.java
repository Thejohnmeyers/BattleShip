
public class Board {
	int[][] gameBoard = new int[10][10];
	public Board() {
		
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				gameBoard[row][col] = 0;
				System.out.print(gameBoard[row][col]);
			}
			System.out.println();
		}
}
		public int getPoint(int x, int y) {
			return gameBoard[x][y];
		}
		public void displayBoard() {
			for(int row = 0; row < 10; row++) {
				for(int col = 0; col < 10; col++) {
					System.out.print(gameBoard[row][col]);
				}
				System.out.println();
			}
		}
}