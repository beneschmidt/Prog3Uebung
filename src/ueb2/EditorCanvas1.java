package ueb2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class EditorCanvas1 extends Panel {

	protected volatile List<Point> pointsOnMap;
	private Dimension dimension;
	private Point mousePoint;
	protected Modus modus;

	// private Cross cross;

	protected enum Modus {
		normal, insert, delete
	}

	public EditorCanvas1(Dimension dimension) {
		this.dimension = dimension;
		pointsOnMap = new LinkedList<>();
		mousePoint = new Point(0, 0);
		modus = Modus.normal;
		this.setLayout(new FlowLayout());
		this.addKeyListener(new InsertKeyListener());
		this.addMouseListener(new ClickMouseAdapter());
		this.addMouseMotionListener(new MotionMouseAdapter());
	}

	@Override
	public Dimension getMinimumSize() {
		return dimension;
	}

	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}

	@Override
	public void paint(Graphics g) {
		Point lastPoint = null;
		for (Point currentPoint : pointsOnMap) {
			drawCrossForPoint(g, currentPoint);

			// Verbindung malen
			if (lastPoint != null) {
				connectTheDots(g, lastPoint, currentPoint);
			}

			lastPoint = currentPoint;
		}

		if (pointsOnMap.size() != 0) {
			Point firstPoint = pointsOnMap.get(0);
			connectTheDots(g, lastPoint, firstPoint);
		}

		if (mousePoint != null) {
			drawCrossForPoint(g, mousePoint);
		}
	}

	private void drawCrossForPoint(Graphics g, Point currentPoint) {
		g.setColor(Color.RED);
		g.drawLine((int) currentPoint.getX() - 1,
				(int) currentPoint.getY() - 1, (int) currentPoint.getX() + 1,
				(int) currentPoint.getY() + 1);
		g.drawLine((int) currentPoint.getX() + 1,
				(int) currentPoint.getY() - 1, (int) currentPoint.getX() - 1,
				(int) currentPoint.getY() + 1);
	}

	private void connectTheDots(Graphics g, Point lastPoint, Point firstPoint) {
		g.setColor(Color.BLUE);
		g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(),
				(int) firstPoint.getX(), (int) firstPoint.getY());
	}

	/**
	 * Faengt die Tastatureingaben ab
	 * 
	 * @author Benne
	 */
	public class InsertKeyListener implements KeyListener {

		protected static final char CLEAR = 'C';
		protected static final char INSERT = 'I';

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {

			// INSERT wird gestartet
			if (e.getKeyChar() == INSERT && modus != Modus.insert) {
				modus = Modus.insert;
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
			// Bereich wird geleert
			else if (e.getKeyChar() == CLEAR && modus == Modus.insert) {
				pointsOnMap.clear();
				repaint();
			}
		}
	}
	
	/**
	 * Faengt die Maustasten ab.
	 * 
	 * @author Benne
	 */
	protected class ClickMouseAdapter extends MouseAdapter {

		int doubleClickTimer = 300; // double-click speed in ms
		long timeSinceLastClick = 0; // last mouse down time
		int lastXPosition = 0, lastYPosition = 0; // last x and y

		@Override
		public void mouseClicked(MouseEvent event) {

			// LINKS-Klick waehrend des INSERTS
			if (event.getButton() == MouseEvent.BUTTON1) {
				// DOPPEL-Klick
				if (isDoubleClick(event)) {
					modus = Modus.normal;
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else if (modus == Modus.insert) {
					pointsOnMap.add(new Point(event.getX(), event.getY()));
					repaint();
				}
			}
		}

		/**
		 * @param event
		 * @return Doppelklick ausgefuehrt oder nicht
		 */
		protected boolean isDoubleClick(MouseEvent event) {
			if ((lastXPosition == event.getX())
					&& (lastYPosition == event.getY())
					&& ((event.getWhen() - timeSinceLastClick) < doubleClickTimer)) {
				return true;
			} else {
				timeSinceLastClick = event.getWhen();
				lastXPosition = event.getX();
				lastYPosition = event.getY();
				return false;
			}
		}
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
