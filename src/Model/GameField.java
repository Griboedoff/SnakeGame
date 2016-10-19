package Model;

import Model.Cells.BaseCell;
import Model.Cells.EmptyCell;

import java.io.Serializable;

public class GameField implements Serializable
{
	private BaseCell[][] field;

	GameField(int width, int height)
	{
		field = new BaseCell[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				field[i][j] = new EmptyCell();
	}

	public int getHeight()
	{
		return field.length;
	}

	public int getWidth()
	{
		return field[0].length;
	}

	public boolean isInField(int x, int y)
	{
		return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
	}

	public boolean isInField(Point point)
	{
		return isInField(point.getX(), point.getY());
	}

	int countEmptyCells()
	{
		int counter = 0;
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++)
				if (field[y][x] instanceof EmptyCell)
					counter++;
		return counter;
	}

	BaseCell getCell(int x, int y) throws IndexOutOfBoundsException
	{
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		return field[y][x];
	}

	BaseCell getCell(Point point)
	{
		return getCell(point.getX(), point.getY());
	}

	public void setCell(int x, int y, BaseCell state) throws IndexOutOfBoundsException
	{
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		field[y][x] = state;
	}

	public void setCell(Point p, BaseCell cell)
	{
		setCell(p.getX(), p.getY(), cell);
	}
}
