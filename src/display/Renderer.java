package display;

import java.awt.Color;
import java.awt.Graphics;

import enums.FlagType;
import main.Board;
import main.Cell;

public class Renderer {

	private Board board;
	private final int cellSize;

	public Renderer(Board board, final int cellSize) {
		this.board = board;
		this.cellSize = cellSize;
	}


	public void render(Graphics g) {
		// Iterate through each cell
		for(int row = 0; row < board.getBoardSize().y; row++)
			for(int col = 0; col < board.getBoardSize().x; col++) {

				if(board.isBoardGenerated()) {
					Cell currentCell = board.getCell(col, row);

					// Cell has been clicked on
					if(currentCell.isClicked()) {

						// Draw all cell backgrounds as lightGray
						g.setColor(Color.lightGray);
						g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

						// Draw nearbyMineCount for empty cells
						if(!currentCell.isMine()) {

							//g.setFont(new Font("Arial", 32, Font.PLAIN));
							g.setColor(Color.black);

							if(currentCell.getNearbyMineCount() != 0) {
								g.drawString(Integer.toString(currentCell.getNearbyMineCount()), col * cellSize + cellSize/4, row * cellSize + 3 *cellSize / 4);
							}
						}
						// Draw mine
						else {
							g.setColor(Color.red);
							g.fillOval(col * cellSize + 1, row * cellSize + 1, cellSize - 3, cellSize - 3);
						}
					}
					else {
						// Draw all cell backgrounds as grey
						g.setColor(Color.gray);
						g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
					}

					// Draw flag
					if(currentCell.getFlagType() == FlagType.Flag) {
						g.setColor(Color.green);
						g.fillOval(col * cellSize + 1, row * cellSize + 1, cellSize - 3, cellSize - 3);
					}
					// Draw suspected mine
					else if(currentCell.getFlagType() == FlagType.Suspected) {
						g.setColor(Color.white);
						g.drawString("?", col * cellSize + cellSize/4, row * cellSize + 3 *cellSize / 4);
					}
				}
				else {
					// Draw all cell backgrounds as grey
					g.setColor(Color.black);
					g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
				}

				// Give a grey border to each cell
				g.setColor(Color.darkGray);
				g.drawRect(col * cellSize, row * cellSize, cellSize-1, cellSize-1);

			}
	}



//	public void renderAllMines() {
//		for(int row = 0; row < board.getBoardSize().y; row++)
//			for(int col = 0; col < board.getBoardSize().x; col++) {
//				Cell currentCell = board.getCell(col, row);
//
//				if(currentCell.isMine()) {
//					g.setColor(Color.red);
//					g.fillOval(col * cellSize + 1, row * cellSize + 1, cellSize - 3, cellSize - 3);
//				}
//			}
//	}





}
