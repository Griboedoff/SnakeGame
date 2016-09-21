package Model;

import Model.Cells.CellFactory;
import Model.Cells.SnakeCell;

import java.io.Serializable;

@SuppressWarnings("SameReturnValue")
class Snake implements Serializable
{
	private SnakeCell head;
	private SnakeCell tail;

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	private Direction direction;
	private int length;

	Snake(int xHead, int yHead, Direction direction)
	{
		head = CellFactory.createSnakeCell(xHead, yHead, null);
		tail = head;
		this.direction = direction;
	}

	int getLength()
	{
		return length;
	}

	public void setHead(SnakeCell head)
	{
		this.head = head;
		length++;
	}

	public SnakeCell getTail()
	{
		return tail;
	}

	public void setTail(SnakeCell tail)
	{
		this.tail = tail;
		length--;

	}

	SnakeCell getHead()
	{
		return head;
	}
}