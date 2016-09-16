package Model;

import Model.Controllers.Controller;
import Model.Infrastructure.LevelRepo;

public class Engine
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

	private int getCurrentGameLownessMS()
	{
		return currentLevel.getGameLownessMS() == null ? defaultGameLownessMS : currentLevel.getGameLownessMS();
	}

	public void run() throws InterruptedException
	{
		StepResult stepResult = StepResult.NONE;
		Direction direction = null;
		while (true)
		{
			Thread.currentThread().wait(getCurrentGameLownessMS());
			direction = controller.getNewDirection();
			stepResult = currentLevel.makeStep(direction);
		}
	}
}
