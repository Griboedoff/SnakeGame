package SwingGui;

import Model.Cells.*;

import java.awt.Color;
import java.awt.Graphics;

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
		graphics.setColor(new Color(255, 0, 0));
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

    public void visitPortalCell(PortalCell cell, int x, int y)
    {
        graphics.setColor(new Color(0, 0, 255));
        graphics.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(cellSize * x, cellSize * y, cellSize, cellSize);
    }

    public void visitPoisonCell(PoisonCell cell, int x, int y)
    {
        graphics.setColor(new Color(150, 255, 0));
        graphics.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(cellSize * x, cellSize * y, cellSize, cellSize);
    }

    public void visitReverseCell(ReverseCell cell, int x, int y)
    {
        graphics.setColor(new Color(255, 128, 0));
        graphics.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(cellSize * x, cellSize * y, cellSize, cellSize);
    }
}
