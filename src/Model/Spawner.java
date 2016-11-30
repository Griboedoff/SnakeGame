package Model;

import Model.Cells.*;

import java.util.Random;

import static Model.PointTranslator.pointTo3D;

public class Spawner
{
	private static final Random random = new Random();

	private static void spawn(Space space, BaseCell object)
	{
		int free_amount = space.countEmptyCells();
		int foodCellNumber = random.nextInt(free_amount);
		for (int z = 0; z < space.getSize().getZ(); z++)
		{
			GameField section = space.getSection(new Point3d(-1, -1, z));
			int emptiesInSectionAmount = section.countEmptyCells();
			if (foodCellNumber >= emptiesInSectionAmount)
			{
				foodCellNumber -= emptiesInSectionAmount;
				continue;
			}
			spawnOnSection(space, new Point3d(-1, -1, z), object);
			break;
		}
	}

	public static void spawnOnSection(Space space, Point3d fieldVector, BaseCell object)
	{
		GameField section = space.getSection(fieldVector);
		int foodCellNumber = random.nextInt(section.countEmptyCells());
		for (int i = 0; i < section.getHeight(); i++)
			for (int j = 0; j < section.getWidth(); j++)
			{
				BaseCell cell = section.getCell(j, i);
				if (cell instanceof EmptyCell)
					foodCellNumber--;
				if (foodCellNumber == 0)
				{
					space.setCell(pointTo3D(j, i, fieldVector), object);
					return;
				}
			}
	}

	static void spawnRandom(Space space)
	{
		BaseCell[] allCases = new BaseCell[]{new FoodCell(), new WallCell(), new PoisonCell(), new ReverseCell()};
		for (int i = allCases.length; i > 0; i--)
			if (random.nextInt(i) == 0)
			{
				spawn(space, allCases[i - 1]);
				break;
			}
	}
}
