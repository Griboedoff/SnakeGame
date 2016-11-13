package SwingGui;

import Infrastructure.ISnakeController;
import Model.Direction;
import Model.Level;
import Infrastructure.IRenderer;

import java.awt.event.WindowEvent;
import java.util.HashSet;

public class SwingGUI implements IRenderer, ISnakeController
{
	private LevelWindow frame;
	private HashSet<Integer> pressedKeys;
	private SwingKeyListener listener;

	private Level level()
	{
		return frame.level;
	}

	public SwingGUI()
	{
		frame = new LevelWindow("Snake");
		frame.setSize(500, 500);
		frame.setUndecorated(true);
		frame.setVisible(true);
		pressedKeys = new HashSet<>();
	}

	public void addKeyListener(SwingKeyListener l)
	{
		frame.addKeyListener(l);
		listener = l;
	}

	@Override
	public Direction getNewDirection()
	{
		return getNextDirection();
	}

	private Direction getNextDirection()
	{
		Direction res = listener.currentDirection;
        listener.currentDirection = Direction.NONE;
        return res;
	}

	@Override
	public void renderLevel(Level level)
	{
		if (frame.level != level)
			frame.level = level;
		frame.updateField(frame.getGraphics());
	}

	@Override
	public void renderGameEnd(boolean isCompleted)
	{
		frame.updateGameEnd(frame.getGraphics());
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
