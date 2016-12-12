package Model;

import Model.Cells.BaseCell;
import Model.Cells.EmptyCell;
import Model.Cells.WallCell;

import java.util.HashMap;

public class Space
{
    private final HashMap<Vector, BaseCell> cells;
    private final Vector size;

    private Space(HashMap<Vector, BaseCell> cells, Vector size)
    {
        this.cells = cells;
        this.size = size;
    }

    public Vector getSize() { return size; }
    public int getDim() { return size.getDim(); }

    public void setCell(Vector point, BaseCell cell)
    {
        if (!isInSpace(point))
            throw new IllegalArgumentException();
        if (cell instanceof EmptyCell && cells.containsKey(point))
            cells.remove(point);
        cells.put(point, cell);
    }

    BaseCell getCell(Vector point) throws IllegalArgumentException
    {
        if (!isInSpace(point))
            throw new IllegalArgumentException("Point " + point.toString() + " is outside space boundaries");
        if (!cells.containsKey(point))
            return new EmptyCell();
        return cells.get(point);
    }

    public GameField getSection(Vector pointIn, Vector fieldVector)
    {
        int[] res_size = new int[2];
        for (int i = 0; i < 2; i++)
            res_size[i] = size.getCoord(fieldVector.getCoord(i));
        GameField res = new GameField(res_size[0], res_size[1]);
        for (int i = 0; i < res_size[0]; i++)
            for (int j = 0; j < res_size[1]; j++)
                res.setCell(i, j, getCell(PointTranslator.point2dToVector(new Point2d(i, j), pointIn, fieldVector)));
        return res;
    }

    public boolean isInSpace(Vector point)
    {
        for (int i = 0; i < getDim(); i++)
            if (0 > point.getCoord(i) || point.getCoord(i) > size.getCoord(i))
                return false;
        return true;
    }

    public void affectSnake(Snake snake, Vector fieldVector, Vector point)
    {
        if (isInSpace(point))
            getCell(point).affectSnake(snake, fieldVector, this);
        else
            snake.die();
    }

    public static Space getTestSpace()
    {
        HashMap<Vector, BaseCell> cells = new HashMap<Vector, BaseCell>();
        for (int i = 0; i < 20; i++){
            cells.put(new Vector(new int[] {i, 1, 1}), new WallCell());
            cells.put(new Vector(new int[] {1, i, 1}), new WallCell());
            cells.put(new Vector(new int[] {1, 1, i}), new WallCell());
            cells.put(new Vector(new int[] {i, 2, 2}), new WallCell());
        }
        return new Space(cells, new Vector(new int[] {20, 20, 20}));
    }
}
