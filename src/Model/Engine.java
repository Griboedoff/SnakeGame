package Model;

import Infrastructure.IRenderer;
import Infrastructure.ISnakeController;
import Infrastructure.LevelRepo;

public class Engine
{
	private int defaultGameLownessMS = 300;
	private LevelRepo levelRepo;
	private Level currentLevel;
	private ISnakeController snakeController;
	private IRenderer renderer;

	public Engine(String pathToLevelRepo, ISnakeController snakeController, IRenderer renderer)
	{
		levelRepo = new LevelRepo(pathToLevelRepo);
		this.snakeController = snakeController;
		this.renderer = renderer;
	}

	public void run() throws InterruptedException
	{
		while (true)
		{
			currentLevel = renderer.selectLevel(levelRepo);
			while (!currentLevel.isOver())
			{
				Direction direction = snakeController.getNewDirection();
				currentLevel.tick(direction);
				renderer.renderLevel(currentLevel);
				Thread.sleep(defaultGameLownessMS);
			}
			renderer.renderGameEnd(false);
		}
	}
}
