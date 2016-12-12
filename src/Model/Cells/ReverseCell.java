package Model.Cells;

import Model.Snake;
import Model.Space;
import Model.Vector;
import Swing.SwingGui.SwingPainterVisitor;

//import Model.Point3d;
//import Model.Space;

public class ReverseCell extends BaseCell
{
	public ReverseCell()
	{
	}

	@Override
	public void affectSnake(Snake snake, Vector fieldVector, Space space)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		space.setCell(newHead.getLocation(), newHead);

		snake.justSetDirection(snake.getTail().getLocation().sub(snake.getTail().getPrev().getLocation()));

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
