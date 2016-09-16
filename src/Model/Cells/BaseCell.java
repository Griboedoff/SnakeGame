package Model.Cells;

import Model.Point;

public abstract class BaseCell
{

	private Point coordinates;

	BaseCell(int x, int y)
	{
		coordinates = new Point(x, y);
	}

	public Point getCoordinates()
	{
		return coordinates;
	}
}
