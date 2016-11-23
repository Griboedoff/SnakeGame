package Model;

import Model.Cells.BaseCell;
import Model.Cells.FoodCell;
import Model.Cells.SnakeCell;
import Swing.LevelEditor.LevelEditor;
import Swing.LevelEditor.SnakeNotFoundException;

import java.io.Serializable;
import java.util.Random;


public class Level implements Serializable
{
	private static final long serialVersionUID = 213456783;
	private String name;
	private GameField field;
	private int currentLevel;
	private Snake snake;
	private boolean isOver;
	private Random random;
	private int magic;
	private Space space;
	private ICellSelector selector;

	private Level(String name, Point snakeP, Direction direction)
	{
		this.name = name;
		snake = new Snake(snakeP, direction);
		Spawner.spawn(field, new FoodCell());
		random = new Random();
		magic = 30;
		field = space.getSection(new Point(0, 0, 1));
		field.setCell(snake.getHead().getLocation(), snake.getHead(), selector);
	}

	public static Level fromLevelEditor(LevelEditor editor) throws SnakeNotFoundException
	{
		return new Level(editor.getName(),
				editor.getSnakeCoordinates(),
				editor.getDirection());
	}

	public GameField getField()
	{
		return field;
	}

	public Snake getSnake()
	{
		return snake;
	}

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
		if (direction != null && direction != Direction.NONE)
			snake.setDirection(direction);
		Point nextCell = snake.getNextMoveCell();
		boolean inField = field.isInField(nextCell);
		if (inField)
			field.getCell(nextCell.getX(), nextCell.getY()).affectSnake(snake, field);
		isOver = !(inField && snake.isAlive());
		if (random.nextInt(magic) == 0)
			Spawner.spawnRandom(field);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
