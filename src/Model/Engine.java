package Model;

import Model.Controllers.Controller;
import Model.Infrastructure.LevelRepo;

class Engine
{
	private int defaultGameLownessMS;
	private LevelRepo levelRepo;
	private Level currentLevel;
	private Controller controller;

	public Engine(String pathToLevelRepo, Controller controller)
	{
		levelRepo = new LevelRepo(pathToLevelRepo);
		this.controller = controller;
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
		while (true)
		{
			Direction direction = controller.getNewDirection();
			SnakeStepResult snakeStepResult = currentLevel.makeStep(direction);
			if (snakeStepResult == SnakeStepResult.DIE)
			{
				currentLevel = loadLevel(currentLevel.getName());
				if (currentLevel != null)
					currentLevel.resetRandom();
			}
			Thread.currentThread().wait(defaultGameLownessMS);
		}
	}
}
