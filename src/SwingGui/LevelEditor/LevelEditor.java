package SwingGui.LevelEditor;

import Model.Cells.BaseCell;
import Model.Cells.SnakeCell;
import Model.Direction;
import Model.GameField;
import Model.Point;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Math.min;

public class LevelEditor
{
	private GameField field;
	private String name;
	private Direction direction;
	private final Point defaultSize = new Point(30, 40);


	public LevelEditor()
	{
		field = new GameField(defaultSize.getX(), defaultSize.getY());
	}

	public void changeSize(int x, int y) throws IllegalArgumentException
	{
		if (x < 1 || y < 1)
			throw new IllegalArgumentException("Field size must be greater than 0");
		GameField newField = new GameField(x, y);
		for (int yy = 0; yy < min(y, field.getHeight()); yy++)
			for (int xx = 0; xx < min(x, field.getWidth()); xx++)
				newField.setCell(xx, yy, field.getCell(xx, yy));
		field = newField;
	}

	public void changeSize(Point p) throws IndexOutOfBoundsException
    {
        changeSize(p.getX(), p.getY());
    }

	public GameField getField()
	{
		return field;
	}

	public Point getSize()
	{
		return new Point(field.getWidth(), field.getHeight());
	}

	public void setCell(Point p, Class<?> cellClass)
	{
		if (!field.isInField(p))
			return;
		BaseCell cell;
		try
		{
			cell = (BaseCell) cellClass.getConstructor(int.class, int.class).newInstance(p.getX(), p.getY());
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
		{
			throw new RuntimeException("Can't invoke ctor");
		}
		field.setCell(p.getX(), p.getY(), cell);
	}

	public BaseCell getCell(int x, int y)
	{
		return field.getCell(x, y);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String newName)
	{
		name = newName;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction newDirection)
	{
		direction = newDirection;
	}

	public Point getSnakeCoordinates() throws IndexOutOfBoundsException
	{
		for (int x = 0; x < getSize().getX(); x++)
			for (int y = 0; y < getSize().getY(); y++)
				if (field.getCell(x, y) instanceof SnakeCell)
					return new Point(x, y);
		throw new IndexOutOfBoundsException();
	}
}
