package Model;

import Model.Cells.SnakeCell;

import java.io.Serializable;

public class Snake implements Serializable
{
	private static final long serialVersionUID = 213456783;

	private SnakeCell head;
	private SnakeCell tail;
	private Point3d vectorDirection;
	private int length;
	private boolean isAlive;

	Snake(int xHead, int yHead, int zHead, Point3d direction)
	{
		this(new Point3d(xHead, yHead, zHead), direction);
	}

	Snake(Point3d point, Point3d direction)
	{
		head = new SnakeCell(point);
		tail = head;
		this.vectorDirection = direction;
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

	public Point3d getVectorDirection()
	{
		return vectorDirection;
	}

	public void setVectorDirection(Point3d vectorDirection)
	{
		if (! this.vectorDirection.add(vectorDirection).equals(new Point3d(0, 0, 0)))
			this.vectorDirection = vectorDirection;
	}

	public void justSetDirection(Point3d direction)
	{
		this.vectorDirection = direction;
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
		return head.getLocation().add(vectorDirection);
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