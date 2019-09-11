package generation;

import java.awt.Point;
import java.util.Random;

import main.Cell;
import utils.Pair;

public class RandomNumber extends Generator {

	private Random r = new Random();

	private Cell[][] generation;
	private Pair boardSize;
	private int mineCount;

	@Override 
	public Cell[][] generate(Pair boardSize, int mineCount, Point safeCell) {

		// Create new board
		this.generation = new Cell[boardSize.x][boardSize.y];
		this.boardSize = boardSize;
		this.mineCount = mineCount;

		// Initialise each cell in the board
		for(int row = 0; row < boardSize.y; row++) 
			for(int col = 0; col < boardSize.x; col++) {
				generation[col][row] = new Cell(col, row);
			}

		
		generateMines(safeCell);

		generateNumbers(generation, boardSize);

		return generation;
	}


	@Override
	protected void generateMines(Point safeCell) {
		int minesPlaced = 0;

		while(minesPlaced < mineCount) {
			// Pick a random cell in the board
			Cell currentCell = generation[r.nextInt(boardSize.x)][r.nextInt(boardSize.y)];

			// Not a mine
			if(!currentCell.isMine() ) {
				if(currentCell.getCol() == safeCell.x && currentCell.getRow() == safeCell.y) {
					continue;
				}

				currentCell.setMine(true);
				minesPlaced++;
			}
			// Already a mine
			else {
				continue;
			}

		}
		System.out.println(minesPlaced+ " mines placed.");
	}





}
