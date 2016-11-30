package Model;

import Model.Cells.BaseCell;
import Model.Cells.FoodCell;
import Model.Cells.SnakeCell;
//import Swing.LevelEditor.LevelEditor;
//import Swing.LevelEditor.SnakeNotFoundException;

import java.io.Serializable;
import java.util.Random;

import static Model.PointTranslator.directionToPoint3D;
import static Model.PointTranslator.pointTo3D;


public class Level implements Serializable
{
	private static final long serialVersionUID = 213456783;
	private String name;
	private GameField field;
	//private int currentLevel;
	private Snake snake;
	private boolean isOver;
	private Random random;
	private int magic;
	private Space space;
    private Point3d fieldVector;
    private int coordNum;

	public Level(String name, Point3d snakeP, Direction direction)
	{
		this.name = name;
        space = Space.getTestSpace();
        fieldVector = new Point3d(-1, -1, snakeP.getZ());
        coordNum = 2;
        field = space.getSection(fieldVector);
        snake = new Snake(snakeP, directionToPoint3D(direction, fieldVector));
        space.setCell(snake.getHead().getLocation(), snake.getHead());
        Spawner.spawnOnSection(space, fieldVector, new FoodCell());
        random = new Random();
        magic = 2;
	}
	/*
	public static Level fromLevelEditor(LevelEditor editor) throws SnakeNotFoundException
	{
		return new Level(editor.getName(),
				editor.getSnakeCoordinates(),
				editor.getDirection());
	}
	*/
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
        field = space.getSection(fieldVector);
		if (direction != null && direction != Direction.NONE)
			snake.setVectorDirection(directionToPoint3D(direction, fieldVector));
		Point3d nextPoint = snake.getNextMoveCell();
		boolean inSpace = space.isInSpace(nextPoint);
		if (inSpace)
            space.getCell(nextPoint).affectSnake(snake, fieldVector, space);
		isOver = !(inSpace && snake.isAlive());
		if (random.nextInt(magic) == 0)
			Spawner.spawnRandom(space);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void rotate(int coordNum) throws IllegalArgumentException
    {
        this.coordNum = coordNum;
        Point3d snakeLocation = snake.getHead().getLocation();
        switch (coordNum){
            case 0:
                fieldVector = new Point3d(snakeLocation.getX(), -1, -1);
                break;
            case 1:
                fieldVector = new Point3d(-1, snakeLocation.getY(), -1);
                break;
            case 2:
                fieldVector = new Point3d(-1, -1, snakeLocation.getZ());
                break;
            default:
                throw new IllegalArgumentException("coordNum must be in {0, 1, 2}");
        }
    }


}
