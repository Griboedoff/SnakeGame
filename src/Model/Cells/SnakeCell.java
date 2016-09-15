package Model.Cells;

public class SnakeCell extends BaseCell {
	private SnakeCell prev;
	private SnakeCell next;

	SnakeCell(int x, int y) {
		super(x, y);
	}

//	public Point getCoordinates() {
//		return super.getCoordinates();
//	}

	public SnakeCell connectTo(SnakeCell to) {
		next = to;
		to.prev = this;
		return this;
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