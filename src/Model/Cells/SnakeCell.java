package Model.Cells;

public class SnakeCell extends BaseCell
{
	private SnakeCell prev;
	private SnakeCell next;

	SnakeCell(int x, int y)
	{
		super(x, y);
	}

	public SnakeCell connectTo(SnakeCell to)
	{
		next = to;
		to.prev = this;
		return this;
	}

	public SnakeCell getPrev()
	{
		return prev;
	}

	public void setNext(SnakeCell next)
	{
		this.next = next;
	}
}
