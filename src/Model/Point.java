package Model;

import java.io.Serializable;

public class Point implements Serializable
{
	private static final long serialVersionUID = 213456783;

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

	@Override
	public String toString()
	{
		return "Point{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Point))
			return false;
		Point p = (Point) obj;
		return p.x == x && p.y == y;
	}

	Point add(Point other)
	{
		return new Point(x + other.x, y + other.y);
	}

	public Point sub(Point other)
    {
        return new Point(x - other.x, y - other.y);
    }

	public boolean isPositive()
	{
		return x > 0 && y > 0;
	}
}
