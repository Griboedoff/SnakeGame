package Model.Cells;

import Model.GameField;
import Model.Snake;
import Swing.SwingGui.SwingPainterVisitor;

public class EmptyCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		field.setCell(newHead.getLocation(), newHead);
		field.setCell(snake.getTail().getLocation(), new EmptyCell());
		snake.deleteTail();
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitEmptyCell(this, x, y);
	}

}