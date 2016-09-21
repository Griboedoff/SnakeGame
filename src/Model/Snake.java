package Model;

import Model.Cells.*;

import java.io.Serializable;

@SuppressWarnings("SameReturnValue")
class Snake implements Serializable
{
	private SnakeCell head;
	private SnakeCell tail;
	private Direction direction;
	private GameField field;
	private int length;

	Snake(int xHead, int yHead, Direction direction, GameField field)
	{
		head = CellFactory.createSnakeCell(xHead, yHead, null);
		tail = head;
		this.direction = direction;
		this.field = field;
		field.setCell(head.getCoordinates(), head);
	}

	int getLength()
	{
		return length;
	}

	SnakeCell getHead()
	{
		return head;
	}

	SnakeStepResult makeStep(Direction newDirection)
	{
		if (newDirection != null)
			this.direction = newDirection;
		Point nextCell = head.getCoordinates().add(direction.getVector());
		if (!field.isInField(nextCell) || field.getCell(nextCell) instanceof SnakeCell)
			return SnakeStepResult.DIE;
		if (field.getCell(nextCell) instanceof EmptyCell)
			return moveTo(nextCell);
		if (field.getCell(nextCell) instanceof FoodCell)
			return moveAndEat(nextCell);
		return null;
	}

	private SnakeStepResult moveTo(Point point)
	{
		updateHead(point);
		deleteTail();
		return SnakeStepResult.NONE;
	}

	private SnakeStepResult moveAndEat(Point point)
	{
		updateHead(point);
		return SnakeStepResult.GROW;
	}

	private void updateHead(Point point)
	{
		head = ((SnakeCell) CellFactory.createCell(CellTypes.SNAKE, point)).connectTo(head);
		field.setCell(point, head);
		length++;
	}

	private void deleteTail()
	{
		field.setCell(tail.getCoordinates(), CellFactory.createCell(CellTypes.EMPTY, tail.getCoordinates()));
		tail = tail.getPrev();
		tail.setNext(null);
		length--;
	}
}