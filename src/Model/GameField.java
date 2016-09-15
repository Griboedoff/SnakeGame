package Model;

public class GameField {
	private CellState[][] Field;

	public GameField(int width, int height) {
		Field = new CellState[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				Field[i][j] = CellState.EMPTY;
	}

	private boolean coordinatesInField(int x, int y) {
		return 0 < x && x < Field.length && 0 < y && y < Field[0].length;
	}

	public CellState getCell(int x, int y) throws IndexOutOfBoundsException {
		if (!coordinatesInField(x, y))
			throw new IndexOutOfBoundsException();
		return Field[x][y];
	}

	public void setCell(int x, int y, CellState state) throws IndexOutOfBoundsException {
		if (!coordinatesInField(x, y))
			throw new IndexOutOfBoundsException();
		Field[x][y] = state;
	}
}
