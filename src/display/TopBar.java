package display;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Game;

public class TopBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;

	private boolean inGame;
	
	// Pre-game interface
	JLabel mineLabel;
	JTextField mineField;

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
		
		mineLabel = createLabel("");
		mineField = createTextField("");

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
		
		mineLabel.setText("Mine count: ");
		mineField.setText(Integer.toString(Game.DEFAULT_MINE_COUNT));
		
		add(mineLabel, BorderLayout.WEST);
		add(mineField, BorderLayout.EAST);
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


	public JButton createResetButton() {
		JButton button = new JButton(happyFace);

		// Add mouse listener to reset game
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
