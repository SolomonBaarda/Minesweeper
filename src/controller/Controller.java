package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import enums.CellContent;
import enums.FlagType;
import main.Board;
import main.Cell;
import main.Game;

public class Controller implements MouseListener {

	private Game game;
	private Board board;
	private final int cellSize;
	
	public Controller(Game game, Board board, int cellSize) {
		this.game = game;
		this.board = board;
		this.cellSize = cellSize;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int click = e.getButton();
		Point p = e.getPoint();
		
		Cell cellClicked = board.getCell(p.x / cellSize, p.y / cellSize);
				
		// Left click
		if(click == MouseEvent.BUTTON1) {
			if(cellClicked.getContent() == CellContent.Empty) {
				
			}
			else if(cellClicked.getContent() == CellContent.Mine) {
				game.setGameOver(true);
				System.out.println("Game over!");
			}
		}
		// Right click
		else if(click == MouseEvent.BUTTON3) {
			// Toggle between flag types
			if(cellClicked.getFlagType() == FlagType.None) {
				cellClicked.setFlagType(FlagType.Flag);
			}
			else if(cellClicked.getFlagType() == FlagType.Flag) {
				cellClicked.setFlagType(FlagType.Suspected);
			}
			else if(cellClicked.getFlagType() == FlagType.Suspected) {
				cellClicked.setFlagType(FlagType.None);
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
