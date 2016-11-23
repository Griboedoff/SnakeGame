package Model;

import java.io.Serializable;

public class Point implements Serializable
{
	private static final long serialVersionUID = 213456783;

	private int x;
	private int y;
	private int z;

	public Point(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getZ()
	{
		return z;
	}

	@Override
	public String toString()
	{
		return "Point{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Point point = (Point) o;

		return x == point.x && y == point.y && z == point.z;
	}

	@Override
	public int hashCode()
	{
		int result = x;
		result = 31 * result + y;
		result = 31 * result + z;
		return result;
	}

	Point add(Point other)
	{
		return new Point(x + other.x, y + other.y, z + other.z);
	}

	public Point sub(Point other)
	{
		return new Point(x - other.x, y - other.y, z - other.z);
	}

	public boolean isPositive()
	{
		return x > 0 && y > 0 && z > 0;
	}
}
