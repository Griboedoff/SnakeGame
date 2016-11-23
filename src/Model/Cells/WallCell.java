package Model.Cells;

import Model.GameField;
import Model.Snake;
import Swing.SwingGui.SwingPainterVisitor;

public class WallCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		snake.setDead();
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitWallCell(this, x, y);
	}
}
