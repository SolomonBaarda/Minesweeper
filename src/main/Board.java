package main;

import generation.Generator;
import generation.RandomNumber;
import utils.Pair;

public class Board {

	private Cell[][] board;
	private Pair boardSize;
	
	private Generator randomGenerator = new RandomNumber();
	private final int mineCount;

	public Board(int columnCount, int rowCount, final int mineCount) {
		this(new Pair(columnCount, rowCount), mineCount);
		
	}

	public Board(Pair boardSize, final int mineCount) {
		this.boardSize = boardSize;
		this.mineCount = mineCount;
		
		board = randomGenerator.generate(boardSize, mineCount);
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

	public int getMineCount() {
		return mineCount;
	}

	


}
