package ueb3;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {

	private Panel panel;
	private Graph graph;

	public MainFrame(Dimension dimension) {
		super();
		this.setSize(dimension);
		panel = new Panel();
		this.setLayout(new FlowLayout());
		this.add(panel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void drawGraph(Graph graph) {
		this.graph = graph;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		for (Polygon polygon : graph) {
			Point lastPoint = null;
			for (Point point : polygon.getPoints()) {
				if (lastPoint != null) {
					g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) point.getX(), (int) point.getY());
				}
				lastPoint = point;
			}
			
			Point firstPoint = polygon.getPoints().get(0);
			if (lastPoint != null) {
				g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) firstPoint.getX(), (int) firstPoint.getY());
			}
		}
	}
}
