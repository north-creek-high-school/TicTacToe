/**
 * Kabir Shergill & Pranav Manchikanti
 * Advanced Programming Topics
 * Period 3
 * Tic Tac Toe Partner-Programming Project
 */

/** 
 * When run, this program launches a two-player,
 * gui-based tic-tac-toe game which responds to 
 * user mouse events.
 */

import java.awt.*;
import javax.swing.JOptionPane;

/**
 * This the layout that the user sees when they launch the game, hence the name
 * GameBackground. This class HAS a drawing panel, a 2D array of cells which
 * keeps track of where the symbols are on the grid, a graphics object, and a
 * type of symbol.
 * 
 * @author Kabir Shergill & Pranav Manchikanti
 */

public class GameBackground {
	private Graphics2D g;
	private DrawingPanel panel;
	private GameEngine game;
	private int[][] cellArray = new int[3][3];
	private String type = "o";

	/**
	 * The main starts the game by constructing a game background object, and then
	 * calling the initialize method on it which sets up the game for the user.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {

		GameBackground gameBackground = new GameBackground();
		gameBackground.initialize();
	}

	/**
	 * This is the constructor for the Game Background which creates and sets the
	 * preferences for the drawing panel object.
	 */
	public GameBackground() {
		panel = new DrawingPanel(600, 600);
		panel.setBackground(Color.WHITE);
		g = panel.getGraphics();
	}

	/**
	 * This method is what actually sets up the game for the user by drawing the
	 * grid layout, creating the game's engine, and then listening for a mouse-click
	 * event.
	 */
	public void initialize() {
		drawGrid();
		game = new GameEngine();
		panel.onClick((x, y) -> whenClick(x, y));
	}

	/**
	 * This method is responsible for drawing the lines that make up the grid
	 * layout.
	 */
	private void drawGrid() {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(8));
		g.drawLine(200, 0, 200, 600);
		g.drawLine(400, 0, 400, 600);
		g.drawLine(0, 200, 600, 200);
		g.drawLine(0, 400, 600, 400);
	}

	/**
	 * This method is the on click event handler. Once checking if the user has
	 * clicked in an empty spot, it will store the user's turn in the cellArray. It
	 * will then call the drawSymbol method and call the showMessage method which is
	 * dependent on whether their is a winner or not.
	 * 
	 * @param x is the x-coordinate of where the user clicks on the panel.
	 * @param y is the y-coordinate of where the user clicks on the panel.
	 */
	private void whenClick(int x, int y) {
		// Converting the x-y coordinates of the user click
		// into a position on the cellArray.
		int col = x / 200;
		int row = y / 200;

		// If the spot isn't taken, store the user's move
		// into the array.
		if (!game.spotTaken(row, col, cellArray)) {

			if (!type.equals("x")) {
				type = "x";
				// 1 in the array represents an X.
				cellArray[row][col] = 1;
			} else {
				type = "o";
				// 2 in the array represents an O.
				cellArray[row][col] = 2;
			}

			// Draw the symbol onto the grid. (adding 1 to the row and col because
			// of one-based indexing.
			drawSymbol(col + 1, row + 1);

			// show the message dialog depending on the winner.
			showMessage(game.decideWinner(cellArray));
		}
	}

	/**
	 * This method is responsible for drawing an individual symbol onto the grid.
	 * 
	 * @param col is the column position of the symbol in the 2D array.
	 * @param row is the row position of the symbol in the 2D array.
	 */
	private void drawSymbol(int col, int row) {
		g.setFont(new Font("Arial", Font.PLAIN, 150));
		// Manipulate the position of symbol to center it in its box.
		g.drawString(type, col * 200 - 135, row * 200 - 60);

	}

	/**
	 * This method is in charge of handling the end-of-game message dialogs.
	 * 
	 * @param winner is an integer value representing who the winner of the game is,
	 *               1 = player one wins, 2 = player 2 wins, 0 = tie, and 3 = no end
	 *               as of yet.
	 */
	private void showMessage(int winner) {
		// Array of button options for pop-up message.
		Object[] options = { "Yes, please", "No, thanks" };
		String message = "";

		// Depending on the winner, set the appropriate message.
		if (winner == 1) {
			message = "Player one wins! (X)";
		}

		if (winner == 2) {
			message = "Player two wins! (O)";
		}

		if (winner == 0) {
			message = "We have a tie :(";
		}
		// Only if the game has ended, then display message.
		if (winner != 3) {
			JOptionPane.showMessageDialog(null, message);
			int response = JOptionPane.showOptionDialog(null, "Do you want to play again?", "The game has ended :(",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			// React to the user's choice.
			handleUserResponse(response);
		}
	}

	/**
	 * This method is responsible for handling the user's response to the message
	 * dialog.
	 * 
	 * @param response is the same integer value as showMessage() which represents
	 *                 the winner of the game.
	 */
	private void handleUserResponse(int response) {
		// If the user wants to play again, reset the board,
		// otherwise, quit the drawing panel session.
		if (response == JOptionPane.YES_OPTION) {
			resetBoard();
		} else {
			System.exit(0);
		}
	}

	/**
	 * This method is responsible for resetting the game-board to its original
	 * settings and is employed when the user selects that they want to play again.
	 */
	private void resetBoard() {
		// White out the grid lines.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 600);
		// Draw new grid lines.
		drawGrid();
		// Empty the cell array by filling it with 0's
		// (which represent empty cells)
		resetCellArray();
	}

	/**
	 * This method is responsible for filling the cellArray with zeros, which
	 * represent that they are empty.
	 */
	private void resetCellArray() {
		for (int i = 0; i < cellArray.length; i++) {
			for (int j = 0; j < cellArray[i].length; j++) {
				cellArray[i][j] = 0;
				type = "o";
			}
		}
	}
}