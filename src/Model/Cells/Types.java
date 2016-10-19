package Model.Cells;

public enum Types
{
    EMPTY, FOOD, SNAKE, WALL;

    public BaseCell getCellByName()
    {
        switch (this)
        {
            case FOOD:
                return new FoodCell();
            case SNAKE:
                return new SnakeCell();
            case WALL:
                return new WallCell();
            default:
                return new EmptyCell();
        }
    }
}
