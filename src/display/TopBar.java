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

	public TopBar(Game game) {
		this.game = game;

		new GridLayout(1, 3);

		JLabel score = createLabel("Score");
		add(score);

		JButton reset = createResetButton();
		add(reset);

		JLabel time = createLabel("Time");
		add(time);

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
