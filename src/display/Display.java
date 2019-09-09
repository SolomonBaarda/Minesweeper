package display;

import java.awt.Canvas;
import javax.swing.JFrame;

import utils.Pair;

public class Display extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas = new Canvas();

	public Display(int displaySizePixelsX, int displaySizePixelsY) {
		this(new Pair(displaySizePixelsX, displaySizePixelsY));
	}

	public Display(Pair displaySizePixels) {
		//Make our program shutdown when we exit out.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Minesweeper");

//		GridLayout l = new GridLayout(2, 1);
//		l.setVgap(10);
//		setLayout(l);
		
		// Set the size of the canvas
		canvas.setBounds(0, 0, displaySizePixels.x, displaySizePixels.y);


		
		//Add our graphics component
		add(canvas);

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
