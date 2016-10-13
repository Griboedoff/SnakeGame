package Model.Cells;

import SwingGui.PainterVisitor;
import Model.GameField;
import Model.Point;
import Model.Snake;

public class SnakeCell extends BaseCell
{

	private SnakeCell prev;
	private SnakeCell next;
	private Point coordinates;

	public SnakeCell(int x, int y)
	{
		coordinates = new Point(x, y);
	}

	public SnakeCell(Point p)
	{
		coordinates = p;
	}

	public Point getCoordinates()
	{
		return coordinates;
	}

	public SnakeCell()
	{
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

	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		snake.setAlive(false);
	}

	@Override
	public void acceptVisitor(PainterVisitor v, int x, int y)
	{
		v.visitSnakeCell(this, x, y);
	}

}
