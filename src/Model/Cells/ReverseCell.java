package Model.Cells;

import Model.Direction;
import Model.GameField;
import Model.Point;
import Model.Snake;
import SwingGui.PainterVisitor;

public class ReverseCell extends BaseCell
{
    @Override
    public void affectSnake(Snake snake, GameField field)
    {
        SnakeCell newHead = new SnakeCell(snake.getNextMoveCell());
        snake.updateHead(newHead);
        field.setCell(newHead.getCoordinates().getX(), newHead.getCoordinates().getY(), newHead);

        Point newDirectionVector = snake.getTail().getCoordinates().sub(snake.getTail().getPrev().getCoordinates());
        Direction newDirection = Direction.byPoint(newDirectionVector);
        snake.justSetDirection(newDirection);

        SnakeCell cell = snake.getHead();
        while (cell != null)
        {
            SnakeCell horse = cell.getNext();
            cell.setNext(cell.getPrev());
            cell.setPrev(horse);
            cell = cell.getPrev();
        }

        SnakeCell amazing = snake.getHead();
        snake.justSetHead(snake.getTail());
        snake.setTail(amazing);
    }

    @Override
    public void acceptVisitor(PainterVisitor v, int x, int y) { v.visitReverseCell(this, x, y); }
}
