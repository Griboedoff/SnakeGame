package Model.Cells;

import SwingGui.PainterVisitor;
import Model.Snake;
import Model.GameField;
import java.awt.*;

public class WallCell extends BaseCell
{	public Color color = Color.BLACK;

    public WallCell()
    {
    }

    @Override
    public void affectSnake(Snake snake, GameField field)
    {
        snake.setAlive(false);
    }

    @Override
    public void acceptVisitor(PainterVisitor v, int x, int y)
    {
        v.visitWallCell(this, x, y);
    }
}
