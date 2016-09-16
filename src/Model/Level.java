package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.Serializable;

public class Level implements Serializable {
	private int gameLownessMS;
	private GameField field;
	private Snake snake;

	public Level(int width, int height, int xSnake, int ySnake, String direction) {
		field = new GameField(width, height);
		snake = new Snake(xSnake, ySnake, Direction.valueOf(direction), field);
		gameLownessMS = 200;
	}

	public StepResult makeStep(Direction dir) {
		return snake.makeStep(dir);
	}

	public static Level CreateFromFile(File file) {
		throw new NotImplementedException();
	}

	public int getGameLownessMS() {
		return gameLownessMS;
	}

	public void setGameLownessMS(int gameLownessMS) {
		this.gameLownessMS = gameLownessMS;
	}
}
