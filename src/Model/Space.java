package Model;

import Model.Cells.BaseCell;
import Model.Cells.EmptyCell;
import Model.Cells.WallCell;

import java.util.HashMap;

public class Space
{
	private final HashMap<Point3d, BaseCell> cells;
	private final Point3d size;

	private Space(HashMap<Point3d, BaseCell> cells, Point3d size) //throws SpaceCreationException
	{
		this.cells = cells;
		this.size = size;
	}

	static Space getTestSpace()
	{
		HashMap<Point3d, BaseCell> cells = new HashMap<>();
		int size = 20;
		for (int i = 0; i < size; i++)
		{
			for (int j = 1; j < size; j += size - 3)
				for (int k = 1; k < size; k += size - 3)
				{
					cells.put(new Point3d(i, j, k), new WallCell());
					cells.put(new Point3d(j, i, k), new WallCell());
					cells.put(new Point3d(j, k, i), new WallCell());
				}
			for (int j = 0; j < size; j += size - 1)
				for (int k = 0; k < size; k += size - 1)
				{
					cells.put(new Point3d(i, j, k), new WallCell());
					cells.put(new Point3d(j, i, k), new WallCell());
					cells.put(new Point3d(j, k, i), new WallCell());
				}
		}
		return new Space(cells, new Point3d(size, size, size));
	}

	GameField getSection(Point3d p)
	{
		if (p.getX() != -1)
			return rotateX(p);
		else if (p.getY() != -1)
			return rotateY(p);
		else
			return rotateZ(p);
	}

	private GameField rotateZ(Point3d p)
	{
		BaseCell[][] cells = new BaseCell[this.size.getX()][this.size.getY()];
		for (int x = 0; x < cells.length; x++)
			for (int y = 0; y < cells[0].length; y++)
			{
				if (this.cells.containsKey(new Point3d(x, y, p.getZ())))
					cells[x][y] = this.cells.get(new Point3d(x, y, p.getZ()));
				else
					cells[x][y] = new EmptyCell();
			}
		return new GameField(cells);
	}

	private GameField rotateY(Point3d p)
	{
		BaseCell[][] cells = new BaseCell[this.size.getZ()][this.size.getX()];
		for (int z = 0; z < cells.length; z++)
			for (int x = 0; x < cells[0].length; x++)
			{
				if (this.cells.containsKey(new Point3d(x, p.getY(), z)))
					cells[z][x] = this.cells.get(new Point3d(x, p.getY(), z));
				else
					cells[z][x] = new EmptyCell();
			}
		return new GameField(cells);
	}

	private GameField rotateX(Point3d p)
	{
		BaseCell[][] cells = new BaseCell[this.size.getY()][this.size.getZ()];
		for (int y = 0; y < cells.length; y++)
			for (int z = 0; z < cells[0].length; z++)
			{
				if (this.cells.containsKey(new Point3d(p.getX(), y, z)))
					cells[y][z] = this.cells.get(new Point3d(p.getX(), y, z));
				else
					cells[y][z] = new EmptyCell();
			}
		return new GameField(cells);
	}

	boolean isInSpace(Point3d point)
	{
		return !(point.getX() < 0 || point.getX() >= size.getX()) &&
				!(point.getY() < 0 || point.getY() >= size.getY()) &&
				!(point.getZ() < 0 || point.getZ() >= size.getZ());
	}

	BaseCell getCell(Point3d point) throws IllegalArgumentException
	{
		if (!isInSpace(point))
			throw new IllegalArgumentException();
		if (!cells.containsKey(point))
			return new EmptyCell();
		return cells.get(point);
	}

	public BaseCell getCell(int x, int y, int z)
	{
		return getCell(new Point3d(x, y, z));
	}

	public void setCell(Point3d point, BaseCell cell)
	{
		if (!isInSpace(point))
			throw new IllegalArgumentException();
		if (cell instanceof EmptyCell)
			if (cells.containsKey(point))
				cells.remove(point);
		cells.put(point, cell);
	}

	public void setCell(int x, int y, int z, BaseCell cell)
	{
		setCell(new Point3d(x, y, z), cell);
	}

	int countEmptyCells()
	{
		return size.getX() * size.getY() * size.getZ() - cells.size();
	}

	Point3d getSize()
	{
		return size;
	}
}
