package SwingGui.LevelEditor;

import Model.Cells.BaseCell;
import SwingGui.PainterVisitor;

import javax.swing.*;
import java.awt.*;

public class LevelEditorWindow extends JFrame
{
	public static final int CELL_SIZE = 12;

	public LevelEditorWindow(String title) throws HeadlessException
	{
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void updateField(Graphics g, LevelEditor levelEditor)
	{
		PainterVisitor v = new PainterVisitor(g, CELL_SIZE);
		for (int x = 0; x < levelEditor.getField().getWidth(); x++)
			for (int y = 0; y < levelEditor.getField().getHeight(); y++)
			{
				BaseCell c = levelEditor.getCell(x, y);
				c.acceptVisitor(v, x, y);
				g.setColor(Color.BLACK);
				g.drawRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			}
	}
}
