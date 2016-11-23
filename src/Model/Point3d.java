package Model;

import java.io.Serializable;

public class Point3d implements Serializable
{
	private static final long serialVersionUID = 213456783;

	private int x;
	private int y;
	private int z;

	public Point3d(int x, int y, int z)
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
		return "Point3d{" +
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

		Point3d point = (Point3d) o;

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

	Point3d add(Point3d other)
	{
		return new Point3d(x + other.x, y + other.y, z + other.z);
	}

	public Point3d sub(Point3d other)
	{
		return new Point3d(x - other.x, y - other.y, z - other.z);
	}

	public boolean isPositive()
	{
		return x > 0 && y > 0 && z > 0;
	}
}