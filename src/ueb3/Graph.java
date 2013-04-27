package ueb3;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph implements Iterable<Polygon> {

	private List<Polygon> polygons;

	/**
	 * @return den groessten X-Wert
	 */
	public int getMaxX() {
		int max = Integer.MIN_VALUE;
		for (Polygon polygon : polygons) {
			for (Point point : polygon.getPoints()) {
				if (point.getX() > max)
					max = (int) point.getX();
			}
		}

		return max;
	}

	/**
	 * @return den groessten Y-Wert
	 */
	public int getMaxY() {
		int max = Integer.MIN_VALUE;
		for (Polygon polygon : polygons) {
			for (Point point : polygon.getPoints()) {
				if (point.getY() > max)
					max = (int) point.getY();
			}
		}

		return max;
	}

	public Graph(int polygonCount) {
		polygons = new LinkedList<>();
	}

	public void addPolygon(Polygon polygon) {
		polygons.add(polygon);
	}

	@Override
	public Iterator<Polygon> iterator() {
		return polygons.iterator();
	}

	@Override
	public String toString() {
		return "Graph [polygons=" + polygons.size() + "]";
	}

}
