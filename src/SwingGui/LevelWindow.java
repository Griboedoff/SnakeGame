package SwingGui;

import Model.Cells.BaseCell;
import Model.Level;

import javax.swing.*;
import java.awt.*;

class LevelWindow extends JFrame
{
	private static final int CELL_SIZE = 12;
	Level level;

	LevelWindow(String title) throws HeadlessException
	{
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void update(Graphics g)
	{
		PainterVisitor v = new PainterVisitor(g, CELL_SIZE);
		for (int x = 0; x < level.getFieldWidth(); x++)
			for (int y = 0; y < level.getFieldHeight(); y++)
			{
				BaseCell c = level.getFieldCell(x, y);
				c.acceptVisitor(v, x, y);
			}
	}
}
