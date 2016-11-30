package Model;

import java.io.Serializable;

import static java.lang.Math.abs;

public class Point2d implements Serializable
{
	private static final long serialVersionUID = 213456783;

	private final int x;
	private final int y;

	public Point2d(int x, int y)
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

	public int getLength()
	{
		return abs(x) + abs(y);
	}

	@Override
	public String toString()
	{
		return "Point2d{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Point2d point = (Point2d) o;

		return x == point.x && y == point.y;
	}

	@Override
	public int hashCode()
	{
		int result = x;
		result = 31 * result + y;
		return result;
	}

	Point2d add(Point2d other)
	{
		return new Point2d(x + other.x, y + other.y);
	}

	public Point2d sub(Point2d other)
	{
		return new Point2d(x - other.x, y - other.y);
	}

	public boolean isPositive()
	{
		return x > 0 && y > 0;
	}
}
