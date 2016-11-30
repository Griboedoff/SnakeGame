package Model.Cells;

import Model.Point3d;
import Model.Snake;
import Model.Space;
import Model.Spawner;
import Swing.SwingGui.SwingPainterVisitor;

public class FoodCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Point3d fieldVector, Space space)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		space.setCell(newHead.getLocation(), newHead);
		Spawner.spawnOnSection(space, fieldVector, new FoodCell());
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitFoodCell(this, x, y);
	}

}
