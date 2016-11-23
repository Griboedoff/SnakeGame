package Model.Cells;

import Model.Direction;
import Model.GameField;
import Model.Point;
import Model.Snake;
import Swing.SwingGui.SwingPainterVisitor;

public class ReverseCell extends BaseCell
{
	public ReverseCell()
	{
	}

	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		field.setCell(newHead.getLocation().getX(), newHead.getLocation().getY(), newHead);

		Point newDirectionVector = snake.getTail().getLocation().sub(snake.getTail().getPrev().getLocation());
		Direction newDirection = Direction.fromPoint(newDirectionVector);
		snake.justSetDirection(newDirection);

		SnakeCell cell = snake.getHead();
		while (cell != null)
		{
			SnakeCell horse = cell.getNext();
			cell.setNext(cell.getPrev());
			cell.setPrev(horse);
			cell = cell.getPrev();
		}

		SnakeCell amazing = snake.getHead();
		snake.justSetHead(snake.getTail());
		snake.setTail(amazing);
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitReverseCell(this, x, y);
	}
}
