package ueb1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

public class Decoration extends Panel {

	public enum Orientation {
		HORIZONTAL, VERTICAL
	}

	/** Die minimale Dimension des Components */
	private Dimension size;
	/** Fuellfarben, die abwechselnd gemalt werden sollen */
	private Color fillColor1, fillColor2;
	/** Orientierung der Linien */
	private Orientation orientation;

	public Decoration(Orientation orientation, int width, int height, Color fillColor1, Color fillColor2) {
		super();
		this.fillColor1 = fillColor1;
		this.fillColor2 = fillColor2;
		this.size = new Dimension(width, height);
		this.orientation = orientation;
	}

	@Override
	public Dimension getMinimumSize() {
		return size;
	}

	@Override
	public Dimension getPreferredSize() {
		return size;
	}

	@Override
	public void paint(Graphics g) {
		/*
		 * Horizontale Linien nebeneinander gestapelt bis zum Ende
		 */
		if (orientation == Orientation.HORIZONTAL) {
			int height = 0;
			while (height < size.getHeight()) {
				g.setColor(fillColor1);
				for (int j = 0; j <= 2; j++, height++)
					g.drawLine(0, height, (int) size.getWidth(), height);
				g.setColor(fillColor2);
				for (int j = 0; j <= 2; j++, height++)
					g.drawLine(0, height, (int) size.getWidth(), height);
			}
		}
		/*
		 * sonst Vertikale Linien nebeneinander gestapelt bis zum Ende
		 */
		else {
			int width = 0;
			while (width < size.getWidth()) {
				g.setColor(fillColor1);
				for (int j = 0; j <= 2; j++, width++)
					g.drawLine(width, 0, width, (int) size.getHeight());
				g.setColor(fillColor2);
				for (int j = 0; j <= 2; j++, width++)
					g.drawLine(width, 0, width, (int) size.getHeight());
			}
		}
	}
}
