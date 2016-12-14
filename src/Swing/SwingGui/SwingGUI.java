package Swing.SwingGui;

import Infrastructure.IGameController;
import Infrastructure.IRenderer;
import Infrastructure.LevelRepo;
import Model.Level;

class SwingGUI implements IRenderer, IGameController
{
	private final LevelWindow frame;

	SwingGUI()
	{
		frame = new LevelWindow();
		frame.setSize(500, 500);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}

	@Override
	public void updateLevel(Level level)
	{
		level.rotate(frame.listener.getDeltaViewCoordinates());
		level.tick(frame.listener.getCurrentDirection());
	}

	@Override
	public Level selectLevel(LevelRepo repo)
	{
		return repo.getLevelFromFile(frame.showLevelSelectingDialog(repo));
	}

	@Override
	public void renderLevel(Level level)
	{
		if (frame.getLevel() != level)
			frame.setLevel(level);
		frame.panel.repaint();
	}

	@Override
	public void renderGameEnd()
	{
		frame.updateGameEnd();
	}
}
