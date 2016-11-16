package Model.Cells;

import Swing.SwingGui.PainterVisitor;
import Model.Spawner;
import Model.GameField;
import Model.Snake;

public class FoodCell extends BaseCell
{
	public FoodCell(int x, int y)
	{
		super(x, y);
	}

	public FoodCell()
	{
	}

	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		field.setCell(newHead.getCoordinates().getX(), newHead.getCoordinates().getY(), newHead);
		Spawner.spawn(field, new FoodCell());
	}

	@Override
	public void acceptVisitor(PainterVisitor v, int x, int y)
	{
		v.visitFoodCell(this, x, y);
	}

}
