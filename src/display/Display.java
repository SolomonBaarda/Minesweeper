package display;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.Pair;

public class Display extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Canvas canvas = new Canvas();

	public Display(JPanel topPanel, Pair displaySizePixels) {

		//Make our program shutdown when we exit out.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Minesweeper");

		BorderLayout l = new BorderLayout();
		//l.setVgap(10);
		setLayout(l);


		add(topPanel, BorderLayout.NORTH);

		// Set the size of the canvas
		canvas.setBounds(0, 0, displaySizePixels.x, displaySizePixels.y);


		//Add our graphics component
		add(canvas);

		setResizable(false);
		
		pack();
		
		//Put our frame in the centre of the screen.
		setLocationRelativeTo(null);

		//Make our frame visible.
		setVisible(true);

		//Create our object for buffer strategy.
		canvas.createBufferStrategy(3);

		canvas.requestFocus();
	}

	public Canvas getCanvas() {
		return canvas;
	}




}
