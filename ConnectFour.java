import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		ConnectFour game = new ConnectFour();
		game.play();
	}
}

public class ConnectFour {
    private static final int DEFAULT_COLUMNS = 7;
    private static final int DEFAULT_ROWS = 6;
    private static final int DEFAULT_WIN_LENGTH = 4;
    private static final int DEFAULT_PLAYERS = 2;

    private int columns;
    private int rows;
    private int winLength;
    private int players;
    private int[][] board;
    private int currentPlayer;

    public ConnectFour() {
        Scanner input = new Scanner(System.in);
		
        System.out.print("Enter number of columns on the game board (default: " + DEFAULT_COLUMNS + "): ");
        columns = input.nextInt();
        if (columns < DEFAULT_WIN_LENGTH) {
            columns = DEFAULT_COLUMNS;
        }

        System.out.print("Enter number of rows on the board (default: " + DEFAULT_ROWS + "): ");
        rows = input.nextInt();

        System.out.print("Enter number of players in the game (default: " + DEFAULT_PLAYERS + "): ");
        players = input.nextInt();
        if (players < 2) {
            players = DEFAULT_PLAYERS;
        }

        System.out.print("Enter the length of a winning streak (default: " + DEFAULT_WIN_LENGTH + "): ");
        winLength = input.nextInt();
        if (winLength > columns) {
            winLength = DEFAULT_WIN_LENGTH;
        }

        board = new int[rows][columns];
        currentPlayer = 1;
    }

    public void play() {
		Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        while (!gameOver) {
            displayBoard();
            System.out.print("Player " + currentPlayer + ", choose a column to drop a token: ");
            int column = input.nextInt() - 1;
            dropToken(column);
            gameOver = checkForWin();
            currentPlayer = currentPlayer % players + 1;
        }
        System.out.println("Player " + currentPlayer + " wins!");
    }

    private void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
		for (int i = 0; i < columns; i++) {
            System.out.print((i + 1) + "  ");
        }
		System.out.println();
    }

    private void dropToken(int column) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == 0) {
                board[i][column] = currentPlayer;
                return;
            }
        }
        System.out.println("Error: column is full.");
    }

    private boolean checkForWin() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (board[i][j] == currentPlayer) {
					if (checkVerticalWin(i, j) || checkHorizontalWin(i, j) || checkDiagonalWin(i, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean checkVerticalWin(int row, int col) {
		int count = 0;
		for (int i = row; i < rows; i++) {
			if (board[i][col] != currentPlayer) {
				break;
			}
			count++;
		}
		return count >= winLength;
	}

	private boolean checkHorizontalWin(int row, int col) {
		int count = 0;
		for (int j = col; j < columns; j++) {
			if (board[row][j] != currentPlayer) {
				break;
			}
			count++;
		}
		return count >= winLength;
	}

	private boolean checkDiagonalWin(int row, int col) {
		int count = 0;
		for (int i = row, j = col; i < rows && j < columns; i++, j++) {
			if (board[i][j] != currentPlayer) {
				break;
			}
			count++;
		}
		if (count >= winLength) {
			return true;
		}

		count = 0;
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] != currentPlayer) {
				break;
			}
			count++;
		}
		if (count >= winLength) {
			return true;
		}

		count = 0;
		for (int i = row, j = col; i < rows && j >= 0; i++, j--) {
			if (board[i][j] != currentPlayer) {
				break;
			}
			count++;
		}
		if (count >= winLength) {
			return true;
		}

		count = 0;
		for (int i = row, j = col; i >= 0 && j < columns; i--, j++) {
			if (board[i][j] != currentPlayer) {
				break;
			}
			count++;
		}
		return count >= winLength;
	}
	
}
