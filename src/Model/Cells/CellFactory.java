package Model.Cells;

import Model.Point;

public class CellFactory {
	public static BaseCell createCell(CellTypes cellType, int x, int y) {
		if (cellType == CellTypes.EMPTY)
			return new EmptyCell(x, y);
		else if (cellType == CellTypes.FOOD)
			return new FoodCell(x, y);
		else if (cellType == CellTypes.SNAKE)
			return new SnakeCell(x, y);
		throw new IllegalArgumentException(cellType.name());
	}

	public static BaseCell createCell(CellTypes cellType, Point location) {
		return createCell(cellType, location.getX(), location.getY());
	}
}
