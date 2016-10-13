package Model;

import Model.Cells.BaseCell;
import Model.Cells.EmptyCell;
import Model.Cells.FoodCell;

import java.util.Random;

public class FoodSpawner
{
	private static final Random random = new Random();

	public static void spawnApple(GameField field)
	{
		int free_amount = field.countEmptyCells();
		int foodCellNumber = random.nextInt(free_amount);
		for (int y = 0; y < field.getHeight(); y++)
			for (int x = 0; x < field.getWidth(); x++)
			{
				BaseCell cell = field.getCell(x, y);
				if (cell instanceof EmptyCell)
					foodCellNumber--;
				if (foodCellNumber == 0)
				{
					field.setCell(x, y, new FoodCell());
					return;
				}
			}
	}
}
