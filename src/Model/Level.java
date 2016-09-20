package Model;

import java.io.File;
import java.util.Random;
import Model.Cells.*;
import java.io.Serializable;

public class Level implements Serializable
{
	private String name;
	private GameField field;
	private Snake snake;
    private Random random;

	public Level(String name, int width, int height, int xSnake, int ySnake, String direction)
	{
		this.name = name;
		field = new GameField(width, height);
		snake = new Snake(xSnake, ySnake, Direction.valueOf(direction), field);
        generateFood();
        random = new Random(System.currentTimeMillis());
	}

	public StepResult makeStep(Direction dir) {
		StepResult stepRes = snake.makeStep(dir);
		if (stepRes == StepResult.GROW)
			generateFood();
        return stepRes;
	}

	private void generateFood() {
        int free_amount = field.getHeight() * field.getWidth() - snake.getLength();
        int foodCellNumber = random.nextInt(free_amount);
        for (int y = 0; y < field.getHeight(); y++)
            for (int x = 0; x < field.getWidth(); x++){
                if (field.getCell(x, y) instanceof EmptyCell)
                    foodCellNumber--;
                    if (foodCellNumber < 0)
                        field.setCell(x, y, CellFactory.createCell(CellTypes.FOOD, x, y));
            }
    }

	public String getName()
	{
		return name;
	}
}
