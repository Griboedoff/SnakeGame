package Model.Cells;

import Model.Space;
//import Model.Point3d;
import Model.Snake;
//import Model.Space;
import Model.Vector;
import Swing.SwingGui.SwingPainterVisitor;

import java.io.Serializable;

public abstract class BaseCell implements Serializable
{
	private static final long serialVersionUID = 213456783;
	Vector location;

	BaseCell()
	{
	}

	BaseCell(Vector location)
	{
		this.location = location;
	}

	public Vector getLocation()
	{
		return location;
	}

	public abstract void affectSnake(Snake snake, Vector fieldVector, Space space);

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
