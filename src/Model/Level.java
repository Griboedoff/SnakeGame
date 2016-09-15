package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.Serializable;

public class Level implements Serializable {
	private GameField field;
	private Snake snake;
    private int gameLownessMS;

	public Level(int width, int height, int xSnake, int ySnake, String direction)
	{
		field = new GameField(width, height);
        snake = new Snake(xSnake, ySnake, Direction.valueOf(direction), field);
        gameLownessMS = 200;
	}

	public void go() {
        StepResult stepResult = StepResult.NONE;
        Direction direction = null;
        while (true) {
            Thread.currentThread().sleep(gameLownessMS);
            direction = getNewDirection();
            stepResult = snake.makeStep(direction);
        }
    }

    public Direction getNewDirection() {
        throw new NotImplementedException();
    }

	public static Level CreateFromFile(File file) {
		throw new NotImplementedException();
	}
}
