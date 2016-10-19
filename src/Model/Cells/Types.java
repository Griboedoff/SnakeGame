package Model.Cells;

public enum Types
{
	EMPTY, FOOD, SNAKE, WALL;

	public BaseCell toCell(int x, int y)
	{
		switch (this)
		{
			case FOOD:
				return new FoodCell();
			case SNAKE:
				return new SnakeCell(x, y);
			case WALL:
				return new WallCell();
			default:
				return new EmptyCell();
		}
	}
}
