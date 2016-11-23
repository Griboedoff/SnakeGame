//package Model.Cells;
//
//import Model.GameField;
//import Model.Point3d;
//import Model.Snake;
//import Swing.SwingGui.SwingPainterVisitor;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class PortalCell extends BaseCell
//{
//	private Random random;
//
//	public PortalCell()
//	{
//		random = new Random();
//	}
//
//	public PortalCell(int x, int y, int z)
//	{
//		super(x, y, z);
//		this.random = new Random();
//	}
//
//	@Override
//	public void affectSnake(Snake snake, GameField field)
//	{
//		ArrayList<Point3d> allPortals = new ArrayList<>();
//		for (int y = 0; y < field.getHeight(); y++)
//			for (int x = 0; x < field.getWidth(); x++)
//				if (field.getCell(x, y) instanceof PortalCell)
//					allPortals.add(new Point3d(x, y));
//		Point3d newCoordinates = allPortals.get(random.nextInt(allPortals.size()));
//		SnakeCell newHead = new SnakeCell(newCoordinates);
//		snake.updateHead(newHead);
//		field.setCell(newHead.getLocation().getX(), newHead.getLocation().getY(), newHead);
//	}
//
//	@Override
//	public void acceptVisitor(SwingPainterVisitor v, int x, int y)
//	{
//		v.visitPortalCell(this, x, y);
//	}
//}
