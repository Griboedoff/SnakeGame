package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
	private GameField Field;
	ArrayList<Snake> Snakes;

	public Level(int width, int height) {
		Field = new GameField(width, height);
	}

	public static Level CreateFromFile(File file) {
		throw new NotImplementedException();
	}
}
