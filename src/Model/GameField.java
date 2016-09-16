package Model;

import Model.Cells.*;

class GameField {
	private BaseCell[][] Field;

	GameField(int width, int height) {
		Field = new BaseCell[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				Field[i][j] = new EmptyCell(i, j);
	}

	private boolean isInField(int x, int y) {
		return 0 < x && x < Field.length && 0 < y && y < Field[0].length;
	}

	boolean isInField(Point point) {
		return isInField(point.getX(), point.getY());
	}

	private BaseCell getCell(int x, int y) throws IndexOutOfBoundsException {
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		return Field[x][y];
	}

	BaseCell getCell(Point point) {
		return getCell(point.getX(), point.getY());
	}

	private void setCell(int x, int y, BaseCell state) throws IndexOutOfBoundsException {
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		Field[x][y] = state;
	}

	void setCell(Point p, BaseCell state) {
		setCell(p.getX(), p.getY(), state);
	}
}
