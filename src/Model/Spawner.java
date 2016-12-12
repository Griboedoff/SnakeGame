package Model;

import Model.Cells.*;

import java.util.Random;


public class Spawner
{
	private static final Random random = new Random();
	private static final BaseCell[] allCases = new BaseCell[]{new FoodCell(), new WallCell(), new PoisonCell(), new ReverseCell()};

	private static void spawn(Space space, BaseCell object)
	{
	}

	public static void spawnOnSection(Space space, Vector pointIn, Vector fieldVector, BaseCell object)
	{
		GameField section = space.getSection(pointIn, fieldVector);
		int foodCellNumber = random.nextInt(section.countEmptyCells());
		for (int i = 0; i < section.getHeight(); i++)
			for (int j = 0; j < section.getWidth(); j++)
			{
				BaseCell cell = section.getCell(j, i);
				if (cell instanceof EmptyCell)
					foodCellNumber--;
				if (foodCellNumber == 0)
				{
					space.setCell(PointTranslator.point2dToVector(new Point2d(j, i), pointIn, fieldVector), object);
					return;
				}
			}
	}

	static void spawnRandom(Space space)
	{
		for (int i = allCases.length; i > 0; i--)
			if (random.nextInt(i) == 0)
			{
				spawn(space, allCases[i - 1]);
				break;
			}
	}
}
