package Model;

public class SnakeCell extends CellState{
	private SnakeCell prev;
	private SnakeCell next;

	public SnakeCell(int x, int y, SnakeCell nextOne) {
		super(x, y);
		prev = nextOne;
		nextOne.prev = this;
	}


	public Point getCoordinates() {
		return super.getCoordinates();
	}

	public SnakeCell getPrev() {
		return prev;
	}

	public void setPrev(SnakeCell prev) {
		this.prev = prev;
	}

	public SnakeCell getNext() {
		return next;
	}

	public void setNext(SnakeCell next) {
		this.next = next;
	}
}
