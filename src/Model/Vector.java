package Model;

import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class Vector
{
	private final int[] value;

	public Vector(int[] value)
	{
		this.value = value;
	}

	static Vector getZero(int dim)
	{
		return new Vector(new int[dim]);
	}

	int getCoord(int num)
	{
		return value[num];
	}

	int getDim()
	{
		return value.length;
	}

	int[] getValue()
	{
		int[] res = new int[getDim()];
		for (int i = 0; i < getDim(); i++)
			res[i] = getCoord(i);
		return res;
	}

	Vector add(Vector other) throws ArithmeticException
	{
		if (getDim() != other.getDim())
			throw new ArithmeticException("Vectors must have equal dimensions");
		int[] res_value = new int[getDim()];
		for (int i = 0; i < getDim(); i++)
			res_value[i] = this.getCoord(i) + other.getCoord(i);
		return new Vector(res_value);
	}

	private Vector mul(int multiplier)
	{
		int[] res_value = new int[getDim()];
		for (int i = 0; i < getDim(); i++)
			res_value[i] = getCoord(i) * multiplier;
		return new Vector(res_value);
	}

	public Vector sub(Vector other) throws ArithmeticException
	{
		return this.add(other.mul(-1));
	}

	int getNorm()
	{
		return IntStream.range(0, getDim())
				.map(i -> abs(getCoord(i)))
				.sum();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null || getClass() != o.getClass()) return false;
		Vector vector = (Vector) o;
		return IntStream.range(0, getDim())
				.noneMatch(i -> value[i] != vector.getCoord(i));
	}

	@Override
	public int hashCode()
	{
		int res = 0;
		int mlt = 1;
		for (int i = 0; i < getDim(); i++)
		{
			res += value[i] * mlt;
			mlt *= 2;
		}
		return res;
	}

	public String toString()
	{
		String res = "(";
		for (int i = 0; i < getDim(); i++)
			res += Integer.toString(value[i]) + ", ";
		return res + ")";
	}
}
