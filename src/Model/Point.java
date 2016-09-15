package Model;

public class Point {

    private int x;
    private int y;

    public int getX() { return x; }
    public int getY() { return y; }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point add(Point other) {
        return new Point(x + other.x, y + other.y);
    }
}
