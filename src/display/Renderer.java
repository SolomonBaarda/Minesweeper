package display;

import java.awt.Color;
import java.awt.Graphics;

import enums.FlagType;
import main.Board;
import main.Cell;
import main.Game;

public class Renderer {

	private Board board;
	private final int cellSize;
	private Game game;
	
	private Sprite flag;

	public Renderer(Board board, final int cellSize, Game game) {
		this.board = board;
		this.cellSize = cellSize;
		this.game = game;
		
		loadSprites();
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

							if(currentCell.getNearbyMineCount() != 0) {

								int count = currentCell.getNearbyMineCount();
								if(count == 1) {
									g.setColor(Color.blue);
								}
								else if(count == 2) {
									g.setColor(Color.green);
								}
								else if(count == 3) {
									g.setColor(Color.red);
								}
								else if(count == 4) {
									g.setColor(Color.black);
								}

								g.drawString(Integer.toString(count), col * cellSize + cellSize/4, row * cellSize + 3 *cellSize / 4);
							}
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
					
					if(game.isGameOver()) {
						if(currentCell.isMine()) {
							// Set appropriate colour 
							if(!currentCell.isClicked()) {
								g.setColor(Color.black);
							}
							else {
								g.setColor(Color.red);
							}
							// Draw mine
							g.fillOval(col * cellSize + 1, row * cellSize + 1, cellSize - 3, cellSize - 3);
						}
						
						if(currentCell.getFlagType() == FlagType.Flag && !currentCell.isMine()) {
							// Draw incorrect flag
							g.setColor(Color.cyan);
							g.fillOval(col * cellSize + 1, row * cellSize + 1, cellSize - 3, cellSize - 3);
						}
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

				
				//g.drawImage();
			}
	}

	
	
	
	public void loadSprites() {
		flag = new Sprite(game.loadImage("src/sprites/flag-no-background-small.png")); 
	}





}
