package main;

import java.awt.Point;
import java.util.ArrayList;

import enums.FlagType;
import generation.Generator;
import generation.RandomNumber;
import utils.Pair;

/**
 * @author Solom
 *
 */
public class Board {

	private Cell[][] board;
	private ArrayList<Cell> mineList;
	private Pair boardSize;

	private Generator randomGenerator = new RandomNumber();
	private final int mineCount;
	
	private boolean boardGenerated;

	public Board(int columnCount, int rowCount, final int mineCount) {
		this(new Pair(columnCount, rowCount), mineCount);

	}

	public Board(Pair boardSize, final int mineCount) {
		this.boardSize = boardSize;
		this.mineCount = mineCount;

	}


	public void printBoard() {
		System.out.println("Board size: "+ boardSize.x +" x "+ boardSize.y);

		for(int row = 0; row < boardSize.y; row++) {
			for(int column = 0; column < boardSize.x; column++) {
				if(!getCell(column, row).isMine()) {
					System.out.print(getCell(column, row).getNearbyMineCount() +" ");
				}
				else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}

	}

	
	/**
	 * Method that generates the board.
	 * @param safeCell
	 */
	public void generate(Point safeCell) {
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


	public String[] getGameStats() {
		int cellsClicked = 0;
		int minesCorrectlyFlagged = 0;

		for(int row = 0; row < boardSize.y; row++) 
			for(int column = 0; column < boardSize.x; column++) {
				Cell currentCell = getCell(column, row);
				if(currentCell.isClicked()) {
					cellsClicked++;
				}
				if(currentCell.getFlagType() == FlagType.Flag && currentCell.isMine()) {
					minesCorrectlyFlagged++;
				}
			}

		int totalCells = boardSize.x * boardSize.y - mineCount;
		float cellsClickedPercent = ((float)cellsClicked / (float)totalCells) * 100f;
		String clicks = "You uncovered " +cellsClicked+ " cells (" +cellsClickedPercent + "%)";
		float minesCorrectlyFlaggedPercent = ((float)minesCorrectlyFlagged / (float)mineCount) * 100f;
		String mines = "You correctly flagged " +minesCorrectlyFlagged+ " mines (" +minesCorrectlyFlaggedPercent+ "%)";

		String[] stats = {clicks, mines};

		return stats;
	}


	public void smartCellClick(Cell clickedCell) {
		clickedCell.setClicked(true);

		if(!clickedCell.isMine()) {

			if(clickedCell.getNearbyMineCount() == 0) {
				clickNearby(clickedCell);
			}


		}
	}



	private void clickNearby(Cell startingCell) {
		int nearbyMineCountLevel = startingCell.getNearbyMineCount();

		// Loop through 3x3 of nearby cells
		for(int yOffset = -1; yOffset < 2; yOffset++)
			for(int xOffset = -1; xOffset < 2; xOffset++) {

				int newX = startingCell.getCol() + xOffset;
				int newY = startingCell.getRow() + yOffset;

				// Ensure new cell is inside board
				if(newX < 0) {
					newX = 0;
				}
				else if(newX >= boardSize.x) {
					newX = boardSize.x - 1;
				}
				if(newY < 0) {
					newY = 0;
				}
				else if(newY >= boardSize.y) {
					newY = boardSize.y - 1;
				}

				Cell nextCell = getCell(newX, newY);

				// Skip cells already clicked
				if(nextCell.isClicked()) {
					continue;
				}
				else if(nextCell.getNearbyMineCount() == nearbyMineCountLevel || cellNearEdge(nextCell)) {
					smartCellClick(nextCell);
				}



			}
	}


	private boolean cellNearEdge(Cell cellToCheck) {
		int localMineCountLevel = cellToCheck.getNearbyMineCount();

		for(int yOffset = -1; yOffset < 2; yOffset++)
			for(int xOffset = -1; xOffset < 2; xOffset++) {
				
				int newX = cellToCheck.getCol() + xOffset;
				int newY = cellToCheck.getRow() + yOffset;
				
				// Ensure new cell is inside board
				if(newX < 0) {
					newX = 0;
				}
				else if(newX >= boardSize.x) {
					newX = boardSize.x - 1;
				}
				if(newY < 0) {
					newY = 0;
				}
				else if(newY >= boardSize.y) {
					newY = boardSize.y - 1;
				}
				
				if(getCell(newX, newY).getNearbyMineCount() != localMineCountLevel) {
					return true;
				}
			}


		return false;
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
