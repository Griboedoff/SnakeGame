package Model.Cells;

import Model.Snake;
import Model.Space;
import Model.Vector;
import Swing.SwingGui.SwingPainterVisitor;

//import Model.Point3d;
//import Model.Space;

public class PoisonCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Vector fieldVector, Space space)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		space.setCell(snake.getHead().getLocation(), newHead);
		if (snake.getLength() < 4)
		{
			snake.die();
			return;
		}
		for (int i = 0; i < 3; i++)
		{
			space.setCell(snake.getTail().getLocation(), new EmptyCell());
			snake.deleteTail();
		}
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitPoisonCell(this, x, y);
	}
}
