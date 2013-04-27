package ueb1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ueb1.Decoration.Orientation;

public class MainFrame extends Frame {

	private static final int DRAWING_CANVAS_HEIGHT = 300;
	private static final int DRAWING_CANVAS_WIDTH = 300;
	private static final Dimension FRAME_DIMENSION = new Dimension(400, 400);

	public MainFrame() {
		this.setSize(FRAME_DIMENSION);
		DrawingCanvas drawingCanvas = new DrawingCanvas(DRAWING_CANVAS_WIDTH, DRAWING_CANVAS_HEIGHT);
		Decoration upper = new Decoration(Orientation.VERTICAL, 300 + (2 * 50), 50, Color.BLUE, Color.GREEN);
		Decoration lower = new Decoration(Orientation.VERTICAL, 300 + (2 * 50), 50, Color.BLUE, Color.GREEN);
		Decoration left = new Decoration(Orientation.HORIZONTAL, 50, 300 + (2 * 50), Color.BLUE, Color.GREEN);
		Decoration right = new Decoration(Orientation.HORIZONTAL, 50, 300 + (2 * 50), Color.BLUE, Color.GREEN);
		BorderLayout layout = new BorderLayout();
		drawingCanvas.setLayout(layout);
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(upper, BorderLayout.NORTH);
		this.add(lower, BorderLayout.SOUTH);
		this.add(drawingCanvas, BorderLayout.CENTER);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		drawingCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}
