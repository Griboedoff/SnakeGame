package Model.Cells;

import Model.Point;

public class SnakeCell extends BaseCell
{
	private SnakeCell prev;
	private SnakeCell next;
	private Point coordinates;

	SnakeCell(int x, int y)
	{
		coordinates = new Point(x, y);
	}

	public Point getCoordinates()
	{
		return coordinates;
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
