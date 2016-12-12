package Model;

import static java.lang.Math.abs;

public class Vector
{
    private int[] value;

    public Vector(int[] value)
    {
        this.value = value;
    }

    public int getCoord(int num) { return value[num]; }

    public int getDim() { return value.length; }

    public int[] getValue()
    {
        int[] res = new int[getDim()];
        for (int i = 0; i < getDim(); i++)
            res[i] = getCoord(i);
        return res;
    }

    public Vector add(Vector other) throws ArithmeticException
    {
        if (getDim() != other.getDim())
            throw new ArithmeticException("Vectors must have equal dimensions");
        int[] res_value = new int[getDim()];
        for (int i = 0; i < getDim(); i++)
            res_value[i] = this.getCoord(i) + other.getCoord(i);
        return new Vector(res_value);
    }

    public Vector mul(int multiplier)
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

    public int getNorm()
    {
        int res = 0;
        for (int i = 0; i < getDim(); i++)
            res += abs(getCoord(i));
        return res;
    }

    public static Vector getZero(int dim)
    {
        return new Vector(new int[dim]);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        for (int i = 0; i < getDim(); i++)
            if (value[i] != vector.getCoord(i))
                return false;
        return true;
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
