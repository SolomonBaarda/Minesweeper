package main;

import enums.FlagType;

public class Cell {
	private boolean isMine;
	private FlagType flagType;
	private int col, row;
	private int nearbyMineCount;
	private boolean clicked;


	public Cell(int col, int row) {
		this.col = col;
		this.row = row;
		isMine = false;
		flagType = FlagType.None;
		nearbyMineCount = -1;
		clicked = false;
	}



	@Override
	public String toString() {
		return "Cell [isMine=" + isMine + ", col=" + col + ", row=" + row + "]";
	}


	public boolean isMine() {
		return isMine;
	}



	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}




	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}



	public FlagType getFlagType() {
		return flagType;
	}



	public void setFlagType(FlagType flagType) {
		this.flagType = flagType;
	}



	public int getNearbyMineCount() {
		return nearbyMineCount;
	}



	public void setNearbyMineCount(int nearbyMines) {
		this.nearbyMineCount = nearbyMines;
	}



	public boolean isClicked() {
		return clicked;
	}



	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}


	
	

}

