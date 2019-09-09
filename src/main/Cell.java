package main;

import enums.CellContent;
import enums.FlagType;

public class Cell {
	private CellContent content;
	private FlagType flagType;
	private int col, row;
	private int nearbyMineCount;


	public Cell(int col, int row) {
		content = CellContent.Empty;
		flagType = FlagType.None;
		nearbyMineCount = -1;
		this.col = col;
		this.row = row;
	}



	@Override
	public String toString() {
		return "Cell(" +col+ "," +row+ ") [content: " +content+ "]";
	}


	public CellContent getContent() {
		return content;
	}


	public void setContent(CellContent content) {
		this.content = content;
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


	
	

}

