package LevelEditor;

import Model.Cells.Types;
import Model.Direction;
import Model.Level;
import Model.Point;

import static java.lang.Math.max;

public class Editor
{
	private Types[][] field;
	private String name;
	private Direction direction;
	private final Point defaultSize = new Point(30, 40);

	public Editor()
	{
		field = new Types[defaultSize.getY()][defaultSize.getX()];
		for (int i = 0; i < defaultSize.getY(); i++)
			for (int j = 0; j < defaultSize.getX(); j++)
				field[j][i] = Types.EMPTY;
	}

	public void changeSize(int x, int y) throws IndexOutOfBoundsException
	{
		if (x < 1 || y < 1)
			throw new IndexOutOfBoundsException();
		Types[][] newField = new Types[y][x];
		for (int i = 0; i < max(y, field.length); i++)
			for (int j = 0; j < max(x, field[0].length); j++)
				newField[j][i] = field[j][i];
		field = newField;
	}

	public Point getSize()
	{
		return new Point(field.length, field[0].length);
	}

	public void setCellType(int x, int y, Types type)
	{
		field[y][x] = type;
	}

	public Types getCellType(int x, int y)
	{
		return field[y][x];
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

	public int getSnakesAmount()
	{
		int snakesAmount = 0;
		for (int x = 0; x < getSize().getX(); x++)
			for (int y = 0; y < getSize().getY(); y++)
				if (getCellType(x, y) == Types.SNAKE)
					snakesAmount++;
		return snakesAmount;
	}

	public Point getSnakeCoordinates() throws IndexOutOfBoundsException
	{
		if (getSnakesAmount() != 1)
			throw new IndexOutOfBoundsException();
		for (int x = 0; x < getSize().getX(); x++)
			for (int y = 0; y < getSize().getY(); y++)
				if (getCellType(x, y) == Types.SNAKE)
					return new Point(x, y);
		throw new IndexOutOfBoundsException();
	}

	public Level getLevel() throws IndexOutOfBoundsException
	{
		Point snakeCoordinates = getSnakeCoordinates();
		Point size = getSize();
		Level res = new Level(name, size.getX(), size.getY(), snakeCoordinates.getX(), snakeCoordinates.getY(), direction);
		for (int x = 0; x < size.getX(); x++)
			for (int y = 0; y < size.getY(); y++)
				res.setFieldCell(x, y, field[y][x].getCellByName());
		return res;
	}
}
