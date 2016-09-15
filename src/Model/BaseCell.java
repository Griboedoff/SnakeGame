package Model;

public abstract class BaseCell {

    private Point coordinates;
    public Point getCoordinates() { return coordinates; }

    public BaseCell(int x, int y){
        coordinates = new Point(x, y);
    }
    public BaseCell(Point point) { coordinates = point; }
}
