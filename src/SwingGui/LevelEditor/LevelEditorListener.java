package SwingGui.LevelEditor;

import Model.Cells.EmptyCell;
import Model.Cells.FoodCell;
import Model.Cells.SnakeCell;
import Model.Cells.WallCell;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LevelEditorListener implements KeyListener, MouseListener
{
	private Point mouseLocation;
	private Class<?> currentSelectedCellType;
	public boolean needToSet;

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
			case (KeyEvent.VK_LEFT):
				currentSelectedCellType = FoodCell.class;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		needToSet = true;
		mouseLocation = e.getPoint();
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
