package Model.Cells;

import SwingGui.PainterVisitor;
import Model.GameField;
import Model.Snake;

public class EmptyCell extends BaseCell
{

	public EmptyCell()
	{
	}

	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		field.setCell(newHead.getCoordinates(), newHead);

		field.setCell(snake.getTail().getCoordinates(), new EmptyCell());
		snake.deleteTail();
	}

	@Override
	public void acceptVisitor(PainterVisitor v, int x, int y)
	{
		v.visitEmptyCell(this, x, y);
	}

}