package Swing.SwingGui;

import Model.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class SwingKeyListener implements KeyListener
{
	private Direction currentDirection = Direction.NONE;
	private int[] deltaViewCoordinates = new int[]{0, 0};

	Direction getCurrentDirection()
	{
		Direction currDir = currentDirection;
		this.currentDirection = Direction.NONE;
		return currDir;
	}

	int[] getDeltaViewCoordinates()
	{
		int[] to_ret = deltaViewCoordinates;
		deltaViewCoordinates = new int[]{0, 0};
		return to_ret;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch (keyCode)
		{
			case (KeyEvent.VK_UP):
				currentDirection = Direction.DOWN;
				break;
			case (KeyEvent.VK_RIGHT):
				currentDirection = Direction.RIGHT;
				break;
			case (KeyEvent.VK_DOWN):
				currentDirection = Direction.UP;
				break;
			case (KeyEvent.VK_LEFT):
				currentDirection = Direction.LEFT;
				break;
			case (KeyEvent.VK_Q):
				deltaViewCoordinates = new int[]{1, 0};
				break;
			case (KeyEvent.VK_E):
				deltaViewCoordinates = new int[]{-1, 0};
				break;
			case (KeyEvent.VK_R):
				deltaViewCoordinates = new int[]{0, 1};
				break;
			case (KeyEvent.VK_F):
				deltaViewCoordinates = new int[]{0, -1};
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}
}
