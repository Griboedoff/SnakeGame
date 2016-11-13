package Model.Cells;

import Model.GameField;
import Model.Snake;
import SwingGui.PainterVisitor;

public class WallCell extends BaseCell
{
	public WallCell(int x, int y)
	{
		super(x, y);
	}

	public WallCell()
	{
	}

	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		snake.setAlive(false);
	}

	@Override
	public void acceptVisitor(PainterVisitor v, int x, int y)
	{
		v.visitWallCell(this, x, y);
	}
}
