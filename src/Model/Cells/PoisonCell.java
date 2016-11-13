package Model.Cells;

import Model.GameField;
import Model.Snake;
import SwingGui.PainterVisitor;

public class PoisonCell extends BaseCell
{
    public PoisonCell() { }

    @Override
    public void affectSnake(Snake snake, GameField field)
    {
        SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
        snake.updateHead(newHead);
        field.setCell(snake.getHead().getCoordinates(), newHead);
        if (snake.getLength() < 4)
        {
            snake.setAlive(false);
            return;
        }
        for (int i = 0; i < 3; i++)
        {
            field.setCell(snake.getTail().getCoordinates(), new EmptyCell());
            snake.deleteTail();
        }
    }

    @Override
    public void acceptVisitor(PainterVisitor v, int x, int y) { v.visitPoisonCell(this, x, y); }
}
