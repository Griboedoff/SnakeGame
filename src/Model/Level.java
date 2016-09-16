package Model;

import java.io.Serializable;

public class Level implements Serializable
{
	private Integer gameLownessMS;
	private String name;
	private GameField field;
	private Snake snake;

	public Level(int width, int height, int xSnake, int ySnake, String direction)
	{
		field = new GameField(width, height);
		snake = new Snake(xSnake, ySnake, Direction.valueOf(direction), field);
	}

	public StepResult makeStep(Direction dir)
	{
		return snake.makeStep(dir);
	}

	public Integer getGameLownessMS()
	{
		return gameLownessMS;
	}

	public void setGameLownessMS(Integer gameLownessMS)
	{
		this.gameLownessMS = gameLownessMS;
	}

	public String getName()
	{
		return name;
	}
}
