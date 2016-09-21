package Model;

import Model.Cells.BaseCell;
import Model.Cells.CellFactory;
import Model.Cells.CellTypes;
import Model.Cells.EmptyCell;

import java.io.Serializable;

class GameField implements Serializable
{
	private BaseCell[][] Field;

	GameField(int width, int height)
	{
		Field = new BaseCell[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				Field[i][j] = CellFactory.createCell(CellTypes.EMPTY);
	}

	int getWidth()
	{
		return Field.length;
	}

	int getHeight()
	{
		return Field[0].length;
	}

	private boolean isInField(int x, int y)
	{
		return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
	}

	boolean isInField(Point point)
	{
		return isInField(point.getX(), point.getY());
	}

	int countEmptyCells()
	{
		int counter = 0;
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++)
				if (getCell(x, y) instanceof EmptyCell)
					counter++;
		return counter;
	}

	BaseCell getCell(int x, int y) throws IndexOutOfBoundsException
	{
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		return Field[y][x];
	}

	BaseCell getCell(Point point)
	{
		return getCell(point.getX(), point.getY());
	}

	void setCell(int x, int y, BaseCell state) throws IndexOutOfBoundsException
	{
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		Field[y][x] = state;
	}

	void setCell(Point p, BaseCell cell)
	{
		setCell(p.getX(), p.getY(), cell);
	}
}
