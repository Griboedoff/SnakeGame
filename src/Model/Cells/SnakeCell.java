package Model.Cells;

import Model.Space;
//import Model.Point3d;
import Model.Snake;
//import Model.Space;
import Model.Vector;
import Swing.SwingGui.SwingPainterVisitor;

public class SnakeCell extends BaseCell
{
	private SnakeCell prev;
	private SnakeCell next;

	public SnakeCell(Vector p)
	{
		location = p;
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

	void setPrev(SnakeCell prev)
	{
		this.prev = prev;
	}

	SnakeCell getNext()
	{
		return next;
	}

	public void setNext(SnakeCell next)
	{
		this.next = next;
	}

	@Override
	public void affectSnake(Snake snake, Vector fieldVector, Space space)
	{
		snake.die();
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitSnakeCell(this, x, y);
	}
}
