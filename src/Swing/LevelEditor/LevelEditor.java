package Swing.LevelEditor;

import Model.Cells.BaseCell;
import Model.Cells.SnakeCell;
import Model.Direction;
import Model.GameField;
import Model.Level;
import Model.Point3d;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Math.min;

public class LevelEditor
{
	private GameField field;
	private String name;
	private Direction direction;


	LevelEditor()
	{
		Point3d defaultSize = new Point3d(30, 20);
		field = new GameField(defaultSize.getX(), defaultSize.getY());
	}

	LevelEditor(Level level)
	{
		field = level.getField();
		direction = level.getSnake().getVectorDirection();
		name = level.getName();
	}

	private void changeSize(int x, int y) throws IllegalArgumentException
	{
		if (x < 1 || y < 1)
			throw new IllegalArgumentException("Field size must be greater than 0");
		GameField newField = new GameField(x, y);
		for (int yy = 0; yy < min(y, field.getHeight()); yy++)
			for (int xx = 0; xx < min(x, field.getWidth()); xx++)
				newField.setCell(xx, yy, field.getCell(xx, yy));
		field = newField;
	}

	public void changeSize(Point3d p) throws IndexOutOfBoundsException
	{
		changeSize(p.getX(), p.getY());
	}

	public GameField getField()
	{
		return field;
	}

	private Point3d getSize()
	{
		return new Point3d(field.getWidth(), field.getHeight());
	}

	void setCell(Point3d p, Class<?> cellClass)
	{
		if (!field.isInField(p))
			return;
		BaseCell cell;
		try
		{
			cell = (BaseCell) cellClass.getConstructor(int.class, int.class).newInstance(p.getX(), p.getY());
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
		{
			throw new RuntimeException("Can't invoke ctor " + cellClass.getName(), e);
		}
		field.setCell(p.getX(), p.getY(), cell);
	}

	BaseCell getCell(int x, int y)
	{
		return field.getCell(x, y);
	}

	public String getName()
	{
		return name;
	}

	void setName(String newName)
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

	public Point3d getSnakeCoordinates() throws SnakeNotFoundException
	{
		for (int x = 0; x < getSize().getX(); x++)
			for (int y = 0; y < getSize().getY(); y++)
				if (field.getCell(x, y) instanceof SnakeCell)
					return new Point3d(x, y);
		throw new SnakeNotFoundException("Can't find snake");
	}

	String validate()
	{
		if (name == null || name.equals(""))
			return "Nameless level is unacceptable";
		if (direction == null)
			return "The snake hasn't direction";
		int snakesAmount = 0;
		for (int x = 0; x < getSize().getX(); x++)
			for (int y = 0; y < getSize().getY(); y++)
				if (field.getCell(x, y) instanceof SnakeCell)
					snakesAmount++;
		if (snakesAmount != 1)
			return "Amount of snakes must be equal to one";
		return "OK";
	}
}