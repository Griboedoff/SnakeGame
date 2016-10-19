package SwingGui;

import Model.Cells.EmptyCell;
import Model.Cells.FoodCell;
import Model.Cells.SnakeCell;
import Model.Cells.WallCell;

import java.awt.*;

public class PainterVisitor

{
	private Graphics graphics;
	private int cellSize;

	public PainterVisitor(Graphics graphics, int cellSize)
	{
		this.graphics = graphics;
		this.cellSize = cellSize;
	}

	public void visitEmptyCell(EmptyCell cell, int x, int y)
	{
		graphics.setColor(new Color(95, 120, 18));
		graphics.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
	}

	public void visitSnakeCell(SnakeCell cell, int x, int y)
	{
		graphics.setColor(new Color(47, 47, 17));
		graphics.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(cellSize * x, cellSize * y, cellSize, cellSize);
	}

	public void visitFoodCell(FoodCell cell, int x, int y)
	{
		graphics.setColor(new Color(141, 5, 45));
		graphics.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(cellSize * x, cellSize * y, cellSize, cellSize);
	}

	public void visitWallCell(WallCell cell, int x, int y)
    {
        graphics.setColor(new Color(150, 30, 90));
        graphics.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(cellSize * x, cellSize * y, cellSize, cellSize);
    }
}
