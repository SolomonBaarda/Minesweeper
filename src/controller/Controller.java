package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import enums.FlagType;
import main.Board;
import main.Cell;

public class Controller implements MouseListener {

	private Board board;
	private final int cellSize;
	
	public Controller(Board board, final int cellSize) {
		this.board = board;
		this.cellSize = cellSize;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int click = e.getButton();
		Point p = e.getPoint();
		
		Cell cellClicked = board.getCell(p.x / cellSize, p.y / cellSize);
				
		if(click == MouseEvent.BUTTON1) {
			cellClicked.setFlagType(FlagType.Flag);
		}
		else if(click == MouseEvent.BUTTON3) {
			cellClicked.setFlagType(FlagType.None);
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
