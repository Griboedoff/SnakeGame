package Model;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.Serializable;

public enum Direction implements Serializable
{
	UP, LEFT, DOWN, RIGHT, NONE;

	private static final long serialVersionUID = 213456783;

	public Point getVector()
	{
		switch (this)
		{
			case UP:
				return new Point(0, 1);
			case DOWN:
				return new Point(0, -1);
			case RIGHT:
				return new Point(1, 0);
			case LEFT:
				return new Point(-1, 0);
			default:
				return new Point(0, 0);
		}
	}

	public static Direction byPoint(Point point) throws ValueException
	{
		int x = point.getX();
		int y = point.getY();
		if (x > 0 && y == 0)
			return Direction.RIGHT;
		if (x < 0 && y == 0)
			return Direction.LEFT;
		if (x == 0 && y > 0)
			return Direction.UP;
		if (x == 0 && y < 0)
			return Direction.DOWN;
		if (x == 0 && y == 0)
			return Direction.NONE;
		throw new ValueException("No direction for vector");
	}

	public Direction reverse()
	{
		switch (this)
		{
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			default:
				return NONE;
		}
	}

	public static Direction fromString(Object selectedItem)
	{
		if (selectedItem instanceof String)
		{
			String direction = (String) selectedItem;
			switch (direction)
			{
				case "UP":
					return Direction.UP;
				case "DOWN":
					return Direction.DOWN;
				case "RIGHT":
					return Direction.RIGHT;
				case "LEFT":
					return Direction.LEFT;
			}
		}
		return NONE;
	}

}
