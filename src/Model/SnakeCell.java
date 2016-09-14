package Model;

import java.awt.Point;

public abstract class SnakeCell {
	private Point Coordinates;
	private SnakeCell Head;
	private SnakeCell Tail;

	public SnakeCell(int x, int y, SnakeCell from) {
		Coordinates = new Point(x, y);
		Head = from;
		from.Tail = this;
	}


	public Point getCoordinates() {
		return Coordinates;
	}

	public void setCoordinates(Point coordinates) {
		Coordinates = coordinates;
	}

	public SnakeCell getHead() {
		return Head;
	}

	public void setHead(SnakeCell head) {
		Head = head;
	}

	public SnakeCell getTail() {
		return Tail;
	}

	public void setTail(SnakeCell tail) {
		Tail = tail;
	}
}
