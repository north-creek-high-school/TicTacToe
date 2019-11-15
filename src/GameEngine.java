/**
 * Kabir Shergill & Pranav Manchikanti
 * Advanced Programming Topics
 * Period 3
 * Tic Tac Toe Partner-Programming Project
 */

/**
 * This is the GameEngine class which handles the mechanics of the tic tac toe
 * game itself. Its main function is to determine who the winner of the game is.
 */
public class GameEngine {
	
	private GameBackground background;
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.background.initialize();
		game.background.panel.onClick((x, y) -> game.whenClick(x, y));
	}
	
	public GameEngine() {
		background = new GameBackground();
		
	}
	
	private void whenClick(int x, int y) {
		
		// Converting the x-y coordinates of the user click
		// into a position on the cellArray.
		int col = x / 200;
		int row = y / 200;

		// If the spot isn't taken, store the user's move
		// into the array.
		if (!spotTaken(row, col, background.getCellArray())) {

			background.drawSymbol(col, row);

			// show the message dialog depending on the winner.
			background.showMessage(decideWinner(background.getCellArray()));
		}
	}
	
	
	
	
	/**
	 * This method checks whether a spot in the tic tac toe grid is taken/ filled or
	 * not.
	 * 
	 * @param row       is the row position of the symbol in the 2D array.
	 * @param col       is the column position of the symbol in the 2D array.
	 * @param cellArray is the 2D int array that represents the tic tac toe grid.
	 */
	public boolean spotTaken(int row, int col, int[][] cellArray) {

		// If a spot is empty (has value 0), then it returns false, and returns true
		// other wise.
		if (cellArray[row][col] == 0) {
			return false;
		}
		return true;
	}

	/**
	 * This method calls a series of check methods to decide if there is a winner or
	 * not.
	 *
	 * @param cellArray is the 2D int array that represents the tic tac toe grid.
	 */
	public int decideWinner(int[][] cellArray) {

		/*
		 * If a method return 3, that essentially means that it has failed to find a
		 * winner. Otherwise 0 means tie, 1 means player one has won, and 2 means player
		 * two has won.
		 */
		if (checkRows(cellArray) != 3) {
			return checkRows(cellArray);
		} else if (checkColumns(cellArray) != 3) {
			return checkColumns(cellArray);
		} else if (checkDiagonals(cellArray) != 3) {
			return checkDiagonals(cellArray);
		} else {
			return checkTie(cellArray);
		}

	}

	/**
	 * This method checks if there is a tie if it is already determined if there is
	 * no winner prior.
	 * 
	 * @param cellArray is the 2D int array that represents the tic tac toe grid.
	 */
	private int checkTie(int[][] cellArray) {
		for (int i = 0; i < cellArray.length; i++) {
			for (int j = 0; j < cellArray[i].length; j++) {
				if (cellArray[i][j] == 0) {
					return 3;
				}
			}
		}

		// 0 is returned if there is no tie, which means there just isn't any winner.
		return 0;
	}

	/**
	 * This method checks each row of the grid to see if there is a winner.
	 *
	 * @param cellArray is the 2D int array that represents the tic tac toe grid.
	 */
	private int checkRows(int[][] cellArray) {

		// The for loop checks each row in the array for a winner.
		for (int i = 0; i < cellArray.length; i++) {
			if (cellArray[i][0] == cellArray[i][1] && cellArray[i][1] == cellArray[i][2]) {
				if (cellArray[i][0] == 1) {
					return 1;

				}
				if (cellArray[i][0] == 2) {
					return 2;

				}
			}
		}

		// If no winner is found, then 3 is returned, which represents a tie/ no winner.
		return 3;
	}

	/**
	 * This method checks each column of the grid to see if there is a winner.
	 *
	 * @param cellArray is the 2D int array that represents the tic tac toe grid.
	 */
	private int checkColumns(int[][] cellArray) {

		// The for loop checks each column in the array for a winner.
		for (int j = 0; j < cellArray.length; j++) {
			if (cellArray[0][j] == cellArray[1][j] && cellArray[1][j] == cellArray[2][j]) {
				if (cellArray[0][j] == 1) {
					return 1;
				}
				if (cellArray[0][j] == 2) {
					return 2;
				}
			}
		}
		return 3;
	}

	/**
	 * This method checks diagnolly in the grid to see if there is a winner.
	 *
	 * @param cellArray is the 2D int array that represents the tic tac toe grid.
	 */
	private int checkDiagonals(int[][] cellArray) {

		// This checks the diagnol from top left to bottom right for a winner.
		if (cellArray[0][0] == cellArray[1][1] && cellArray[1][1] == cellArray[2][2]) {
			if (cellArray[0][0] == 1) {
				return 1;
			}
			if (cellArray[0][0] == 2) {
				return 2;
			}
		}

		// This checks the diagnol from top right to bottom left for a winner.
		if (cellArray[0][2] == cellArray[1][1] && cellArray[1][1] == cellArray[2][0]) {
			if (cellArray[0][2] == 1) {
				return 1;
			}
			if (cellArray[0][2] == 2) {
				return 2;
			}
		}

		// If no winner is found, then 3 is returned, which represents a tie/ no winner.
		return 3;
	}

}