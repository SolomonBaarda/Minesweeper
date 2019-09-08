package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {

	public Controller() {
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int click = e.getButton();
		
		if(click == MouseEvent.BUTTON1) {
			
		}
		else if(click == MouseEvent.BUTTON2) {
			
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
