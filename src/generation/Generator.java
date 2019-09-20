package generation;

import main.Cell;
import utils.Pair;

public abstract class Generator {

	protected Cell[][] generation;
	protected Pair boardSize;
	protected int mineCount;

	public Cell[][] generate(Pair boardSize, int mineCount, Pair safeCell) {
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





	protected abstract void generateMines(Pair safeCell);




	/**
	 * Method that iterates over a board and calculates the nearbyMines 
	 * count for each cell.
	 * 
	 * @param generation	Board to edit
	 * @param boardSize		Width and height of board
	 */
	protected void generateNumbers(Cell[][] generation, Pair boardSize) {
		// Loop through all cells
		for(int row = 0; row < boardSize.y; row++) 
			for(int col = 0; col < boardSize.x; col++) {

				Cell currentCell = generation[col][row];
				// Not a mine
				if(!currentCell.isMine()) {

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

							if(generation[x][y].isMine()) {
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