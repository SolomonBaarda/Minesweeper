package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import controller.Controller;
import display.Display;
import display.Renderer;
import utils.Pair;

public class Game implements Runnable {

	private static final Pair DEFAULT_BOARD_SIZE = new Pair(32, 32);
	private static final int DEFAULT_CELL_SIZE = 32; // Pixels
	private static final Pair DEFAULT_DISPLAY_SIZE = new Pair(DEFAULT_BOARD_SIZE.x * DEFAULT_CELL_SIZE, DEFAULT_BOARD_SIZE.y * DEFAULT_CELL_SIZE);

	private static final int DEFAULT_MINE_COUNT = 2;

	private Board board;
	private Controller controller;
	private Display display;

	private Renderer renderer;
	private boolean gameOver;

	public Game() {
		this(DEFAULT_BOARD_SIZE, DEFAULT_DISPLAY_SIZE, DEFAULT_CELL_SIZE, DEFAULT_MINE_COUNT);
	}

	public Game(Pair boardSize, Pair displaySize, int cellSize, int mineCount) {

		board = new Board(boardSize, mineCount);
		controller = new Controller(this, board, cellSize);

		display = new Display(displaySize);
		display.getCanvas().addMouseListener(controller);

		renderer = new Renderer(board, cellSize);
		
		//board.printBoard();
	}


	public void update() {
		if(!gameOver) {
			if(board.isGameWon()) {
				printWin();
				setGameOver(true);
				render();
			}
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
	
	
	public void printWin() {
		System.out.println("You win!");
		printStats();
	}
	
	public void printLose() {
		System.out.println("You lose!");
		printStats();
	}
	
	public void printStats() {
		String[] stats = board.getGameStats();
		
		for(int i = 0; i < stats.length; i++) {
			System.out.println(stats[i]);
		}
		System.out.println();
	}



	public Renderer getRenderer() {
		return renderer;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}
}
