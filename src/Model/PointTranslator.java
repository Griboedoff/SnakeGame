package Model;

class PointTranslator
{
	private static Point3d pointTo3D(Point2d point, Point3d fieldVector)
	{
		if (fieldVector.getX() != -1)
			return new Point3d(fieldVector.getX(), point.getX(), point.getY());
		if (fieldVector.getY() != -1)
			return new Point3d(point.getY(), fieldVector.getY(), point.getX());
		if (fieldVector.getZ() != -1)
			return new Point3d(point.getX(), point.getY(), fieldVector.getZ());
		throw new RuntimeException("Incorrect value of fieldVector");
	}

	static Point3d pointTo3D(int x, int y, Point3d fieldVector)
	{
		return pointTo3D(new Point2d(x, y), fieldVector);
	}

	private static Point2d pointTo2D(Point3d point, Point3d fieldVector)
	{
		if (fieldVector.getX() != -1)
			return new Point2d(point.getY(), point.getZ());
		if (fieldVector.getY() != -1)
			return new Point2d(point.getZ(), point.getX());
		if (fieldVector.getZ() != -1)
			return new Point2d(point.getX(), point.getY());
		throw new RuntimeException("Incorrect value of fieldVector");
	}

	public static Point2d pointTo2D(int x, int y, int z, Point3d fieldVector)
	{
		return pointTo2D(new Point3d(x, y, z), fieldVector);
	}

	public static Point3d directionToPoint3D(Direction direction, Point3d fieldVector)
	{
		Point2d vector = direction.getVector();
		if (fieldVector.getX() != -1)
			return new Point3d(0, vector.getX(), vector.getY());
		if (fieldVector.getY() != -1)
			return new Point3d(vector.getY(), 0, vector.getX());
		if (fieldVector.getZ() != -1)
			return new Point3d(vector.getX(), vector.getY(), 0);
		throw new IllegalArgumentException("Incorrect fieldVector: " + fieldVector.toString());
	}
}
