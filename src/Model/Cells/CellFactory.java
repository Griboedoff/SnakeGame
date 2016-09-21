package Model.Cells;

import Model.Point;

public class CellFactory
{
	public static BaseCell createCell(CellTypes cellType)
	{
		if (cellType == CellTypes.EMPTY)
			return new EmptyCell();
		else if (cellType == CellTypes.FOOD)
			return new FoodCell();
		throw new IllegalArgumentException(cellType.name());
	}

	public static SnakeCell createSnakeCell(int x, int y, SnakeCell to)
	{
		SnakeCell sc = new SnakeCell(x, y);
		if (to != null)
			sc.connectTo(to);
		return sc;
	}

	public static SnakeCell createSnakeCell(Point p, SnakeCell connectTo)
	{
		return createSnakeCell(p.getX(), p.getY(), connectTo);
	}
}
