package Model;

import java.util.Iterator;

public class Snake {

	private SnakeCell head;
    private SnakeCell tail;
	private Direction direction;

	public Snake(int xHead, int yHead, Direction direction) {
		head = new SnakeCell(xHead, yHead, null);
        tail = head;
		this.direction = direction;
	}

	public Model.Direction getDirection() {
		return direction;
	}

	public SnakeCell getHead() {
		return head;
	}

	public SnakeCell getTail() {
		return tail;
	}

	public void step(Direction newDirection) {
        if (newDirection != null)
            this.direction = newDirection;
        Point nextCell = head.sumWith(direction.getVector());

    }
}