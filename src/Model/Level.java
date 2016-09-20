package Model;

import java.io.Serializable;

public class Level implements Serializable
{
	private String name;
	private GameField field;
	private Snake snake;

	public Level(String name, int width, int height, int xSnake, int ySnake, String direction)
	{
		this.name = name;
		field = new GameField(width, height);
		snake = new Snake(xSnake, ySnake, Direction.valueOf(direction), field);
	}

	StepResult makeStep(Direction dir)
	{
		return snake.makeStep(dir);
	}

	public String getName()
	{
		return name;
	}
}
