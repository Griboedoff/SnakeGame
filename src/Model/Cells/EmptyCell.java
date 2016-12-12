package Model.Cells;

import Model.Snake;
import Model.Space;
import Model.Vector;
import Swing.SwingGui.SwingPainterVisitor;

//import Model.Point3d;
//import Model.Space;

public class EmptyCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Vector fieldVector, Space space)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		space.setCell(newHead.getLocation(), newHead);
		space.setCell(snake.getTail().getLocation(), new EmptyCell());
		snake.deleteTail();
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitEmptyCell(this, x, y);
	}

}