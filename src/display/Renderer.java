package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import enums.FlagType;
import main.Board;
import main.Cell;
import main.Game;

public class Renderer {

	private Board board;
	private final int cellSize;
	private Game game;

	private Sprite flag;
	private Sprite mine;

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
					// Get reference to current cell to render
					Cell currentCell = board.getCell(col, row);
					int x = currentCell.getCol() * cellSize;
					int y = currentCell.getRow() * cellSize;

					// Cell has been clicked on
					if(currentCell.isClicked()) {
						// Draw all cell backgrounds as lightGray
						g.setColor(Color.lightGray);
						g.fillRect(x, y, cellSize, cellSize);

						// Draw nearbyMineCount for empty cells
						if(!currentCell.isMine()) {

							// Only draw numbers > 0
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

								g.setFont(new Font("Arial Bold", Font.PLAIN, 3 * cellSize / 4));
								g.drawString(Integer.toString(count), col * cellSize + cellSize/4, row * cellSize + 3 *cellSize / 4);
							}
						}
					}
					// Cell not clicked 
					else {
						// Draw all non clicked cells as grey
						g.setColor(Color.gray);
						g.fillRect(x, y, cellSize, cellSize);
					}

					// Draw flag
					if(currentCell.getFlagType() == FlagType.Flag) {
						// Draw flag sprite
						renderSprite(g, currentCell, flag);
					}
					// Draw suspected mine
					else if(currentCell.getFlagType() == FlagType.Suspected) {
						g.setColor(Color.white);
						g.drawString("?", col * cellSize + cellSize/4, row * cellSize + 3 *cellSize / 4);
					}

					// Draw all mines when game over
					if(game.isGameOver()) {
						if(currentCell.isMine() && currentCell.getFlagType() != FlagType.Flag) {
							// Non clicked mine
							if(!currentCell.isClicked()) {
								// Draw non discovered mine
								renderSprite(g, currentCell, mine);
							}
							// Mine clicked on
							else {
								// Draw mine clicked on - red
								renderSprite(g, currentCell, mine, Color.red);
							}
						}

						if(currentCell.getFlagType() == FlagType.Flag && !currentCell.isMine()) {
							// Draw incorrect flag - pink
							renderSprite(g, currentCell, flag, Color.pink);
						}
					}


					// Give a grey border to each cell
					g.setColor(Color.darkGray);
					g.drawRect(col * cellSize, row * cellSize, cellSize-1, cellSize-1);
				}
				// Draw non rendered board all grey
				else {
					// Draw random colours while not in game - weird loading menu idea
					Random r = new Random();

					if(r.nextInt(64) == 0) {

						float colourValue;
						do {
							colourValue = r.nextFloat();
						}
						while(colourValue < 0.4 || colourValue > 0.9);

						g.setColor(new Color(colourValue, colourValue, colourValue));
						g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
					}

				}



			}
	}


	/**
	 * @param g
	 * @param cell
	 * @param sprite
	 */
	public void renderSprite(Graphics g, Cell cell, Sprite sprite) {
		renderSprite(g, cell, sprite, null);
	}


	/**
	 * @param g
	 * @param cell
	 * @param sprite
	 * @param colour
	 */
	public void renderSprite(Graphics g, Cell cell, Sprite sprite, Color colour) {
		g.drawImage(sprite.getImage(), cell.getCol() * cellSize, cell.getRow() * cellSize, cellSize, cellSize, colour, null);
	}


	public void loadSprites() {
		flag = new Sprite(game.loadImage("src/sprites/flag-no-background-small.png")); 
		mine = new Sprite(game.loadImage("src/sprites/mine-no-background-small.jpg"));
	}





}
