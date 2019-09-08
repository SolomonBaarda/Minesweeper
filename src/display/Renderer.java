package display;

import java.awt.Color;
import java.awt.Graphics;

import enums.CellContent;
import main.Board;
import main.Cell;

public class Renderer {

	private Board board;
	private int cellSize;

	public Renderer(Board board, int cellSize) {
		this.board = board;
		this.cellSize = cellSize;
	}


	public void render(Graphics g) {
		for(int row = 0; row < board.getBoardSize().y; row++)
			for(int col = 0; col < board.getBoardSize().x; col++) {

				Cell currentCell = board.getCell(col, row);

				// Draw all cell backgrounds as black
				g.setColor(Color.black);
				g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
				g.setColor(Color.darkGray);
				g.drawRect(col * cellSize, row * cellSize, cellSize-1, cellSize-1);

				if(currentCell.getContent() == CellContent.Mine) {
					g.setColor(Color.red);
					g.fillOval(col * cellSize, row * cellSize, cellSize, cellSize);
				}

			}
		}

}
