package Model.Cells;

//import Model.Point3d;
import Model.Space;
import Model.Snake;
//import Model.Space;
import Model.Vector;
import Swing.SwingGui.SwingPainterVisitor;

public class WallCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Vector fieldVector, Space space)
	{
		snake.die();
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitWallCell(this, x, y);
	}
}
