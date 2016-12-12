package Model;

class PointTranslator
{
    public static Vector directionToVector(Direction direction, Vector fieldVector, int dim)
    {
        int[] res_value = new int[dim];
        Point2d dirVector2d = direction.getVector();
        res_value[fieldVector.getCoord(0)] = dirVector2d.getX();
        res_value[fieldVector.getCoord(1)] = dirVector2d.getY();
        return new Vector(res_value);
    }

    public static Vector point2dToVector(Point2d point, Vector vectorIn, Vector fieldVector)
    {
        int[] res_value = vectorIn.getValue();
        res_value[fieldVector.getCoord(0)] = point.getX();
        res_value[fieldVector.getCoord(1)] = point.getY();
        return new Vector(res_value);
    }
}
