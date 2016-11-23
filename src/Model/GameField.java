package Model;

import Model.Cells.BaseCell;
import Model.Cells.EmptyCell;

import java.io.Serializable;
import java.util.stream.IntStream;

public class GameField implements Serializable
{
	private static final long serialVersionUID = 213456783;

	private BaseCell[][] field;


	public GameField(int width, int height)
	{
		field = new BaseCell[width][height];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				field[x][y] = new EmptyCell();
	}

	public GameField(BaseCell[][] field)
	{
		this.field = field;
	}

	public int getHeight()
	{
		return field[0].length;
	}

	public int getWidth()
	{
		return field.length;
	}

	public BaseCell getCell(int x, int y) throws IndexOutOfBoundsException
	{
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		return field[x][y];
	}

	BaseCell getCell(Point point, ICellSelector cellSelector)
	{
		Point p = cellSelector.selectCell(point);
		return getCell(p.getX(), p.getY());
	}

	public void setCell(int x, int y, BaseCell newCell) throws IndexOutOfBoundsException
	{
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException(String.format("x=%d y=%d", x, y));
		field[x][y] = newCell;
	}


	public void setCell(Point point, BaseCell newCell, ICellSelector cellSelector)
	{
		Point p = cellSelector.selectCell(point);
		setCell(p.getX(), p.getY(), newCell);
	}

	private boolean isInField(int x, int y)
	{
		return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
	}

	public boolean isInField(Point point)
	{
		return isInField(point.getX(), point.getY());
	}

	int countEmptyCells()
	{
		return IntStream
				.range(0, getHeight())
				.map(y -> (int) IntStream
						.range(0, getWidth())
						.filter(x -> field[x][y] instanceof EmptyCell)
						.count())
				.sum();
	}
}
