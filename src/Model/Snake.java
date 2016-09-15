package Model;

import Model.Cells.*;

class Snake {

	private SnakeCell head;
	private SnakeCell tail;
	private Direction direction;
	private GameField field;

	public Snake(int xHead, int yHead, Direction direction, GameField field) {
		head = (SnakeCell) CellFactory.createCell(CellTypes.SNAKE, xHead, yHead);

		tail = head;
		this.direction = direction;
		this.field = field;
	}

	public Model.Direction getDirection() {
		return direction;
	}

	public SnakeCell getHead() {
		return head;
	}

	public SnakeCell getTail() {
		return tail;
	}

	public StepResult makeStep(Direction newDirection) {
		if (newDirection != null)
			this.direction = newDirection;
		Point nextCell = head.getCoordinates().add(direction.getVector());
		if (!field.isInField(nextCell) || field.getCell(nextCell) instanceof SnakeCell)
			return StepResult.DIE;
		if (field.getCell(nextCell) instanceof EmptyCell)
			return moveTo(nextCell);
		if (field.getCell(nextCell) instanceof FoodCell)
			return moveAndEat(nextCell);

		return null;
	}

	private StepResult moveTo(Point point) {
		updateHead(point);
		deleteTail();
		return StepResult.NONE;
	}

	private StepResult moveAndEat(Point point) {
		updateHead(point);
		return StepResult.GROW;
	}

	private void updateHead(Point point) {
		head = ((SnakeCell) CellFactory.createCell(CellTypes.SNAKE, point)).connectTo(head);

		field.setCell(point, head);
	}

	private void deleteTail() {
		field.setCell(tail.getCoordinates(), CellFactory.createCell(CellTypes.EMPTY, tail.getCoordinates()));
		tail = tail.getPrev();
		tail.setNext(null);
	}
}