package Swing.SwingGui;

import Infrastructure.IRenderer;
import Infrastructure.IGameController;
import Infrastructure.LevelRepo;
import Model.Direction;
import Model.Level;

class SwingGUI implements IRenderer, IGameController
{
	private LevelWindow frame;

	SwingGUI()
	{
		frame = new LevelWindow("Snake");
		frame.setSize(500, 500);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}

	private Level level()
	{
		return frame.getLevel();
	}

	@Override
	public Direction getNewDirection()
	{
		return getNextDirection();
	}

	private Direction getNextDirection()
	{
		Direction res = frame.listener.getCurrentDirection();
		frame.listener.resetCurrentDirection();
		return res;
	}

    @Override
    public int getNewViewCoord()
    {
        return frame.listener.getViewCoord();
    }

	@Override
	public Level selectLevel(LevelRepo repo)
	{
		String name = frame.showLevelSelectingDialog(repo);
		return repo.getLevelFromFile(name);
	}

	@Override
	public void renderLevel(Level level)
	{
		if (frame.getLevel() != level)
			frame.setLevel(level);
		frame.panel.repaint();
	}

	@Override
	public void renderGameEnd(boolean isCompleted)
	{
		frame.updateGameEnd();
	}
}
