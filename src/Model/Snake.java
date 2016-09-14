package Model;

import java.awt.Point;
import java.util.Iterator;

public class Snake implements Iterable<Point> {
	private class SnakeIterator implements Iterator<Point> {
		private SnakeCell Current;

		SnakeIterator(SnakeCell start) {
			Current = start;
		}

		@Override
		public boolean hasNext() {
			return Current.getTail() == null;
		}

		@Override
		public Point next() {
			Current = Current.getTail();
			return Current.getHead().getCoordinates();
		}
	}

	private SnakeCell Head;
	private Direction Direction;

	@Override
	public Iterator<Point> iterator() {
		return new SnakeIterator(Head);
	}

	public Snake(SnakeCell head, Direction direction) {
		Head = head;
		Direction = direction;
	}

	public Model.Direction getDirection() {
		return Direction;
	}

	public void setDirection(Model.Direction direction) {
		Direction = direction;
	}

	public SnakeCell getHead() {
		return Head;
	}

	public void setHead(SnakeCell head) {
		Head = head;
	}
}