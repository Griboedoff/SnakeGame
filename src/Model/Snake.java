package Model;

import Model.Cells.SnakeCell;

import java.io.Serializable;

public class Snake implements Serializable
{
	private static final long serialVersionUID = 213456783;

	private SnakeCell head;
	private SnakeCell tail;
	private Vector direction;
	private int length;
	private boolean isAlive;

	Snake(Vector location, Vector direction)
	{
		head = new SnakeCell(location);
		tail = head;
		this.direction = direction;
		isAlive = true;
	}

	public Vector getLocation() { return head.getLocation(); }

	boolean isAlive()
	{
		return isAlive;
	}

	public void die()
	{
		isAlive = false;
	}

	public int getLength()
	{
		return length;
	}

	public Vector getDirection() { return direction; }

	public void setDirection(Vector direction)
	{
		if (direction.getNorm() != 1)
			throw new IllegalArgumentException("Direction must have norm 1");
		if (!this.direction.add(direction).equals(Vector.getZero(direction.getDim())))
			this.direction = direction;
	}

	public void justSetDirection(Vector direction)
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

	public Vector getNextMoveCell() { return head.getLocation().add(direction);	}

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