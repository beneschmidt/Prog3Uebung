package ueb2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class EditorCanvas3 extends EditorCanvas1 {

	private Point mousePoint;

	public EditorCanvas3(Dimension dimension) {
		super(dimension);
		mousePoint = new Point(0, 0);
		this.addMouseMotionListener(new MotionMouseAdapter());

		this.removeKeyListener(this.getKeyListeners()[0]);
		this.addKeyListener(new ExtendedKeyListener());

		this.removeMouseListener(this.getMouseListeners()[0]);
		this.addMouseListener(new ExtendedClickMouserAdapter());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (mousePoint != null) {
			drawRectAroundMouse(g, mousePoint);
		}
	}

	/**
	 * Malt ein Rechteck um den Mauszeiger. Liegen Punkte unter diesem Rechteck,
	 * wird es ausgefuellt, ansonsten sieht man nur den Rand.
	 * 
	 * @param g
	 * @param currentPoint
	 */
	private void drawRectAroundMouse(Graphics g, Point currentPoint) {
		if (modus == Modus.delete) {
			g.setColor(Color.RED);

			Rectangle mouseRect = getRectangleAroundMouse();

			if (hasDotsInRectangle(mouseRect))
				g.fillRect((int) mouseRect.getX(), (int) mouseRect.getY(),
						(int) mouseRect.getWidth(), (int) mouseRect.getHeight());
			else
				g.drawRect((int) mouseRect.getX(), (int) mouseRect.getY(),
						(int) mouseRect.getWidth(), (int) mouseRect.getHeight());
		}
	}

	/**
	 * @return das Rechteck, das unter der Maus gezeichnet wird/werden soll
	 */
	private Rectangle getRectangleAroundMouse() {
		return new Rectangle((int) mousePoint.getX() - 30,
				(int) mousePoint.getY() - 30, 60, 60);
	}

	/**
	 * @param rect
	 * @return hat das uebergebene Rechteck Punkte auf der Map?
	 */
	private boolean hasDotsInRectangle(Rectangle rect) {
		for (Point point : pointsOnMap) {
			if (isPointInRectangleRange(rect, point))
				return true;
		}
		return false;
	}

	/**
	 * @param rect
	 * @param point
	 * @return liegt der Punkt im Rechteck?
	 */
	private boolean isPointInRectangleRange(Rectangle rect, Point point) {
		if (point.getX() >= rect.getX()
				&& point.getX() <= rect.getX() + rect.getWidth()
				&& point.getY() >= rect.getY()
				&& point.getY() <= rect.getY() + rect.getWidth())
			return true;
		else
			return false;
	}

	/**
	 * Loescht alle Punkte, die unter dem uebergebenen Rechteck liegen
	 */
	private void deleteUnderlayingButtons(Rectangle rectangle) {
		for (Point point : pointsOnMap) {
			if (isPointInRectangleRange(rectangle, point)) {
				LinkedList<Point> tempList = new LinkedList<>(pointsOnMap);
				tempList.remove(point);
				pointsOnMap = new LinkedList<>(tempList);
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

	private class ExtendedClickMouserAdapter extends ClickMouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			super.mouseClicked(event);

			if (event.getButton() == MouseEvent.BUTTON1) {
				if (modus == Modus.delete) {
					Rectangle rectangle = getRectangleAroundMouse();
					deleteUnderlayingButtons(rectangle);
					repaint();
				}
			}
		}
	}

	/**
	 * Erweitert den InsertKeyListener um die DELETE-Funktion
	 * @author schmidtb
	 */
	public class ExtendedKeyListener extends InsertKeyListener {

		private static final char DELETE = 'D';

		@Override
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);

			// DELETE wird gestartet
			if (e.getKeyChar() == DELETE && modus != Modus.delete) {
				modus = Modus.delete;
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));				
			}
		}
	}
}
