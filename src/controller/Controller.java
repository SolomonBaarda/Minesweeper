package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import display.TopBar;
import enums.FlagType;
import main.Board;
import main.Cell;
import main.Game;
import utils.Pair;

public class Controller implements MouseListener {

	private Game game;
	private Board board;
	private final int cellSize;

	public Controller(Game game, int cellSize) {
		this.game = game;
		this.board = game.getBoard();
		this.cellSize = cellSize;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// Don't allow mouse clicks when the game is finished 
		if(!game.isGameOver()) {

			int click = e.getButton();
			Point p = e.getPoint();

			int cellX = p.x / cellSize;
			int cellY = p.y / cellSize;

			// Don't allow mouse clicks when the board has not been generated 
			if(board.isBoardGenerated()) {

				Cell cellClicked = board.getCell(cellX, cellY);

				// Left click
				if(click == MouseEvent.BUTTON1) {
					// Flag on tile
					if(cellClicked.getFlagType() != FlagType.Flag) {
						// Click all nearby cells 
						board.smartClick(cellClicked);

						// Mine
						if(cellClicked.isMine()) {
							// Game over
							game.gameLose();
						}
					}
				}

				// Right click
				else if(click == MouseEvent.BUTTON3) {
					if(!cellClicked.isClicked()) {
						// Toggle between flag types
						if(cellClicked.getFlagType() == FlagType.None) {
							if(board.getFlagCount() < board.getMaxFlagCount()) {
								cellClicked.setFlagType(FlagType.Flag);
							}
						}
						else if(cellClicked.getFlagType() == FlagType.Flag) {
							cellClicked.setFlagType(FlagType.Suspected);
						}
						else if(cellClicked.getFlagType() == FlagType.Suspected) {
							cellClicked.setFlagType(FlagType.None);
						}
					}
				}
			}
			// Generate board if not done 
			else {
				
				int mineCount = game.getTopBar().getMinesToGenerate();
				game.getTopBar().inGameTopBar();
				game.resetGame();
				game.getTopBar().setInGame(true);
				
				board.generate(new Pair(cellX, cellY), mineCount);
				
				board.smartClick(board.getCell(cellX, cellY));
			}
		}

	}




	@Override
	public void mouseClicked(MouseEvent arg0) {	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}




}
