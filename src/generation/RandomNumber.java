package generation;

import java.util.Random;

import enums.CellContent;
import main.Cell;
import utils.Pair;

public class RandomNumber extends Generator {

	private Random r = new Random();

	@Override 
	public Cell[][] generate(Pair boardSize, int mineCount) {

		// Create new board
		Cell[][] generation = new Cell[boardSize.x][boardSize.y];

		// Initialise each cell in the board
		for(int row = 0; row < boardSize.y; row++) 
			for(int col = 0; col < boardSize.x; col++) {
				generation[col][row] = new Cell(col, row);
			}

		//final int totalCells = boardSize.x * boardSize.y;
		int minesPlaced = 0;

		while(minesPlaced < mineCount) {
			for(int row = 0; row < boardSize.y; row++) 
				for(int col = 0; col < boardSize.x; col++) {
					if(r.nextInt(25) == 0) {
						generation[col][row].setContent(CellContent.Mine);
						minesPlaced++;
					}
					if(minesPlaced >= mineCount) {
						System.out.println("Board generated with " +minesPlaced+ " mines.");

						return generation;
					}

				}

		}
		System.out.println("Board generated with " +minesPlaced+ " mines.");

		return generation;
	}


}
