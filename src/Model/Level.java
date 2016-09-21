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
		snake = new Snake(xSnake, ySnake, direction, field);
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
		SnakeStepResult stepRes = snake.makeStep(dir);
		if (stepRes == SnakeStepResult.GROW)
			generateFood();
		return stepRes;
	}

	private void generateFood()
	{
		int free_amount = field.getHeight() * field.getWidth() - snake.getLength();
		int foodCellNumber = random.nextInt(free_amount);
		for (int y = 0; y < field.getHeight(); y++)
			for (int x = 0; x < field.getWidth(); x++)
			{
				BaseCell cell = field.getCell(x, y);
				if (cell instanceof EmptyCell)
					foodCellNumber--;
				if (foodCellNumber == 0)
				{
					field.setCell(x, y, CellFactory.createCell(CellTypes.FOOD, x, y));
					return;
				}
			}
	}

	public String getName()
	{
		return name;
	}
}
