package Swing.SwingGui;

import Model.Cells.*;

import java.awt.*;

public class SwingPainterVisitor

{
	private final Graphics graphics;
	private final int cellSize;

	public SwingPainterVisitor(Graphics graphics)
	{
		this.graphics = graphics;
		this.cellSize = LevelWindow.CELL_SIZE;
	}

	public void visitEmptyCell(EmptyCell cell, int x, int y)
	{
		drawCell(new Color(95, 120, 18), x, y);
	}

	public void visitSnakeCell(SnakeCell cell, int x, int y)
	{
		drawCellWithBorder(new Color(47, 47, 17), x, y);
	}

	public void visitFoodCell(FoodCell cell, int x, int y)
	{
		drawCellWithBorder(new Color(255, 0, 0), x, y);
	}

	public void visitWallCell(WallCell cell, int x, int y)
	{
		drawCellWithBorder(new Color(150, 30, 90), x, y);
	}

	public void visitPoisonCell(PoisonCell cell, int x, int y)
	{
		drawCellWithBorder(new Color(150, 255, 0), x, y);
	}

	public void visitReverseCell(ReverseCell cell, int x, int y)
	{
		drawCellWithBorder(new Color(255, 128, 0), x, y);
	}

	private void drawCellWithBorder(Color c, int x2, int y2)
	{
		drawCell(c, x2, y2);
		drawBorder(x2, y2);
	}

	private void drawCell(Color c, int x2, int y2)
	{
		graphics.setColor(c);
		graphics.fillRect(x2 * cellSize, y2 * cellSize, cellSize, cellSize);
	}

	private void drawBorder(int x2, int y2)
	{
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x2 * cellSize, y2 * cellSize, cellSize, cellSize);
	}
}
