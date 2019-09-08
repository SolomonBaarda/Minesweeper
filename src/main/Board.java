package main;

import generation.RandomNumber;
import utils.Pair;

public class Board {

	private Cell[][] board;
	private Pair boardSize;
	
	private static RandomNumber randomGenerator = new RandomNumber();

	public Board(int columnCount, int rowCount) {
		this(new Pair(columnCount, rowCount));
	}

	public Board(Pair boardSize) {
		this.boardSize = boardSize;

		board = randomGenerator.generate(boardSize, Game.DEFAULT_MINE_COUNT);
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
