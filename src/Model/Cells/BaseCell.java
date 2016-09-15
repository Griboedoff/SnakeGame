package Model.Cells;

import Model.Point;

public abstract class BaseCell {

	private Point coordinates;

	public Point getCoordinates() {
		return coordinates;
	}

	BaseCell(int x, int y) {
		coordinates = new Point(x, y);
	}
}