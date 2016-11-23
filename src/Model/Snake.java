package Model;

import Model.Cells.SnakeCell;

import java.io.Serializable;

public class Snake implements Serializable
{
	private static final long serialVersionUID = 213456783;

	private SnakeCell head;
	private SnakeCell tail;
	private Direction direction;
	private int length;
	private boolean isAlive;

	Snake(int xHead, int yHead, int zHead, Direction direction)
	{
		this(new Point3d(xHead, yHead, zHead), direction);
	}

	Snake(Point3d point, Direction direction)
	{
		head = new SnakeCell(point);
		tail = head;
		this.direction = direction;
		isAlive = true;
	}

	boolean isAlive()
	{
		return isAlive;
	}

	public void setDead()
	{
		isAlive = false;
	}

	public int getLength()
	{
		return length;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		if (!(direction == this.direction.reverse()))
			this.direction = direction;
	}

	public void justSetDirection(Direction direction)
	{
		this.direction = direction;
	}

	public void justSetHead(SnakeCell newHead)
	{
		this.head = newHead;
	}

	public SnakeCell getTail()
	{
		return tail;
	}

	public void setTail(SnakeCell tail)
	{
		this.tail = tail;
	}

	public SnakeCell getHead()
	{
		return head;
	}

	public void setHead(SnakeCell newHead)
	{
		if (newHead != null)
			newHead.connectTo(this.head);
		this.head = newHead;
	}

	public Point3d getNextMoveCell()
	{
		return head.getLocation().add(direction.getVector());
	}

	public void updateHead(SnakeCell newHead)
	{
		newHead.connectTo(head);
		head = newHead;
		length++;
	}

	public void deleteTail()
	{
		tail = tail.getPrev();
		tail.setNext(null);
		length--;
	}
}