package main;

public class Cell {
	private CellContent content;
	private int col, row;	


	public Cell(int col, int row) {
		content = CellContent.Empty;
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



}

