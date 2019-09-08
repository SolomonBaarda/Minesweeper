package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import controller.Controller;
import display.Display;
import display.Renderer;
import utils.Pair;

public class Game implements Runnable {

	public static final Pair DEFAULT_BOARD_SIZE = new Pair(32, 32);
	public static final int DEFAULT_CELL_SIZE = 16; // Pixels
	public static final Pair DEFAULT_DISPLAY_SIZE = new Pair(DEFAULT_BOARD_SIZE.x * DEFAULT_CELL_SIZE, DEFAULT_BOARD_SIZE.y * DEFAULT_CELL_SIZE);

	private Board board;
	private Controller controller;
	private Display display;

	private Renderer renderer;
	private boolean gameOver;

	public Game() {
		board = new Board(DEFAULT_BOARD_SIZE);

		controller = new Controller();
		display = new Display(DEFAULT_DISPLAY_SIZE);
		display.getCanvas().addKeyListener(controller);

		renderer = new Renderer(board, DEFAULT_CELL_SIZE);

		//board.printBoard();
	}


	public void update() {
		if(!gameOver) {
			
		}

		//System.out.println("Update complete");
	}



	public void render() {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			// Create 3 buffers for the game.
			display.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		renderer.render(g);

		g.dispose();
		bs.show();

		//System.out.println("Render complete");
	}




	@Override
	public void run() {
		long lastTime = System.nanoTime(); //long 2^63
		double nanoSecondConversion = 1000000000.0 / 8; // 8 ticks per second
		double changeInSeconds = 0;

		while(!gameOver) {
			long now = System.nanoTime();

			changeInSeconds += (now - lastTime) / nanoSecondConversion;
			while(changeInSeconds >= 1) {
				// Update both at the same time
				update();
				render();
				changeInSeconds--;
			}
			// Update graphics here for best rendering 
			//render();

			lastTime = now;
		}
	}



	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}
}
