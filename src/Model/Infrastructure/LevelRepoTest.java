package Model.Infrastructure;

import Model.Cells.SnakeCell;
import Model.Direction;
import Model.Level;

import org.junit.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LevelRepoTest
{
	private LevelRepo levelRepo;
	private Path path;
	private Level testLevel;

	@Before
	public void setUp() throws Exception
	{
		testLevel = new Level("test", 5, 5, 3, 2, Direction.DOWN);
		String currentDir = Paths.get("").toAbsolutePath().toString();
		path = Paths.get(currentDir, "TestSerialization");
		Files.createDirectory(path);
		levelRepo = new LevelRepo(path.toString());
	}

	@After
	public void tearDown() throws Exception
	{
		Files.delete(Paths.get(path.toString(), testLevel.getName() + ".level"));
		Files.delete(path);
	}

	@Test
	public void getLevelFromFile() throws Exception
	{
		Assert.assertTrue(levelRepo.saveLevelToFile(testLevel));
		Level deserialized = levelRepo.getLevelFromFile(testLevel.getName());
		Assert.assertEquals(testLevel.getName(), deserialized.getName());
		Assert.assertTrue(deserialized.getFieldCell(3, 2) instanceof SnakeCell);
		Assert.assertTrue(deserialized.getFieldCell(3, 2) == deserialized.getSnakeHead());
		Assert.assertTrue(deserialized.getSnakeLen() == testLevel.getSnakeLen());
	}
}