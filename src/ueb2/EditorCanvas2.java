package ueb2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorCanvas2 extends EditorCanvas1 {

	private Point mousePoint;

	public EditorCanvas2(Dimension dimension) {
		super(dimension);
		mousePoint = new Point(0, 0);
		this.addMouseMotionListener(new MotionMouseAdapter());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (mousePoint != null) {
			drawBigCrossForPoint(g, mousePoint);
		}
	}

	private void drawBigCrossForPoint(Graphics g, Point currentPoint) {
		g.setColor(Color.RED);
		g.drawLine((int) currentPoint.getX() - 3, (int) currentPoint.getY() - 3, (int) currentPoint.getX() + 3, (int) currentPoint.getY() + 3);
		g.drawLine((int) currentPoint.getX() + 3, (int) currentPoint.getY() - 3, (int) currentPoint.getX() - 3, (int) currentPoint.getY() + 3);
	}

	/**
	 * Beschaeftigt sich mit der Bewegung der Maus ueber das Panel
	 * 
	 * @author Benne
	 */
	private class MotionMouseAdapter extends MouseAdapter {

		@Override
		public void mouseMoved(MouseEvent e) {
			mousePoint.setLocation(e.getX(), e.getY());
			repaint();
		}
	}
}
