package Model;

import Model.Cells.*;

import java.util.Random;

public class Spawner
{
	private static final Random random = new Random();

	public static void spawn(GameField field, BaseCell object)
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
					field.setCell(x, y, object);
					return;
				}
			}
	}

	static void spawnRandom(GameField field)
	{
		BaseCell[] allCases = new BaseCell[]{new FoodCell(), new WallCell(), new PoisonCell(), new ReverseCell()};
		for (int i = allCases.length; i > 0; i--)
			if (random.nextInt(i) == 0)
			{
				spawn(field, allCases[i - 1]);
				break;
			}
	}
}
