package Model.Cells;

import Model.Snake;
import Model.Space;
import Model.Spawner;
import Model.Vector;
import Swing.SwingGui.SwingPainterVisitor;

//import Model.Point3d;
//import Model.Space;
//import Model.Spawner;

public class FoodCell extends BaseCell
{
	@Override
	public void affectSnake(Snake snake, Vector fieldVector, Space space)
	{
		SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
		snake.updateHead(newHead);
		space.setCell(newHead.getLocation(), newHead);
		Spawner.spawnOnSection(space, snake.getLocation(), fieldVector, new FoodCell());
	}

	@Override
	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
	{
		v.visitFoodCell(this, x, y);
	}

}
