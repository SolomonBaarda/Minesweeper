package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import controller.Controller;
import display.Display;
import display.Renderer;
import display.TopBar;
import utils.Pair;

public class Game implements Runnable {

	private static final Pair DEFAULT_BOARD_SIZE = new Pair(24, 24);
	private static final int DEFAULT_CELL_SIZE = 32; // Pixels
	private static final Pair DEFAULT_DISPLAY_SIZE = new Pair(DEFAULT_BOARD_SIZE.x * DEFAULT_CELL_SIZE, DEFAULT_BOARD_SIZE.y * DEFAULT_CELL_SIZE);

	public static final int DEFAULT_MINE_COUNT = 124;

	private static final int TICKS_PER_SECOND = 8;

	private int gameTimeSeconds;
	private int tickCount;

	private Board board;
	private Controller controller;
	private Display display;

	TopBar topBar;

	private Renderer renderer;
	private boolean gameOver;

	public Game() {
		this(DEFAULT_BOARD_SIZE, DEFAULT_DISPLAY_SIZE, DEFAULT_CELL_SIZE);
	}

	public Game(Pair boardSize, Pair displaySize, int cellSize) {

		// Create board of size
		board = new Board(boardSize);
		// Create mouse listener for game
		controller = new Controller(this, cellSize);

		// Create top bar for reset game etc
		topBar = new TopBar(this);
		// Create the display
		display = new Display(topBar, displaySize);
		display.getCanvas().addMouseListener(controller);

		renderer = new Renderer(board, cellSize, this);

		//board.printBoard();
	}


	public void update() {
		if(board.isBoardGenerated()) {
			board.updateFlagCount();
			topBar.update();

			if(board.isGameWon()) {
				gameWin();
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
		double nanoSecondConversion = 1000000000.0 / TICKS_PER_SECOND; 
		double changeInSeconds = 0;

		tickCount = 0;

		while(true) {
			long now = System.nanoTime();

			changeInSeconds += (now - lastTime) / nanoSecondConversion;
			while(changeInSeconds >= 1) {
				// Always render
				render();

				if(!gameOver) {
					// Only register updates if game not over
					update();

					tickCount++;
					if(tickCount % TICKS_PER_SECOND == 0) {
						gameTimeSeconds++;
					}

				}
				changeInSeconds--;
			}

			lastTime = now;
		}
	}



	public void resetGame() {
		board.clearBoard();
		topBar.inGameTopBar();

		tickCount = 0;
		gameTimeSeconds = 0;

		gameOver = false;
	}



	public BufferedImage loadImage(String path) {

		try {
			BufferedImage img = ImageIO.read(new File(path));
			return img;

		} catch (IOException e) {
			System.out.println("Could not load image with path: " + path);
			e.printStackTrace();
			return null;
		}
	}


	public void gameWin() {
		topBar.setButtonWin();
		gameIsOver();

		printStats();
	}

	public void gameLose() {
		topBar.setButtonLose();
		gameIsOver();

		printStats();
	}


	public void gameIsOver() {
		setGameOver(true);
		render();
	}



	public void printStats() {
		ArrayList<String> stats = getGameStats();

		for(int i = 0; i < stats.size(); i++) {
			System.out.println(stats.get(i));
		}
		System.out.println();
	}


	public ArrayList<String> getGameStats() {
		ArrayList<String> stats = board.getGameStats();

		return stats;
	}




	public TopBar getTopBar() {
		return topBar;
	}

	public int getGameTimeSeconds() {
		return gameTimeSeconds;
	}

	public Board getBoard() {
		return board;
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
