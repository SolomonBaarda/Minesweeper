package generation;

import java.util.Random;

import main.Cell;
import utils.Pair;

public class RandomNumber extends Generator {

	private Random r = new Random();

	@Override
	protected void generateMines(Pair safeCell) {
		int minesPlaced = 0;

		while(minesPlaced < mineCount) {
			// Pick a random cell in the board
			Cell currentCell = generation[r.nextInt(boardSize.x)][r.nextInt(boardSize.y)];

			// Not a mine
			if(!currentCell.isMine() ) {
//				if(currentCell.getCol() == safeCell.x && currentCell.getRow() == safeCell.y) {
//					continue;
//				}
				if(currentCell.getCol() > safeCell.x - 4 && currentCell.getCol() < safeCell.x + 4)
					if(currentCell.getRow() > safeCell.y - 4 && currentCell.getRow() < safeCell.y + 4) {
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
