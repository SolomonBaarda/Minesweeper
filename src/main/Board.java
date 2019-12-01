package main;

import java.util.ArrayList;

import enums.FlagType;
import generation.Generator;
import generation.RandomNumber;
import utils.Pair;


public class Board {

	private Cell[][] board;
	private ArrayList<Cell> mineList;
	private Pair boardSize;

	private Generator randomGenerator = new RandomNumber();
	private int mineCount;

	private boolean boardGenerated;

	private int flagCount;
	private final int maxFlagCount;

	public Board(int columnCount, int rowCount) {
		this(new Pair(columnCount, rowCount));
	}

	public Board(Pair boardSize) {
		this.boardSize = boardSize;
		mineCount = 0;

		flagCount = 0;
		maxFlagCount = mineCount;
	}



	/**
	 * Method that generates the board.
	 * @param safeCell
	 */
	public void generate(Pair safeCell, int mineCount) {
		this.mineCount = mineCount;

		board = randomGenerator.generate(boardSize, mineCount, safeCell);
		mineList = getAllMines();

		boardGenerated = true;
	}


	public boolean isGameWon() {
		for(Cell cell: mineList) {
			if(cell.getFlagType() != FlagType.Flag) {
				return false;
			}
		}
		return true;
	}


	public void initialise(Pair boardSize) {
		this.boardSize = boardSize;
		board = new Cell[boardSize.x][boardSize.y];
	}

	public ArrayList<Cell> getAllMines() {
		ArrayList<Cell> mines = new ArrayList<>();
		for(int row = 0; row < boardSize.y; row++) 
			for(int column = 0; column < boardSize.x; column++) {
				Cell currentCell = getCell(column, row);

				if(currentCell.isMine()) {
					mines.add(currentCell);
				}
			}
		return mines;
	}


	public void updateFlagCount() {
		int newFlagCount = 0;

		for(int row = 0; row < boardSize.y; row++) 
			for(int col = 0; col < boardSize.x; col++) {
				Cell currentCell = getCell(col, row);
				if(currentCell.getFlagType() == FlagType.Flag) {
					newFlagCount++;
				}
			}
		flagCount = newFlagCount;
	}

	public ArrayList<String> getGameStats() {
		int cellsClicked = 0;
		int minesCorrectlyFlagged = 0;
		int flaggedIncorrectCells = 0;

		for(int row = 0; row < boardSize.y; row++) 
			for(int column = 0; column < boardSize.x; column++) {
				Cell currentCell = getCell(column, row);
				if(currentCell.isClicked()) {
					cellsClicked++;
				}
				if(currentCell.getFlagType() == FlagType.Flag) {
					if(currentCell.isMine()) {
						minesCorrectlyFlagged++;
					}
					else {
						flaggedIncorrectCells++;
					}
				}
			}

		ArrayList<String> stats = new ArrayList<>();

		int totalCells = boardSize.x * boardSize.y - mineCount;
		float cellsClickedPercent = ((float)cellsClicked / (float)totalCells) * 100f;
		stats.add("You uncovered " +cellsClicked+ " cells (" +cellsClickedPercent + "%)");

		float minesCorrectlyFlaggedPercent = ((float)minesCorrectlyFlagged / (float)mineCount) * 100f;
		stats.add("You correctly flagged " +minesCorrectlyFlagged+ " mines (" +minesCorrectlyFlaggedPercent+ "%)");

		if(flaggedIncorrectCells > 0) {
			stats.add("You incorrectly flagged " +flaggedIncorrectCells+ " cells!");
		}

		return stats;
	}


	public void smartClick(Cell clickedCell) {
		// Set cell to be clicked
		if(!clickedCell.isClicked()) {
			clickedCell.setClicked(true);

			// Check if not mine
			if(!clickedCell.isMine()) {
				if(clickedCell.getNearbyMineCount() == 0) {
					clickNearby(clickedCell);
				}
			}
		}
		else {
			return;
		}
	}



	private void clickNearby(Cell startingCell) {
		int nearbyMineCountLevel = startingCell.getNearbyMineCount();

		// Loop through 3x3 of nearby cells
		for(int yOffset = -1; yOffset < 2; yOffset++)
			for(int xOffset = -1; xOffset < 2; xOffset++) {

				Pair newValue = ensureWithinBoundries(startingCell.getCol() + xOffset, startingCell.getRow() + yOffset);
				Cell nextCell = getCell(newValue.x, newValue.y);

				// Skip cells already clicked
				if(nextCell.isClicked()) {
					if(nextCell.getFlagType() != FlagType.None) {
						nextCell.setFlagType(FlagType.None);
					}
					continue;
				}
				else if(nextCell.getNearbyMineCount() == nearbyMineCountLevel || cellNearEdge(nextCell)) {
					smartClick(nextCell);
				}



			}
	}


	private boolean cellNearEdge(Cell cellToCheck) {
		int localMineCountLevel = cellToCheck.getNearbyMineCount();

		for(int yOffset = -1; yOffset < 2; yOffset++)
			for(int xOffset = -1; xOffset < 2; xOffset++) {

				Pair newValue = ensureWithinBoundries(cellToCheck.getCol() + xOffset, cellToCheck.getRow() + yOffset);

				if(getCell(newValue.x, newValue.y).getNearbyMineCount() != localMineCountLevel) {
					return true;
				}
			}

		return false;
	}



	public void clearBoard() {
		boardGenerated = false;

		board = new Cell[boardSize.x][boardSize.y];
	}




	public Pair ensureWithinBoundries(int newValueX, int newValueY) {
		// Ensure new cell is inside board
		if(newValueX < 0) {
			newValueX = 0;
		}
		else if(newValueX >= boardSize.x) {
			newValueX = boardSize.x - 1;
		}
		if(newValueY < 0) {
			newValueY = 0;
		}
		else if(newValueY >= boardSize.y) {
			newValueY = boardSize.y - 1;
		}

		return new Pair(newValueX, newValueY);
	}






	public int getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}

	public int getMaxFlagCount() {
		return maxFlagCount;
	}

	public boolean isBoardGenerated() {
		return boardGenerated;
	}

	public void setBoardGenerated(boolean boardGenerated) {
		this.boardGenerated = boardGenerated;
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
