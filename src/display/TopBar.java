package display;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Game;

public class TopBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;

	JLabel score;
	JButton reset;
	JLabel time;

	public TopBar(Game game) {
		this.game = game;

		setLayout(new GridLayout(1, 5));

		add(new JLabel(""));
		
		score = createLabel("");
		add(score);

		reset = createResetButton();
		add(reset);

		time = createLabel("");
		add(time);
		
		add(new JLabel(""));

	}


	public void update() {
		int maxFlagCount = game.getBoard().getMaxFlagCount();
		int flagsLeft = maxFlagCount - game.getBoard().getFlagCount();
		score.setText("Flags left: " +flagsLeft+ "/" +maxFlagCount);
	}

	public void reset() {
		score.setText("");
		time.setText("");
	}


	public JLabel createLabel(String text) {
		JLabel label = new JLabel(text);

		return label;
	}


	public JButton createResetButton() {
		JButton button = new JButton("Reset");

		// Add mouse listener to reset game
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				game.resetGame();
			}
		});

		return button;
	}



}
