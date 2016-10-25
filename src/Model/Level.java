package Model;

import SwingGui.LevelEditor.LevelEditor;
import Model.Cells.BaseCell;
import Model.Cells.SnakeCell;

import java.io.Serializable;

public class Level implements Serializable
{
	private String name;
	private GameField field;
	private Snake snake;
	private boolean isOver;

	boolean isOver()
	{
		return isOver;
	}

	public int getFieldWidth()
	{
		return field.getWidth();
	}

	public int getFieldHeight()
	{
		return field.getHeight();
	}

	public Level(String name, int width, int height, int xSnake, int ySnake, Direction direction)
	{
		this(name, new GameField(width, height), xSnake, ySnake, direction);
	}

	private Level(String name, GameField field, int xSnake, int ySnake, Direction direction)
	{
		this.name = name;
		this.field = field;
		snake = new Snake(xSnake, ySnake, direction);
		field.setCell(snake.getHead().getCoordinates(), snake.getHead());
		FoodSpawner.spawnApple(field);
	}

	public static Level fromLevelEditor(LevelEditor editor)
	{
		Point snakeCoordinates = editor.getSnakeCoordinates();
		return new Level(editor.getName(),
				editor.getField(),
				snakeCoordinates.getX(),
				snakeCoordinates.getY(),
				editor.getDirection());
	}

	public BaseCell getFieldCell(int x, int y)
	{
		return field.getCell(x, y);
	}

	public void setFieldCell(int x, int y, BaseCell cell)
	{
		field.setCell(x, y, cell);
	}

	public int getSnakeLen()
	{
		return snake.getLength();
	}

	public SnakeCell getSnakeHead()
	{
		return snake.getHead();
	}

	void tick(Direction direction)
	{
		Point p;
		if (direction != null && Direction.NONE != direction)
			p = direction.getVector().add(snake.getDirection().getVector());
		if (direction != null && direction != Direction.NONE)//&& .equals(new Point(0,0)))
			snake.setDirection(direction);
		Point nextCell = snake.getNextMoveCell();
		boolean inField = field.isInField(nextCell);
		if (inField)
			field.getCell(nextCell).affectSnake(snake, field);
		isOver = !(inField && snake.isAlive());
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name) { this.name = name; }
}
