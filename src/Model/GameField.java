package Model;

public class GameField {
	private BaseCell[][] Field;

	public GameField(int width, int height) {
		Field = new BaseCell[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				Field[i][j] = new EmptyCell(i, j);
	}

	public boolean coordinatesInField(int x, int y) {
		return 0 < x && x < Field.length && 0 < y && y < Field[0].length;
	}

	public boolean coordinatesInField(Point point) {
		return coordinatesInField(point.getX(), point.getY());
	}

	public BaseCell getCell(int x, int y) throws IndexOutOfBoundsException {
		if (!coordinatesInField(x, y))
			throw new IndexOutOfBoundsException();
		return Field[x][y];
	}

	public BaseCell getCell(Point point) {
		return getCell(point.getX(), point.getY());
	}

	public void setCell(int x, int y, BaseCell state) throws IndexOutOfBoundsException {
		if (!coordinatesInField(x, y))
			throw new IndexOutOfBoundsException();
		Field[x][y] = state;
	}
}
