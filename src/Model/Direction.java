package Model;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.HashMap;

public enum Direction implements Serializable
{
	UP, LEFT, DOWN, RIGHT, NONE;

	private static final long serialVersionUID = 213456783;

	private static final HashMap<String, Direction> fromString;
	private static final HashMap<Direction, Direction> reverseDirection;

	static
	{
		fromString = new HashMap<>();
		fromString.put("UP", Direction.UP);
		fromString.put("DOWN", Direction.DOWN);
		fromString.put("RIGHT", Direction.RIGHT);
		fromString.put("LEFT", Direction.LEFT);

		reverseDirection = new HashMap<>();
		reverseDirection.put(UP, DOWN);
		reverseDirection.put(DOWN, UP);
		reverseDirection.put(LEFT, RIGHT);
		reverseDirection.put(RIGHT, LEFT);
		reverseDirection.put(NONE, NONE);
	}

	public static Direction fromPoint(Point3d point) throws InvalidParameterException
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
		throw new InvalidParameterException("No direction for vector");
	}

	public static Direction fromString(Object selectedItem)
	{

		if (selectedItem instanceof String)
		{
			String direction = (String) selectedItem;
			return fromString.get(direction);
		}
		return NONE;
	}

	public Point2d getVector()
	{
		switch (this)
		{
			case UP:
				return new Point2d(0, 1);
			case DOWN:
				return new Point2d(0, -1);
			case RIGHT:
				return new Point2d(1, 0);
			case LEFT:
				return new Point2d(-1, 0);
			default:
				return new Point2d(0, 0);
		}
	}

	public Direction reverse()
	{
		return reverseDirection.get(this);
	}

}
