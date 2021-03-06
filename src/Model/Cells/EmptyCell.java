package Model.Cells;

import Swing.SwingGui.PainterVisitor;
import Model.GameField;
import Model.Snake;

public class EmptyCell extends BaseCell
{

	public EmptyCell()
	{
	}

	public EmptyCell(int x, int y)
	{
		super(x, y);
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