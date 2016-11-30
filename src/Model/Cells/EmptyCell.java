package Model.Cells;

import Model.Point3d;
import Model.Snake;
import Model.Space;
import Swing.SwingGui.SwingPainterVisitor;

public class EmptyCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Point3d fieldVector, Space space)
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