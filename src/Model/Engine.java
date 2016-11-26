package Model;

import Infrastructure.IRenderer;
import Infrastructure.IGameController;
import Infrastructure.LevelRepo;

public class Engine
{
	private int defaultGameLownessMS = 300;
	private LevelRepo levelRepo;
	private Level currentLevel;
	private IGameController gameController;
	private IRenderer renderer;

	public Engine(String pathToLevelRepo, IGameController snakeController, IRenderer renderer)
	{
		levelRepo = new LevelRepo(pathToLevelRepo);
		this.gameController = snakeController;
		this.renderer = renderer;
	}

    public void run() throws InterruptedException
    {
        currentLevel = new Level("Test", new Point3d(10, 10, 10), Direction.UP);
        while (!currentLevel.isOver())
        {
            int viewCoord = gameController.getNewViewCoord();
            currentLevel.rotate(viewCoord);
            Direction direction = gameController.getNewDirection();
            currentLevel.tick(direction);
            renderer.renderLevel(currentLevel);
            Thread.sleep(defaultGameLownessMS);
        }
        renderer.renderGameEnd(false);
    }
}
