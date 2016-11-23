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
    private Point3d fieldVector;

	private Level(String name, Point3d snakeP, Direction direction)
	{
		this.name = name;
		snake = new Snake(snakeP, pointTo3D(direction.getVector()));
		Spawner.spawn(field, new FoodCell());
		random = new Random();
		magic = 30;
		space.setCell(snake.getHead().getLocation(), snake.getHead());
        field = space.getSection(new Point3d(-1, -1, 1));
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
			snake.setVectorDirection(pointTo3D(direction.getVector()));
		Point3d nextPoint3d = snake.getNextMoveCell();
        Point2d nextPoint2d = pointTo2D(nextPoint3d);
        BaseCell nextCell = field.getCell(nextPoint2d);
		boolean inField = field.isInField(nextPoint2d);
		if (inField)
			nextCell.affectSnake(snake, fieldVector, space);
		isOver = !(inField && snake.isAlive());
		if (random.nextInt(magic) == 0)
			Spawner.spawnRandom(space);
        field = space.getSection(fieldVector);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void rotate(Point3d vector) throws Exception
    {
        return 1;
    }

    public Point3d pointTo3D(Point2d point)
    {

    }

    public Point2d pointTo2D(Point3d point)
    {

    }
}
