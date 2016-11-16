package Model;

import Model.Cells.SnakeCell;

import java.io.Serializable;

@SuppressWarnings("SameReturnValue")
public class Snake implements Serializable
{
	private SnakeCell head;
	private SnakeCell tail;
	private Direction direction;
	private int length;
	private boolean isAlive;

	boolean isAlive()
	{
		return isAlive;
	}

	public void setAlive(boolean alive)
	{
		isAlive = alive;
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
		if (! (direction == this.direction.reverse()) )
		this.direction = direction;
	}

	public void justSetDirection(Direction direction)
    {
        this.direction = direction;
    }

	public void setHead(SnakeCell newHead)
	{
		if (newHead != null)
			newHead.connectTo(this.head);
		this.head = newHead;
	}

	public void justSetHead(SnakeCell newHead) {this.head = newHead; }

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

	public Point getNextMoveCell()
	{
		return head.getCoordinates().add(direction.getVector());
	}

	Snake(int xHead, int yHead, Direction direction)
	{
		head = new SnakeCell(xHead, yHead);
		tail = head;
		this.direction = direction;
		isAlive = true;
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