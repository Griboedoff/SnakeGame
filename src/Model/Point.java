package Model;

import java.io.Serializable;

public class Point implements Serializable
{
	private int x;
	private int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	Point add(Point other)
	{
		return new Point(x + other.x, y + other.y);
	}
}
