package Model.Cells;

import Model.GameField;
import Model.Point3d;
import Model.Snake;
import Model.Space;
import Swing.SwingGui.SwingPainterVisitor;

public class PoisonCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Point3d fieldVector, Space space)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		space.setCell(snake.getHead().getLocation(), newHead);
		if (snake.getLength() < 4)
		{
			snake.setDead();
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
