package generation;

import main.Cell;
import utils.Pair;

public abstract class Generator {

	public abstract Cell[][] generate(Pair boardSize, int mineCount);

	protected abstract void generateMines();
	
	protected abstract void generateNumbers();
	
}