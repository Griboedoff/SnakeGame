package Model.Cells;

import Model.GameField;
import Model.Point;
import Model.Snake;
import Swing.SwingGui.PainterVisitor;

import java.util.ArrayList;
import java.util.Random;

public class PortalCell extends BaseCell
{
	private Random random;

	public PortalCell()
	{
		random = new Random();
	}

	public PortalCell(int x, int y)
	{
		super(x, y);
		this.random = new Random();
	}

	@Override
	public void affectSnake(Snake snake, GameField field)
	{
		ArrayList<Point> allPortals = new ArrayList<>();
		for (int y = 0; y < field.getHeight(); y++)
			for (int x = 0; x < field.getWidth(); x++)
				if (field.getCell(x, y) instanceof PortalCell)
					allPortals.add(new Point(x, y));
		Point newCoordinates = allPortals.get(random.nextInt(allPortals.size()));
		SnakeCell newHead = new SnakeCell(newCoordinates);
		snake.updateHead(newHead);
		field.setCell(newHead.getCoordinates().getX(), newHead.getCoordinates().getY(), newHead);
	}

	@Override
	public void acceptVisitor(PainterVisitor v, int x, int y)
	{
		v.visitPortalCell(this, x, y);
	}
}
