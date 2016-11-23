package Model;

import Model.Cells.BaseCell;
import Model.Exceptions.SpaceCreationException;

import java.util.HashMap;
import java.util.HashSet;

public class Space
{
	private HashMap<Point3d, BaseCell> cells;
	private Point3d size;

	public Space(HashSet<BaseCell> cells, Point3d size) throws SpaceCreationException
	{
		this.cells = buildDict(cells);
		this.size = size;

		int expectedSize = size.getX() * size.getY() * size.getZ();
		int realSize = this.cells.size();
		if (realSize != expectedSize)
			throw new SpaceCreationException(String.format("Expected space size %s, real %s", expectedSize, realSize));
	}

	private HashMap<Point3d, BaseCell> buildDict(HashSet<BaseCell> cells)
	{
		HashMap<Point3d, BaseCell> result = new HashMap<>();
		for (BaseCell c : cells)
			result.put(c.getLocation(), c);
		return result;
	}

	public GameField getSection(Point3d p)
	{
		if (p.getX() != 0)
		{
			BaseCell[][] cells = new BaseCell[this.size.getY()][this.size.getZ()];
			for (int y = 0; y < cells.length; y++)
				for (int z = 0; z < cells[0].length; z++)
					cells[y][z] = this.cells.get(new Point3d(p.getX(), y, z));
			return new GameField(cells);
		} else if (p.getY() != 0)
		{
			BaseCell[][] cells = new BaseCell[this.size.getZ()][this.size.getX()];
			for (int z = 0; z < cells.length; z++)
				for (int x = 0; x < cells[0].length; x++)
					cells[z][x] = this.cells.get(new Point3d(x, p.getY(), z));
			return new GameField(cells);
		} else
		{
			BaseCell[][] cells = new BaseCell[this.size.getX()][this.size.getY()];
			for (int x = 0; x < cells.length; x++)
				for (int y = 0; y < cells[0].length; y++)
					cells[x][y] = this.cells.get(new Point3d(x, y, p.getZ()));
			return new GameField(cells);
		}
	}
}
