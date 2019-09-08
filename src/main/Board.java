package main;

import utils.Pair;

public class Board {

	private Cell[][] board;
	private Pair boardSize;

	public Board(int columnCount, int rowCount) {
		this(new Pair(columnCount, rowCount));
	}

	public Board(Pair boardSize) {
		this.boardSize = boardSize;

		// Create new board
		board = new Cell[boardSize.x][boardSize.y];

		// Initialise each cell in the board
		for(int row = 0; row < boardSize.y; row++) 
			for(int col = 0; col < boardSize.x; col++) {
				board[col][row] = new Cell(col, row);
			}

	}


	
	public void printBoard() {
		System.out.println("Canvas size: "+ boardSize.x +" x "+ boardSize.y);

		for(int row = 0; row < boardSize.y; row++) {
			for(int column = 0; column < boardSize.x; column++) {
				System.out.print(getCell(column, row).getContent() +" ");
			}
			System.out.println();
		}

	}



	public Pair getBoardSize() {
		return boardSize;
	}

	public Cell getCell(int col, int row) {
		return board[col][row];
	}

	public Cell[][] getBoard() {
		return board;
	}




}
