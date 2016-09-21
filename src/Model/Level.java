package Model;

import Model.Cells.*;

import java.io.Serializable;
import java.util.Random;

public class Level implements Serializable
{
	private String name;
	private GameField field;
	private Snake snake;
	private Random random;

	public Level(String name, int width, int height, int xSnake, int ySnake, Direction direction)
	{
		this.name = name;
		field = new GameField(width, height);
		snake = new Snake(xSnake, ySnake, direction);
		field.setCell(snake.getHead().getCoordinates(), snake.getHead());
		random = new Random(System.currentTimeMillis());
		generateFood();
	}

	public BaseCell getFieldCell(int x, int y)
	{
		return field.getCell(x, y);
	}

	public int getSnakeLen()
	{
		return snake.getLength();
	}

	public SnakeCell getSnakeHead()
	{
		return snake.getHead();
	}

	void resetRandom()
	{
		random = new Random(System.currentTimeMillis());
	}

	SnakeStepResult makeStep(Direction dir)
	{
		SnakeStepResult stepRes = moveSnake(dir);
		if (stepRes == SnakeStepResult.GROW)
			generateFood();
		return stepRes;
	}

	private SnakeStepResult moveSnake(Direction direction)
	{
		if (direction != null)
			snake.setDirection(direction);
		Point nextCell = snake
				.getHead()
				.getCoordinates()
				.add(snake.getDirection().getVector());
		if (!field.isInField(nextCell) || field.getCell(nextCell) instanceof SnakeCell)
			return SnakeStepResult.DIE;
		if (field.getCell(nextCell) instanceof EmptyCell)
			return moveSnakeTo(nextCell);
		if (field.getCell(nextCell) instanceof FoodCell)
			return moveSnakeAndEat(nextCell);
		return null;
	}

	private SnakeStepResult moveSnakeTo(Point point)
	{
		updateHead(point);
		deleteTail();
		return SnakeStepResult.NONE;
	}

	private SnakeStepResult moveSnakeAndEat(Point point)
	{
		updateHead(point);
		return SnakeStepResult.GROW;
	}

	private void updateHead(Point point)
	{
		SnakeCell head = snake.getHead();
		snake.setHead(CellFactory.createSnakeCell(point, head));
		field.setCell(point, snake.getHead());
	}

	private void deleteTail()
	{
		SnakeCell tail = snake.getTail();
		field.setCell(tail.getCoordinates(), CellFactory.createCell(CellTypes.EMPTY));
		snake.setTail(tail.getPrev());
		snake.getTail().setNext(null);
	}

	private void generateFood()
	{
		int free_amount = field.countEmptyCells();
		int foodCellNumber = random.nextInt(free_amount);
		for (int y = 0; y < field.getHeight(); y++)
			for (int x = 0; x < field.getWidth(); x++)
			{
				BaseCell cell = field.getCell(x, y);
				if (cell instanceof EmptyCell)
					foodCellNumber--;
				if (foodCellNumber == 0)
				{
					field.setCell(x, y, CellFactory.createCell(CellTypes.FOOD));
					return;
				}
			}
	}

	public String getName()
	{
		return name;
	}
}
