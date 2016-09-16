package Model;

import Model.Cells.BaseCell;
import Model.Cells.EmptyCell;

class GameField {
	private BaseCell[][] Field;

	GameField(int width, int height) {
		Field = new BaseCell[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				Field[i][j] = new EmptyCell(j, i);
	}

	public int getWidth() { return Field.length; }
	public int getHeight() { return Field[0].length; }

	private boolean isInField(int x, int y) {
		return 0 < x && x < getWidth() && 0 < y && y < getHeight();
	}

	boolean isInField(Point point) {
		return isInField(point.getX(), point.getY());
	}

	private BaseCell getCell(int x, int y) throws IndexOutOfBoundsException {
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		return Field[y][x];
	}

	BaseCell getCell(Point point) {
		return getCell(point.getX(), point.getY());
	}

	private void setCell(int x, int y, BaseCell state) throws IndexOutOfBoundsException {
		if (!isInField(x, y))
			throw new IndexOutOfBoundsException();
		Field[y][x] = state;
	}

	void setCell(Point p, BaseCell state) {
		setCell(p.getX(), p.getY(), state);
	}
}
