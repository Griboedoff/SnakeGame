package Model.Cells;

import Model.Point3d;
import Model.Snake;
import Model.Space;
import Swing.SwingGui.SwingPainterVisitor;

public class ReverseCell extends BaseCell
{
	public ReverseCell()
	{
	}

	@Override
	public void affectSnake(Snake snake, Point3d fieldVector, Space space)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		space.setCell(newHead.getLocation(), newHead);

		Point3d newDirectionVector = snake.getTail().getLocation().sub(snake.getTail().getPrev().getLocation());
		snake.justSetDirection(newDirectionVector);

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
