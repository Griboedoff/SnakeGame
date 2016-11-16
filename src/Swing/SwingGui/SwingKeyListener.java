package Swing.SwingGui;

import Model.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class SwingKeyListener implements KeyListener
{
	private Direction currentDirection = Direction.NONE;

	Direction getCurrentDirection()
	{
		return currentDirection;
	}

	void resetCurrentDirection()
	{
		this.currentDirection = Direction.NONE;
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
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}
}
