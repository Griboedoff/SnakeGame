package Model.Cells;

import Model.Point;

public class CellFactory
{
	public static BaseCell createCell(CellTypes cellType, int x, int y)
	{
		if (cellType == CellTypes.EMPTY)
			return new EmptyCell(x, y);
		else if (cellType == CellTypes.FOOD)
			return new FoodCell(x, y);
		throw new IllegalArgumentException(cellType.name());
	}

	public static SnakeCell createSnakeCell(int x, int y, SnakeCell to)
	{
		SnakeCell sc = new SnakeCell(x, y);
		if (to!= null)
			sc.connectTo(to);
		return sc;
	}

	public static SnakeCell createSnakeCell(Point p, SnakeCell connectTo)
	{
		return createSnakeCell(p.getX(), p.getY(), connectTo);
	}

	public static BaseCell createCell(CellTypes cellType, Point location)
	{
		return createCell(cellType, location.getX(), location.getY());
	}
}
