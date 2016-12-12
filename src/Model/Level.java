package Model;

import Model.Cells.BaseCell;
import Model.Cells.FoodCell;
import Model.Cells.SnakeCell;

import java.io.Serializable;
import java.util.Random;


public class Level implements Serializable
{
	private static final long serialVersionUID = 213456783;
	private String name;
	private GameField field;
	private final Snake snake;
	private boolean isOver;
	private final Random random;
	private final int magic;
	private final Space space;
	private Vector fieldVector;

	public Level(String name, Vector snakePosition, Vector snakeDirection, Space space)
	{
		this.name = name;
		this.space = space;
		fieldVector = new Vector(new int[] {0, 1});
		snake = new Snake(snakePosition, snakeDirection);
		space.setCell(snake.getHead().getLocation(), snake.getHead());
		Spawner.spawnOnSection(space, snakePosition, fieldVector, new FoodCell());
        field = space.getSection(snake.getLocation(), fieldVector);
		random = new Random();
		magic = 2;
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

	public void tick(Direction direction)
	{
		if (direction != null && direction != Direction.NONE)
			snake.setDirection(PointTranslator.directionToVector(direction, fieldVector, space.getDim()));
        space.affectSnake(snake, fieldVector, snake.getNextMoveCell());
		isOver = !(snake.isAlive());
		if (random.nextInt(magic) == 0)
			Spawner.spawnRandom(space);
        field = space.getSection(snake.getLocation(), fieldVector);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void rotate(int[] coordNums) throws IllegalArgumentException
	{
		if (coordNums.length != 2)
		    throw new IllegalArgumentException("Amount of coords must be 2");
        for (int i = 0; i < 2; i++)
            if (0 > coordNums[i] || coordNums[i] >= space.getDim())
                throw new IllegalArgumentException("Numbers of coords not in space");
        if (coordNums[0] == coordNums[1])
            throw new IllegalArgumentException("Numbers of coords must be different");
        fieldVector = new Vector(coordNums);
	}

	public static Level getTestLevel()
    {
        return new Level("Test", new Vector(new int[] {10, 10, 10}), new Vector(new int[] {0, 1, 0}), Space.getTestSpace());
    }
}
