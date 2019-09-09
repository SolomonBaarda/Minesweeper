package generation;

import java.util.Random;

import enums.CellContent;
import main.Cell;
import utils.Pair;

public class RandomNumber extends Generator {

	private Random r = new Random();

	private Cell[][] generation;
	private Pair boardSize;
	private int mineCount;

	@Override 
	public Cell[][] generate(Pair boardSize, int mineCount) {

		// Create new board
		this.generation = new Cell[boardSize.x][boardSize.y];
		this.boardSize = boardSize;
		this.mineCount = mineCount;

		// Initialise each cell in the board
		for(int row = 0; row < boardSize.y; row++) 
			for(int col = 0; col < boardSize.x; col++) {
				generation[col][row] = new Cell(col, row);
			}

		generateMines();

		generateNumbers();

		return generation;
	}


	@Override
	protected void generateMines() {
		int minesPlaced = 0;

		while(minesPlaced < mineCount) {
			// Pick a random cell in the board
			Cell currentCell = generation[r.nextInt(boardSize.x)][r.nextInt(boardSize.y)];

			// Not a mine
			if(currentCell.getContent() != CellContent.Mine) {
				currentCell.setContent(CellContent.Mine);
				minesPlaced++;
			}
			// Already a mine
			else {
				continue;
			}

		}
		System.out.println(minesPlaced+ " mines placed.");
	}


	@Override
	protected void generateNumbers() {
		// Loop through all cells
		for(int row = 0; row < boardSize.y; row++) 
			for(int col = 0; col < boardSize.x; col++) {

				Cell currentCell = generation[col][row];
				// Not a mine
				if(currentCell.getContent() != CellContent.Mine) {

					int nearbyMines = 0;
					// Loop through 3x3 of nearby cells
					for(int yOffest = -1; yOffest < 2; yOffest++)
						for(int xOffset = -1; xOffset < 2; xOffset++) {
							// Check if cell is a mine

							int x = currentCell.getCol() + xOffset;
							int y = currentCell.getRow() + yOffest;

							// If cell is outside the board, skip cell
							if(x < 0 || y < 0 || x >= boardSize.x || y >= boardSize.y) {
								continue;
							}

							if(generation[x][y].getContent() == CellContent.Mine) {
								nearbyMines++;
							}
						}

					currentCell.setNearbyMineCount(nearbyMines);
				}
				// No need to do a count if cell is a mine
				else {
					continue;
				}
			}
		System.out.println("Numbers generated.");

	}


}
