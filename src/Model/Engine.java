package Model;

import Infrastructure.IGameController;
import Infrastructure.IRenderer;
import Infrastructure.LevelRepo;

public class Engine
{
	private final int defaultGameLownessMS = 200;
	private final LevelRepo levelRepo;
	private Level currentLevel;
	private final IGameController gameController;
	private final IRenderer renderer;

	public Engine(String pathToLevelRepo, IGameController gameController, IRenderer renderer)
	{
		levelRepo = new LevelRepo(pathToLevelRepo);
		this.gameController = gameController;
		this.renderer = renderer;
	}

	public void run() throws InterruptedException
	{
		currentLevel = Level.getTestLevel();

		while (!currentLevel.isOver())
		{
			gameController.updateLevel(currentLevel);
			renderer.renderLevel(currentLevel);
			Thread.sleep(defaultGameLownessMS);
		}

		renderer.renderGameEnd(false);
	}
}
