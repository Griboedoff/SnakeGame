package Model.Cells;

import Model.GameField;
import Model.Point3d;
import Model.Snake;
import Model.Space;
import Swing.SwingGui.SwingPainterVisitor;

import java.io.Serializable;

public abstract class BaseCell implements Serializable
{
	private static final long serialVersionUID = 213456783;
	public Point3d location;

	BaseCell()
	{
	}

	BaseCell(int x, int y, int z)
	{
		location = new Point3d(x, y, z);
	}

	public Point3d getLocation()
	{
		return location;
	}

	public abstract void affectSnake(Snake snake, GameField field, Space space);

	public abstract void acceptVisitor(SwingPainterVisitor v, int x, int y);

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BaseCell baseCell = (BaseCell) o;

		return location != null ? location.equals(baseCell.location) : baseCell.location == null;
	}

	@Override
	public int hashCode()
	{
		return location != null ? location.hashCode() : 0;
	}
}
