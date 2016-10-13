package Model;

import java.io.Serializable;

public enum Direction implements Serializable
{
	UP, LEFT, DOWN, RIGHT, NONE;

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
}
