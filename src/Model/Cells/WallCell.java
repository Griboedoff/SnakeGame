package Model.Cells;

import Model.Point3d;
import Model.Snake;
import Model.Space;
import Swing.SwingGui.SwingPainterVisitor;

public class WallCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Point3d fieldVector, Space space)
	{
		snake.setDead();
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitWallCell(this, x, y);
	}
}
