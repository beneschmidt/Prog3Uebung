package ueb2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

public class Cross extends Panel {

	private static final Dimension dimension = new Dimension(10, 10);

	public Cross() {
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
		g.drawLine(0, 0, 10, 10);
		g.drawLine(10, 0, 0, 10);
	}
}
