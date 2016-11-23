package Model.Cells;

import Model.GameField;
import Model.Snake;
import Model.Spawner;
import Swing.SwingGui.SwingPainterVisitor;

public class FoodCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		field.setCell(newHead.getLocation().getX(), newHead.getLocation().getY(), newHead);
		Spawner.spawn(field, new FoodCell());
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitFoodCell(this, x, y);
	}

}
