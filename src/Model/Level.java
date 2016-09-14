package Model;

import java.util.ArrayList;

public class Level {
	private GameField Field;
	ArrayList<Snake> Snakes;

	public Level(int width, int height) {
		Field = new GameField(width, height);
	}
}
