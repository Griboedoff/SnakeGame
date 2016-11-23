package Model.Cells;

import Model.GameField;
import Model.Snake;
import Swing.SwingGui.SwingPainterVisitor;

public class PoisonCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		field.setCell(snake.getHead().getLocation(), newHead);
		if (snake.getLength() < 4)
		{
			snake.setDead();
			return;
		}
		for (int i = 0; i < 3; i++)
		{
			field.setCell(snake.getTail().getLocation(), new EmptyCell());
			snake.deleteTail();
		}
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitPoisonCell(this, x, y);
	}
}
