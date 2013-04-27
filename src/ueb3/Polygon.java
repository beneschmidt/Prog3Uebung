package ueb3;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Polygon {

	private List<Point> points;

	public Polygon() {
		points = new LinkedList<>();
	}

	public void addPoint(Point point) {
		points.add(point);
	}

	public int getPointCount() {
		return points.size();
	}

	public List<Point> getPoints() {
		return points;
	}

}
