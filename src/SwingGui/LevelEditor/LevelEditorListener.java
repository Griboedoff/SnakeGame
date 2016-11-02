package SwingGui.LevelEditor;

import Model.Cells.EmptyCell;
import Model.Cells.FoodCell;
import Model.Cells.SnakeCell;
import Model.Cells.WallCell;
import Model.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LevelEditorListener implements KeyListener, MouseListener
{
	private Point mouseLocation;
	private Class<?> currentSelectedCellType;
	public boolean levelDone;
	public boolean needToSet;
	public boolean needToChangeSize;
	public Direction direction;

	public Point getMouseLocation()
	{
		return mouseLocation;
	}

	public Class getCurrentSelectedCellType()
	{
		return currentSelectedCellType;
	}

	public LevelEditorListener()
	{
		mouseLocation = new Point(0, 0);
		currentSelectedCellType = EmptyCell.class;
		needToSet = false;
		needToChangeSize = false;
		levelDone = false;
		direction = Direction.UP;
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
			case (KeyEvent.VK_S):
				currentSelectedCellType = SnakeCell.class;
				break;
			case (KeyEvent.VK_W):
				currentSelectedCellType = WallCell.class;
				break;
			case (KeyEvent.VK_E):
				currentSelectedCellType = EmptyCell.class;
				break;
			case (KeyEvent.VK_F):
				currentSelectedCellType = FoodCell.class;
				break;
			case (KeyEvent.VK_ENTER):
				levelDone = true;
				break;
			case (KeyEvent.VK_SPACE):
				needToChangeSize = true;
				break;
			case (KeyEvent.VK_UP):
				direction = Direction.UP;
				break;
			case (KeyEvent.VK_DOWN):
				direction = Direction.DOWN;
				break;
			case (KeyEvent.VK_LEFT):
				direction = Direction.LEFT;
				break;
			case (KeyEvent.VK_RIGHT):
				direction = Direction.RIGHT;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
//		needToSet = true;
//		mouseLocation = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		mouseLocation = e.getPoint();
		needToSet = true;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
