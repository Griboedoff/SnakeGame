package Swing.SwingGui;

import Model.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class SwingKeyListener implements KeyListener
{
	private Direction currentDirection = Direction.NONE;
	private int viewCoord = 2;

	Direction getCurrentDirection()
	{
		Direction currDir = currentDirection;
		this.currentDirection = Direction.NONE;
		return currDir;
	}

	int getViewCoord()
	{
		return viewCoord;
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
			case (KeyEvent.VK_1):
				viewCoord = 0;
				break;
			case (KeyEvent.VK_2):
				viewCoord = 1;
				break;
			case (KeyEvent.VK_3):
				viewCoord = 2;
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
