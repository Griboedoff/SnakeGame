package Model.Cells;

import Model.GameField;
import Model.Snake;
import Swing.SwingGui.PainterVisitor;

import java.awt.*;
import java.io.Serializable;

public abstract class BaseCell implements Serializable
{
	public Color color;

	BaseCell()
	{
	}

	BaseCell(int x, int y)
	{
	}

	public abstract void affectSnake(Snake snake, GameField field);

	public abstract void acceptVisitor(PainterVisitor v, int x, int y);
}
