package Model.Cells;

import SwingGui.PainterVisitor;
import Model.GameField;
import Model.Snake;

import java.awt.*;
import java.io.Serializable;

public abstract class BaseCell implements Serializable
{
	public Color color;

	BaseCell()
	{
	}

	public abstract void affectSnake(Snake snake, GameField field);

	public abstract void acceptVisitor(PainterVisitor v, int x, int y);
}
