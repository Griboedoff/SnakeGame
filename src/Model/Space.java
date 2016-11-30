package Model;

import Model.Cells.BaseCell;
import Model.Cells.EmptyCell;
import Model.Cells.WallCell;
import Model.Exceptions.SpaceCreationException;

import java.util.HashMap;
import java.util.HashSet;

public class Space
{
	private HashMap<Point3d, BaseCell> cells;
	private Point3d size;

	public Space(HashMap<Point3d, BaseCell> cells, Point3d size) //throws SpaceCreationException
	{
		//this.cells = buildDict(cells);
        this.cells = cells;
		this.size = size;

        /*
		int expectedSize = size.getX() * size.getY() * size.getZ();
		int realSize = this.cells.size();
		if (realSize != expectedSize)
			throw new SpaceCreationException(String.format("Expected space size %s, real %s", expectedSize, realSize));
		*/
	}

    /*
	private HashMap<Point3d, BaseCell> buildDict(HashSet<BaseCell> cells)
	{
		HashMap<Point3d, BaseCell> result = new HashMap<>();
		for (BaseCell c : cells)
			result.put(c.getLocation(), c);
		return result;
	}
    */

	public GameField getSection(Point3d p)
	{
		if (p.getX() != -1)
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
		} else if (p.getY() != -1)
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
		} else
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
	}

	public boolean isInSpace(Point3d point)
    {
        if (point.getX() < 0 || point.getX() >= size.getX())
            return false;
        if (point.getY() < 0 || point.getY() >= size.getY())
            return false;
        if (point.getZ() < 0 || point.getZ() >= size.getZ())
            return false;
        return true;
    }

	public BaseCell getCell(Point3d point) throws IllegalArgumentException
    {
        if (! isInSpace(point))
            throw new IllegalArgumentException();
        if (! cells.containsKey(point))
            return new EmptyCell();
        return cells.get(point);
    }

    public BaseCell getCell(int x, int y, int z) { return getCell(new Point3d(x, y, z)); }

    public void setCell(Point3d point, BaseCell cell)
    {
        if (! isInSpace(point))
            throw new IllegalArgumentException();
        if (cell instanceof EmptyCell)
            if (cells.containsKey(point))
                cells.remove(point);
        cells.put(point, cell);
    }

    public void setCell(int x, int y, int z, BaseCell cell) { setCell(new Point3d(x, y, z), cell); }

    public int countEmptyCells()
    {
        return size.getX() * size.getY() * size.getZ() - cells.size();
    }

    public Point3d getSize() { return size; }

    public static Space getTestSpace()
    {
        HashMap<Point3d, BaseCell> cells = new HashMap<Point3d, BaseCell>();
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
}
