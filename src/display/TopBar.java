package display;

import java.awt.BorderLayout;
import java.awt.Font;
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

		setLayout(new GridLayout(1, 3));
		
		score = createLabel("");
		add(score, BorderLayout.CENTER);

		reset = createResetButton();
		add(reset, BorderLayout.CENTER);

		time = createLabel("");
		add(time, BorderLayout.CENTER);
		
	}


	public void update() {
		// Update score
		int maxFlagCount = game.getBoard().getMaxFlagCount();
		int flagsLeft = maxFlagCount - game.getBoard().getFlagCount();
		score.setText("Flags left: " +flagsLeft+ "/" +maxFlagCount);
		
		// Update time 
		time.setText("Time: " +Integer.toString(game.getGameTimeSeconds()));
	}

	public void reset() {
		score.setText("");
		time.setText("");
	}


	public JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		Font f = new Font("Arial Bold", Font.PLAIN, 24);
		label.setFont(f);
		
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
