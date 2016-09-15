package Model;

public class CellState{

    private Point coordinates;
    public Point getCoordinates() { return coordinates; }

    public CellState(int x, int y){
        coordinates = new Point(x, y);
    }
}
