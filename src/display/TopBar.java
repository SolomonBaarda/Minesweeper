package display;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Game;
import utils.Pair;

public class TopBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;

	private boolean inGame;

	// Pre-game interface
	JLabel mineCount, dimensionX, dimensionY;
	JTextField mineValue, dimValueX, dimValueY;
	JButton generate;

	// In-game interface
	JLabel score, time;
	JButton reset;
	ImageIcon happyFace, sunglassesFace, deadFace;

	private Font f = new Font("Arial Bold", Font.PLAIN, 24);

	public TopBar(Game game) {
		this.game = game;

		loadResetButtonIcons();

		inGame = false;

		score = createLabel("");
		reset = createResetButton();
		time = createLabel("");

		mineCount = createLabel("");
		mineValue = createTextField("");
		generate = createGenerateButton();

		preGameTopBar();
	}


	public void inGameTopBar() {
		removeAll();
		setLayout(new GridLayout(1, 3));

		score.setText("");
		time.setText("");
		setButtonReset();

		add(score, BorderLayout.CENTER);
		add(reset, BorderLayout.CENTER);
		add(time, BorderLayout.CENTER);

		score.setVisible(true);

		update();
	}


	public void preGameTopBar() {
		removeAll();
		setLayout(new GridLayout(1, 1));

		mineCount.setText("Mine count: ");
		mineValue.setText(Integer.toString(Game.DEFAULT_MINE_COUNT));

		add(mineCount, BorderLayout.WEST);
		add(mineValue, BorderLayout.EAST);
	}


	public void update() {
		if(inGame) {
			// Update score
			int maxFlagCount = game.getBoard().getMaxFlagCount();
			int flagsLeft = maxFlagCount - game.getBoard().getFlagCount();
			score.setText("Flags left: " +flagsLeft+ "/" +maxFlagCount);

			// Update time 
			time.setText("Time: " +Integer.toString(game.getGameTimeSeconds()));
		}
		else {

		}
	}


	public JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(f);

		return label;
	}

	public JTextField createTextField(String label) {
		JTextField text = new JTextField(label);
		text.setFont(f);

		return text;

	}

	public JButton createGenerateButton() {
		JButton button = new JButton("generate");

		// Add mouse listener to reset game
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getBoard().initialise(new Pair(24, 24));
			}
		});

		return button;
	}

	public JButton createResetButton() {
		JButton button = new JButton(happyFace);

		// Add mouse listener to reset game
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.resetGame();
				preGameTopBar();
			}
		});

		return button;
	}


	public void loadResetButtonIcons() {
		Image happy = game.loadImage("src/sprites/happy-face.png");
		happyFace = new ImageIcon(happy);

		Image sunglasses = game.loadImage("src/sprites/sunglasses-face.png");
		sunglassesFace = new ImageIcon(sunglasses);

		Image dead = game.loadImage("src/sprites/dead-face.png");
		deadFace = new ImageIcon(dead);
	}





	public int getMinesToGenerate() {
		int mines;

		try {
			mines = Integer.parseInt(mineValue.getText());
		}
		catch(java.lang.NumberFormatException e) {
			System.out.println("You did not enter a number.  Generating with default of " +Game.DEFAULT_MINE_COUNT +".");
			mines = Game.DEFAULT_MINE_COUNT;
		}

		return mines;
	}


	public boolean isInGame() {
		return inGame;
	}


	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}


	public void setButtonWin() {
		reset.setIcon(sunglassesFace);
	}

	public void setButtonReset() {
		reset.setIcon(happyFace);
	}

	public void setButtonLose() {
		reset.setIcon(deadFace);
	}




}
