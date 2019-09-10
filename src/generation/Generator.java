package generation;

import java.awt.Point;

import main.Cell;
import utils.Pair;

public abstract class Generator {

	public abstract Cell[][] generate(Pair boardSize, int mineCount, Point safeCell);

	protected abstract void generateMines(Point safeCell);
	
	protected abstract void generateNumbers();
	
}