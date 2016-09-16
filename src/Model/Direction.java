package Model;

public enum Direction
{
	UP, LEFT, DOWN, RIGHT;

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
		}
		return null;
	}
}
