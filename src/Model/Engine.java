package Model;

import Infrastructure.ISnakeController;
import Infrastructure.LevelRepo;
import Infrastructure.IRenderer;

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

	private Level loadLevel(String levelName)
	{
		try
		{
			return levelRepo.getLevelFromFile(levelName);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void run() throws InterruptedException
	{
//		currentLevel = new Level("1", 30, 40, 3, 4, Direction.UP);
		currentLevel = levelRepo.getLevelFromFile("First.level");
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
