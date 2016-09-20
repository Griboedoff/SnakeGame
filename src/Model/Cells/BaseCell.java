package Model.Cells;

import Model.Point;

import java.io.Serializable;

public abstract class BaseCell implements Serializable
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
