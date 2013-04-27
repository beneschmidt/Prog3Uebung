package ueb1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

public class DrawingCanvas extends Panel {

	private int width, height;

	public DrawingCanvas(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(width, height);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawLine(0, 0, width, height);
		g.drawLine(0, height, width, 0);
	}

}
